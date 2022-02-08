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
        <div id="deck-container">
          <div id="number-correct-container">
            <p class="number-correct">{{ numberCorrect }} Correct</p>
            <p class="number-correct">{{ numberIncorrect }} Incorrect</p>
          </div>
          <div id="pie-chart">
            <div
              class="pie animate"
              v-bind:style="{
                '--percentage': percentCorrect,
                '--color': '#00a758',
              }"
            >
              {{ percentCorrect }}%
            </div>
          </div>
          <div id="card-totals">
            <p id="total-cards">{{ totalCards }} Total</p>
            <p id="total-unstudied">{{ unstudiedCards }} Unstudied</p>
          </div>
        </div>
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
    percentCorrect() {
      return Math.floor(
        (this.numberCorrect / this.$store.state.deck.cards.length) * 100
      );
    },
    totalCards() {
      return this.$store.state.deck.cards.length;
    },
    unstudiedCards() {
      return (
        this.$store.state.deck.cards.length -
        (this.numberCorrect + this.numberIncorrect)
      );
    },
  },
  created() {},
};
</script>

<style scoped>
p {
  color: #464443;
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
#deck-container {
  min-height: 67vh;
  width: 76%;
  min-width: 76vw;
  border-radius: 20px;
  background-color: #e4e0dd;
  border: solid #b4b0ad 1px;
  margin: 0vh 3vh 0vh auto;
  padding: 4vh 0vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2vh 0vh;
}
#number-correct-container {
  width: 90%;
  display: flex;
  justify-content: space-between;
}
.number-correct {
  font-size: 4.5vh;
}
#card-totals {
  margin: 0vh 5vh 0vh auto;
  text-align: right;
}
#total-cards {
  font-size: 3vh;
}
#total-unstudied {
  font-size: 2.5vh;
}
.footer {
  z-index: 3;
}
@property --percentage {
  syntax: "<number>";
  inherits: true;
  initial-value: 0;
}
.pie {
  --percentage: 20;
  --border-thickness: 70px;
  --width: 300px;

  width: var(--width);
  aspect-ratio: 1;
  position: relative;
  display: inline-grid;
  margin: 5px;
  place-content: center;
  font-size: 35px;
  font-weight: bold;
  color: #464443;
}
.pie:before,
.pie:after {
  content: "";
  position: absolute;
  border-radius: 50%;
}
.pie:before {
  inset: 0;
  background: radial-gradient(farthest-side, var(--color) 98%, #0000)
      top/var(--border-thickness) var(--border-thickness) no-repeat,
    conic-gradient(var(--color) calc(var(--percentage) * 1%), #0000 0);
  -webkit-mask: radial-gradient(
    farthest-side,
    #0000 calc(99% - var(--border-thickness)),
    #000 calc(100% - var(--border-thickness))
  );
  mask: radial-gradient(
    farthest-side,
    #0000 calc(99% - var(--border-thickness)),
    #000 calc(100% - var(--border-thickness))
  );
}
.pie:after {
  inset: calc(50% - var(--border-thickness) / 2);
  background: var(--color);
  transform: rotate(calc(var(--percentage) * 3.6deg))
    translateY(calc(50% - var(--width) / 2));
}
.animate {
  animation: percentage 1s 0.5s both;
}
.no-round:before {
  background-size: 0 0, auto;
}
.no-round:after {
  content: none;
}
@keyframes percentage {
  from {
    --percentage: 0;
  }
}
</style>
