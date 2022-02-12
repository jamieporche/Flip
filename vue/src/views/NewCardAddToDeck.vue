<template>
  <div class="view">
    <article>
      <div id="form-background">
        <div id="form-container">
          <form>
            <h2>Create a new Card</h2>
            <label for="frontOfCard">Question</label>
            <input
              type="text"
              id="frontOfCard"
              name="frontOfCard"
              placeholder="Question"
              class="input"
              v-model="newCard.frontOfCard"
              required
            />
            <label for="backOfCard">Answer</label>
            <textarea
              id="backOfCard"
              name="backOfCard"
              rows="5"
              cols="50"
              class="input"
              v-model="newCard.backOfCard"
              placeholder="Answer"
              required
            ></textarea>
            <label for="tags">Tags</label>
            <input
              type="text"
              id="tags"
              name="tags"
              placeholder="Tags"
              class="input"
              v-model="newCard.tags"
              required
            />
            <div id="save-buttons">
              <button id="save" v-on:click.prevent="createCard">Save</button>
              <button id="save-and-new" v-on:click.prevent="createCardAndReset">
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
  name: "new-card-add-to-deck",
  data() {
    return {
      newCard: {
        frontOfCard: "",
        backOfCard: "",
        tags: "",
        userId: this.$store.state.user.id,
      },
    };
  },
  methods: {
    createCard() {
      const newCard = {
        ...this.newCard,
      };
      newCard.deckId = this.$route.params.id;
      this.$store.dispatch("CREATE_NEW_CARD_ADD_TO_DECK", newCard);
    },
    createCardAndReset() {
      const newCard = {
        ...this.newCard,
      };
      newCard.deckId = this.$route.params.id;
      this.$store.dispatch("CREATE_NEW_CARD_ADD_TO_DECK_SAVE_AND_NEW", newCard);
      this.newCard = {
        frontOfCard: "",
        backOfCard: "",
        tags: "",
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
  align-items: center;
  justify-content: center;
  min-height: 69vh;
  background-color: #00a7592d;
  padding: 4vh;
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
