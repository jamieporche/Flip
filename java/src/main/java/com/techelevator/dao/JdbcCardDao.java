package com.techelevator.dao;

import com.techelevator.model.Card;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCardDao implements CardDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcCardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Card getCardByCardId(int cardId) {
        String sql = "SELECT * FROM cards WHERE card_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cardId);
        if (results.next()) {
            return mapRowToCard(results);
        } else {
            throw new RuntimeException("cardId " + cardId + " was not found.");
        }
    }

    @Override
    public List<Card> getListOfCardsByUserId(int userId) {
        String sql = "SELECT * FROM cards WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        List<Card> cardList = new ArrayList<>();
        while (results.next()) {
            cardList.add(mapRowToCard(results));
        }
        return cardList;
    }

    private Card mapRowToCard(SqlRowSet rowSet) {
        Card card = new Card();
        card.setCardId(rowSet.getInt("card_id"));
        card.setFrontOfCard(rowSet.getString("front"));
        card.setBackOfCard(rowSet.getString("back"));
        return card;
    }
}
