/* eslint no-console: 0 */  // --> OFF
/* eslint no-unused-vars: 0 */  // --> OFF
const functions = require('firebase-functions')
const Promise = require("bluebird")
const cors = require('cors')({origin: true})
const crypto = require('crypto')
const request = require("request")
// const rp = require("request-promise")
const axios = require('axios')

const test = false

const oauth2 = require("simple-oauth2").create(credentials)

let token = null

const params = {
  redirect_uri: !test ? 
    'https://us-central1-wow-guild-manager.cloudfunctions.net/auth'
    :
    'http://localhost:5000/wow-guild-manager/us-central1/auth',
  host: !test ? 
    'https://wow-guild-manager.web.app'
    : 
    'http://localhost:8080',
  get blob() {
    return crypto.randomBytes(20).toString('hex')
  }
}

const getProfileNameSpace = (region) => {
  return '?namespace=profile-' + this.region
}

const getToken = () => {
    if (token === null || token.expired()) {
      return oauth2.clientCredentials
        .getToken()
        .then(oauth2.accessToken.create)
        .then(t => {
          token = t
          return t.token.access_token
        })
    } else {
      return Promise.resolve(token.token.access_token)
    }
}

exports.oauthToken = functions.https.onRequest((_, res) => {
  return cors(_, res, () => {
    getToken().then(token => {
        res.send(`${token}`)
    })
  })
})

exports.auth = functions.https.onRequest(async (req, res) => {
  console.log(req.query.code)
  axios.post('https://us.battle.net/oauth/token' 
    + '?grant_type=authorization_code'
    + `&code=${req.query.code}`
    + `&client_id=${credentials.client.id}`
    + `&redirect_uri=${params.redirect_uri}`
    + `&client_secret=${credentials.client.secret}`
  ).then(response => {
    console.log('got data')
    const Location = 
      `${params.host}/`
      + `?access_token=${response.data.access_token}`
      + `&token_type=${response.data.token_type}`
      + `&expires_in=${response.data.expires_in}` 
      console.log('got dat data boi')
      res.writeHead(302, {Location: Location})
      res.end()
  }).catch((error) => {
    return cors(req, res, () => {
      console.log('error')
      res.writeHead(400, {Location: params.host})
      res.end()
    })
  })
})


exports.client = functions.https.onRequest((_, res) => {
  return cors(_, res, () => {
    res.send({
      clientId: credentials.client.id,
      blob: params.blob,
      test
    })
  })
})

const blizzardApiGetActions = {
  'roster': roster
}

async function roster(req, res){
  let query = req.query
  let uri = 
    query.host +
    query.guildendpoint +
    `/${query.realmslug}` +
    `/${query.guildslug}` +
    `/roster` + 
    `?namespace=${query.namespace}` +
    `&locale=${query.locale}` +
    `&access_token=${query.access_token}`

  let members = (await axios.get(uri)).data.members
  uri = 
    query.host +
    query.characterendpoint +
    `/${query.realmslug}`
  // res.send(characters.data.members)
  let requests = []

  members.forEach(member => {
    let memberUri = 
      uri +
      `/${member.character.name}` + 
      `?locale=${query.locale}` +
      `&access_token=${query.access_token}`
    requests.push(axios.get(memberUri))
  })

  let count = members.length
  let completed = 0
  const characters = []
  requests.forEach(request => {
    request
      .then(response => {
        completed++;
        characters.push(response.data)
        response
      })
      .catch(error => {
        console.log(error.response.data)
        count--;
      }).finally(() => {
        if(count === completed) {
          res.send({members, characters})
        }
      })
  })
  // }).catch(error => {
  //   console.log(error.response)
  // })
}

exports.blizzardApiGet = functions.https.onRequest((req, res) => {
  const action = req.query.action
  return cors(req,res, () => { 
    blizzardApiGetActions[action](req, res)
  })
})


