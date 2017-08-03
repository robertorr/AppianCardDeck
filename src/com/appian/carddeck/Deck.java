package com.appian.carddeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deck.java
 *
 * Class representing a traditional deck of cards comprised of 52 cards with four suits and thirteen ranks.
 */
public class Deck implements IDeck {

    private List<Card> _cards;

    /**
     *
     */
    public Deck() {
        _cards = new ArrayList<>(52);
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                _cards.add(new Card(r, s));
            }
        }
    }

    /**
     *
     */
    public void sort() {
        Collections.sort(_cards);
    }

    /**
     *
     */
    @Override
    public void shuffle() {

    }

    /**
     *
     * @return
     */
    @Override
    public Card dealOneCard() {
        Card c = null;
        if (_cards.size() > 0) {
            c = _cards.remove(_cards.size() - 1);
        }
        return c;
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return _cards.size();
    }

    /**
     *
     * @return
     */
    public List<Card> getCards() {
        return _cards;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "";
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return 0;
    }
}
