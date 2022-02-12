<template>
  <div class="view">
    <article>
      <div id="form-background">
        <div id="form-container">
          <form>
            <h2>Create a new Deck</h2>
            <label for="frontOfCard">Deck Name</label>
            <input
              type="text"
              id="deckName"
              name="deckName"
              placeholder="Deck Name"
              class="input"
              v-model="newDeck.deckName"
              required
            />
            <div id="save-buttons">
              <button id="save" v-on:click.prevent="createDeck">Save</button>
              <button id="save-and-new" v-on:click.prevent="createDeckAndReset">
                Save and New
              </button>
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

export default {
  components: {
    FooterComponent,
  },
  name: "new-deck",
  data() {
    return {
      newDeck: {
        deckName: "",
        userId: this.$store.state.user.id,
      },
    };
  },
  methods: {
    createDeck() {
      const newDeck = {
        ...this.newDeck,
      };

      this.$store.dispatch("CREATE_NEW_DECK", newDeck);
    },
    createDeckAndReset() {
      const newDeck = {
        ...this.newDeck,
      };

      this.$store.dispatch("CREATE_NEW_DECK_SAVE_AND_NEW", newDeck);
      this.newDeck = {
        deckName: "",
        userId: this.$store.state.user.id,
      };
    },
  },
  computed: {},
  created() {},
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
#form-background {
  margin-top: 11vh;
}
#form-container {
  grid-area: body;
  display: flex;
  justify-content: center;
  min-height: 77vh;
  background-color: #00a7592d;
}
form {
  width: 50%;
  display: flex;
  flex-direction: column;
  padding: 10vh 10vh;
}
button {
  border: none;
  color: white;
  background-color: #0094ff;
  padding: 1.5vh 5vh;
  border-radius: 10px;
  font-weight: bold;
}
button:hover {
  background-color: #026db9;
}
#save-buttons {
  display: flex;
  justify-content: flex-end;
  gap: 4vh;
  margin-top: 4vh;
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
