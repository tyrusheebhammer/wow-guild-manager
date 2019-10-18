import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Announcements from '@/views/announcements.vue'
import Login from '@/views/login.vue'
import GuildSelect from '@/views/guildselect.vue'
import Home from '@/views/home.vue'
import Polls from '@/views/polls.vue'
import Members from '@/views/members.vue'
import Calendar from '@/views/calendar.vue'
import Member from '@/views/member.vue'

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
