<template>
  <div class="view">
    <nav>
      <div id="nav">
        <router-link
          :to="{
            name: 'study-session',
            params: { deckId: this.$store.state.deck.deckId },
          }"
          class="nav-button"
          >Study Deck Again</router-link
        >
      </div>
    </nav>
    <div id="results-container">
      <div id="number-correct-container">
        <p class="number-correct">{{ numberCorrect }} Correct</p>
        <p class="number-correct">{{ numberIncorrect }} Incorrect</p>
      </div>
      <div id="pie-chart">
        <div
          class="pie animate"
          v-bind:style="{
            '--percentage': percentCorrect,
            '--color': '#0094ff',
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
    <div class="footer">
      <footer-component />
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
      return Math.ceil(
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
#results-container {
  grid-area: body;
  min-height: 73vh;
  margin-top: 21vh;
  padding: 4vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
  gap: 5vh 0vh;
  overflow: auto;
  background-color: #00a7592d;
}
#number-correct-container {
  width: 90%;
  display: flex;
  justify-content: space-between;
}
.number-correct {
  font-size: 4.5vh;
  margin: 1vh 0;
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
  grid-area: footer;
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
