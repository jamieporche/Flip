<template>
  <div class="view">
    <nav>
      <div id="nav">
        <router-link
          :to="{ name: 'study-session', params: { id: deck.id } }"
          class="nav-button"
          :disabled="deck.cards.length === 0"
          :event="deck.cards.length > 0 ? 'click' : ''"
          v-bind:class="{ disabled: deck.cards.length === 0 }"
          >Study Deck</router-link
        >
        <router-link
          :to="{ name: 'add-card', params: { id: deck.id } }"
          v-if="this.$store.state.user.id == deck.userId"
          class="nav-button"
        >
          Add Cards</router-link
        >
        <router-link
          :to="{ name: 'edit-deck', params: { id: deck.id } }"
          v-if="this.$store.state.user.id == deck.userId"
          class="nav-button"
        >
          Edit Details</router-link
        >
      </div>
    </nav>
    <article>
      <div id="deck-container" v-bind:class="{ flex: deck.cards.length > 0 }">
        <h1>{{ deck.deckName }}</h1>
        <div class="cards">
          <flashcard-component
            v-if="deck.cards.length > 0"
            :front="this.$store.state.deck.cards[currentCardIndex].frontOfCard"
            :back="this.$store.state.deck.cards[currentCardIndex].backOfCard"
            class="flashcard-component"
          />
        </div>
        <div class="card-navigation" v-if="deck.cards.length > 0">
          <img
            src="../assets/left.png"
            id="previous-card"
            v-on:click="changeCard($event)"
          />
          <span>
            {{ currentCardIndex + 1 }} /
            {{ this.$store.state.deck.cards.length }}
          </span>
          <img
            src="../assets/right.png"
            id="next-card"
            v-on:click="changeCard($event)"
          />
        </div>
        <div id="deck-info">
          <p id="created-by">
            Created by <span class="bold">{{ deck.username }}</span>
          </p>
          <p id="public">{{ deck.public ? "Public" : "Not Public" }}</p>
          <button
            id="submit"
            v-if="!deck.public && currentUserId == deck.userId"
            v-on:click="submitDeckToPublish"
            :disabled="deck.submitted"
          >
            {{
              deck.submitted
                ? "Deck Pending Admin Appproval"
                : "Submit Deck to be Published"
            }}
          </button>
          <p id="description">{{ deck.description }}</p>
        </div>
        <h3>
          {{
            deck.cards.length > 0
              ? "Cards in this Deck"
              : "No Cards in this Deck"
          }}
        </h3>
        <div class="card-list" v-if="deck.cards.length > 0">
          <div
            v-for="card in cards"
            v-bind:key="card.id"
            class="card-list-item"
          >
            <p class="front">{{ card.frontOfCard }}</p>
            <hr />
            <p class="back">{{ card.backOfCard }}</p>
            <img
              class="remove-card"
              src="../assets/x-icon.png"
              v-if="currentUserId == deck.userId"
              v-on:click.stop="removeCard(card.id)"
            />
          </div>
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
import cardDeckService from "../services/CardDeckService.js";

export default {
  components: {
    FooterComponent,
    FlashcardComponent,
  },
  name: "deck-details",
  data() {
    return {
      deck: {},
      cards: [],
      isLoading: true,
      currentCardIndex: 0,
      currentUserId: this.$store.state.user.id,
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

      document.querySelector(".flashcard").classList.remove("is-flipped");
      document
        .querySelector(".card__face--back")
        .classList.remove("show-answer");
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
    removeCard(id) {
      if (
        window.confirm(
          "Are you sure you want to remove this card from the deck?"
        )
      ) {
        cardDeckService
          .removeCard(this.$route.params.id, id)
          .then((response) => {
            if (response.status === 200) {
              let cardsRemaining = this.cards.filter((card) => {
                return card.id != id;
              });
              this.cards = cardsRemaining;
            }
          });
      }
    },
    submitDeckToPublish() {
      let submittedDeck = this.deck;
      submittedDeck.submitted = true;
      deckService.edit(submittedDeck);
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
  display: grid;
  grid-template-areas:
    "body"
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
  z-index: 1;
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
#deck-container {
  grid-area: body;
  min-height: 73vh;
  margin-top: 21vh;
  padding: 4vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5vh 0vh;
  overflow: auto;
  background-color: #00a7592d;
}
.flex {
  display: flex;
}
#deck-info {
  margin: 0vh auto 0vh 3vh;
  font-size: 2.2vh;
}
.card-navigation {
  width: 20vh;
  display: flex;
  justify-content: space-between;
}
.flashcard-component {
  height: 24vh;
  width: 40vh;
}
.deck {
  width: 60vh;
}
#submit {
  border: none;
  color: white;
  background-color: #0094ff;
  padding: 1.5vh 3vh;
  border-radius: 10px;
  font-size: 2vh;
  text-decoration: none;
}
#submit:hover {
  background-color: #026db9;
}
.deck-buttons {
  display: flex;
  justify-content: space-between;
}
.card-list {
  margin: 0vh auto 0vh 3vh;
  width: 95%;
  display: flex;
  flex-direction: column;
  gap: 2vh;
}
.disabled {
  cursor: not-allowed;
  background-color: rgb(6, 102, 171);
}
.card-list-item {
  border-radius: 20px;
  background-color: white;
  border: solid #b4b0ad 1px;
  display: flex;
  justify-content: center;
}
.front {
  width: 30%;
  padding: 3vh;
}
.back {
  padding: 3vh;
  width: 65%;
}
.remove-card {
  height: 4vh;
  align-self: center;
  margin-right: 2vh;
}
.bold {
  font-weight: bold;
}
.footer {
  z-index: 3;
  grid-area: footer;
}
</style>
