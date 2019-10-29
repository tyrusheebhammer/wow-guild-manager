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
            <v-col cols="4">
                <v-row>
                    <v-col cols="4" class="py-0 my-0 lightgray--text"><v-icon>mdi-account-box</v-icon></v-col>
                    <v-col cols="4" class="py-0 my-0 lightgray--text"><v-icon>mdi-account-box</v-icon></v-col>
                    <v-col cols="4" class="py-0 my-0 lightgray--text"><v-icon>mdi-account-box</v-icon></v-col>
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