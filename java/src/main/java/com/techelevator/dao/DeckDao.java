package com.techelevator.dao;

import com.techelevator.model.Deck;

import java.util.List;

public interface DeckDao {
    Deck getDeckByDeckId(int deckId);        // 1D. this pulls deck and its cards by deckId
    List<Deck> getDecksByUserId(int userId);      // 2D. this pulls all the decks that the user has made
    Deck createNewDeck(Deck deck);           // 3D. this will create a new deck
    Deck makeDeckPublic(Deck deck);        // 4D. this will change deck ispublic property to true
    Deck deleteDeck(int deckId);         // 5D. this will delete a deck


}
