<template>
  <v-container fluid>
    <v-row dense>
      <v-col 
        cols="12" sm="6" 
        v-for="category in categories"
        :key="category.title"
        class="py-0 my-0"
      >
        <announcement-card 
          :title="category.title"
          :subtitle="category.subtitle"
        ></announcement-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import AnnouncementCard from '@/components/AnnouncementCard.vue'
export default {
  name: 'Announcements',
  components: {
    AnnouncementCard
  },
  data() {
    return {
      categories: [
        {
          title: 'Members',
          subtitle: 'List of guild members'
        },
        {
          title: 'Calendar',
          subtitle: 'Create and manage events'
        },
      ]
    }
  }
}
</script>

<style>

</style>
