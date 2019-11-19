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
/* eslint no-console: 0 */  // --> OFF

export default {
  
  name: 'Login',
  methods: {
    login() {
      console.log(this.$store.state.playerAuthRequestUri)
      window.location.href = '//' + this.$store.state.playerAuthRequestUri, true;
    }
  },
  computed: {
    imageSrc() {
      return this.$store.state.imageSrc.bnet
    },
    clientId() {
      return this.$store.state.clientId
    }
  },
  created() {
    if(this.$store.getters.userId) this.$router.push('/guildSelect')
    let query = this.$route.query
    if(
      query.access_token && 
      query.token_type && 
      query.expires_in
    ) {
      console.log('just got back from auth yo')
      this.$store.commit('saveToken', {
        access_token: query.access_token,
        token_type: query.token_type,
        expires_in: query.expires_in
      })
      this.$router.push({name: 'LoggingIn'})
    }
  }
}
</script>

<style>

</style>
