/* eslint no-console: 0 */  // --> OFF
import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
let test = false
Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    host: 'wow-guild-manager.web.app',
    imageSrc: {
      bnet: 'https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/battle-net-icon-9.jpg?alt=media&token=e89197eb-15a0-4e75-baf8-37794e91d4d5'
    },
    clientId: 'clientId',
    functions: {
      host: 'https://us-central1-wow-guild-manager.cloudfunctions.net',
      endpoints: {
        client: '/client',
        oauthToken: '/oauthToken',
        auth: '/auth'
      },
    },
    blob: undefined,
    namespace: {
      profile: {
        us: 'profile-us'
      }
    },
    battleNetId: '',
    tokens: {
      authorizationCode: '',
      clientCredentials: ''
    },
    playerAuth: {
      host: 'us.battle.net',
      test: undefined,
      endpoints: {
        authorize: '/oauth/authorize',
        accessToken: '/oauth/token',
        userInfo: '/oauth/userinfo'
      },
      redirectUri: test ? 'https://us-central1-wow-guild-manager.cloudfunctions.net/auth'
        :
      'http://localhost:5000/wow-guild-manager/us-central1/auth', //change this when done testing
      responseType: 'code',
      grantType: 'authorization_code',
      code: undefined
    },
    actions: {
      characters: '/wow/user/characters'
    },
    get playerAuthRequestUri() {
      return this.playerAuth.host 
        +  this.playerAuth.endpoints.authorize
        + `?client_id=${this.clientId}`
        // + `&scope=${this.scope.wowProfile}`
        + `&state=${this.blob}`
        + `&redirect_uri=${this.playerAuth.redirectUri}`
        + `&response_type=${this.playerAuth.responseType}`
    }
  },
  mutations: {
    setupClient(state) {
      axios
        .get(this.state.functions.host + this.state.functions.endpoints.client)
        .then(res => {
          state.clientId = res.data.clientId
          state.blob = res.data.blob
          test = res.data.test
          console.log(state.clientId, state.blob);
      })
    },
  },
  actions: {
    
  },
  modules: {
  }
})
