<template>
  <div class="view">
    <nav>
      <router-link :to="{ name: 'new-card' }" class="nav-button"
        >Study Deck Again</router-link
      >
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
            <h2>Results</h2>
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
import FooterComponent from "../components/FooterComponent.vue";
import deckService from "../services/DeckService.js";

export default {
  components: {
    FooterComponent,
  },
  name: "results",
  data() {
    return {
      deck: this.$store.state.deck,
      isLoading: true,
      currentCardIndex: 0,
    };
  },
  methods: {
    changeCard(event) {
      const maxIndex = this.$store.state.deck.cards.length - 1;

      if (event.target.id === "previous-card") {
        if (this.currentCardIndex > 0) {
          this.currentCardIndex--;
        } else {
          this.currentCardIndex = maxIndex;
        }
      } else if (event.target.id === "next-card") {
        if (this.currentCardIndex < maxIndex) {
          this.currentCardIndex++;
        } else {
          this.currentCardIndex = 0;
        }
      }
    },
    retrieveDeck() {
      deckService
        .getDeckById(this.$route.params.deckId)
        .then((response) => {
          this.$store.commit("SET_DECK", response.data);
          this.deck = response.data;
          this.isLoading = false;
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert(
              "Deck not available. This deck may have been deleted or you have entered an invalid deck ID."
            );
            this.$router.push("/");
          }
        });
    },
  },
  computed: {},
  created() {
    this.retrieveDeck();
  },
};
</script>

<style scoped>
h1 {
  color: #464443;
  margin: 0vh auto 0vh 3vh;
  font-size: 4vh;
}
h3 {
  margin: 0vh auto 0vh 3vh;
  color: #464443;
  font-size: 3vh;
}
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
  min-height: 67vh;
  width: 76%;
  min-width: 76vw;
  border-radius: 20px;
  background-color: #e4e0dd;
  border: solid #b4b0ad 1px;
  margin: 0vh 3vh 0vh auto;
  padding: 4vh 0vh 4vh 0vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5vh 0vh;
}
.cards {
  align-self: center;
}
.card-navigation {
  width: 20vh;
  display: flex;
  justify-content: space-between;
}
.flashcard-component {
  height: 45vh;
  width: 75vh;
}
.deck {
  width: 60vh;
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
