<template>
  <div class="view">
    <nav>
      <div id="nav">
        <router-link :to="{ name: 'results' }" class="nav-button warn">
          End Study Session</router-link
        >
        <button class="nav-button" v-on:click.stop="shuffleDeck(cards)">
          Shuffle Deck
        </button>
      </div>
    </nav>
    <article>
      <div id="study-container">
        <div class="cards">
          <flashcard-component
            :front="frontOfCard"
            :back="backOfCard"
            class="flashcard-component"
          />
        </div>
        <div class="study-buttons" v-on:click.stop="markIsCorrect($event)">
          <button class="study-button warn">Incorrect</button>
          <span>
            {{ currentCardIndex + 1 }} /
            {{ this.$store.state.deck.cards.length }}
          </span>
          <button
            class="study-button"
            id="mark-correct"
            v-on:click.stop="markIsCorrect($event)"
          >
            Correct
          </button>
        </div>
      </div>
    </article>
    <div class="footer">
      <footer-component />
    </div>
  </div>
</template>

<script>
import FlashcardComponent from "../components/FlashcardComponent.vue";
import FooterComponent from "../components/FooterComponent.vue";
import deckService from "../services/DeckService.js";

export default {
  components: {
    FooterComponent,
    FlashcardComponent,
  },
  name: "study-session",
  data() {
    return {
      deck: this.$store.state.deck,
      cards: [],
      isLoading: true,
      currentCardIndex: 0,
    };
  },
  methods: {
    markIsCorrect(event) {
      if (event.target.tagName === "BUTTON") {
        const maxIndex = this.$store.state.deck.cards.length - 1;

        if (event.target.id === "mark-incorrect") {
          let cardState = {
            index: this.currentCardIndex,
            isCorrect: false,
          };
          this.$store.commit("MARK_CARD_ISCORRECT", cardState);
        } else if (event.target.id === "mark-correct") {
          let cardState = {
            index: this.currentCardIndex,
            isCorrect: true,
          };
          this.$store.commit("MARK_CARD_ISCORRECT", cardState);
        }

        document.querySelector(".flashcard").classList.remove("is-flipped");
        document
          .querySelector(".card__face--back")
          .classList.remove("show-answer");

        if (this.currentCardIndex + 1 > maxIndex) {
          this.$router.push({ name: "results" });
        }

        this.currentCardIndex++;
      }
    },
    retrieveDeck() {
      deckService
        .getDeckById(this.$route.params.id)
        .then((response) => {
          this.$store.commit("SET_DECK", response.data);
          this.deck = response.data;
          this.cards = response.data.cards;
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
    shuffleDeck(cards) {
      for (let i = cards.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        const temp = cards[i];
        cards[i] = cards[j];
        cards[j] = temp;
      }

      this.cards = cards;
      this.currentCardIndex = 1;
      this.currentCardIndex = 0;
    },
  },
  computed: {
    frontOfCard() {
      if (this.cards[this.currentCardIndex] === undefined) {
        return "undefined";
      } else {
        return this.cards[this.currentCardIndex].frontOfCard;
      }
    },
    backOfCard() {
      if (this.cards[this.currentCardIndex] === undefined) {
        return "undefined";
      } else {
        return this.cards[this.currentCardIndex].backOfCard;
      }
    },
  },
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
  display: grid;
  grid-template-areas:
    "nav"
    "body"
    "footer";
}
#nav {
  grid-area: nav;
  width: 100vw;
  margin-top: 11vh;
  padding: 2vh 0;
  background-color: #e9e7e7;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: solid 2px #b4b0ad;
  position: fixed;
  z-index: 2;
}
.nav-button {
  background-color: rgba(0, 148, 255, 255);
  color: #f7fafc;
  text-decoration: none;
  border-radius: 10px;
  padding: 2vh;
  margin: 0 7vh;
  font-size: 2vh;
  font-weight: bold;
  text-align: center;
  cursor: pointer;
  width: 15%;
  box-sizing: border-box;
  border: none;
}
.nav-button:hover {
  background-color: rgb(6, 102, 171);
}
.study-button {
  border: none;
  color: white;
  font-weight: bold;
  background-color: #00a758;
  padding: 1.5vh 5vh;
  border-radius: 10px;
  text-decoration: none;
  font-weight: bold;
}
.study-button:hover {
  background-color: #007a41;
}
.warn {
  background-color: #cc0000;
}
.warn:hover {
  background-color: rgb(168, 2, 2);
}
#study-container {
  grid-area: body;
  min-height: 59vh;
  margin-top: 21vh;
  padding: 4vh 4vh 4vh 4vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 5vh 0vh;
  overflow: auto;
  background-color: #00a7592d;
}
.cards {
  align-self: center;
}
.study-buttons {
  width: 75vh;
  display: flex;
  justify-content: space-between;
}
.flashcard-component {
  height: 45vh;
  width: 75vh;
}
.footer {
  z-index: 3;
  grid-area: footer;
}
</style>
