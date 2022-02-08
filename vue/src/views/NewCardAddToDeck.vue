<template>
  <div class="view">
    <nav>
      <router-link :to="{ name: 'home' }" class="nav-button"
        >View Your Cards</router-link
      >
      <router-link :to="{ name: 'my-decks' }" class="nav-button">
        View Your Decks</router-link
      >
    </nav>
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
      this.$store.dispatch("CREATE_NEW_CARD", newCard);
      this.$router.push({ name: "home" });
    },
    createCardAndReset() {
      const newCard = {
        ...this.newCard,
      };
      this.$store.dispatch("CREATE_NEW_CARD", newCard);
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
  min-height: 68vh;
  margin: 20vh 0px 0px 0px;
}
form {
  display: flex;
  flex-direction: column;
  padding: 5vh 10vh;
}
button {
  border: none;
  color: white;
  background-color: rgb(49, 92, 49);
  padding: 1.5vh 5vh;
  border-radius: 20px;
}
#save-buttons {
  display: flex;
  justify-content: flex-end;
  gap: 4vh;
  margin-top: 4vh;
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
  border-radius: 20px;
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
