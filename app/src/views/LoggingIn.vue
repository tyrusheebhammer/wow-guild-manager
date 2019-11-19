<template>
  <v-container class="text-center">
    <div class="text-center">
      <v-progress-circular
        :size="50"
        color="primary"
        indeterminate
      ></v-progress-circular>
  </div>
  <div class="text-center">
    cooking up something saucy here boi <br> {{token}}
  </div>
  </v-container>
</template>

<script>
import { mapState } from 'vuex'
export default {
/* eslint no-console: 0 */  // --> OFF
    name: 'LoggingIn',
    computed: {
      ...mapState(['token', 'guilds'])
    },
    created() {
      this.$store.subscribe((mutation) => {
        if(mutation.type === 'updateGuilds') {
          this.$router.push({name: 'GuildSelect'})
        }
      })
      this.$store.dispatch('appSignIn')
    },
}
</script>

<style>

</style>