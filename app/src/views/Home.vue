<template>
  <v-container fluid>
    <v-row>
      <v-col cols="12" sm="6" v-for="category in categories" :key="category.title">
        <category-card
          :key="category.title"
          :src="category.image"
          :title="category.title"
          :subtitle="category.subtitle"
          :href="category.href"
        ></category-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import CategoryCard from "@/components/CategoryCard.vue";
export default {
  name: "Home",
  components: {
    CategoryCard
  },
  data() {
    return {
      categories: [
        {
          image:
            "https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/Sword.svg?alt=media&token=15a90937-c0e5-488a-b828-506650d7b798",
          title: "Members",
          subtitle: "List of guild members",
          href: "/members/"
        },
        {
          image:
            "https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/calendar-alt-regular.svg?alt=media&token=2820b5c3-6f95-4bb0-bb59-525a472e0a51",
          title: "Calendar",
          subtitle: "Create and manage events",
          href: "/calendar/"
        },
        {
          image:
            "https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/scroll-solid.svg?alt=media&token=776e6d78-4c3e-42f9-9416-5dd0a2a01cc9",
          title: "Announcements",
          subtitle: "Announce updates in your guild and schedule",
          href: "/announcements/"
        },
        {
          image:
            "https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/Polls.svg?alt=media&token=0f9ce1c0-3061-48bf-af20-5d080e5896c9",
          title: "Polls",
          subtitle: "Create polls and view results",
          href: "/polls/"
        }
      ]
    };
  },
  created() {
    if (this.$store.state.firebase.user === undefined) {
      this.$router.push("/login");
    } else if (this.$store.state.firebase.selectedGuild === undefined) {
      this.$router.push("/guildSelect");
    }
  }
};
</script>

<style>
</style>
