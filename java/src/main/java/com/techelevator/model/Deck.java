package com.techelevator.model;

<<<<<<< Updated upstream
public class Decks {
=======
public class Deck {
>>>>>>> Stashed changes
    private int deckId;
    private String deckName;
    private int userId;
    private boolean isPublic;

<<<<<<< Updated upstream
=======
    public Deck() {}

    public Deck(int deckId, String deckName) {
        this.deckId = deckId;
        this.deckName = deckName;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
>>>>>>> Stashed changes
}
