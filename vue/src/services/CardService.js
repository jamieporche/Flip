import axios from 'axios';

export default {

  getCardsByUser(userId) {
    return axios.get(`/users/${userId}/cards`);
  },
  create(card) {
    return axios.post('/cards', card);
  },
  edit(card) {
    return axios.put(`/cards/${card.id}/`, card);
  },
  getCardById(id) {
    return axios.get(`/cards/${id}`);
  },
  delete(id){
    return axios.delete(`/cards/${id}`);
  }

}
