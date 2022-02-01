package com.techelevator.model;

import java.util.UUID;

public class Card {

    private int cardId;
    private String frontOfCard;
    private String backOfCard;
    private int userId;
    private String username;

    // private int tagId; ?
    // private String tagName; ?

    public Card() {
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getFrontOfCard() {
        return frontOfCard;
    }

    public void setFrontOfCard(String frontOfCard) {
        this.frontOfCard = frontOfCard;
    }

    public String getBackOfCard() {
        return backOfCard;
    }

    public void setBackOfCard(String backOfCard) {
        this.backOfCard = backOfCard;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
