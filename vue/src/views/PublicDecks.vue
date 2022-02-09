<template>
  <div class="view">
    <!-- experiment below -->
    <div class="header">
      <header-component />
    </div>
    <!-- experiment above -->
    <nav>
      <router-link :to="{ name: 'home' }" class="nav-button">
        View Your Cards</router-link
      >
      <router-link :to="{ name: 'my-decks' }" class="nav-button">
        View Your Decks</router-link
      >
    </nav>
    <div id="main-body">
      <div id="main">
        <article>
          <div id="deck-container">
            <div class="deck" v-for="deck in decks" v-bind:key="deck.id">
              <deck-component
                class="deck-card"
                :name="deck.deckName"
                :size="deck.cards.length"
                :createdBy="deck.userName"
                :id="deck.deckId"
              />
              <div class="deck-buttons">
                <router-link
                  class="deck-button"
                  :disabled="deck.cards.length === 0"
                  :event="deck.cards.length > 0 ? 'click' : ''"
                  v-bind:class="{ disabled: deck.cards.length === 0 }"
                  :to="{
                    name: 'study-session',
                    params: { deckId: deck.deckId },
                  }"
                  >Study</router-link
                >
              </div>
            </div>
          </div>
        </article>
      </div>
    </div>
    <div class="footer">
        <footer-component />
      </div>
  </div>
</template>

<script>
import DeckComponent from "../components/DeckComponent.vue";
import FooterComponent from "../components/FooterComponent.vue";
// experiment below:
import HeaderComponent from "../components/HeaderComponent.vue";
// experiment above

export default {
  components: {
    FooterComponent,
    DeckComponent,
    // experiment below:
    HeaderComponent,
    // experiment above
  },
  name: "public-decks",
  data() {
    return {};
  },
  computed: {
    decks() {
      return this.$store.state.decks;
    },
  },
  created() {
    this.$store.dispatch("LOAD_PUBLIC_DECKS");
  },
  methods: {},
};
</script>

<style scoped>
.view {
  min-height: 100vh;
  display: grid;
  grid-template-columns: 1fr 5fr;
  grid-template-areas:
    "header header"
    "nav body"
    "footer footer";
  column-gap: 10px;
}
.header {
  grid-area: header;
}
nav {
  grid-area: nav;
  /* height: 80%;
  width: 20%;
  position: fixed;
  left: 0;
  top: 16.2vh; */
  padding-top: 60px;
  padding-bottom: 20px;
  overflow-x: hidden;
  /* background-color: #e4e0dd; */
  /* border-right: solid #b4b0ad 1px; */
  background-image: url("../assets/lighter-blue-green-background.png");
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
}
#deck-container {
  grid-area: body;
  min-height: 81vh;
  /* width: 77.5%; */
  /* min-width: 73vw; */
  border-radius: 20px;
  background-image: url("../assets/lighter-blue-green-background.png");
  /*background-color: #e4e0dd;*/
  /*border: solid #b4b0ad 1px;*/
  margin-right: 10px;
  padding: 4vh 4vh 4vh 4vh;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-content: space-between;
  gap: 7vh 3vh;
  overflow: auto;
}
.deck {
  width: 60vh;
}
.deck-card {
  width: 100%;
}
.deck-buttons {
  display: flex;
  justify-content: flex-end;
}
.deck-button {
  border: none;
  color: white;
  background-color: rgba(0, 167, 88, 255);
  padding: 1.5vh 5vh;
  border-radius: 20px;
  text-decoration: none;
}
.deck-button:hover {
  background-color: rgb(2, 131, 70);
}
.disabled {
  cursor: not-allowed;
  background-color: rgb(49, 92, 49);
}
.disabled:hover {
  background-color: rgb(49, 92, 49);
}
.nav-button {
  background-color: rgba(0, 148, 255, 255);
  color: #f7fafc;
  text-decoration: none;
  border-radius: 10px;
  padding: 30px;
  font-size: 18px;
  font-weight: bold;
  margin: 10px;
  text-align: center;
  cursor: pointer;
  width: 60%;
  justify-self: flex-end;
}
.nav-button:hover {
  background-color: rgb(6, 102, 171);
}
/* #main {
  margin-top: 19.5vh;
  min-height: 75vh;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  padding-bottom: 3vh;
} */
#main-body {
  display: flex;
  flex-direction: column;
}
.footer {
  grid-area: footer;
  z-index: 3;
}
</style>
