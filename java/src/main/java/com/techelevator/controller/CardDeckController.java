package com.techelevator.controller;


import com.techelevator.dao.CardDeckDao;
import com.techelevator.model.CardDeck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class CardDeckController {

    @Autowired
    CardDeckDao cardDeckDao;

    @RequestMapping(path = "/card-decks", method = RequestMethod.POST)
    public void addCardToDeck(@RequestBody List<CardDeck> cardDecks) {
        cardDeckDao.addCardsToDeck(cardDecks);
    }

    @RequestMapping(path = "/decks/{deckId}/cards/{cardId}", method = RequestMethod.DELETE)
    public void removeCardFromDeck(@PathVariable int deckId, @PathVariable int cardId) {
         cardDeckDao.removeCardFromDeck(deckId, cardId);
    }

    @RequestMapping(path = "/card-decks/cards/{cardId}", method = RequestMethod.GET)
    public List<CardDeck> getCardDecksByCardId(@PathVariable int cardId) {
        return cardDeckDao.getCardDecksByCardId(cardId);
    }

}