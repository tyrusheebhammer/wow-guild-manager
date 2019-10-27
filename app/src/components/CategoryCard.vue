<template>
    <router-link :to="href" class="no-underline">
        <v-container fill-height class="py-2" >
            <v-card min-height="110" outlined class="pink-outline">
                <v-row class="py-3" align-center>
                    <v-col cols="4" class="py-auto">
                        <v-img contain :src="imgSrc" class="px-3"></v-img>
                    </v-col>
                    <v-col cols="8" class="m-0 pa-0">
                        <v-row class="m-0 pa-0">
                        <v-col cols="9" class="m-0 pa-0 stroke">
                            <v-card-title class="headline mx-1 pa-0 primary--text ">{{ title }}</v-card-title >
                        </v-col>
                        </v-row>
                        <v-row class="m-0 pa-0">
                        <v-col cols="9" class="m-0 pa-0">
                            <v-card-subtitle class="mx-1 pa-0 small-line-height">{{ subtitle }}</v-card-subtitle>
                        </v-col>
                        
                        </v-row>
                    </v-col>
                </v-row>
            </v-card>
        </v-container>
    </router-link>
</template>

<script>
export default {
  name: "CategoryCard",
  props: {
    "img-src": String,
    title: String,
    subtitle: String,
    href: String
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