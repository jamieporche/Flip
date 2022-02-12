<template>
  <div class="view">
    <div id="main">
      <article>
        <div id="deck-container">
          <div id="card-list-empty" v-if="decks.length == 0">
            <p class="no-decks">There are no decks to review</p>
          </div>
          <div class="deck" v-for="deck in decks" v-bind:key="deck.id">
            <deck-component
              class="deck-card"
              :name="deck.deckName"
              :size="deck.cards.length"
              :createdBy="deck.username"
              :id="deck.id"
            />
            <div class="deck-buttons">
              <button
                class="deck-button"
                id="reject"
                v-on:click.stop="review($event, deck.id)"
              >
                Reject
              </button>
              <button
                class="deck-button"
                v-on:click.stop="review($event, deck.id)"
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
    let params = { isSubmitted: true, isPublic: false };
    this.$store.dispatch("LOAD_DECKS", params);
  },
  methods: {
    review(event, id) {
      let deck = this.decks.find((deck) => deck.id === id);
      deck.submitted = false;

      if (event.target.id !== "reject") {
        deck.public = true;
      }

      DeckService.edit(deck);
    },
  },
};
</script>

<style scoped>
.view {
  min-height: 100vh;
  display: grid;
  grid-template-areas:
    "body"
    "footer";
}
p {
  color: #464443;
}
#deck-container {
  min-height: 57vh;
  background-color: #00a7592d;
  padding: 10vh 0vh;
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
  background-color: #0094ff;
  padding: 1.5vh 5vh;
  border-radius: 10px;
  text-decoration: none;
  font-weight: bold;
}
#reject {
  background-color: #cc0000;
}
#reject:hover {
  background-color: rgb(153, 3, 3);
}
.deck-button:hover {
  background-color: #026db9;
}
.disabled {
  cursor: not-allowed;
  background-color: #026db9;
}
.disabled:hover {
  background-color: #026db9;
}
.deck-buttons {
  display: flex;
  justify-content: space-between;
}
#main {
  grid-area: body;
  margin-top: 11vh;
  min-height: 75vh;
  display: flex;
  flex-direction: column;
  align-items: stretch;
}
.no-decks {
  font-size: 7vh;
  text-align: center;
}
.footer {
  z-index: 3;
  grid-area: footer;
}
</style>
