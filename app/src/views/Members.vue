
<template>
  <v-container>
    <v-row>
      <v-col class="my-0 py-0" cols="12" v-for="member in members" :key="member.id">
        <member-card 
          :name="member.name"
          :raiderIOScore="member.raiderIOScore"
          :role="member.role"
          :simDps="member.simDps"
          :rank="1"
          :level="120"
          :ilevel="430"
          :charClass="'Rogue'"
        >

        </member-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

import { db } from '../main'
import MemberCard from '@/components/MemberCard.vue';
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
  components: {
    MemberCard
  }
}
</script>

<style>

</style>
