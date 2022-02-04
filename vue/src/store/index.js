import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import cardService from '../services/CardService.js'
import deckService from '../services/DeckService.js'

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
      cards: []
    },
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
      console.log("Last deck: " + state.decks[state.decks.length - 1].deckName)
    },
    SET_DECK(state, deck) {
      state.deck = deck;
    },
    ADD_DECK(state, deck) {
      // console.log("decks before: " + state.decks);
      state.decks.push(deck);
      console.log(deck.deckName);
      // console.log("decks after: " + state.decks);
      // return "Deck added";
    },
    EDIT_CARD(state, editedCard) {
      for (let i = 0; i < state.cards.length; i++) {
        if (state.cards[i].cardId === editedCard.cardId) {
          state.cards[i] = editedCard;
        }
      }
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
    LOAD_USERS_DECKS(context, userId) {
      console.log("loading users decks");
      deckService.getDecksByUser(userId).then(response => {
        const decks = response.data;
        context.commit('SET_DECKS', decks);
      });
    },
    CREATE_NEW_DECK(context, deck) {
      deckService.create(deck).then(response => {
          const newDeck = response.data;
          context.commit('ADD_DECK', newDeck);
          console.log("Deck created");
      });
    },
    EDIT_CARD(context, card) {
      cardService.editCard(card).then(response => {
        const editedCard = response.data;
        context.commit('EDIT_CARD', editedCard);
      });
    },
  }
})
