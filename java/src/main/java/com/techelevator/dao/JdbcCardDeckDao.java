package com.techelevator.dao;

import com.techelevator.model.CardDecks;
import com.techelevator.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcCardDeckDao implements CardDeckDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcCardDeckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override         // 1CD. this will add card to deck
    public void addCardsToDeck(List<CardDecks> cardDecks) {
        String sql = "INSERT INTO card_deck (deck_id, card_id) VALUES (?,?)";

        for (CardDecks cardDeck : cardDecks) {
            jdbcTemplate.update(sql, cardDeck.getDeckId(), cardDeck.getCardId());
        }
    }



    @Override       // 2CD. this will remove a card from deck
    public void removeCardFromDeck(CardDecks cardDecks) {
        String sql = "DELETE FROM card_deck WHERE deck_id = ? AND card_id = ?";
        jdbcTemplate.update(sql, cardDecks.getDeckId(), cardDecks.getCardId());
    }
}
