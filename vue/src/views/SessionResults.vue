<template>
  <div class="view">
    <nav>
      <router-link
        :to="{
          name: 'study-session',
          params: { deckId: this.$store.state.deck.deckId },
        }"
        class="nav-button"
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
            <div id="number-correct-container">
              <p class="number-correct">{{ numberCorrect }} Correct</p>
              <p class="number-correct">{{ numberIncorrect }} Incorrect</p>
            </div>
            <!-- <div class="pie animate" style="--p: 90; --c: lightgreen">90%</div> -->
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

export default {
  components: {
    FooterComponent,
  },
  name: "results",
  data() {
    return {};
  },
  methods: {},
  computed: {
    numberCorrect() {
      let sumCorrect = this.$store.state.deck.cards.filter(
        (card) => card.isCorrect === true
      ).length;

      return sumCorrect;
    },
    numberIncorrect() {
      let sumIncorrect = this.$store.state.deck.cards.filter(
        (card) => card.isCorrect === false
      ).length;

      return sumIncorrect;
    },
  },
  created() {},
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
#number-correct-container {
  width: 90%;
  display: flex;
  justify-content: space-between;
}
.number-correct {
  font-size: 5vh;
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
