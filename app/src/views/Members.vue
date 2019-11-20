
<template>
  <v-container>
    <v-row>
      <v-col class="my-0 py-0" cols="12" v-for="member in guildMembers" :key="member.character.id">
        <member-card 
          :name="member.character.name"
          :raiderIOScore="0"
          :role="'-'"
          :simDps="0"
          :rank="member.rank"
          :level="member.character.level"
          :ilevel="0"
          :charClass="'-'"
          :thumbnail="member.character.thumbnail"
        >

        </member-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
/* eslint no-console: 0 */  // --> OFF
/* eslint vue/no-unused-components: 0 */  // --> OFF

import { db } from '../main'
import MemberCard from '@/components/MemberCard.vue';
import { isUndefined } from 'util';

export default {
  name: 'Members',
  data() {
    return {
      members: []
    }
  },
  firestore () {
    return {
      members: db.collection('Members')
    }
  },
  computed: {
    guildMembers() {
      return this.$store.state.selectedGuild.guildMembers
    }
  },
  created() {
    if(isUndefined(this.user)) this.$router.push('/')
    this.$store.subscribe((mutation) => {
        if(mutation.type === 'updateMembersAndCharactersForGuild') {
          this.members = this.$store.getters.guildMembers
        }
    })
    this.$store.dispatch('generateRosterForSelectedGuild')
    this.$store.commit('updatePageName', "Members")
  },
  components: {
    MemberCard
  }
}
</script>

<style>

</style>
