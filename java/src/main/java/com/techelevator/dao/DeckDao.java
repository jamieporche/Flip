package com.techelevator.dao;

import com.techelevator.model.Deck;

import java.util.List;

public interface DeckDao {
    Deck getDeckByDeckId(int deckId);
    List<Deck> getDecksByUserId(int userId);
    Deck createDeck(Deck deck);
    void deleteDeck(int deckId);
    Deck updateDeck(Deck deck);
    List<Deck> getDecks(String isSubmitted, String isPublic);
}
