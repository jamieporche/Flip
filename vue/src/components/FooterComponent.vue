<template>
  <div class="footer-container">
    <h1>Brought to you by StudyBlueGreen</h1>
    <div id="nav">
      <router-link id="about-us" v-bind:to="{ name: 'about-us' }" class="link"
        >About Us</router-link
      >
      <!-- empty router link, we can decide later to make a About us page if we would like -->
    </div>
  </div>
</template>

<script>
export default {
  name: "footer-component",
};
</script>

<style scoped>
.footer-container {
  width: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  border: solid 2px #b4b0ad;
  background-image: linear-gradient(#e4e0dd, white);
  background-image: ("./assets/cool-background(1).png");
  font-size: 1.5vh;
  color: #464443;
}
#about-us {
  font-size: 2.5vh;
  text-decoration: none;
  color: #464443;
}
#about-us:hover {
  color: #f2ab6d;
}
</style>