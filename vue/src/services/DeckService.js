import axios from 'axios';

export default {

  getDecks(isSubmitted, isPublic) {
    return axios.get(`/decks?submitted=${isSubmitted}&public=${isPublic}`);
  },
  getDecksByUser(userId) {
    return axios.get(`/users/${userId}/decks`);
  },
  getDeckById(id) {
    return axios.get(`/decks/${id}`);
  },
  create(deck) {
    return axios.post("/decks", deck);
  },
  delete(id){
    return axios.delete(`/decks/${id}`);
  },
  edit(deck) {
    return axios.put(`/decks/${deck.id}`, deck);
  },
}