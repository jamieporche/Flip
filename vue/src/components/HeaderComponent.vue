<template>
  <div id="container">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <div id="logo">
      <p>Logo Here</p>
    </div>
    <div id="app-name">
      <p>FLASH CARD STUDY NAME HERE</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "header-component",
};
</script>
<style>
div#container {
  display: flex;
  justify-content: space-evenly;
  border: solid 2px black;
  background-image: linear-gradient(to bottom, rgb(240, 240, 230), #9c9c9c);
}

#logo {
  font-size: 5vh;
  display: flex;
  justify-content: space-evenly;
}
#app-name {
  display: flex;
}
</style>