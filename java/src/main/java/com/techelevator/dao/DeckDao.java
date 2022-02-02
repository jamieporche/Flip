package com.techelevator.dao;

import com.techelevator.model.Deck;

public interface DeckDao {
    Deck getDeckByDeckId(int deckId);        // 1D. this pulls deck and its cards by deckId
}
