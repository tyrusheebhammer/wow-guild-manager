<template>
  <v-app>
    <v-app-bar app class="black">
      <v-row>
        <v-col cols="2" >
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
          <v-row dense class="px-2">
            <span>WoW Guild Manager</span>
          </v-row>
          <v-row dense justify="space-between" class="mx-0 caption">
            <v-col cols="4" class="py-0 my-0">
              <span class="lightgray--text text-left">
                Home Page
              </span>
            </v-col>
            <v-col cols="4" class="text-right py-0 my-0">
              <span class="primary--text">
                <router-link to="/helloworld" class="no-underline">Endocrap</router-link>
              </span>
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
export default {
  name: "App",
  data: () => ({
    //
  })
};
</script>

<style>
.no-underline {
  text-decoration: none;
}
</style>
