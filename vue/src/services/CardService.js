import axios from 'axios';

export default {

  getCardsByUser(userId) {
    return axios.get(`/users/${userId}/cards/`);
  },
  create(card) {
    return axios.post('/users/card/create', card);
  }

}
