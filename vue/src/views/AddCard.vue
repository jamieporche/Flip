<template>
  <div class="view">
    <nav>
      <router-link :to="{ name: 'my-decks' }" class="nav-button">
        View Your Decks</router-link
      >
    </nav>
    <div id="main-body">
      <div id="main">
        <article>
          <div id="card-container">
            <div id="action-buttons">
              <button class="action-button">
                Create New Card and Add to Deck
              </button>
              <button class="action-button" v-on:click.stop="addCards">
                Add Selected to Deck
              </button>
            </div>
            <table id="tblCards">
              <thead>
                <tr class="tbl-header">
                  <th class="checkbox">&nbsp;</th>
                  <th class="question">Question</th>
                  <th class="answer">Answer</th>
                  <th class="tags">Tags</th>
                </tr>
              </thead>
              <tbody>
                <tr id="search-bars">
                  <td>
                    <input
                      type="checkbox"
                      id="selectAll"
                      v-on:click="selectAll($event)"
                      v-bind:checked="allCardsSelected"
                      class="checkbox"
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      id="questionFilter"
                      v-model="filter.question"
                      class="question"
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      id="answerFilter"
                      v-model="filter.answer"
                      class="answer"
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      id="tagsFilter"
                      v-model="filter.tags"
                      class="tags"
                    />
                  </td>
                </tr>
                <tr
                  v-for="(card, index) in filteredCards"
                  v-bind:key="index"
                  v-bind:class="{
                    selected: card.isSelected === true,
                  }"
                  class="card-details"
                >
                  <td>
                    <input
                      type="checkbox"
                      v-bind:id="card.cardId"
                      v-bind:value="card.cardId"
                      v-on:change="selectCard($event)"
                      v-model="card.isSelected"
                      class="checkbox"
                    />
                  </td>
                  <td class="question">{{ card.frontOfCard }}</td>
                  <td class="answer">{{ card.backOfCard }}</td>
                  <td class="tags">{{ card.tags }}</td>
                </tr>
              </tbody>
            </table>
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
// import cardDeckService from

export default {
  components: {
    FooterComponent,
  },
  name: "add-card",
  data() {
    return {
      filter: {
        question: "",
        answer: "",
        tags: "",
      },
      searchBy: "tags",
      cards: [],
      selectedCardIds: [],
    };
  },
  computed: {
    filteredCards() {
      let isFilterEmpty =
        this.filter.question === "" &&
        this.filter.answer === "" &&
        this.filter.tags === "";
      if (!isFilterEmpty) {
        return this.$store.state.cards.filter((card) => {
          let isQuestionAMatch = card.frontOfCard
            .toLowerCase()
            .includes(this.filter.question.toLowerCase());
          let isAnswerAMatch = card.backOfCard
            .toLowerCase()
            .includes(this.filter.answer.toLowerCase());
          let isTagsAMatch = card.tags
            .toLowerCase()
            .includes(this.filter.tags.toLowerCase());
          return isQuestionAMatch && isAnswerAMatch && isTagsAMatch;
        });
      } else {
        return this.$store.state.cards;
      }
    },
    allCardsSelected() {
      return this.$store.state.cards.length === this.selectedCardIds.length;
    },
  },
  created() {
    this.$store.dispatch("LOAD_USERS_CARDS", this.$store.state.user.id);
  },
  methods: {
    filterByTags() {
      return this.$store.state.cards.filter((card) => {
        if (card.tags !== null) {
          return card.tags.toLowerCase().includes(this.filter.toLowerCase());
        }
      });
    },
    filterByContent() {
      return this.$store.state.cards.filter((card) => {
        if (card.tags !== null) {
          let isFrontMatch = card.frontOfCard
            .toLowerCase()
            .includes(this.filter.toLowerCase());
          let isBackMatch = card.backOfCard
            .toLowerCase()
            .includes(this.filter.toLowerCase());
          return isFrontMatch || isBackMatch;
        }
      });
    },
    selectCard(event) {
      if (event.target.checked) {
        this.selectedCardIds.push(parseInt(event.target.id));
      } else {
        this.selectedCardIds = this.selectedCardIds.filter(
          (id) => id !== parseInt(event.target.id)
        );
      }
    },
    selectAll(event) {
      let checkboxes = document.querySelectorAll("input[type=checkbox]");
      if (event.target.checked) {
        this.selectedCardIds = this.filteredCards.map((card) => card.cardId);
        checkboxes.forEach((checkbox) => {
          checkbox.checked = true;
          if (checkbox.parentElement.parentElement.id != "search-bars") {
            checkbox.parentElement.parentElement.classList.add("selected");
          }
        });
      } else {
        checkboxes.forEach((checkbox) => {
          checkbox.checked = false;
          if (checkbox.parentElement.parentElement.id != "search-bars") {
            checkbox.parentElement.parentElement.classList.remove("selected");
          }
        });
        this.selectedCardIds = [];
      }
    },
    addCards() {
      let cardDecks = [];
      this.selectedCardIds.forEach((id) =>
        cardDecks.push({ cardId: id, deckId: this.$route.params.id })
      );
    },
  },
};
</script>

