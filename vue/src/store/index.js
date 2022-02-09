import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import cardService from '../services/CardService.js'
import deckService from '../services/DeckService.js'
import cardDeckService from '../services/CardDeckService.js'
import router from '../router'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    cards: [],
    card: {},
    decks: [],
    deck: {
      deckId: '',
      deckName: '',
      isPublic: false,
      userName: '',
      cards: [],
      isCorrect: false,
    },
    teammates: [ { dan: { profilePhoto: "../assets/dan-headshot.jpg", bio: "", linkedinUrl: "https://www.linkedin.com/in/daniel-schnelzer-398944223/" } },
    { anna: { profilePhoto: "../assets/anna-headshot.jpg", bio: "", linkedinUrl: "https://www.linkedin.com/in/annaokada1/" } },
    { sean: { profilePhoto: "", bio: "", linkedinUrl: "https://www.linkedin.com/in/seanoberc/" } },
    { jamie: { profilePhoto: "../jamie-headshot.png", bio: "", linkedinUrl: "https://www.linkedin.com/in/jamieporche/" } }
  ],
},
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_CARDS(state, cards) {
      state.cards = cards;
    },
    SET_CARD(state, card) {
      state.card = card;
    },
    ADD_CARD(state, card) {
      state.cards.push(card);
    },
    SET_DECKS(state, decks) {
      state.decks = decks;
    },
    SET_DECK(state, deck) {
      state.deck = deck;
    },
    ADD_DECK(state, deck) {
      state.decks.push(deck);
    },
    EDIT_CARD(state, editedCard) {
      for (let i = 0; i < state.cards.length; i++) {
        if (state.cards[i].cardId === editedCard.cardId) {
          state.cards[i] = editedCard;
        }
      }
    },
    MARK_CARD_ISCORRECT(state, cardState) {
      state.deck.cards[cardState.index].isCorrect = cardState.isCorrect;
    }, 
    DELETE_DECK(state, deckId){
      let filteredDecks = state.decks.filter((deck) =>{
        return deck.deckId != deckId;
      });
      state.decks = filteredDecks;
    },
    DELETE_CARD(state, cardId){
      state.cards = state.cards.filter((card) =>{
        return card.cardId != cardId;
      })
    },
    EDIT_DECK(state, editedDeck) {
      for (let i = 0; i < state.decks.length; i++) {
        if (state.decks[i].deckId === editedDeck.deckId) {
          state.decks[i] = editedDeck;
        }
      }
    },
  },
  actions: {
    LOAD_USERS_CARDS(context, userId) {
      cardService.getCardsByUser(userId).then(
        (response) => {
          const cards = response.data;
          context.commit('SET_CARDS', cards);
        });
    },
    CREATE_NEW_CARD(context, card) {
      cardService.create(card).then(response => {
        if (response.status == 200) {
          const newCard = response.data;
          context.commit('ADD_CARD', newCard);
        }
      });
    },
    CREATE_NEW_CARD_ADD_TO_DECK(context, card) {
      cardService.create(card).then(response => {
        if (response.status == 200) {
          const newCard = response.data;
          const cardToAdd = [{ cardId: newCard.cardId, deckId: card.deckId }];
          console.log(cardToAdd);
          cardDeckService.addCards(cardToAdd).then(response => {
            if (response.status === 200) {
              console.log("deck id " + card.deckId);
              router.push({ name: "deck-details", params: { id: card.deckId } });
            }
          });
        }
      });
    },
    CREATE_NEW_CARD_ADD_TO_DECK_SAVE_AND_NEW(context, card) {
      cardService.create(card).then(response => {
        if (response.status == 200) {
          const newCard = response.data;
          const cardToAdd = [{ cardId: newCard.cardId, deckId: card.deckId }];
          console.log(cardToAdd);
          cardDeckService.addCards(cardToAdd);
        }
      });
    },
    LOAD_USERS_DECKS(context, userId) {
      deckService.getDecksByUser(userId).then(response => {
        const decks = response.data;
        context.commit('SET_DECKS', decks);
      });
    },
    LOAD_PUBLIC_DECKS(context) {
      deckService.getPublicDecks().then(response => {
        const decks = response.data;
        context.commit('SET_DECKS', decks);
      });
    },
    LOAD_SUBMITTED_DECKS(context) {
      deckService.getSubmittedDecks().then(response => {
        const decks = response.data;
        context.commit('SET_DECKS', decks);
      });
    },
    CREATE_NEW_DECK(context, deck) {
      deckService.create(deck).then(response => {
          const newDeck = response.data;
          context.commit('ADD_DECK', newDeck);
          router.push({ name: "my-decks" });
      });
    },
    CREATE_NEW_DECK_SAVE_AND_NEW(context, deck) {
      deckService.create(deck).then(response => {
          const newDeck = response.data;
          context.commit('ADD_DECK', newDeck);
      });
    },
    EDIT_CARD(context, card) {
      cardService.editCard(card).then(response => {
        const editedCard = response.data;
        context.commit('EDIT_CARD', editedCard);
      });
    },
    DELETE_DECK(context, deckId){
      deckService.deleteDeck(deckId).then(response => {
        if(response.status === 200){
        context.commit('DELETE_DECK', deckId);
        }
      });
    },
    DELETE_CARD(context, cardId){
      cardService.deleteCard(cardId).then(response =>{
        if(response.status === 200){
          context.commit('DELETE_CARD', cardId);
        }
      });
    },
    EDIT_DECK(context, deck) {
      deckService.edit(deck).then(response => {
        if (response.status === 200) {
          context.commit('EDIT_DECK', deck);
          router.push({ name: "my-decks" });
        }
      })
    },
  }
})
