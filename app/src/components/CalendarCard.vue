<template>
  <v-container @click="cardWasClicked" fill-height class="pa-0 ma-0">
    <v-row>
      <v-col cols="12" class="py-0">
        <v-card max-height="80" class="mx-auto py-1 ma-1 backgroundGray">
          <v-row>
            <v-col cols="5" class="center" align-center>
              <div class="mx-2 primary--text center">{{ date }}</div>
            </v-col>
            <v-divider vertical class="primary"></v-divider>
            <v-col cols="6" class="pa-0 ma-0">
              <v-row class="pa-0 ma-0">
                <v-col cols="12" class="pa-0 ma-0">
                  <div class="mx-2 lightgray--text">{{ title }}</div>
                </v-col>
              </v-row>
              <v-row class="pa-0 ma-0">
                <v-col cols="12" class="pa-0 ma-0">
                  <div class="mx-2 lightgray--text">{{ subtitle }}</div>
                </v-col>
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
  name: "CalendarCard",
  props: {
    id: Number,
    date: String,
    title: String,
    subtitle: String,
    creator: String
  },
  methods: {
    cardWasClicked() {
      this.$emit("click", this.id);
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

.backgroundGray {
  background: #2b2b2b !important;
}
</style>