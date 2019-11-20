<template>
    <router-link :to="to" class="no-underline">
        <v-card
            outlined
            class="mx-auto darkgray pink-outline"
            max-width="344"
        >
            <v-card-text>
                <v-row>
                    <v-col cols="3">
                        <v-img :src="src"></v-img>
                    </v-col>
                    <v-col cols="8" class="mx-2">
                        <v-row>
                            <p class="headline primary--text my-0">
                                {{ title }}
                            </p>
                        </v-row>
                        <v-row>
                            <div class="small-line-height">{{ subtitle }}</div>
                        </v-row>
                    </v-col>
                </v-row>
            </v-card-text>
        </v-card>
    </router-link>
</template>

<script>
export default {
  name: "CategoryCard",
  props: {
    src: String,
    title: String,
    subtitle: String,
    href: String,
    disabled: Boolean,
  },
  computed: {
      to() {
          return !this.disabled ? this.href : '/home' 
      }
  }
};
</script>

<style>
    .small-line-height {
        line-height: 17px !important;
    }

    .pink-outline {
        border: 0.5px solid rgb(244, 124, 255) !important;
        background: #2B2B2B !important;
    }
    
</style>