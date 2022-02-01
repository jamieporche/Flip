package com.techelevator.dao;

import com.techelevator.model.Deck;

public interface DeckDao {
    Deck getDeckByDeckId(int deckId);
}
