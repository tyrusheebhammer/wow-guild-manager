const functions = require('firebase-functions')
const Promise = require("bluebird");
const cors = require('cors')({origin: true});
// const request = require("request");
// const rp = require("request-promise");
// const R = require("ramda");
// const tmp = require("tmp");
// const fs = require("fs");

// functions.config().blizzard = {
//     oauth: {
//         client: {}
//     }
// }

// functions.config().blizzard.oauth.client.id = "fdbc9b3583da40c8ae9b81c9c39a8b52"
// functions.config().blizzard.oauth.client.secret = "DJ7FO8q8t1o3AAzP1Dqx146p4iiwSYUz"

const credentials = {
    client: {
      id: functions.config().blizzard.oauth.client.id,
      secret: functions.config().blizzard.oauth.client.secret
    },
    auth: {
      tokenHost: "https://us.battle.net"
    }
};

const oauth2 = require("simple-oauth2").create(credentials);
let token = null;

const getToken = () => {
    if (token === null || token.expired()) {
      return oauth2.clientCredentials
        .getToken()
        .then(oauth2.accessToken.create)
        .then(t => {
          token = t;
          return t.token.access_token;
        });
    } else {
      return Promise.resolve(token.token.access_token);
    }
};

exports.oauthToken = functions.https.onRequest((_, response) => {
  return cors(_, response, () => {
    getToken().then(token => {
        response.send(`${token}`)
    })
  })
});

exports.clientId = functions.https.onRequest((_, response) => {
  return cors(_, response, () => {
    response.send(credentials.client.id);
  })
});
