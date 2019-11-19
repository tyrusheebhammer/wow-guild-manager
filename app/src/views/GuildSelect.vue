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
import { mapState } from 'vuex';
export default {
  name: 'GuildSelect',
  created: function() {
    this.$store.commit('updatePageName', "Guild Select")
  },
  computed: mapState(['guilds']),
}
</script>

<style>
  .small-line-height {
        line-height: 17px !important;
    }
</style>
