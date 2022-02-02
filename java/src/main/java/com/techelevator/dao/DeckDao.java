package com.techelevator.dao;

import com.techelevator.model.Deck;

import java.util.List;

public interface DeckDao {
    Deck getDeckByDeckId(int deckId);        // 1D. this pulls deck and its cards by deckId
    List<Deck> getDecksByUserId(int userId);      // 2D. this pulls all the decks that the user has made
}
