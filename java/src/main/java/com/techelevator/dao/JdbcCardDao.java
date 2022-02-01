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
        Card card = new Card();
        String sql = " SELECT cards.card_id, cards.front, cards.back, users.username\n" +
                " FROM cards\n" +
                "  JOIN users ON  cards.user_id = users.user_id\n" +
                "  WHERE cards.card_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cardId);
        if (results.next()) {
            card = mapRowToCard(results);
        }
            return card;

//        } else {
//            throw new RuntimeException("cardId " + cardId + " was not found.");
//        }
    }

    @Override                      // will have to modify again with tags
    public List<Card> getListOfCardsByUserId(int userId) {
        String sql = " SELECT cards.card_id, cards.front, cards.back, users.username\n" +
                " FROM cards\n" +
                "  JOIN users ON  cards.user_id = users.user_id\n" +
                "  WHERE cards.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        List<Card> cardList = new ArrayList<>();
        while (results.next()) {
            cardList.add(mapRowToCard(results));
        }
        return cardList;
    }

    @Override
    public List<Card> getListOfCardsByTags(String[] tags) {
        String sql = "SELECT * FROM cards "
                + "JOIN ";
        return null;
    }

    private Card mapRowToCard(SqlRowSet rowSet) {
        Card card = new Card();
        card.setCardId(rowSet.getInt("card_id"));
        card.setFrontOfCard(rowSet.getString("front"));
        card.setBackOfCard(rowSet.getString("back"));
        card.setUsername(rowSet.getString("username"));
        return card;
    }
}
