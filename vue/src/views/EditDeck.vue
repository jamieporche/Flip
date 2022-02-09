<template>
  <div class="view">
    <nav>
      <router-link :to="{ name: 'home' }" class="nav-button"
        >View Your Cards</router-link
      >
      <router-link :to="{ name: 'my-decks' }" class="nav-button">
        View Your Decks</router-link
      >
      <router-link :to="{ name: 'public-decks' }" class="nav-button">
        View Public Decks</router-link
      >
    </nav>
    <article>
      <div id="form-background">
        <div id="form-container">
          <form>
            <h2>Edit Deck</h2>
            <label for="deck-name">Deck Name</label>
            <input
              type="text"
              id="deck-name"
              name="deck-name"
              placeholder="Deck Name"
              class="input"
              v-model="deck.deckName"
              required
            />
            <div id="buttons">
              <button
                id="delete-button"
                v-on:click.prevent="deleteDeck(deck.deckId)"
                class="deck-button"
              >
                Delete
              </button>
              <button id="save" v-on:click.prevent="editDeck">Save</button>
            </div>
          </form>
        </div>
      </div>
    </article>
    <div class="footer">
      <footer-component />
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
  name: "edit-deck",
  data() {
    return {
      deck: {},
    };
  },
  methods: {
    editDeck() {
      const editedDeck = this.deck;
      this.$store.dispatch("EDIT_DECK", editedDeck);
    },
    deleteDeck(deckId) {
      if (window.confirm("Are you sure you want to delete?")) {
        this.$store.dispatch("DELETE_DECK", deckId);
        this.$router.push({ name: "my-decks" });
      }
    },
    retrieveDeck() {
      deckService
        .getDeckById(this.$route.params.id)
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
.view {
  display: flex;
  flex-direction: column;
}
h2 {
  margin: 0vh 0vh 4vh 0vh;
  text-align: center;
  font-size: 4vh;
  color: #464443;
}
label {
  padding-left: 1.5vh;
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
#form-container {
  width: 75.8%;
  border-radius: 20px;
  background-color: #e4e0dd;
  border: solid #b4b0ad 1px;
  margin: 0vh 3vh 3vh auto;
}
#form-background {
  min-height: 70vh;
  margin: 20vh 0px 0px 0px;
}
form {
  display: flex;
  flex-direction: column;
  padding: 10vh;
}
button {
  border: none;
  color: white;
  background-color: rgb(49, 92, 49);
  padding: 1.5vh 5vh;
  border-radius: 20px;
}
#buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 4vh;
}
#delete-button {
  display: flex;
  justify-content: flex-start;
  gap: 4vh;
  background-color: red;
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
.input {
  width: 100%;
  right: 0;
  box-sizing: border-box;
  font-family: "Roboto", sans-serif;
  border: 2px solid #ccc;
  border-radius: 10px;
  font-size: 16px;
  background-color: white;
  padding: 12px 20px 12px 12px;
  margin: 1vh 3vh 2vh 0vh;
}
::placeholder {
  color: grey;
}
.footer {
  z-index: 3;
}
</style>
