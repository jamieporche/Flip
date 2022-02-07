<template>
  <div class="header-container">
    <div id="logo">
      <img src="../assets/flip-logo.png"/>
    </div>
    <div id="app-name">
      <p>StudyBlueGreen</p>
    </div>
    <div id="home">
      <router-link
        v-bind:to="{ name: 'home' }"
        v-if="$store.state.token != ''"
        class="link"
        >Home</router-link
      >
    </div>
    <div id="logout">
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        class="link"
        >Logout</router-link
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "header-component",
};
</script>

<style scoped>
p {
  margin: 0;
}
.header-container {
  height: 13vh;
  display: flex;
  justify-content: flex-start;
  align-items: flex-end;
  border: solid 2px #b4b0ad;
  background-image: linear-gradient(white, #e4e0dd);
  padding: 0vh 5vh 3vh 5vh;
  color: #464443;
}
#logo {
  font-size: 3vh;
  color: #464443;
}
#app-name {
  padding: 0vh 5vh;
  font-size: 5vh;
  color: #464443;
}
#logout {
  margin-left: auto;
}
.link {
  text-decoration: none;
  font-size: 2.5vh;
  color: #464443;
}
.link:hover {
  color: #f2ab6d;
}
.router-link-exact-active {
  border-bottom: solid #f2ab6d 3px;
}
</style>