<style scoped>
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
#main {
  margin-top: 18vh;
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
#card-container {
  min-height: 60vh;
  width: 76%;
  min-width: 76vw;
  border-radius: 20px;
  background-color: #e4e0dd;
  border: solid #b4b0ad 1px;
  margin: 0vh 3vh 0vh auto;
  padding: 4vh 0vh 4vh 0vh;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-content: space-between;
  gap: 3vh 3vh;
  overflow: auto;
}
.card-details {
  color: #464443;
}
.selected {
  background-color: rgba(112, 177, 112, 0.335);
}
tbody {
  background-color: white;
}
th {
  background-color: #a66f5b;
  color: white;
}
th,
td {
  text-align: left;
  /* border-right: solid 1px grey; */
  padding: 2vh;
}
tr {
  border-radius: 20px;
  border: solid #b4b0ad 1px;
}
table {
  width: 90%;
  border-collapse: separate;
  border-spacing: 0 2.5vh;
}
td {
  border: solid 1px transparent;
}
tr td:first-child {
  border-top-left-radius: 20px;
}
tr td:first-child {
  border-bottom-left-radius: 20px;
}
tr td:last-child {
  border-top-right-radius: 20px;
}
tr td:last-child {
  border-bottom-right-radius: 20px;
}
th {
  text-align: center;
}
tr th:first-child {
  border-top-left-radius: 20px;
}
tr th:first-child {
  border-bottom-left-radius: 20px;
}
tr th:last-child {
  border-top-right-radius: 20px;
}
tr th:last-child {
  border-bottom-right-radius: 20px;
}
.checkbox {
  width: 10%;
}
.question {
  width: 25%;
}
.answer {
  width: 45%;
}
.tags {
  width: 20%;
}
#action-buttons {
  width: 90%;
  display: flex;
  justify-content: space-between;
}
.action-button {
  border: none;
  color: white;
  font-weight: bold;
  background-color: rgb(49, 92, 49);
  padding: 1.5vh 5vh;
  border-radius: 20px;
  text-decoration: none;
}
.action-button:hover {
  background-color: rgb(36, 66, 36);
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
input[type="text"] {
  width: 100%;
  box-sizing: border-box;
  border: none;
  border-bottom: solid 1px grey;
  font-size: 16px;
  background-color: white;
  padding: 2vh 2vh 0.3vh 2vh;
  margin: 1vh 3vh 2vh 0vh;
}
input[name="search"]:focus {
  border-color: #f2ab6d;
}
.footer {
  z-index: 3;
}
.no-cards {
  font-size: 10vh;
  text-align: center;
}
input[type="checkbox"] {
  width: 2.5vh;
  height: 2.5vh;
}
.checkbox input[type="checkbox"]:checked + span {
  background-color: rgb(49, 92, 49);
}
</style>
