/*
 *    File: Deck.java
 *  Author: Robert J. Orr
 */
package com.appian.carddeck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing a traditional deck of cards comprised of 52 cards with four suits and thirteen ranks.
 */
public class Deck implements IDeck, Serializable {

    private static final long serialVersionUID = 1L;

    private final List<Card> _cards;

    /**
     *
     */
    public Deck() {
        _cards = new ArrayList<>(52);
        this.populateDeck();
    }

    /**
     *
     */
    protected void populateDeck() {
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                _cards.add(new Card(r, s));
            }
        }
    }

    /**
     *
     */
    public void reset() {
        _cards.clear();
        this.populateDeck();
    }

    /**
     * Sorts the card deck into an 'ordered' representation.  Defers to Collections.sort() to
     * sort the underlying list.
     */
    public void sort() {
        Collections.sort(_cards);
    }

    /**
     * Shuffles the deck of cards.  Implements the <code>com.appian.carddeck.IDeck.shuffle()</code> method.
     * Uses the Durstenfeld version of the Fisher-Yates shuffle.  The shuffle uses <i>O(n)</i> time,
     * and because it swaps cards 'in place', it uses does not require additional memory.
     */
    @Override
    public void shuffle() {
        for (int i = _cards.size() - 1; i > 0; --i) {
            // Casting rather than rounding ensures 0 <= j < i, which enables us to skip checking for (j == i)
            int j = (int)(Math.random() * i);
            // System.out.println("swapping cards " + i + " and " + j);
            Card temp = _cards.get(j);
            _cards.set(j, _cards.get(i));
            _cards.set(i, temp);
        }
    }

    /**
     * Deals one card from the deck.  Implements the <code>com.appian.carddeck.IDeck.dealOneCard()</code> method.
     * The dealt card is removed from the deck so that the deck's size is correct.
     * Also, the card is removed from the 'end' of the deck (i.e., the end of the list) so that the list can
     * shrink without having to shift the underlying elements when, for example, an underlying array is used to
     * represent the deck.  This speeds up the deal operation if it is called in quick succession.
     *
     * @return A single instance of Card
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
    protected List<Card> getCards() {
        return _cards;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return _cards.toString();
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(final Object obj) {
        // short-cut optimization
        if (this == obj) {
            return true;
        }

        // type check, also handles null reference
        if (!(obj instanceof Deck)) {
            return false;
        }

        // Defer to List.equals(), which uses the equals() method of the individual elements of the list
        Deck d = (Deck)obj;
        return _cards.equals(d.getCards());
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        // Defer to List.hashCode(), which uses the individual elements of the list to calculate the overall hash code
        return _cards.hashCode();
    }
}
