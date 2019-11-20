/* eslint no-console: 0 */ // --> OFF
/* eslint no-unused-vars: 0 */ // --> OFF
import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import * as firebase from 'firebase'
import {
  isString,
  isUndefined
} from 'util'

let test = true
Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    host: 'wow-guild-manager.web.app',
    imageSrc: {
      bnet: 'https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/battle-net-icon-9.jpg?alt=media&token=e89197eb-15a0-4e75-baf8-37794e91d4d5'
    },
    pageName: 'Guild Manager',
    clientId: 'clientId',
    functions: {
      host: !test ? 'https://us-central1-wow-guild-manager.cloudfunctions.net' : 'http://localhost:5000/wow-guild-manager/us-central1',
      endpoints: {
        client: '/client',
        oauthToken: '/oauthToken',
        auth: '/auth',
        blizzardApiGet: '/blizzardApiGet'
      },
    },
    blizzardApi: {
      host: 'https://us.api.blizzard.com',
      endpoints: {
        characters: '/wow/user/characters',
        guild: '/data/wow/guild',
        character: '/wow/character'
      },
      namespace: {
        profile: {
          us: 'profile-us'
        }
      },
      locale: {
        us: 'en_US'
      }
    },
    blob: undefined,
    playerAuth: {
      host: 'us.battle.net',
      test: undefined,
      endpoints: {
        authorize: '/oauth/authorize',
        accessToken: '/oauth/token',
        userInfo: '/oauth/userinfo'
      },
      redirectUri: !test ? 'https://us-central1-wow-guild-manager.cloudfunctions.net/auth' : 'http://localhost:5000/wow-guild-manager/us-central1/auth', //change this when done testing
      responseType: 'code',
      grantType: 'authorization_code',
      code: undefined,

      scopes: {
        wow_profile: 'wow.profile',
        profile: 'profile'
      },
      _token: undefined,
      userInfo: undefined
    },
    selectedGuild: undefined,
    characters: undefined,
    guilds: undefined,
    user: undefined,
    get playerAuthRequestUri() {
      let scopes = this.playerAuth.scopes
      return this.playerAuth.host +
        this.playerAuth.endpoints.authorize +
        `?client_id=${this.clientId}` +
        `&scope=${scopes.wow_profile} ${scopes.profile}` +
        `&state=${this.blob}` +
        `&redirect_uri=${this.playerAuth.redirectUri}` +
        `&response_type=${this.playerAuth.responseType}`
    },
  },
  mutations: {
    setupClient(state) {
      axios
        .get(state.functions.host + state.functions.endpoints.client)
        .then(res => {
          state.clientId = res.data.clientId
          state.blob = res.data.blob
          test = res.data.test
          console.log(state.clientId, state.blob);
        })
    },
    saveToken(state, token) {
      localStorage.setItem('access_token', token.access_token)
      localStorage.setItem('access_token__expires_in', token.expires_in)
      localStorage.setItem('access_token__token_type', token.token_type)
      state.playerAuth._token = token
    },
    firebaseSignIn(state) {
      let auth = state.playerAuth
      let endpoint =
        `https://${auth.host}` +
        auth.endpoints.userInfo +
        `?access_token=${this.getters.token}`

      axios
        .get(endpoint)
        .then(res => {
          console.log(res.data)
          state.playerAuth.userInfo = res.data
          let email = `${res.data.battletag}@${state.host}`
          let password = res.data.sub
          return firebaseSignIn(email, password)
        }).then(user => {
          let newUser = {
            id: user.user.uid,
            battletag: state.playerAuth.userInfo.battletag,
          }
          state.firebase.user = newUser
          endpoint =
            state.blizzardApi.host +
            state.blizzardApi.endpoints.characters +
            `?access_token=${this.getters.token}`
          return axios.get(endpoint)
        }).then(
          res => {
            console.log(res)
            state.characters = res.data.characters
            let uniqueGuilds = getUniqueGuilds(state.characters)
            state.guilds = uniqueGuilds
          }
        )
    },
    updateUserInfo(state, userInfo) {
      state.playerAuth.userInfo = userInfo
    },
    updateCharacters(state, characters) {
      state.characters = characters
    },
    updateGuilds(state, guilds) {
      state.guilds = guilds
    },
    updatePageName(state, pageName) {
      console.log(pageName);
      state.pageName = pageName
    },
    setUser(state, user) {
      state.user = Object.assign({}, state.user, user)
    },
    updateSelectedGuild(state, payload) {
      state.selectedGuild = payload.guild
    },
    updateMembersAndCharactersForGuild(state, payload) {
      state.selectedGuild = Object.assign({},
        state.selectedGuild, {
          guildMembers: payload.guildMembers
        }
      )
      let updateIndex = state.guilds.findIndex(guild => guild.name === state.selectedGuild.name)

      state.guilds.splice(updateIndex, 1, state.selectedGuild)
    }
  },
  actions: {
    appSignIn({
      commit
    }) {
      let auth = this.state.playerAuth
      let endpoint =
        `https://${auth.host}` +
        auth.endpoints.userInfo +
        `?access_token=${this.getters.token}`

      axios
        .get(endpoint)
        .then(res => {
          console.log(res.data)
          commit('updateUserInfo', res.data)

          let email = `${res.data.battletag}@${this.state.host}`
          let password = res.data.sub
          return firebaseSignIn(email, password)
        }).then(user => {
          let newUser = {
            id: user.user.uid,
            battletag: this.state.playerAuth.userInfo.battletag,
          }
          commit('setUser', newUser)
          endpoint =
            this.state.blizzardApi.host +
            this.state.blizzardApi.endpoints.characters +
            `?access_token=${this.getters.token}`
          return axios.get(endpoint)
        }).then(
          res => {
            console.log(res)
            commit('updateCharacters', res.data.characters)
            let uniqueGuilds = getUniqueGuilds(this.state.characters)
            commit('updateGuilds', uniqueGuilds)
          }
        )
    },
    async generateRosterForSelectedGuild({
      commit
    }) {
      if(this.state.selectedGuild.guildMembers) return;
      let slugs = this.getters.selectedGuildSlug
      const api = this.state.blizzardApi

      let functionsEndpoint =
        this.state.functions.host +
        this.state.functions.endpoints.blizzardApiGet +
        `?action=roster` +
        `&host=${api.host}` +
        `&guildendpoint=${api.endpoints.guild}` +
        `&characterendpoint=${api.endpoints.character}` +
        `&realmslug=${slugs.realm}` +
        `&guildslug=${slugs.guild}` +
        `&namespace=${api.namespace.profile.us}` +
        `&locale=${api.locale.us}` +
        `&access_token=${this.getters.token}`
      await axios
        .get(functionsEndpoint)
        .then(res => {
          let members = res.data.members
          let characters = res.data.characters
          let guildMembers = []
          members.forEach((member, index) => {
            let character = characters.find(character => member.character.name === character.name)
            if (isUndefined(character)) {
              console.log('character too low level')
              return
            }
            let fullCharacter = Object.assign({}, member.character, character)
            let guildMember = Object.assign({}, member, {
              character: fullCharacter
            })
            guildMembers.push(guildMember)
          })

          console.log(guildMembers)

          commit('updateMembersAndCharactersForGuild', {
            guildMembers
          })
        })
    }
  },
  modules: {},
  getters: {
    selectedGuildSlug: state => {
      return {
        guild: generateSlug(state.selectedGuild.name),
        realm: generateSlug(state.selectedGuild.realm)
      }
    },
    pageName: state => state.pageName,
    userId: state => state.user ? state.user.id : undefined,
    user: state => state.user,
    token: state => {
      let token = state.playerAuth._token ? state.playerAuth._token.access_token : localStorage.getItem('access_token')
      console.log(token);
      return token
    },
    guilds: state => state.guilds,
    selectedGuild: state => state.selectedGuild,
    guildMembers: state => state.selectedGuild.guildMembers
  }
})

function generateSlug(value) {
  return value
    .toLowerCase()
    .replace(/'/g, '')
    .replace(/ /g, '-')

}

async function firebaseSignUp(email, password) {
  return firebase.auth().createUserWithEmailAndPassword(email, password)
}

async function firebaseSignIn(email, password) {
  let emailValidity = []
  emailValidity = await firebase.auth().fetchSignInMethodsForEmail(email)
  if (!emailValidity.includes('password')) {
    await firebaseSignUp(email, password)
  }
  return firebase.auth().signInWithEmailAndPassword(email, password)
}

function getUniqueGuilds(characters) {
  let guilds = []
  characters.forEach(character => {
    let guildCompound = character.guild + character.guildRealm
    let testingFunction = guild => guild.compound === guildCompound
    if (!guilds.some(testingFunction)) {
      if (!isString(guildCompound)) return
      guilds.push({
        compound: guildCompound,
        name: character.guild,
        realm: character.guildRealm,
        characters: [character]
      })
    } else {
      let guildIndex = guilds.findIndex(testingFunction)
      guilds[guildIndex].characters.push(character);
    }
  });
  console.log(guilds)
  return guilds
}