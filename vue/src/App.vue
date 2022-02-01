<template>
  <div id="app">
    <header-component class="header" />
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <router-view />
    <footer-component class="footer"/>
  </div>
</template>

<script>
import FooterComponent from "./components/FooterComponent.vue";
import HeaderComponent from "./components/HeaderComponent.vue";
export default {
  components: { HeaderComponent, FooterComponent },
};
</script>

<style>
#app {
  margin: 0px;
}
.header {
  position: fixed;
  top: 0px;
  width: 100vw;
}

.footer {
  position: fixed;
  bottom: 0px;
  width: 100vw;
}
body {
  background: url("./assets/pexels-fauxels-3184464.jpg");
  background-size: 100vw;
  background-repeat: no-repeat;
  margin: 0px;
}
</style>