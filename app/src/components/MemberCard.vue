<template>
  <v-container>
      <v-card min-height="100" class="darkgray">
        <v-row>
            <v-col class="ml-2 my-0" cols="3">
                <v-img src="https://render-us.worldofwarcraft.com/character/stormrage/231/204308711-avatar.jpg" lazy-src="@/assets/logo.svg">

                </v-img>
            </v-col>
            <v-col cols="4">
                <v-row >
                    <v-col class="py-0 my-0" cols="12" :class="formatCharClass(charClass)">
                        {{ name }}
                    </v-col>
                </v-row>
                <v-row>
                    <v-col class="py-0 my-0 lightgray--text caption" cols="12">
                        Rank: {{ rank }}
                    </v-col>
                </v-row>
                <v-row>
                    <v-col class="py-0 my-0 lightgray--text caption" cols="12">
                        Level: {{ level }}
                    </v-col>
                </v-row>
                
            </v-col>
            <v-col class="py-2" cols="4">
                <v-row>
                    <v-col cols="4" class="py-0 my-0 px-2  lightgray--text">
                        <v-img src="https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/Wow.svg?alt=media&token=7351283b-9800-4ac1-a97e-fec863bb2ed7"></v-img>   
                    </v-col>
                    <v-col cols="4" class="py-0 my-0 px-2  lightgray--text">
                        <v-img src="https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/raidbots1.svg?alt=media&token=7fa7e79c-9ff5-4ec4-8c70-6b793b0e5bb9"></v-img>
                    </v-col>
                    <v-col cols="4" class="py-0 my-0 px-2 lightgray--text">
                        <v-img src="https://firebasestorage.googleapis.com/v0/b/wow-guild-manager.appspot.com/o/raiderio1.svg?alt=media&token=baf94bd0-9615-414f-943d-d5432ef3d3ea"></v-img>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col class="py-0 my-0 caption text-right lightgray--text">
                        raiderio: {{ raiderIOScore }}
                    </v-col>
                </v-row>
                <v-row>
                    <v-col class="py-0 my-0 caption text-right lightgray--text">
                        iLevel: <span class="blue--text">{{ ilevel }}</span>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
      </v-card>
  </v-container>
</template>

<script>
export default {
    name: 'MemberCard',
    props: {
        name: String,
        raiderIOScore: Number,
        role: String,
        simDps: Number,
        level: Number,
        ilevel: Number,
        charClass: String,
        rank: Number //optional for now
    },
    methods: {
        formatCharClass(str) {
            return str.toLowerCase() + '--text'
        }
    }
}
</script>

<style>
</style>