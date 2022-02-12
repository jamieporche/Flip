<template>
  <div class="view">
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
                class="delete-button"
                v-on:click.prevent="deleteDeck(deck.id)"
              >
                Delete
              </button>
              <button class="save" v-on:click.prevent="editDeck">Save</button>
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
    deleteDeck(id) {
      if (window.confirm("Are you sure you want to delete?")) {
        this.$store.dispatch("DELETE_DECK", id);
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
  min-height: 100vh;
  display: grid;
  grid-template-areas:
    "body"
    "footer";
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
#form-container {
  grid-area: body;
  display: flex;
  justify-content: center;
  min-height: 69vh;
  background-color: #00a7592d;
  padding: 4vh;
}
#form-background {
  margin-top: 11vh;
}
form {
  width: 50%;
  display: flex;
  flex-direction: column;
  padding: 5vh 10vh;
}
button {
  border: none;
  color: white;
  background-color: #0094ff;
  padding: 1.5vh 5vh;
  border-radius: 10px;
  text-decoration: none;
  font-weight: bold;
  cursor: pointer;
}
.save-button:hover {
  background-color: #026db9;
}
#buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 4vh;
}
.delete-button {
  display: flex;
  justify-content: flex-start;
  gap: 4vh;
  background-color: #cc0000;
  cursor: pointer;
}
.delete-button:hover {
  background-color: #9e0404;
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
.input:focus {
  outline: none;
  border-color: rgba(0, 148, 255, 255);
}
::placeholder {
  color: grey;
}
.footer {
  grid-area: footer;
  z-index: 3;
}
</style>
