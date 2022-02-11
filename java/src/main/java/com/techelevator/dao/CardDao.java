package com.techelevator.dao;

import com.techelevator.model.Card;

import java.util.List;

public interface CardDao {

    Card getCardByCardId(int cardId);
    List<Card> getCardsByUserId(int userId);
    Card createCard(Card card);
    Card updateCard(Card card);
    void deleteCard(int cardId);
}
