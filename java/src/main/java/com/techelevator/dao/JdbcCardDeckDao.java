package com.techelevator.dao;

import com.techelevator.model.CardDeck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCardDeckDao implements CardDeckDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCardDeckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addCardsToDeck(List<CardDeck> cardDecks) {
        String sql = "INSERT INTO card_deck (deck_id, card_id) VALUES (?, ?)";

        for (CardDeck cardDeck : cardDecks) {
            jdbcTemplate.update(sql, cardDeck.getDeckId(), cardDeck.getCardId());
        }
    }

    @Override
    public void removeCardFromDeck(int deckId, int cardId) {
        String sql = "DELETE FROM card_deck WHERE deck_id = ? AND card_id = ?";
        jdbcTemplate.update(sql, deckId, cardId);
    }

    @Override
    public List<CardDeck> getCardDecksByCardId(int cardId) {
        String sql = "SELECT * " +
                "FROM card_deck " +
                "WHERE card_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cardId);

        List<CardDeck> cardDecks = new ArrayList<>();
        while (results.next()) {
            cardDecks.add(mapCardDecksToRowSet(results));
        }
        return cardDecks;
    }

    private CardDeck mapCardDecksToRowSet(SqlRowSet rowSet) {
        CardDeck cardDeck = new CardDeck();
        cardDeck.setCardId(rowSet.getInt("card_id"));
        cardDeck.setDeckId(rowSet.getInt("deck_id"));
        return cardDeck;
    }
}
