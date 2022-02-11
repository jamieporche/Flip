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

    @RequestMapping(path = "/decks/{deckId}", method = RequestMethod.GET)
    public Deck getDeckById(@PathVariable int deckId) {
        return deckDao.getDeckByDeckId(deckId);
    }

    @RequestMapping(path = "/users/{userId}/decks", method = RequestMethod.GET)
    public List<Deck> getDecksByUserId(@PathVariable int userId) {
        return deckDao.getDecksByUserId(userId);
    }

    @RequestMapping(path = "/decks", method = RequestMethod.POST)
    public Deck createNewDeck(@RequestBody Deck deck) {
        return deckDao.createDeck(deck);
    }

    @RequestMapping(path = "/decks/{deckId}", method = RequestMethod.DELETE)
    public void deleteDeck(@PathVariable int deckId) {
        deckDao.deleteDeck(deckId);
    }

    @RequestMapping(path = "/decks/{deckId}", method = RequestMethod.PUT)
    public Deck updateDeck(@PathVariable int deckId, @RequestBody Deck deck) {
        return deckDao.updateDeck(deck);
    }

    @RequestMapping(path = "/decks", method = RequestMethod.GET)
    public List<Deck> getDecks(@RequestParam(name="submitted", defaultValue = "false") String isSubmitted,
                               @RequestParam(name="public", defaultValue = "false") String isPublic) {
        return deckDao.getDecks(isSubmitted, isPublic);
    }
}
