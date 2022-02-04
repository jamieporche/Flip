package com.techelevator.controller;

import com.techelevator.dao.DeckDao;
import com.techelevator.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class DeckController {

    @Autowired
    DeckDao deckDao;

    // 1D. this pulls deck and its cards by deckId
    @RequestMapping(path = "/deck/{deckId}/", method = RequestMethod.GET)
    public Deck getDeck(@PathVariable int deckId) {
        return deckDao.getDeckByDeckId(deckId);
    }


    // 2D. this pulls all the decks that the user has made
    @RequestMapping(path = "/users/deck/{userId}", method = RequestMethod.GET)
    public List<Deck> getDecksByUserId(@PathVariable int userId) {
        return deckDao.getDecksByUserId(userId);
    }

    // 3D. this will create a new deck
    @RequestMapping(path = "/users/decks/create/", method = RequestMethod.POST)
    public Deck createNewDeck(@RequestBody Deck deck) {
        return deckDao.createNewDeck(deck);
    }

    // 4D. this will get public decks
    @RequestMapping(path = "/users/get/public/", method = RequestMethod.GET)
    public List<Deck> getPublicDecks() {
        return deckDao.getPublicDecks();
    }

    // 5D. this will delete a deck
    @RequestMapping(path = "/users/decks/{deckId}/", method = RequestMethod.DELETE)
    public void deleteDeck(@PathVariable int deckId) {
        deckDao.deleteDeck(deckId);
    }

    // 6D. this will update an existing deck
    @RequestMapping(path = "/decks/update/{deckId}/", method = RequestMethod.PUT)
    public Deck updateDeck(@PathVariable int deckId, @RequestBody Deck deck) {
        return deckDao.updateDeck(deck);
    }

}
