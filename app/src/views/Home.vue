<template>
  <v-container fluid>
    <v-row dense>
      <v-col 
        cols="12" sm="6" 
        v-for="category in categories"
        :key="category.title"
        class="py-0 my-0"
      >
        <category-card 
          :img-src="category.image"
          :title="category.title"
          :subtitle="category.subtitle"
          :href="category.href"
        ></category-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import CategoryCard from '@/components/CategoryCard.vue'
export default {
  name: 'Home',
  components: {
    CategoryCard
  },
  data() {
    return {
      categories: [
        {
          image: 'https://i.kym-cdn.com/entries/icons/mobile/000/001/313/DERP.jpg',
          title: 'Members',
          subtitle: 'List of guild members',
          href: '/members/'
        },
        {
          image: 'https://i.kym-cdn.com/entries/icons/mobile/000/001/313/DERP.jpg',
          title: 'Calendar',
          subtitle: 'Create and manage events',
          href: '/calendar/'
        },
        {
          image: 'https://i.kym-cdn.com/entries/icons/mobile/000/001/313/DERP.jpg',
          title: 'Announcements',
          subtitle: 'Announce updates in your guild and schedule',
          href: '/announcements/'
        },
        {
          image: 'https://i.kym-cdn.com/entries/icons/mobile/000/001/313/DERP.jpg',
          title: 'Polls',
          subtitle: 'Create polls and view results',
          href: '/polls/'
        },
      ]
    }
  }
}
</script>

<style>

</style>
