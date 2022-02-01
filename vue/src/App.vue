<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <router-view />
  </div>
</template>

<style scope>
#app {
  margin: 0px;
}
body {
  background: url("./assets/pexels-fauxels-3184464.jpg");
  background-size: 100vw;
  background-repeat: no-repeat;
  margin: 0px;
}
</style>