package com.techelevator.dao;

import com.techelevator.model.CardDeck;

import java.util.List;

public interface CardDeckDao {
    void addCardsToDeck(List<CardDeck> cardDecks);
    void removeCardFromDeck(int deckId, int cardId);
    List<CardDeck> getCardDecksByCardId(int cardId);
}
