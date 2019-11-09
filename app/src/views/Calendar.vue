<template>
  <v-container fluid>
    <v-row dense>
      <v-col cols="12" sm="6" v-for="(event, index) in events" :key="event.id" class="py-0 my-0">
        <calendar-card
          @click="showModal"
          :id="index"
          :createDate="event.createDate"
          :startDate="event.startDate"
          :endDate="event.endDate"
          :title="event.title"
          :desc="event.desc"
          :creator="event.creator"
        ></calendar-card>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-dialog v-model="dialog" v-if="selected!==null" persistent max-width="400">
        <v-card>
          <v-card-title class="headline justify-center primary--text">{{ events[selected].title }}</v-card-title>
          <v-divider class="primary"></v-divider>
          <v-card-text class="py-3">{{ events[selected].desc }}</v-card-text>
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

    <div id="fab" data-toggle="modal" @click="showAdd">
      <v-img
        src="https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/AddButton.svg?alt=media&token=d47c8227-8ff4-4e5e-8c3b-5e170f64dfb3"
        lazy-src="@/assets/AddButton.svg"
      ></v-img>
    </div>

    <v-row justify="center">
      <v-dialog v-model="addEvent" persistent max-width="400">
        <v-card>
          <v-card-title class="headline justify-center primary--text">New Event</v-card-title>
          <v-divider class="primary"></v-divider>
          <v-row space-between>
            <v-col cols="2" class="px-4">
              <v-img
                src="https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/calendar-alt-regular.svg?alt=media&token=2820b5c3-6f95-4bb0-bb59-525a472e0a51"
                lazy-src="@/assets/calendar-alt-regular.svg"
                
              ></v-img>
            </v-col>

            <v-col cols="3" sm="3" md="3" class="px-0">
              <v-menu
                ref="menu"
                v-model="menu"
                :close-on-content-click="false"
                :return-value.sync="date"
                transition="scale-transition"
                offset-y
                min-width="290px"
                
              >
                <template v-slot:activator="{ on }">
                  <v-text-field v-model="startDate" label="Start Date" readonly v-on="on"></v-text-field>
                </template>
                <v-date-picker v-model="startDate" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                  <v-btn text color="primary" @click="$refs.menu.save(startDate)">OK</v-btn>
                </v-date-picker>
              </v-menu>
            </v-col>


            <v-col cols="2" class="px-4">
              <v-img
                src="https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/calendar-alt-regular.svg?alt=media&token=2820b5c3-6f95-4bb0-bb59-525a472e0a51"
                lazy-src="@/assets/calendar-alt-regular.svg"
                
              ></v-img>
            </v-col>

            <v-col cols="3" sm="3" md="3" class="px-0">
              <v-menu
                ref="menu"
                v-model="menu"
                :close-on-content-click="false"
                :return-value.sync="date"
                transition="scale-transition"
                offset-y
                min-width="290px"
                
              >
                <template v-slot:activator="{ on }">
                  <v-text-field v-model="endDate" label="End Date" readonly v-on="on"></v-text-field>
                </template>
                <v-date-picker v-model="endDate" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                  <v-btn text color="primary" @click="$refs.menu.save(endDate)">OK</v-btn>
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row>
          <v-row class="px-2">
            <v-col cols="12">
              <v-text-field v-model="titleInput" placeholder="event title" filled/>
            </v-col>
          </v-row>
          <v-row class="px-2">
            <v-col cols="12">
              <v-text-field v-model="descInput" placeholder="event description" filled/>
            </v-col>
          </v-row>
          <span class="pa-0 ma-0">
            <v-card-actions justify="center" class="pa-2">
              <v-row space-between class="mx-2">
                <v-col>
                  <v-btn outlined color="primary" text @click="createEvent">Create</v-btn>
                  <v-btn outlined color="primary" text @click="addEvent = false">Cancel</v-btn>
                </v-col>
              </v-row>
            </v-card-actions>
          </span>
        </v-card>
      </v-dialog>
    </v-row>
  </v-container>
</template>

<script>
/* eslint-disable */
import { db } from "../main";
import CalendarCard from "@/components/CalendarCard.vue";
export default {
  name: "Calendar",
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
      console.log("try to show deletee");
    },
    saveChanges() {
      this.edit = false;
      console.log("do something with firebase to save changes");
    },
    doADelete() {
      this.del = false;
      console.log("do something with firebase to delete");
    },
    showAdd() {
      this.addEvent = true;
      console.log("show add dialog");
    },
    createEvent() {
      
      db.collection("CalendarEvents").add({
        createDate: "Nov. 8th",
        creator: "megan",
        desc: "descInput",
        endDate: "endDate",
        startDate: "startDate",
        title: "titleInput"
      });
      this.addEvent = false;
    }
  },
  data() {
    return {
      dialog: false,
      datePicker: false,
      edit: false,
      del: false,
      addEvent: false,
      titleInput: "titleInput",
      descInput: "descInput",
      startDate: false,
      endDate: false,
      selected: null,
      user: "RLZ7m6MTuoAmZfuMHA7W",
      events: []
    };
  },
  firestore() {
    return {
      events: db.collection("CalendarEvents")
    };
  },
  components: {
    CalendarCard
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
