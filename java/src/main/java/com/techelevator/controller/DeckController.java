package com.techelevator.controller;

import com.techelevator.dao.DeckDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
public class DeckController {

    @Autowired
    DeckDao deckDao;

}
