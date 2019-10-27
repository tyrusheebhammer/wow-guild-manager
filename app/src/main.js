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
  projectId: 'wow-guild-manager',
  databaseURL: 'wow-guild-manager'
})
export const db = firebase.firestore()

Vue.config.productionTip = false
Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
