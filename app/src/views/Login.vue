<template>
  <v-container fluid>
    <v-row align-content="center" align="center">
      <v-col cols="12" align-self="center">
        <v-card 
        outlined
        class="darkgray"
        max-width="400"
        >
          <v-row justify="center" class="text-center" align="center">
            <v-col cols="6" class="lightgray--text">Log in with Battle.net</v-col>
            <v-col cols="3">
              <v-row justify="center">
                <v-img 
                  @click="login"
                  contain 
                  height="80" 
                  width="80"
                  :src="imageSrc"
                ></v-img>
              </v-row>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'Login',
  methods: {
    login() {}
  },
  computed: {
    imageSrc() {
      return this.$store.state.imageSrc.bnet
    }
  }
}
</script>

<style>

</style>
