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
    public Deck getDeck(@PathVariable int deckId){
        return deckDao.getDeckByDeckId(deckId);
    }


    // 2D. this pulls all the decks that the user has made
    @RequestMapping(path= "/users/deck/{userId}" , method = RequestMethod.GET)
    public List<Deck> getDecksByUserId(@PathVariable int userId){
        return deckDao.getDecksByUserId(userId);
    }

    // 3D. this will create a new deck
    @RequestMapping(path = "/users/decks/create/", method = RequestMethod.POST)
    public Deck createNewDeck(@RequestBody Deck deck){
        return deckDao.createNewDeck(deck);
    }



}
