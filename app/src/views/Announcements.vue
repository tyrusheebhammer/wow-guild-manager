<template>
  <v-container fluid>
    <v-row dense>
      <v-col
        cols="12"
        sm="6"
        v-for="(announcement, index) in announcements"
        :key="announcement.title"
        class="py-0 my-0"
      >
        <announcement-card
          @click="showModal"
          :id="index"
          :title="announcement.title"
          :subtitle="announcement.subtitle"
        ></announcement-card>
      </v-col>
    </v-row>

    <!-- <v-row justify="center">
    <v-dialog v-model="dialog" v-if="selected!==null" persistent max-width="290">
      
      <v-card>
        <v-card-title class="headline">{{ announcements[selected].title }}</v-card-title>
        <v-card-text>{{ announcements[selected].subtitle }}</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn v-if="announcements[selected].creator===user" color="primary" text @click="dialog = false">Edit</v-btn>
          <v-btn v-if="announcements[selected].creator===user" color="primary" text @click="dialog = false">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    </v-row>-->
    <div class="text-center">
      <v-dialog v-model="dialog" width="500">
        <template v-slot:activator="{ on }"></template>

        <v-card>
          <v-card-title class="headline grey lighten-2" primary-title>{{ announcements[selected].title }}</v-card-title>

          <v-card-text>{{ announcements[selected].subtitle }}</v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              v-if="announcements[selected].creator===user"
              color="primary"
              text
              @click="dialog = false"
            >Edit</v-btn>
            <v-btn
              v-if="announcements[selected].creator===user"
              color="primary"
              text
              @click="dialog = false"
            >Delete</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </v-container>
</template>

<script>
/* eslint-disable */
import AnnouncementCard from "@/components/AnnouncementCard.vue";
export default {
  name: "Announcements",
  components: {
    AnnouncementCard
  },
  methods: {
    showModal(id, user) {
      console.log("id is ", id);
      this.dialog = true;
      this.selected = id;
    }
  },
  data() {
    return {
      dialog: false,
      selected: null,
      user: "Megan",
      announcements: [
        {
          id: "123",
          title: "Today",
          subtitle: "Megatron has been overthrown",
          creator: "Megan"
        },
        {
          id: "345",
          title: "Tomorrow",
          subtitle: "Tyrus reigns",
          creator: "Tyrus"
        }
      ]
    };
  }
};
</script>

<style>
</style>
