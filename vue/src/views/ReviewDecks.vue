<template>
  <div class="view">
    <nav>
      <router-link :to="{ name: 'new-deck' }" class="nav-button"
        >Create New Deck</router-link
      >
      <router-link :to="{ name: 'home' }" class="nav-button">
        View Your Cards</router-link
      >
      <router-link :to="{ name: 'my-decks' }" class="nav-button">
        View Your Decks</router-link
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
                <button
                  class="deck-button"
                  id="reject"
                  v-on:click.stop="review($event, deck.deckId)"
                >
                  Reject
                </button>
                <button
                  class="deck-button"
                  v-on:click.stop="review($event, deck.deckId)"
                >
                  Approve
                </button>
              </div>
            </div>
          </div>
        </article>
      </div>
      <div class="footer">
        <footer-component />
      </div>
    </div>
  </div>
</template>

<script>
import DeckComponent from "../components/DeckComponent.vue";
import FooterComponent from "../components/FooterComponent.vue";
import DeckService from "../services/DeckService";

export default {
  components: {
    FooterComponent,
    DeckComponent,
  },
  name: "review-decks",
  data() {
    return {};
  },
  computed: {
    decks() {
      return this.$store.state.decks.filter((deck) => deck.submitted);
    },
  },
  created() {
    this.$store.dispatch("LOAD_SUBMITTED_DECKS");
  },
  methods: {
    review(event, deckId) {
      let deck = this.decks.find((deck) => deck.deckId === deckId);
      deck.submitted = false;

      if (event.target.id !== "reject") {
        deck.public = true;
      }

      DeckService.submitDeckToPublish(deck);
    },
  },
};
</script>

<style scoped>
.view {
  min-height: 100vh;
}
nav {
  height: 80%;
  width: 20%;
  position: fixed;
  left: 0;
  top: 16.2vh;
  padding-top: 20px;
  padding-bottom: 20px;
  overflow-x: hidden;
  background-color: #e4e0dd;
  border-right: solid #b4b0ad 1px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
}
#deck-container {
  min-height: 60vh;
  width: 76%;
  min-width: 76vw;
  border-radius: 20px;
  background-color: #e4e0dd;
  border: solid #b4b0ad 1px;
  margin: 0vh 3vh 0vh auto;
  padding: 4vh 0vh 4vh 0vh;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-content: space-between;
  gap: 7vh 0vh;
  overflow: auto;
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
  background-color: rgb(49, 92, 49);
  padding: 1.5vh 5vh;
  border-radius: 20px;
  text-decoration: none;
}
#reject {
  background-color: red;
}
.deck-button:hover {
  background-color: rgb(36, 66, 36);
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
  background-color: #a66f5b;
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
  background-color: #8a5d4d;
}
#main {
  margin-top: 19.5vh;
  min-height: 75vh;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  padding-bottom: 3vh;
}
#main-body {
  display: flex;
  flex-direction: column;
}
.footer {
  z-index: 3;
}
</style>
