import axios from 'axios';

export default {

  getCardsByUser(userId) {
    return axios.get(`/users/${userId}/cards/`);
  },

}
