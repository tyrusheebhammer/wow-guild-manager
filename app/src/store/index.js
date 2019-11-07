/* eslint no-console: 0 */  // --> OFF
import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    imageSrc: {
      bnet: 'https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/battle-net-icon-9.jpg?alt=media&token=e89197eb-15a0-4e75-baf8-37794e91d4d5'
    },
    clientId: 'clientId',
    functionsURL: 'https://us-central1-wow-guild-manager.cloudfunctions.net/',
    endpoints: {
      clientId: 'clientId/',
    },
    battleNetId: '',
    
    tokens: {
      authorizationCode: '',
      clientCredentials: ''
    }
  },
  mutations: {
  },
  actions: {
    setupClient() {
      axios
        .get(this.state.functionsURL + this.state.endpoints.clientId)
        .then(res => {
          console.log(res);
      })
    }
  },
  modules: {
  }
})
