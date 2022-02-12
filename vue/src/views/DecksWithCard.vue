<template>
  <div class="view">
    <div id="main">
      <article>
        <div id="deck-container">
          <div id="card-list-empty" v-if="filteredDecks.length == 0">
            <p class="no-decks">This card doesn't belong to any decks</p>
          </div>
          <div class="deck" v-for="deck in filteredDecks" v-bind:key="deck.id">
            <deck-component
              class="deck-card"
              :name="deck.deckName"
              :size="deck.cards.length"
              :createdBy="deck.username"
              :id="deck.id"
            />
            <div class="deck-buttons">
              <router-link
                class="deck-button"
                :to="{ name: 'edit-deck', params: { id: deck.id } }"
                >Edit</router-link
              >
              <router-link
                class="deck-button"
                :to="{
                  name: 'study-session',
                  params: { id: deck.id },
                }"
                >Study</router-link
              >
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
import cardDeckService from "../services/CardDeckService.js";

export default {
  components: {
    FooterComponent,
    DeckComponent,
  },
  name: "decks-with-card",
  data() {
    return {
      cardDecks: [],
    };
  },
  computed: {
    filteredDecks() {
      let deckIds = this.cardDecks.map((cardDeck) => cardDeck.deckId);

      return this.$store.state.decks.filter((deck) => {
        return deckIds.includes(deck.id);
      });
    },
  },
  created() {
    this.$store.dispatch("LOAD_USERS_DECKS", this.$store.state.user.id);
    cardDeckService
      .getCardDecksByCardId(this.$route.params.id)
      .then((response) => {
        this.cardDecks = response.data;
      });
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
#main {
  margin-top: 11vh;
  min-height: 69vh;
  align-items: flex-end;
}
#deck-container {
  min-height: 63vh;
  background-color: #00a7592d;
  padding: 10vh 4vh 4vh 4vh;
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
}
.deck-button:hover {
  background-color: #026db9;
}
.deck-buttons {
  display: flex;
  justify-content: space-between;
}
.no-decks {
  font-size: 7vh;
  text-align: center;
  color: #464443;
}
.footer {
  grid-area: footer;
  z-index: 3;
}
</style>
