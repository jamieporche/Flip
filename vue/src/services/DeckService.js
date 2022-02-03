import axios from 'axios';

export default {

  getDecksByUser(userId) {
    return axios.get(`/users/deck/${userId}`);
  },
  getDeckById(deckId) {
    return axios.get(`/deck/${deckId}/`);
  },

}