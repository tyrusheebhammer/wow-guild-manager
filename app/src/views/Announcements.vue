<template>
  <v-container fluid>
    <v-row dense>
      <v-col
        cols="12"
        sm="6"
        v-for="(announcement, index) in announcements"
        :key="announcement.id"
        class="py-0 my-0"
      >
        <announcement-card
          @click="showModal"
          :id="index"
          :date="announcement.date"
          :title="announcement.title"
          :subtitle="announcement.subtitle"
          :creator="announcement.creator"
        ></announcement-card>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-dialog v-model="dialog" v-if="selected!==null" persistent max-width="290">
        <v-card>
          <v-card-title class="headline">{{ announcements[selected].title }}</v-card-title>
          <v-card-text>{{ announcements[selected].subtitle }}</v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              v-if="announcements[selected].creator===user"
              color="primary"
              text
              @click="showEdit"
            >Edit</v-btn>
            <v-btn
              v-if="announcements[selected].creator===user"
              color="primary"
              text
              @click="showDelete"
            >Delete</v-btn>
            <v-btn
              v-if="announcements[selected].creator===user | announcements[selected].creator!==user"
              color="primary"
              text
              @click="dialog = false"
            >Close</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>

    <v-row justify="center">
      <v-dialog id="edit" v-model="edit" v-if="selected!==null" persistent max-width="290">
        <v-card>
          <v-card-title class="headline">Edit</v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="12" sm="6" md="3">
                <v-text-field id="titleEntry" label="Solo" placeholder="Title" v-model="announcements[selected].title" solo></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" sm="6" md="3">
                <v-text-field id="messageEntry" label="Solo" placeholder="Message" v-model="announcements[selected].subtitle" solo></v-text-field>
              </v-col>
            </v-row>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              v-if="announcements[selected].creator===user"
              color="primary"
              text
              @click="saveChanges"
            >Save</v-btn>
            <v-btn
              v-if="announcements[selected].creator===user"
              color="primary"
              text
              @click="edit = false"
            >Cancel</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>

    <v-row justify="center">
      <v-dialog id="delete" v-model="del" v-if="selected!==null" persistent max-width="290">
        <v-card>
          <v-card-title class="headline">Delete</v-card-title>
          <v-card-text>Are you sure you want to delete this announcement?</v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              v-if="announcements[selected].creator===user"
              color="primary"
              text
              @click="doADelete"
            >Delete</v-btn>
            <v-btn
              v-if="announcements[selected].creator===user"
              color="primary"
              text
              @click="del = false"
            >Cancel</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
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
      this.user = "Megan";
    },
    showEdit() {
      this.dialog = false;
      this.edit = true;
    },
    showDelete() {
      this.dialog = false;
      this.del = true;
    },
    saveChanges() {
      this.edit = false;
      console.log("do something with firebase to save changes");
    },
    doADelete() {
      this.del = false;
      console.log("do something with firebase to delete");
    }
  },
  data() {
    return {
      dialog: false,
      edit: false,
      del: false,
      selected: null,
      user: "Megan",
      announcements: [
        {
          id: "123",
          date: "Today",
          title: "title",
          subtitle: "Megatron has been overthrown",
          creator: "Megan"
        },
        {
          id: "345",
          date: "Tomorrow",
          title: "title",
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
