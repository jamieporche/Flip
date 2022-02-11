package com.techelevator.model;

public class Card {

    private int id;
    private String frontOfCard;
    private String backOfCard;
    private int userId;
    private String username;
    private String tags;

    public Card() {
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
