<template>
  <v-container @click="cardWasClicked" fill-height class="pa-0 ma-0">
    <v-row>
      <v-col cols="12" class="py-0">
        <v-card max-height="80" class="mx-auto py-1 ma-1 darkgray">
          <v-row>
            <v-col cols="5" class="center py-0" align-center>
              <div class="mx-2 primary--text center">{{ day }}</div>
            </v-col>
            <v-divider vertical class="primary"></v-divider>
            <v-col class="py-0" cols="6">
              <div class="mx-2 lightgray--text">{{ subtitle }}</div>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "AnnouncementCard",
  props: {
    id: Number,
    date: String,
    day: String,
    title: String,
    subtitle: String,
    creator: String
  },
  methods: {
    cardWasClicked() {
      this.$emit("click", this.id)
    }
  }
};
</script>

<style>
.center {
  text-align: center;
  vertical-align: center;
}

.pink-outline {
  border: 0.5px solid rgb(244, 124, 255) !important;
  background: #2b2b2b !important;
}
</style>