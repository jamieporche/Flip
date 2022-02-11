import axios from 'axios';

export default {

    addCards(cardDecks) {
        return axios.post("/card-decks", cardDecks);
    },
    getCardDecksByCardId(cardId) {
        return axios.get(`/card-decks/cards/${cardId}`);
    },
    removeCard(deckId, cardId) {
        return axios.delete(`/decks/${deckId}/cards/${cardId}`);
    },

}
