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
          :dateCreated="announcement.dateCreated"
          :desc="announcement.desc"
          :title="announcement.title"
          :creator="announcement.creator"
          :startDate="announcement.startDate"
          :endDate="announcement.endDate"
        ></announcement-card>
      </v-col>
    </v-row>

    <!-- Selected Dialog -->
    <v-row justify="center">
      <v-dialog
        v-model="dialog"
        v-if="selected!==null"
        persistent
        max-width="290"
        class="pa-0 ma-0"
      >
        <v-card>
          <v-card-title
            class="headline justify-center primary--text"
          >{{ announcements[selected].title }}</v-card-title>
          <v-divider class="primary"></v-divider>
          <v-card-text>{{ announcements[selected].desc }}</v-card-text>
          <span class="pa-0 ma-0">
            <v-card-actions class="pa-2">
              <v-row space-between class="mx-2">
                <v-col>
                  <v-btn
                    v-if="this.userIsCreator"
                    color="primary"
                    outlined
                    text
                    @click="showEdit"
                  >Edit</v-btn>
                  <v-btn
                    v-if="this.userIsCreator"
                    color="primary"
                    outlined
                    text
                    @click="showDelete"
                  >Delete</v-btn>
                  <v-btn color="primary" outlined text @click="dialog = false">Close</v-btn>
                </v-col>
              </v-row>
            </v-card-actions>
          </span>
        </v-card>
      </v-dialog>
    </v-row>

    <!-- Edit -->
    <v-row justify="center">
      <v-dialog
        id="edit"
        v-model="edit"
        v-if="selected!==null && userIsCreator"
        persistent
        max-width="400"
      >
        <v-card>
          <v-card-title class="headline justify-center primary--text">Edit</v-card-title>
          <v-divider class="primary"></v-divider>
          <v-card-text class="px-2 mx-0">
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
                    <v-text-field v-model="startDate" label="Start Date" readonly v-on="on"></v-text-field>
                  </template>
                  <v-date-picker v-model="startDate" no-title scrollable>
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" outlined @click="menuStart = false">Cancel</v-btn>
                    <v-btn text color="primary" outlined @click="$refs.menuStart.save(startDate)">OK</v-btn>
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
                    <v-text-field v-model="endDate" label="End Date" readonly v-on="on"></v-text-field>
                  </template>
                  <v-date-picker v-model="endDate" no-title scrollable>
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" outlined @click="menuEnd = false">Cancel</v-btn>
                    <v-btn text color="primary" outlined @click="$refs.menuEnd.save(endDate)">OK</v-btn>
                  </v-date-picker>
                </v-menu>
              </v-col>
            </v-row>
            <v-row class="px-2" justify="center">
              <v-col cols="12">
                <v-text-field v-model="titleInputEdit" placeholder="announcement title" filled />
              </v-col>
            </v-row>

            <v-row class="px-2" justify="center">
              <v-col cols="12">
                <v-text-field
                  v-model="descInputEdit"
                  placeholder="announcement description"
                  filled
                />
              </v-col>
            </v-row>
          </v-card-text>
          <span v-if="userIsCreator" class="pa-0 ma-0">
            <v-card-actions class="pa-2">
              <v-row space-between class="mx-2">
                <v-col>
                  <v-btn color="primary" text outlined @click="saveChanges">Save</v-btn>
                  <v-btn color="primary" text outlined @click="edit = false">Cancel</v-btn>
                </v-col>
              </v-row>
            </v-card-actions>
          </span>
        </v-card>
      </v-dialog>
    </v-row>

    <!-- Delete Dialog -->
    <v-row justify="center">
      <v-dialog
        id="delete"
        v-model="del"
        v-if="selected!==null && userIsCreator"
        persistent
        max-width="290"
      >
        <v-card>
          <v-card-title class="headline justify-center primary--text">Delete</v-card-title>
          <v-divider class="primary"></v-divider>
          <v-card-text>Are you sure you want to delete this announcement?</v-card-text>
          <span v-if="userIsCreator" class="pa-0 ma-0">
            <v-card-actions class="pa-2">
              <v-row space-between class="mx-2">
                <v-col>
                  <v-btn color="primary" text outlined @click="doADelete">Delete</v-btn>
                  <v-btn color="primary" text outlined @click="del = false">Cancel</v-btn>
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
      <v-dialog v-model="addAnnouncement" persistent max-width="400">
        <v-card>
          <v-card-title class="headline justify-center primary--text">New Announcement</v-card-title>
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
                  <v-text-field v-model="startDate" label="Start Date" readonly v-on="on"></v-text-field>
                </template>
                <v-date-picker v-model="startDate" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" outlined @click="menuStart = false">Cancel</v-btn>
                  <v-btn text color="primary" outlined @click="$refs.menuStart.save(startDate)">OK</v-btn>
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
                  <v-text-field v-model="endDate" label="End Date" readonly v-on="on"></v-text-field>
                </template>
                <v-date-picker v-model="endDate" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" outlined @click="menuEnd = false">Cancel</v-btn>
                  <v-btn text color="primary" outlined @click="$refs.menuEnd.save(endDate)">OK</v-btn>
                </v-date-picker>
              </v-menu>
            </v-col>
          </v-row>
          <v-row class="px-2">
            <v-col cols="12">
              <v-text-field v-model="titleInput" placeholder="announcement title" filled />
            </v-col>
          </v-row>
          <v-row class="px-2">
            <v-col cols="12">
              <v-text-field v-model="descInput" placeholder="announcement description" filled />
            </v-col>
          </v-row>
          <span class="pa-0 ma-0">
            <v-card-actions justify="center" class="pa-2">
              <v-row display="flex" justify="center" class="mx-2">
                <v-col cols="6">
                  <v-btn outlined color="primary" text @click="createAnnouncement">Create</v-btn>
                </v-col>
                <v-col cols="6">
                  <v-btn outlined color="primary" text @click="addAnnouncement = false">Cancel</v-btn>
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
import AnnouncementCard from "@/components/AnnouncementCard.vue";
import { mapState } from 'vuex';
export default {
  name: "Announcements",
  created: function() {
    this.$store.commit('updatePageName', "Announcements")
  },
  computed: {
    ...mapState(['userId']),
    userIsCreator: function() {
      return (
        userId === this.announcements[this.selected].creator
      );
    }
  },
  methods: {
    showModal(id, user) {
      console.log("id is ", id);
      this.dialog = true;
      this.selected = id;
      this.user = "Megan";
    },
    showAdd() {
      this.addAnnouncement = true;
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
      db.collection("Announcements")
        .doc(this.announcements[this.selected].id.toString())
        .set({
          startDate: this.startDate,
          endDate: this.endDate,
          creator: this.user,
          desc: this.descInputEdit,
          title: this.titleInputEdit
        });
    },
    doADelete() {
      this.del = false;
      db.collection("Announcements")
        .doc(this.announcements[this.selected].id.toString())
        .delete();
    },
    createAnnouncement() {
      db.collection("Announcements")
        .doc()
        .set({
          createDate: "Nov. 8th",
          startDate: this.startDate,
          endDate: this.endDate,
          creator: this.user,
          desc: this.descInput,
          title: this.titleInput,
          creator: userId

        });
      this.addAnnouncement = false;
    }
  },
  data() {
    return {
      dialog: false,
      edit: false,
      del: false,
      selected: null,
      addAnnouncement: false,
      titleInput: "",
      descInput: "",
      titleInputEdit: "",
      descInputEdit: "",
      startDate: "",
      endDate: "",
      menu: false,
      date: false,
      user: "RLZ7m6MTuoAmZfuMHA7W",
      dateCreated: "",
      desc: "",
      title: "",
      menuStart: false,
      menuEnd: false,
      announcements: []
    };
  },
  firestore() {
    return {
      announcements: db.collection("Announcements")
    };
  },
  components: {
    AnnouncementCard
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
