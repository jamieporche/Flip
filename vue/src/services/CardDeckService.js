import axios from 'axios';

export default {

    addCard(cardDeck) {
        return axios.post("/decks/add/", cardDeck);
    }

}
