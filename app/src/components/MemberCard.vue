<template>
  <v-container>
      <v-card min-height="100">
        <v-row>
            <v-col cols="3">
                <v-img src="https://render-us.worldofwarcraft.com/character/stormrage/231/204308711-avatar.jpg" lazy-src="@/assets/logo.svg">

                </v-img>
            </v-col>
            <v-col cols="4">{{ name }}</v-col>
            <v-col cols="4"></v-col>
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
        rank: Number //optional for now
    }
}
</script>

<style>

</style>