import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import cardService from '../services/CardService.js'

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
    cards: [
      {
        frontOfCard: "Front 1",
        backOfCard: "Back 1",
        tags: "JavaScript, Java, Algorithms"
      },
      {
        frontOfCard: "Front 2",
        backOfCard: "Back 2",
        tags: "JavaScript, Java, Algorithms"
      },
      {
        frontOfCard: "Front 3",
        backOfCard: "Back 3",
        tags: "JavaScript, Java, Algorithms"
      },
      {
        frontOfCard: "Front 4",
        backOfCard: "Back 4",
        tags: "JavaScript, Java, Algorithms"
      },
      {
        frontOfCard: "Front 5",
        backOfCard: "Back 5",
        tags: "JavaScript, Java, Algorithms"
      },
      {
        frontOfCard: "Front 6",
        backOfCard: "Back 6",
        tags: "JavaScript, Java, Algorithms"
      }
    ]
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
    }
  },
  actions: {
    LOAD_USERS_CARDS(context, userId) {
      cardService.getCardsByUser(userId).then(
        (response) => {
          const cards = response.data;
          context.commit('SET_CARDS', cards);
        });
    }
  }
})
