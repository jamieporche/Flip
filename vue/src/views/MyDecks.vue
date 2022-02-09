<template>
  <div class="view">
    <nav>
      <router-link :to="{ name: 'new-deck' }" class="nav-button"
        >Create New Deck</router-link
      >
      <router-link :to="{ name: 'home' }" class="nav-button">
        View Your Cards</router-link
      >
      <router-link :to="{ name: 'public-decks' }" class="nav-button">
        View Public Decks</router-link
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
                  :to="{ name: 'edit-deck', params: { id: deck.deckId } }"
                  >Edit</router-link
                >
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

export default {
  components: {
    FooterComponent,
    DeckComponent,
  },
  name: "my-decks",
  data() {
    return {};
  },
  computed: {
    decks() {
      return this.$store.state.decks;
    },
  },
  created() {
    this.$store.dispatch("LOAD_USERS_DECKS", this.$store.state.user.id);
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
    "nav body"
    "nav body"
    "footer footer";
  column-gap: 10px;
}
nav {
  grid-area: nav;
  /* height: 80%;
  width: 20%;
  position: fixed;
  left: 0;
  top: 16.2vh; */
  padding-top: 20vh;
  padding-bottom: 20px;
  overflow-x: hidden;
  /* background-color: #e4e0dd;
  border-right: solid #b4b0ad 1px; */
  background-image: url("../assets/lighter-blue-green-background.png");
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
}
#deck-container {
  grid-area: body;
  min-height: 81vh;
  /* width: 76%;
  min-width: 76vw; */
  border-radius: 20px;
  /* background-color: #e4e0dd;
  border: solid #b4b0ad 1px; */
  background-image: url("../assets/lighter-blue-green-background.png");
  /* margin: 0vh 3vh 0vh auto; */
  padding: 10vh 0vh 4vh 0vh;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-content: space-between;
  gap: 7vh 0vh;
  overflow: auto;
  margin: -8vh 10px 0vh auto;
}
.deck {
  width: 60vh;
}
.deck-card {
  width: 100%;
}
.deck-button {
  border: none;
  color: white;
  text-decoration: none;
  background-color: rgba(0, 167, 88, 255);
  padding: 1.5vh 5vh;
  border-radius: 10px;
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
.deck-buttons {
  display: flex;
  justify-content: space-between;
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
#main {
  margin-top: 19.5vh;
  min-height: 75vh;
  /* display: flex;
  flex-direction: column; */
  align-items: flex-end;
  /* padding-bottom: 3vh; */
}
#main-body {
  grid-area: "body";
  /* display: flex;
  flex-direction: column; */
}
.footer {
  grid-area: footer;
  z-index: 3;
}
</style>
