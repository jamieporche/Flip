<template>
  <div class="view">
    <div id="main">
      <article>
        <div id="form-container">
          <form>
            <h2>Edit Card</h2>
            <label for="frontOfCard">Question</label>
            <input
              type="text"
              id="frontOfCard"
              name="frontOfCard"
              placeholder="Question"
              class="input"
              v-model="card.frontOfCard"
              required
            />
            <label for="backOfCard">Answer</label>
            <textarea
              id="backOfCard"
              name="backOfCard"
              rows="5"
              cols="50"
              class="input"
              v-model="card.backOfCard"
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
              v-model="card.tags"
              required
            />
            <div id="buttons">
              <button
                class="delete-button"
                v-on:click.prevent="deleteCard(card.id)"
              >
                Delete
              </button>
              <button class="save-button" v-on:click.prevent="editCard">
                Save
              </button>
            </div>
          </form>
        </div>
      </article>
    </div>
    <div class="footer">
      <footer-component />
    </div>
  </div>
</template>

<script>
import FooterComponent from "../components/FooterComponent.vue";
import cardService from "../services/CardService.js";

export default {
  components: {
    FooterComponent,
  },
  name: "edit-card",
  data() {
    return {
      card: {},
    };
  },
  methods: {
    editCard() {
      const editedCard = this.card;
      this.$store.dispatch("EDIT_CARD", editedCard);
    },
    deleteCard(id) {
      if (window.confirm("Are you sure you want to delete?")) {
        this.$store.dispatch("DELETE_CARD", id);
        this.$router.push({ name: "home" });
      }
    },
  },
  computed: {},
  created() {
    cardService.getCardById(this.$route.params.id).then((response) => {
      this.card = response.data;
    });
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
#main {
  grid-area: body;
  margin-top: 11vh;
  min-height: 75vh;
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
