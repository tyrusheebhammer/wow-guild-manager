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

    <!-- Selected Dialog -->
    <v-row justify="center">
      <v-dialog v-model="dialog" v-if="selected!==null" persistent max-width="400">
        <v-card>
          <v-card-title class="headline justify-center primary--text">{{ events[selected].title }} -- {{ events[selected].creator }} </v-card-title>
          <v-divider class="primary"></v-divider>
          <v-card-text class="py-3">{{ events[selected].desc }}</v-card-text>
          <span class="pa-0 ma-0">
            <v-card-actions class="pa-2">
              <v-row space-between class="mx-2">
                <v-col>
                  <v-btn
                    v-if="this.userIsCreator"
                    outlined
                    color="primary"
                    text
                    @click="showEdit"
                  >Edit</v-btn>
                </v-col>
                <v-col>
                  <v-btn
                    v-if="this.userIsCreator"
                    outlined
                    color="primary"
                    text
                    @click="showDelete"
                  >Delete</v-btn>
                </v-col>
                <v-col>
                  <v-btn outlined color="primary" text @click="dialog = false">Close</v-btn>
                </v-col>
              </v-row>
            </v-card-actions>
          </span>
        </v-card>
      </v-dialog>
    </v-row>

    <!-- Edit -->
    <v-row justify="center">
      <v-dialog id="edit" v-model="edit" v-if="selected!==null" persistent max-width="400">
        <v-card>
          <v-card-title class="headline justify-center primary--text">Edit</v-card-title>
          <v-divider class="primary"></v-divider>
          <v-card-text>
            <v-row justify="center">
              <v-col cols="2" class="px-4">
                <v-img
                  src="https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/calendar-alt-regular.svg?alt=media&token=2820b5c3-6f95-4bb0-bb59-525a472e0a51"
                  lazy-src="@/assets/calendar-alt-regular.svg"
                ></v-img>
              </v-col>

              <v-col cols="3" sm="3" md="3" class="px-0">
                <v-menu
                  ref="menuStart"
                  v-model="menuStart"
                  :close-on-content-click="false"
                  :return-value.sync="date"
                  transition="scale-transition"
                  offset-y
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field v-model="startDate" label="Start Date" v-on="on"></v-text-field>
                  </template>
                  <v-date-picker v-model="startDate" no-title scrollable>
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" @click="menuStart = false">Cancel</v-btn>
                    <v-btn text color="primary" @click="$refs.menuStart.save(startDate)">OK</v-btn>
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
                  ref="menuEnd"
                  v-model="menuEnd"
                  :close-on-content-click="false"
                  :return-value.sync="date"
                  transition="scale-transition"
                  offset-y
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field v-model="endDate" label="End Date" v-on="on"></v-text-field>
                  </template>
                  <v-date-picker v-model="endDate" no-title scrollable>
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" @click="menuEnd = false">Cancel</v-btn>
                    <v-btn text color="primary" @click="$refs.menuEnd.save(endDate)">OK</v-btn>
                  </v-date-picker>
                </v-menu>
              </v-col>
            </v-row>
            <v-row class="px-2">
              <v-col cols="12">
                <v-text-field v-model="titleInputEdit" placeholder="event title" filled />
              </v-col>
            </v-row>
            <v-row class="px-2">
              <v-col cols="12">
                <v-text-field v-model="descInputEdit" placeholder="event description" filled />
              </v-col>
            </v-row>
          </v-card-text>
          <span v-if="userIsCreator" class="pa-0 ma-0">
            <v-card-actions class="pa-2">
              <v-row space-between class="mx-2">
                <v-col>
                  <v-btn outlined color="primary" text @click="saveChanges">Save</v-btn>
                </v-col>
                <v-col>
                  <v-btn outlined color="primary" text @click="edit = false">Cancel</v-btn>
                </v-col>
              </v-row>
            </v-card-actions>
          </span>
        </v-card>
      </v-dialog>
    </v-row>

    <!-- Delete Dialog -->
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
                  <v-btn outlined color="primary" text @click="doADelete">Delete</v-btn>
                  <v-btn outlined color="primary" text @click="del = false">Cancel</v-btn>
                </v-col>
              </v-row>
            </v-card-actions>
          </span>
        </v-card>
      </v-dialog>
    </v-row>

    <!-- Fab -->
    <div id="fab" data-toggle="modal" @click="showAdd">
      <v-img
        src="https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/AddButton.svg?alt=media&token=d47c8227-8ff4-4e5e-8c3b-5e170f64dfb3"
        lazy-src="@/assets/AddButton.svg"
      ></v-img>
    </div>

    <!-- Add -->
    <v-row justify="center">
      <v-dialog v-model="addEvent" persistent max-width="400">
        <v-card>
          <v-card-title class="headline justify-center primary--text">New Event</v-card-title>
          <v-divider class="primary"></v-divider>
          <v-row justify="center">
            <v-col cols="2" class="px-4">
              <v-img
                src="https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/calendar-alt-regular.svg?alt=media&token=2820b5c3-6f95-4bb0-bb59-525a472e0a51"
                lazy-src="@/assets/calendar-alt-regular.svg"
              ></v-img>
            </v-col>

            <v-col cols="3" sm="3" md="3" class="px-0">
              <v-menu
                ref="menuStart"
                v-model="menuStart"
                :close-on-content-click="false"
                :return-value.sync="date"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on }">
                  <v-text-field v-model="startDate" label="Start Date" v-on="on"></v-text-field>
                </template>
                <v-date-picker v-model="startDate" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="menuStart = false">Cancel</v-btn>
                  <v-btn text color="primary" @click="$refs.menuStart.save(startDate)">OK</v-btn>
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
                ref="menuEnd"
                v-model="menuEnd"
                :close-on-content-click="false"
                :return-value.sync="date"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on }">
                  <v-text-field v-model="endDate" label="End Date" v-on="on"></v-text-field>
                </template>
                <v-date-picker v-model="endDate" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="menuEnd = false">Cancel</v-btn>
                  <v-btn text color="primary" @click="$refs.menuEnd.save(endDate)">OK</v-btn>
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row>
          <v-row class="px-2">
            <v-col cols="12">
              <v-text-field v-model="titleInput" placeholder="event title" filled />
            </v-col>
          </v-row>
          <v-row class="px-2">
            <v-col cols="12">
              <v-text-field v-model="descInput" placeholder="event description" filled />
            </v-col>
          </v-row>
          <span class="pa-0 ma-0">
            <v-card-actions justify="center" class="pa-2">
              <v-row justify="center" class="mx-2">
                <v-col>
                  <v-btn outlined color="primary" text @click="createEvent">Create</v-btn>
                </v-col>
                <v-col>
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
import { mapState, mapGetters } from 'vuex';
import CalendarCard from "@/components/CalendarCard.vue";
import { isUndefined } from 'util';
export default {
  name: "Calendar",
  created: function () {
    if(isUndefined(this.$store.getters.user)) this.$router.push('/')
    this.$store.commit('updatePageName', "Calendar")
  },
  computed: {
    userIsCreator: function() {
      return this.$store.getters.user.battletag === this.events[this.selected].creator;
    },
    ...mapGetters['user', 'selectedGuild']
  },
  methods: {
    showModal(id, user) {
      this.dialog = true;
      this.selected = id;
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
      db.collection("CalendarEvents")
        .doc(this.events[this.selected].id.toString())
        .set({
          startDate: this.startDate,
          endDate: this.endDate,
          creator: this.$store.getters.user.battletag,
          desc: this.descInputEdit,
          title: this.titleInputEdit,
          guild: this.$store.getters.selectedGuild.compound
        });
    },
    doADelete() {
      this.del = false;
      db.collection("CalendarEvents")
        .doc(this.events[this.selected].id.toString())
        .delete();
    },
    showAdd() {
      this.addEvent = true;
    },
    createEvent() {
      db.collection("CalendarEvents")
        .doc()
        .set({
          createDate: new Date().toDateString(),
          startDate: this.startDate,
          endDate: this.endDate,
          creator: this.$store.getters.user.battletag,
          desc: this.descInput,
          title: this.titleInput,
          guild: this.$store.getters.selectedGuild.compound
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
      titleInput: "",
      descInput: "",
      titleInputEdit: "",
      descInputEdit: "",
      startDate: "",
      endDate: "",
      menu: false,
      date: false,
      menuEnd: false,
      menuStart: false,
      selected: null,
      title: "",
      events: []
    };
  },
  firestore() {
    return {
      events: db.collection("CalendarEvents").where('guild', '==', this.$store.getters.selectedGuild.compound)
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
