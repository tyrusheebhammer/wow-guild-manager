<template>
  <v-container fluid>
    <v-row>
      <v-col cols="12" sm="6"
        v-for="guild in guilds"
        :key="guild.compound"
      >
        <v-card
          class="mx-auto darkgray py-0"
          max-width="344"
          @click="guildWasSelected(guild)"
        >
          <v-card-text class="mx-2 py-2">
            <v-row>
              <p class="headline primary--text mb-1">
                {{guild.name}}
              </p>
            </v-row>
            <v-row>
              <p class="small-line-height mb-1">
                {{guild.realm}}
              </p>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
  /* eslint no-console: 0 */ // --> OFF
  /* eslint no-unused-vars: 0 */ // --> OFF
import { mapState } from 'vuex';
export default {
  name: 'GuildSelect',
  computed: mapState(['guilds']),
  methods: {
    guildWasSelected(guild) {
      this.$store.commit('updateSelectedGuild', {guild})
      this.$router.push('/home')
    }
  },
  created() {
  }
}
</script>

<style>
  .small-line-height {
        line-height: 17px !important;
    }
</style>
