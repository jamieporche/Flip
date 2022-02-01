<template>
  <div id="container">
      <h1>Brought to you by Team Name Here</h1>
      <div id="nav">
      <router-link id="about-us" v-bind:to="{ }">About Us</router-link>   
      <!-- empty router link, we can decide later to make a About us page if we would like -->
      </div>
  </div>
</template>

<script>
export default {
    name: 'footer-component'
}
</script>

<style scope>


div#container {
  display: flex;
  justify-content: space-around;
  align-content: center;
  border: solid 2px black;
  /* was trying to change different things to get background to show up */
  background-image: ("./assets/cool-background(1).png");
  /* background-image: linear-gradient(to right, rgb(99, 99, 96), #000000); */
  /* have to work on colors */
  font-size: 1.5vh;
}
#about-us {
    font-size: 3vh;
}



</style>