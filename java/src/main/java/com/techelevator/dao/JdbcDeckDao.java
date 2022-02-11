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

    @Override
    public Deck getDeckByDeckId(int deckId) {
        Deck deck = new Deck();
        String sql = "SELECT  decks.deck_id, decks.user_id, decks.deck_name, decks.ispublic, decks.issubmitted, users.username " +
                "FROM decks " +
                "JOIN users ON  users.user_id = decks.user_id " +
                "WHERE decks.deck_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, deckId);
        if (results.next()) {
            deck = mapRowToDeck(results);
        }
        String sql2 = "SELECT cards.card_id, cards.user_id, cards.front, cards.back, cards.card_tags " +
                "FROM cards " +
                "JOIN card_deck ON card_deck.card_id = cards.card_id " +
                "WHERE card_deck.deck_id = ?";
        SqlRowSet cardResults = jdbcTemplate.queryForRowSet(sql2, deckId);
        List<Card> cardList = new ArrayList<>();
        while (cardResults.next()) {
            cardList.add(mapRowToCard(cardResults));
        }
        deck.setCards(cardList);
        return deck;
    }

    @Override
    public List<Deck> getDecksByUserId(int userId) {
        List<Deck> deckList = new ArrayList<>();
        List<Integer> deckIds = new ArrayList<>();
        String sql = "SELECT deck_id FROM decks WHERE user_id = ?";
       SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()){
            deckIds.add(results.getInt("deck_id"));
        }
        for (int i = 0; i < deckIds.size(); i++){
            Deck deck = getDeckByDeckId(deckIds.get(i));
            deckList.add(deck);
        }
        return deckList;
    }

    @Override
    public Deck createDeck(Deck deck) {
        String sql = "INSERT INTO decks (deck_name, user_id) VALUES (?,?) RETURNING deck_id";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, deck.getDeckName(), deck.getUserId());
        return getDeckByDeckId(id);
    }

    @Override
    public void deleteDeck(int deckId) {
        String sql = "DELETE FROM card_deck WHERE deck_id = ?";
        jdbcTemplate.update(sql, deckId);
        String sql2 = "DELETE FROM decks WHERE deck_id = ?";
        jdbcTemplate.update(sql2, deckId);
    }

    @Override
    public Deck updateDeck(Deck deck) {
        String sql = "UPDATE decks SET deck_name = ?, user_id = ?, ispublic = ? , issubmitted = ? WHERE deck_id = ?";
        jdbcTemplate.update(sql, deck.getDeckName(), deck.getUserId(), deck.isPublic(),deck.isSubmitted(), deck.getId());
        return deck;
    }

    @Override
    public List<Deck> getDecks(String isSubmitted, String isPublic) {
        List<Deck> deckList = new ArrayList<>();
        List<Integer> deckIds = new ArrayList<>();
        boolean submitted = Boolean.parseBoolean(isSubmitted);
        boolean published = Boolean.parseBoolean(isPublic);
        String sql = "SELECT deck_id FROM decks WHERE issubmitted = ? AND ispublic = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, submitted, published);
        while (results.next()) {
            deckIds.add(results.getInt("deck_id"));
        } for (int i = 0; i < deckIds.size(); i++) {
            Deck deck = getDeckByDeckId(deckIds.get(i));
            deckList.add(deck);
        }
        return deckList;
    }

    private Deck mapRowToDeck(SqlRowSet rowSet) {
        Deck deck = new Deck();
        deck.setId(rowSet.getInt("deck_id"));
        deck.setDeckName(rowSet.getString("deck_name"));
        deck.setUsername(rowSet.getString("username"));
        deck.setPublic(rowSet.getBoolean("ispublic"));
        deck.setUserId(rowSet.getInt("user_id"));
        deck.setSubmitted(rowSet.getBoolean("issubmitted"));
        return deck;
    }

    private Card mapRowToCard(SqlRowSet rowSet) {
        Card card = new Card();
        card.setId(rowSet.getInt("card_id"));
        card.setFrontOfCard(rowSet.getString("front"));
        card.setBackOfCard(rowSet.getString("back"));
        card.setTags(rowSet.getString("card_tags"));
        card.setUserId(rowSet.getInt("user_id"));
        return card;
    }
}
