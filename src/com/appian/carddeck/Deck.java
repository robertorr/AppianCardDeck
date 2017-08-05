/*
 *    File: Deck.java
 *  Author: Robert J. Orr
 */
package com.appian.carddeck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class representing a traditional deck of cards comprised of 52 cards with four suits and thirteen ranks.
 * This class implements the <code>com.appian.carddeck.IDeck</code> interface.
 * It has been designed with a couple of extension points, namely the <code>getCards()</code> method,
 * which gives access to the underlying <code>List</code> representation of the deck, and the
 * <code>populateDeck()</code> method, which allows for the creation of multi-deck 'shoes'.
 * <b>NOTE:</b> This class is not thread safe, and is designed only for use in single-threaded applications.
 *
 * @author Robert Orr
 * @version 1.0
 */
public class Deck implements IDeck, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * <code>List</code> that contains cards for deck.
     */
    private final List<Card> _cards;

    // TODO: make class thread safe
    // TODO: expose more methods of underlying List, such as set(), get(), and contains()

    /**
     * Constructs a deck of 52 cards, with 13 cards from each of the four standard suits.
     * The card deck is represented as a <code>List</code> of <code>Cards</code>.
     */
    public Deck() {
        _cards = new ArrayList<>(Rank.values().length * Suit.values().length);
        this.populateDeck();
    }

    /**
     * Adds 52 cards to the deck, 13 from each of the canonical suits.
     * This method can be used by extending classes to create a 'shoe' that contains multiples of 52 cards.
     */
    protected void populateDeck() {
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                _cards.add(new Card(r, s));
            }
        }
    }

    /**
     * Empties the deck of any remaining cards and repopulates it with 52 cards.
     */
    public void reset() {
        _cards.clear();
        this.populateDeck();
    }

    /**
     * Sorts the card deck into a conventional 'ordered' representation.
     * Uses <code>List.sort()</code> to sort the underlying list.
     */
    public void sort() {
        final Comparator<Card> comp = (c1, c2) -> {
            // sort by suit first, then by rank within suit
            int result = c1.getSuit().compareTo(c2.getSuit());
            if (result == 0) {
                result = c1.getRank().compareTo(c2.getRank());
            }
            return result;
        };
        _cards.sort(comp);
    }

    /**
     * Shuffles the deck of cards.  Implements the <code>com.appian.carddeck.IDeck.shuffle()</code> method.
     * Uses the Durstenfeld version of the Fisher-Yates shuffle.  The shuffle occurs in <i>O(n)</i> time,
     * and because it swaps cards 'in place', it does not require additional memory.
     */
    @Override
    public void shuffle() {
        for (int i = _cards.size() - 1; i > 0; i--) {
            // Choose card to swap with index card.
            // Casting rather than rounding ensures 0 <= j < i,
            // which enables us to skip checking for (j == i).
            int j = (int) (Math.random() * i);
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
     * represent the deck (i.e., <i>O(1)</i> rather than <i>O(n)</i>).  This speeds up the deal operation if it
     * is called in quick succession.
     *
     * @return A single instance of <code>Card</code>, or <code>null</code> if deck is empty
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
     * Returns the number of cards currently contained in the deck.
     *
     * @return number of cards in the deck
     */
    public int getSize() {
        return _cards.size();
    }

    /**
     * Returns a <code>List</code> representation of the cards in the deck.
     *
     * @return <code>List</code> of cards in the deck
     */
    protected List<Card> getCards() {
        return _cards;
    }

    /**
     * Returns a <code>String</code> representation of the cards in the deck.
     * Defers to <code>List.toString()</code>.
     *
     * @return <code>String</code> representation of the cards in the deck
     */
    @Override
    public String toString() {
        return _cards.toString();
    }

    /**
     * Compares the specified object with this Deck for equality.  Returns
     * <tt>true</tt> if and only if the specified object is also a Deck, both
     * Decks have the same size, and all corresponding pairs of elements in
     * the two Decks are <i>equal</i>.  (Two elements <tt>e1</tt> and
     * <tt>e2</tt> are <i>equal</i> if <tt>(e1==null ? e2==null :
     * e1.equals(e2))</tt>.)  In other words, two Decks are defined to be
     * equal if they contain the same elements in the same order.  Defers
     * to <code>List.equals()</code> to check equality between the underlying
     * card lists.
     *
     * @param obj the object to be compared for equality with this list
     * @return <tt>true</tt> if the specified object is equal to this list
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
        Deck d = (Deck) obj;
        return _cards.equals(d.getCards());
    }

    /**
     * Returns the hash code value for this deck.
     * This method defers to <code>List.hashCode()</code>, which uses the individual elements of the list
     * to calculate the overall hash code.  That is, the hash code will be calculated using the hash codes
     * of the individual cards in the deck.
     *
     * @return the hash code value for this deck
     */
    @Override
    public int hashCode() {
        return _cards.hashCode();
    }
}
