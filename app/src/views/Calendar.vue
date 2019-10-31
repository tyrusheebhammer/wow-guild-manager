<template>
  <v-container fluid>
    <v-row dense>
      <v-col cols="12" sm="6" v-for="(event, index) in events" :key="event.id" class="py-0 my-0">
        <calendar-card
          @click="showModal"
          :id="index"
          :date="event.date"
          :title="event.title"
          :subtitle="event.subtitle"
          :creator="event.creator"
        ></calendar-card>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-dialog v-model="dialog" v-if="selected!==null" persistent max-width="400">
        <v-card>
          <v-card-title class="headline justify-center primary--text">{{ events[selected].title }}</v-card-title>
          <v-divider class="primary"></v-divider>
          <v-card-text class="py-3">{{ events[selected].subtitle }}</v-card-text>
          <span v-if="userIsCreator" class="pa-0 ma-0">
            <v-card-actions class="pa-2">
              <v-row space-between class="mx-2">
                <v-col>
                  <v-btn
                    outlined
                    v-if="events[selected].creator===user"
                    color="primary"
                    text
                    @click="showEdit"
                  >Edit</v-btn>
                </v-col>
                <v-col>
                  <v-btn
                    outlined
                    v-if="events[selected].creator===user"
                    color="primary"
                    text
                    @click="showDelete"
                  >Delete</v-btn>
                </v-col>
                <v-col>
                  <v-btn
                    outlined
                    v-if="events[selected].creator===user | events[selected].creator!==user"
                    color="primary"
                    text
                    @click="dialog = false"
                  >Close</v-btn>
                </v-col>
              </v-row>
            </v-card-actions>
          </span>
        </v-card>
      </v-dialog>
    </v-row>

    <v-row justify="center">
      <v-dialog id="edit" v-model="edit" v-if="selected!==null" persistent max-width="290">
        <v-card>
          <v-card-title class="headline justify-center primary--text">Edit</v-card-title>
          <v-divider class="primary"></v-divider>
          <v-card-text>
            <v-row space-between class="mx-2">
              <v-col>
                <v-text-field
                  id="titleEntry"
                  label="Solo"
                  placeholder="Title"
                  v-model="events[selected].title"
                  solo
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12" sm="6" md="3">
                <v-text-field
                  id="messageEntry"
                  label="Solo"
                  placeholder="Message"
                  v-model="events[selected].subtitle"
                  solo
                ></v-text-field>
              </v-col>
            </v-row>
          </v-card-text>
          <span v-if="userIsCreator" class="pa-0 ma-0">
            <v-card-actions class="pa-2">
              <v-row space-between class="mx-2">
                <v-col>
                  <v-btn
                    outlined
                    v-if="events[selected].creator===user"
                    color="primary"
                    text
                    @click="saveChanges"
                  >Save</v-btn>
                  <v-btn
                    outlined
                    v-if="events[selected].creator===user"
                    color="primary"
                    text
                    @click="edit = false"
                  >Cancel</v-btn>
                </v-col>
              </v-row>
            </v-card-actions>
          </span>
        </v-card>
      </v-dialog>
    </v-row>

    <v-row justify="center">
      <v-dialog id="delete" v-model="del" v-if="selected!==null" persistent max-width="290">
        <v-card>
          <v-card-title class="headline justify-center primary--text">Delete</v-card-title>
          <v-divider class="primary"></v-divider>
          <v-card-text>Are you sure you want to delete this event?</v-card-text>
          <span v-if="userIsCreator" class="pa-0 ma-0">
            <v-card-actions>
              <v-row space-between class="mx-2">
                <v-col>
                  <v-btn
                    outlined
                    v-if="events[selected].creator===user"
                    color="primary"
                    text
                    @click="doADelete"
                  >Delete</v-btn>
                  <v-btn
                    outlined
                    v-if="events[selected].creator===user"
                    color="primary"
                    text
                    @click="del = false"
                  >Cancel</v-btn>
                </v-col>
              </v-row>
            </v-card-actions>
          </span>
        </v-card>
      </v-dialog>
    </v-row>

    <div id="fab" data-toggle="modal">
      <v-img
        src="https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/AddButton.svg?alt=media&token=d47c8227-8ff4-4e5e-8c3b-5e170f64dfb3"
        lazy-src="@/assets/AddButton.svg"
      ></v-img>
    </div>
  </v-container>
</template>

<script>
/* eslint-disable */
import CalendarCard from "@/components/CalendarCard.vue";
export default {
  name: "Calendar",
  components: {
    CalendarCard
  },
  computed: {
    userIsCreator: function() {
      return true;
    }
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
      events: [
        {
          id: "123",
          date: "Oct. 1st",
          title: "EVENT",
          subtitle: "description",
          creator: "Megan"
        },
        {
          id: "345",
          date: "Oct. 2nd",
          title: "EVENT",
          subtitle: "description",
          creator: "Tyrus"
        }
      ]
    };
  }
};
</script>

<style>
#fab {
  position: fixed;
  bottom: 4px;
  right: 0px;
  z-index: 999;
}

#fab:active {
  transform: translateY(4px);
  opacity: 0.8;
}
</style>
