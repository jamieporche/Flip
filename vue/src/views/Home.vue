<template>
  <div class="home">
    <nav>
      <router-link :to="{ name: 'new-card' }" class="nav-button"
        >Create New Card</router-link
      >
      <router-link :to="{ name: 'my-decks' }" class="nav-button">
        View Your Decks</router-link
      >
    </nav>
    <div id="main-body">
      <div id="main">
        <input
          type="text"
          name="search"
          v-model="filter"
          placeholder="Search cards by tag"
        />
        <article>
          <div id="card-container">
            <div
              id="card"
              v-for="card in this.$store.state.cards"
              v-bind:key="card.id"
            >
              <flashcard-component
                :front="card.frontOfCard"
                :back="card.backOfCard"
                class="flashcard-component"
              />
              <p>tags: {{ card.tags }}</p>
              <div id="card-buttons">
                <button>Edit</button>
                <button>Add to Deck</button>
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
import FlashcardComponent from "../components/FlashcardComponent.vue";
import FooterComponent from "../components/FooterComponent.vue";

export default {
  components: {
    FlashcardComponent,
    FooterComponent,
  },
  name: "home",
  data() {
    return {
      cards: [],
      filter: "",
    };
  },
  computed: {
    filteredCards() {
      if (this.filter !== "") {
        return this.$store.state.cards.filter((card) =>
          card.tags.toLowerCase().includes(this.filter.toLowerCase)
        );
      } else {
        return this.$store.state.cards;
      }
    },
  },
  created() {
    this.$store.dispatch("LOAD_USERS_CARDS", this.$store.state.user.id);
  },
};
</script>

<style scoped>
.home {
  min-height: 100vh;
  /* display: block; */
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
#card-container {
  width: 77%;
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
button {
  border: none;
  color: white;
  background-color: rgb(49, 92, 49);
  padding: 1.5vh 5vh;
  border-radius: 20px;
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
#main {
  top: 18vh;
  margin-top: 18vh;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  padding-bottom: 3vh;
}
#main-body {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}
input[type="text"] {
  width: 30%;
  right: 0;
  box-sizing: border-box;
  border: 2px solid #ccc;
  border-radius: 20px;
  font-size: 16px;
  background-color: white;
  background-image: url("../assets/icons8-search-32.png");
  background-repeat: no-repeat;
  padding: 12px 20px 12px 40px;
  margin: 1vh 3vh 2vh 0vh;
}
.flashcard-component {
  height: 24vh;
  width: 40vh;
}
#card-buttons {
  display: flex;
  justify-content: space-between;
}
.footer {
  z-index: 3;
}
</style>
