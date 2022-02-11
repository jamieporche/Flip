package com.techelevator.controller;

import com.techelevator.dao.CardDao;
import com.techelevator.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class CardController {

    @Autowired
    CardDao cardDao;

    @RequestMapping(path = "/cards/{cardId}", method = RequestMethod.GET)
    public Card getCard(@PathVariable int cardId) {
        return cardDao.getCardByCardId(cardId);
    }

    @RequestMapping(path = "/users/{userId}/cards", method = RequestMethod.GET)
    public List<Card> getCardsByUser(@PathVariable int userId) {
        return cardDao.getCardsByUserId(userId);
    }

    @RequestMapping(path = "/cards" , method = RequestMethod.POST)
    public Card createCard(@RequestBody Card card){
        return cardDao.createCard(card);
    }

    @RequestMapping(path = "/cards/{cardId}", method = RequestMethod.PUT)
    public Card updateCard(@PathVariable int cardId , @RequestBody Card card){
        return cardDao.updateCard(card);
    }

    @RequestMapping(path = "/cards/{cardId}", method = RequestMethod.DELETE)
    public void  deleteCard(@PathVariable int cardId){
       cardDao.deleteCard(cardId);
    }


}
