import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import firebase from 'firebase/app'
import 'firebase/firestore'

import { firestorePlugin } from 'vuefire'
Vue.use(firestorePlugin)

firebase.initializeApp({
  apiKey: "AIzaSyDoa26dytWz6Cci3UyZSPn9LwSc2T7i83c",
  authDomain: "wow-guild-manager.firebaseapp.com",
  databaseURL: "https://wow-guild-manager.firebaseio.com",
  projectId: "wow-guild-manager",
  storageBucket: "wow-guild-manager.appspot.com",
  messagingSenderId: "529044982271",
  appId: "1:529044982271:web:81dbc648c4ade5f914ffca",
  measurementId: "G-Q933PC9V5P"
})
export const db = firebase.firestore()

Vue.config.productionTip = false
Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App),
  mounted () {
    store.commit('setupClient')
  }
}).$mount('#app')
