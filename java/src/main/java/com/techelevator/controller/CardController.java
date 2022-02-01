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
public class CardController {

    @Autowired
    CardDao cardDao;

    @RequestMapping(path = "/users/{userId}/cards", method = RequestMethod.GET)
    public List<Card> getCardsList (@PathVariable("userId") int userId) {
        return cardDao.getListOfCardsByUserId(userId);
    }

}
