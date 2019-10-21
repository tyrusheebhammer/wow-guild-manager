<template>
  <v-container class="hello" fluid>
    <article v-for="(location, idx) in locations" :key="idx">
      <img :src="location.image" alt="">
      <h1>
        {{ location.name }}
      </h1>
      <v-btn small color="primary" @click="deleteLocation(location.id)">
        Delete
      </v-btn>
    </article>
    <v-row align="center" justify="center">
      <v-col cols="10">
        <v-row>
          <v-col cols="12" md="6">
            <v-text-field label="Location Name" v-model="name"></v-text-field>
          </v-col>
          <v-col cols="12" md="6">
            <v-text-field label="Location Image URL" v-model="image"></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-row justify="end">
              <v-btn small class="primary--text" @click="addLocation(name, image)" type="submit">Add New Dog</v-btn>
            </v-row>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { db } from '../main'
export default {
  name: 'HelloWorld',
  data () {
    return {
      locations: [],
      name: '',
      image: ''
    }
  },
  firestore () {
    return {
      locations: db.collection('locations').orderBy('createdAt')
    }
  },
  methods: {
    addLocation (name, image) {
      const createdAt = new Date()
      db.collection('locations').add({ name, image, createdAt })
    },
    deleteLocation (id) {
      db.collection('locations').doc(id).delete()
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
