import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Announcements from '@/views/Announcements.vue'
import Login from '@/views/Login.vue'
import GuildSelect from '@/views/GuildSelect.vue'
import Home from '@/views/Home.vue'
import Polls from '@/views/Polls.vue'
import Members from '@/views/Members.vue'
import Calendar from '@/views/Calendar.vue'
import Member from '@/views/Member.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/helloworld',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/guildselect',
      name: 'GuildSelect',
      component: GuildSelect
    },
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/polls',
      name: 'Polls',
      component: Polls
    },
    {
      path: '/members',
      name: 'Members',
      component: Members
    },
    {
      path: '/calendar',
      name: 'Calendar',
      component: Calendar
    },
    {
      path: '/member',
      name: 'Member',
      component: Member
    },
    {
      path: '/announcements',
      name: 'Announcements',
      component: Announcements
    }
  ]
})
