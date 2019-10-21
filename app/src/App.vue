<template>
  <v-app>
    <v-app-bar app class="black">
      <v-row align="center" justify="center" class="">
          <v-img
            src="@/assets/Frame.svg"
            lazy-src="https://picsum.photos/id/11/10/6"
            aspect-ratio="1"
            max-width="40"
            max-height="25"
          ></v-img>
        </v-row>
      <v-toolbar-title class="headline text-uppercase">
        <span>WoW Guild Manager</span>
        <span class="font-weight-light"></span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn text href="https://github.com/vuetifyjs/vuetify/releases/latest" target="_blank">
        <span class="primary--text">Endocry</span>
      </v-btn>
    </v-app-bar>

    <v-content>
      <HelloWorld />
    </v-content>
  </v-app>
</template>

<script>
import HelloWorld from "./components/HelloWorld";

export default {
  name: "App",
  components: {
    HelloWorld
  },
  data: () => ({
    //
  })
};
</script>
