package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.Deck;
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
        String sql2 = " SELECT cards.card_id, cards.user_id, cards.front, cards.back, cards.card_tags, users.username\n" +
                " FROM cards\n" +
                "  JOIN users ON  cards.user_id = users.user_id\n" +
                "  WHERE cards.user_id = ?;";
        SqlRowSet cardResults = jdbcTemplate.queryForRowSet(sql2, deck.getDeckId());
        List<Card> cardList = new ArrayList<>();
        while (cardResults.next()) {
            cardList.add(mapRowToCard(cardResults));
        }
        deck.setCards(cardList);
        return deck;
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

            // Helper Method for mapping/building card objects
    private Card mapRowToCard(SqlRowSet rowSet) {
        Card card = new Card();
        card.setCardId(rowSet.getInt("card_id"));
        card.setFrontOfCard(rowSet.getString("front"));
        card.setBackOfCard(rowSet.getString("back"));
        card.setUsername(rowSet.getString("username"));
        card.setTags(rowSet.getString("card_tags"));
        card.setUserId(rowSet.getInt("user_id"));
        return card;
    }

}
