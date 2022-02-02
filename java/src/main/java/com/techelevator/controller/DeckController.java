package com.techelevator.controller;

import com.techelevator.dao.DeckDao;
import com.techelevator.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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




}
