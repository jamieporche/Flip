package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.Deck;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDeckDao implements DeckDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcDeckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override        // 1D. this pulls deck and its cards by deckId
    public Deck getDeckByDeckId(int deckId) {
        Deck deck = new Deck();
        String sql = " SELECT  decks.deck_id, decks.user_id, decks.deck_name, decks.ispublic, users.username\n" +
                " FROM decks\n" +
                "  JOIN users ON  users.user_id = decks.user_id\n" +
                "  WHERE decks.deck_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, deckId);
        if (results.next()) {
            deck = mapRowToDeck(results);
        }
        String sql2 = " SELECT cards.card_id, cards.user_id, cards.front, cards.back, cards.card_tags\n" +
                " FROM cards\n" +
                " JOIN card_deck ON card_deck.card_id = cards.card_id\n" +
                " JOIN decks ON decks.deck_id = card_deck.deck_id\n" +
                " WHERE decks.deck_id = ?";
        SqlRowSet cardResults = jdbcTemplate.queryForRowSet(sql2, deckId);
        List<Card> cardList = new ArrayList<>();
        while (cardResults.next()) {
            cardList.add(mapRowToCard(cardResults));
        }
        deck.setCards(cardList);
        return deck;
    }


    @Override           // 2D. this pulls all the decks that the user has made
    public List<Deck> getDecksByUserId(int userId) {
        List<Deck> deckList = new ArrayList<>();
        List<Integer> deckIds = new ArrayList<>();
        String sql = " SELECT deck_id FROM decks WHERE user_id = ?";
       SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()){
            deckIds.add(mapIdsToList(results));
        }
        for (int i = 1; i <= deckIds.size(); i++){
            Deck deck = getDeckByDeckId(i);
            deckList.add(deck);
        }
        return deckList;
    }

    @Override       // 3D. this will create a new deck
    public Deck createNewDeck(Deck deck) {
        String sql = " INSERT INTO decks (deck_name, user_id) VALUES (?,?) RETURNING deck_id ";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, deck.getDeckName(), deck.getUserId());
        Deck retrievedDeck = getDeckByDeckId(id);
        return retrievedDeck;
    }


    @Override       // 4D. this will change deck ispublic property to true
    public Deck makeDeckPublic(Deck deck) {
        return null;
    }

    @Override       // 5D. this will delete a deck
    public Deck deleteDeck(int deckId) {
        return null;
    }


    // Helper Method for mapping/building decks
    private Deck mapRowToDeck(SqlRowSet rowSet) {
        Deck deck = new Deck();
        deck.setDeckId(rowSet.getInt("deck_id"));
        deck.setDeckName(rowSet.getString("deck_name"));
        deck.setUserName(rowSet.getString("username"));
        deck.setPublic(rowSet.getBoolean("ispublic"));
        deck.setUserId(rowSet.getInt("user_id"));
        return deck;
    }

            // Helper Method for mapping/building card objects ** this does not return username in this one
    private Card mapRowToCard(SqlRowSet rowSet) {
        Card card = new Card();
        card.setCardId(rowSet.getInt("card_id"));
        card.setFrontOfCard(rowSet.getString("front"));
        card.setBackOfCard(rowSet.getString("back"));
        card.setTags(rowSet.getString("card_tags"));
        card.setUserId(rowSet.getInt("user_id"));
        return card;
    }


    private int mapIdsToList(SqlRowSet rowSet){
        int id = 0;
        id = (rowSet.getInt("deck_id"));
        return id;

    }
}
