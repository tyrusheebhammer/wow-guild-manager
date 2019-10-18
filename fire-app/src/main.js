// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

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

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
