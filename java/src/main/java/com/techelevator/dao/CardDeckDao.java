package com.techelevator.dao;

import com.techelevator.model.CardDecks;
import com.techelevator.model.Deck;

public interface CardDeckDao {
    CardDecks addCardToDeck(CardDecks cardDecks);           // 1CD. this will add card to deck
    void removeCardFromDeck(CardDecks cardDecks);      // 2CD. this will remove a card from deck
}
