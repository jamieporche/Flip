package com.techelevator.controller;


import com.techelevator.dao.CardDeckDao;
import com.techelevator.model.CardDecks;
import com.techelevator.model.Deck;
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


    // 1CD. this will add card to deck
    @RequestMapping(path = "/decks/add/", method = RequestMethod.POST)
    public void addCardToDeck(@RequestBody List<CardDecks> cardDecks) {
        cardDeckDao.addCardsToDeck(cardDecks);
    }


    // 2CD. this will remove a card from deck
    @RequestMapping(path = "/decks/remove/", method = RequestMethod.DELETE)
    public void removeCardFromDeck(@RequestBody CardDecks cardDecks) {
         cardDeckDao.removeCardFromDeck(cardDecks);
    }

}