package com.techelevator.dao;

import com.techelevator.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcDeckDao implements DeckDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcDeckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Deck getDeckById(int deckId) {
        String sql = "SELECT * FROM decks WHERE deck_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, deckId);
        if (results.next()) {
            return mapRowToDeck(results);
        } else {
            throw new RuntimeException("deckId " + deckId + " was not found.");
        }

    }

    private Deck mapRowToDeck(SqlRowSet rowSet) {
        Deck deck = new Deck();
        deck.setDeckId(rowSet.getInt("deck_id"));
        deck.setDeckName(rowSet.getString("deck_name"));
        //maybe include other deck properties here
        return deck;
    }

}
