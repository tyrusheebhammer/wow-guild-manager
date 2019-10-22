<template>
  <v-app>
    <v-app-bar app class="black">
      <v-row align="center" justify="center" class="">
        <router-link to="/">
          <v-img
            src="@/assets/Frame.svg"
            lazy-src="https://picsum.photos/id/11/10/6"
            aspect-ratio="1"
            max-width="40"
            max-height="25"
          ></v-img>
        </router-link>
      </v-row>
      <v-toolbar-title class="headline text-uppercase">
        <span>WoW Guild Manager</span>
        <span class="font-weight-light"></span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn text href="https://github.com/vuetifyjs/vuetify/releases/latest" target="_blank">
        <span class="primary--text">
          <router-link to="/helloworld">Endocrap</router-link>
        </span>
      </v-btn>
    </v-app-bar>
    <v-content>
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
