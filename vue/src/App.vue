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
    <router-view class="router-view" />
  </div>
</template>

<script>
import HeaderComponent from "./components/HeaderComponent.vue";
export default {
  components: { HeaderComponent },
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
  z-index: 2;
}
.router-view {
  z-index: 1;
}
body {
  background: url("./assets/pexels-fauxels-3184464.jpg");
  background-size: 100vw;
  background-repeat: no-repeat;
  margin: 0px;
}
</style>