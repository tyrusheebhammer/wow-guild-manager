<template>
  <v-app>
    <v-app-bar app class="black">
      <v-row>
        <v-col cols="2">
          <v-row class="mt-2">
            <router-link to="/" class="no-underline">
              <v-img
                src="@/assets/Frame.svg"
                lazy-src="https://picsum.photos/id/11/10/6"
                width="60"
                height="35"
              ></v-img>
            </router-link>
          </v-row>
        </v-col>
        <v-col cols="10" class="mx-0">
          <v-row dense class="px-2" justify="space-between">
            <v-col cols="8" class="py-0 my-0">
              <span>{{ pageTitle }}</span>
            </v-col>

            <v-col cols="4" class="py-0 my-0" align="right" >
              <v-row justify="space-between">
                <v-col cols="10" class="pa-0 ma-0">
                  <router-link to="/Home" class="no-underline" v-if="onCategoryPage">
                    <v-img
                      src="https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/Group%201%20(1).svg?alt=media&token=1f2ed237-103e-4da4-b75a-d7a487f41960"
                      width="20"
                      height="20"
                    ></v-img>
                  </router-link>
                  <router-link to="/GuildSelect" class="no-underline" v-if="onHomePage">
                    <v-img
                      src="https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/Group%201%20(1).svg?alt=media&token=1f2ed237-103e-4da4-b75a-d7a487f41960"
                      width="20"
                      height="20"
                    ></v-img>
                  </router-link>
                </v-col>
                <!-- <v-col cols="2" class="py-0 my-0">
                  <div font-size="xx-small" >Home</div>
                </v-col>-->
              </v-row>
            </v-col>
          </v-row>

          <v-row dense justify="space-between" class="mx-0 px-1 caption">
            <v-col cols="4" class="py-0 my-0">
              <span class="lightgray--text text-left">{{ pageName }}</span>
            </v-col>
            <v-col cols="4" class="text-right py-0 my-0">
              <span class="primary--text">{{loggedInUser}}</span>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-app-bar>
    <v-content class="background">
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
import { mapState } from "vuex";
import { isUndefined } from "util";
export default {
  /* eslint no-console: 0 */ // --> OFF
  /* eslint no-unused-vars: 0 */ // --> OFF
  name: "App",
  data: () => ({
    //
  }),
  computed: {
    ...mapState(["user", "pageName", "selectedGuild"]),
    loggedInUser() {
      if (this.user) return this.user.battletag;
      return "Log in";
    },
    pageTitle() {
      return !isUndefined(this.selectedGuild)
        ? this.selectedGuild.name
        : "WoW Guild Manager";
    },
    onCategoryPage() {
      if (
        (this.$route.name === "Announcements") |
        (this.$route.name === "Polls") |
        (this.$route.name === "Members") |
        (this.$route.name === "Calendar")
      ) {
        return true;
      }
      return false;
    },
    onHomePage() {
      if (this.$route.name === "Home") {
        return true;
      }
      return false;
    }
  },
  methods: {}
};
</script>

<style>
.no-underline {
  text-decoration: none;
}
</style>