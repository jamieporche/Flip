<template>
  <div class="header-container">
    <div id="logo">
      <router-link v-bind:to="{ name: 'home' }"
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
      <div class="dropdown" v-if="$store.state.token != ''">
        <p class="heading">Your Library</p>
        <div class="dropdown-content">
          <router-link v-bind:to="{ name: 'home' }">Cards</router-link>
          <router-link v-bind:to="{ name: 'my-decks' }">Decks</router-link>
        </div>
      </div>
      <div class="dropdown" v-if="$store.state.token != ''">
        <p class="heading">Create</p>
        <div class="dropdown-content">
          <router-link v-bind:to="{ name: 'new-card' }">Cards</router-link>
          <router-link v-bind:to="{ name: 'new-deck' }">Decks</router-link>
        </div>
      </div>
      <router-link
        v-bind:to="{ name: 'public-decks' }"
        v-if="$store.state.token != ''"
        class="link"
        >Public Decks</router-link
      >
      <router-link
        :to="{ name: 'review-decks' }"
        class="nav-button link"
        v-if="this.$store.state.user.authorities[0].name === 'ROLE_ADMIN'"
      >
        Review Submitted Decks</router-link
      >
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
</template>

<script>
export default {
  name: "header-component",
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Bungee+Inline&family=Bungee+Shade&family=Fredoka+One&family=Montserrat&display=swap");
p {
  font-size: 2.5vh;
  color: #464443;
}
.header-container {
  display: grid;
  justify-content: start;
  align-items: center;
  grid-template-columns: 1fr 5fr 1fr;
  grid-template-areas: "logo links logout";
  border: none;
  border-bottom: solid 2px #b4b0ad;
  background-color: white;
  color: #464443;
}
img {
  height: 15vh;
  margin: -2vh 5vh;
}
#logo {
  grid-area: logo;
}
#links {
  font-family: "Fredoka One", Times, sans-serif;
  grid-area: links;
  display: flex;
  justify-items: flex-start;
  align-items: baseline;
  margin-bottom: -3vh;
  gap: 4vh;
}
.link {
  text-decoration: none;
  font-size: 2.5vh;
  color: #464443;
}
.link:hover {
  color: #cc0000;
}
.heading:hover {
  color: #cc0000;
}
.dropdown-content {
  display: none;
  position: absolute;
  background-color: white;
  min-width: 8vh;
  border-radius: 0 0 10px 10px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 4;
}
.dropdown-content a {
  color: #464443;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  z-index: 4;
}
.dropdown-content a:hover {
  background-color: #fce3002c;
  z-index: 4;
}
.dropdown:hover .dropdown-content {
  display: block;
  z-index: 4;
}
#logout {
  font-family: "Fredoka One", Times, sans-serif;
  margin-left: auto;
  margin-bottom: -3vh;
  grid-area: logout;
  margin-right: 3vh;
}
.link.router-link-exact-active {
  border-bottom: solid #fce300 3px;
}
</style>