
<template>
  <v-container>
    <v-row>
      <v-col cols="12" v-for="member in members" :key="member.id">
        <member-card 
          :name="member.name"
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
