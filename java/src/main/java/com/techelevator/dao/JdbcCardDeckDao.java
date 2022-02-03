package com.techelevator.dao;

import com.techelevator.model.CardDecks;
import com.techelevator.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcCardDeckDao implements CardDeckDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcCardDeckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override         // 1CD. this will add card to deck
    public CardDecks addCardToDeck(CardDecks cardDecks) {
        String sql = "INSERT INTO card_deck (deck_id, card_id) VALUES (?,?)";
        jdbcTemplate.update(sql, cardDecks.getDeckId(), cardDecks.getCardId() );
        return cardDecks;
    }



    @Override       // 2CD. this will remove a card from deck
    public void removeCardFromDeck(CardDecks cardDecks) {
        String sql = "DELETE FROM card_deck WHERE deck_id = ? AND card_id = ?";
        jdbcTemplate.update(sql, cardDecks.getDeckId(), cardDecks.getCardId());
    }
}