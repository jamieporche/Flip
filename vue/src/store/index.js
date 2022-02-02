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
    decks: [],
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
    ADD_CARD(state, card) {
      state.cards.push(card);
    },
    SET_DECKS(state, decks) {
      state.decks = decks;
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
    LOAD_USERS_DECKS(context, userId) {
      deckService.getDecksByUser(userId).then(response => {
        const decks = response.data;
        context.commit('SET_DECKS', decks);
      })
    }
  }
})
