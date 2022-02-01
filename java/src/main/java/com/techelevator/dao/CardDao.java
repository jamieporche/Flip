package com.techelevator.dao;

import com.techelevator.model.Card;

import java.util.List;

public interface CardDao {

    public Card getCardByCardId(int cardId);
    public List<Card> getListOfCardsByUserId(int userId);

}
