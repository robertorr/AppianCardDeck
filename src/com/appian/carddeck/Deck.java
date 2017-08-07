/*
 *    File: Deck.java
 *  Author: Robert J. Orr
 */
package com.appian.carddeck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

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

    // TODO: make class thread-safe
    // TODO: expose more methods of underlying List, such as set(), get(), and contains() (probably as protected)

    /**
     * Used for serialization.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Comparator for 'natural' ordering of deck.
     */
    private static final Comparator<Card> compNaturalOrder = (c1, c2) -> {
        // sort by suit first, then by rank within suit
        int result = c1.getSuit().compareTo(c2.getSuit());
        if (result == 0) {
            result = c1.getRank().compareTo(c2.getRank());
        }
        return result;
    };
    /**
     * Comparator for 'reverse' ordering of deck.
     */
    private static final Comparator<Card> compReverseOrder = Collections.reverseOrder(Deck.compNaturalOrder);

    /**
     * Class logger instance.
     */
    private final transient Logger _log;
    /**
     * List that contains cards for deck.
     * NOTE: Because cards are dealt from the end of the list (for efficiency),
     * the cards are kept in 'reverse' order in the list (i.e., upon initial population
     * of the deck, the highest value card is at list position 0, and the lowest value card
     * is at the end of the list).
     */
    private final List<Card> _cards;
    /**
     * Pseudo-random number generator.
     */
    private final Random _rand;


    /**
     * Constructs a deck of 52 cards, with 13 cards from each of the four standard suits.
     * The card deck is represented as a <code>List</code> of <code>Cards</code>.
     */
    public Deck() {
        _log = Logger.getAnonymousLogger();

        // To get repeatable sequences for debugging, use seed = 0
        long seed = System.currentTimeMillis();
        _rand = new Random(seed);

        _cards = new ArrayList<>(Rank.values().length * Suit.values().length);
        this.populateDeck();
    }

    /**
     * Adds 52 cards to the deck, 13 from each of the canonical suits.
     * This method can be used by extending classes to create a 'shoe' that contains multiples of 52 cards.
     */
    protected void populateDeck() {
        // Insert cards in 'reverse' order to make deal operation compliant with principle of 'least surprise'.
        // I.e., since cards are dealt from the end of the list, they must be inserted in reverse order.
        Suit[] suitArray = Suit.values();
        Rank[] rankArray = Rank.values();
        for (int s = suitArray.length - 1; s >= 0; s--) {
            for (int r = rankArray.length - 1; r >= 0; r--) {
                _cards.add(new Card(rankArray[r], suitArray[s]));
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
     * A custom <code>Comparator</code> that uses both <code>Rank</code> and <code>Suit</code>
     * is used for the sort, so that the cards can be grouped by <code>Suit</code>, and
     * then sorted within <code>Suit</code>.
     */
    public void sort() {
        // Sort using 'reverse' order comparator to comply with principle of 'least surprise'
        _cards.sort(Deck.compReverseOrder);
    }

    /**
     * Shuffles the deck of cards.  Implements the <code>com.appian.carddeck.IDeck.shuffle()</code> method.
     * Uses the <i>Durstenfeld</i> version of the <i>Fisher-Yates</i> shuffle.  The shuffle occurs in <i>O(n)</i> time,
     * and because it swaps cards 'in place', it does not require additional memory.
     */
    @Override
    public void shuffle() {
        for (int i = _cards.size() - 1; i > 0; i--) {
            // Choose card to swap with index card
            int j = _rand.nextInt(i); // 0 <= j < i
            _log.finer("swapping cards " + i + " and " + j);
            Card temp = _cards.get(j);
            _cards.set(j, _cards.get(i));
            _cards.set(i, temp);
        }
    }

    /**
     * Deals one card from the deck.  Implements the <code>com.appian.carddeck.IDeck.dealOneCard()</code> method.
     * The dealt card is removed from the deck so that the deck's size is correct.
     * Also, the card is removed from the 'end'/'back' of the deck (i.e., the end of the list) so that the list can
     * shrink without having to shift the list elements when, for example, an underlying array is used to
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
     *
     * @return <code>String</code> representation of the cards in the deck
     */
    @Override
    public String toString() {
        // Construct the string in 'reverse' order to make it appear the deck is in 'natural' order (i.e.,
        // cards being dealt from front of deck).  This is done to comply with principle of 'least surprise'.
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = _cards.size() - 1; i >= 0; i--) {
            sb.append(_cards.get(i).toString());
            if (i != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
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
     * @param obj the object to be compared for equality with this Deck
     * @return <tt>true</tt> if the specified object is equal to this Deck, <tt>false</tt> otherwise
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
