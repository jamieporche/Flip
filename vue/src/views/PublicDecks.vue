<template>
  <div class="view">
    <div id="main">
      <article>
        <div id="deck-container">
          <div class="deck" v-for="deck in decks" v-bind:key="deck.id">
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
                :disabled="deck.cards.length === 0"
                :event="deck.cards.length > 0 ? 'click' : ''"
                v-bind:class="{ disabled: deck.cards.length === 0 }"
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

export default {
  components: {
    FooterComponent,
    DeckComponent,
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
    let params = { isSubmitted: false, isPublic: true };
    this.$store.dispatch("LOAD_DECKS", params);
  },
  methods: {},
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
}
#deck-container {
  grid-area: body;
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
.deck-buttons {
  display: flex;
  justify-content: flex-end;
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
.footer {
  grid-area: footer;
  z-index: 3;
}
</style>
