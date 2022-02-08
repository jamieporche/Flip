<template>
  <div class="header-container">
    <div id="logo">
      <router-link
        v-bind:to="{ name: 'home' }"
        v-if="$store.state.token != ''"
        class="link"
        ><img src="../assets/flip-logo.png" alt="Flip" />
      </router-link>
    </div>
    <div id="links">
      <div id="home">
        <router-link
          v-bind:to="{ name: 'home' }"
          v-if="$store.state.token != ''"
          class="link"
          >Home
        </router-link>
      </div>
      <div id="logout">
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          class="link"
          >Logout
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "header-component",
};
</script>

<style scoped>
.header-container {
  /* height: 8vh; */
  display: flex;
  justify-content: flex-start;
  align-items: center;
  justify-items: center;
  display: grid;
  grid-template-columns: 1fr 5fr;
  grid-template-areas: "logo links";
  border: none;
  border-bottom: solid 2px #b4b0ad;
  background-image: linear-gradient(white, #cad6ff);
  /* padding: 1vh 0vh 1vh 0vh; */
  color: #464443;
}

#links {
  width: 95%;
  grid-area: links;
  display: flex;
  justify-items: space-between;
}

img {
  height: 15vh;
  margin-top: -2vh;
  margin-bottom: -2vh;
  /* margin-left: -4vh;
  margin-right: 2vh; */
}

#logo {
  /*width: auto;*/
  grid-area: logo;
  /* display: flex;
  justify-content: center; */
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
  color: #00a758;
}

/*.router-link-exact-active {*/
/*  border-bottom: solid #f2ab6d 3px;*/
/*}*/
</style>