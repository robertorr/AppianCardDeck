/*
 *    File: Card.java
 *  Author: Robert J. Orr
 */

package com.appian.carddeck;

import java.io.Serializable;

/**
 * Class representing a playing card.  Cards have a rank and a suit, and individual cards are comparable.
 * This class is immutable (i.e., the class members cannot be changed after it has been created).
 *
 * @author Robert Orr
 * @version 1.0
 */
public class Card implements Comparable<Card>, Serializable {

    // TODO: add factory method and pull from cache of constant Cards (see java.lang.Integer)

    private static final long serialVersionUID = -3520807187208430671L;

    /**
     * Card rank.
     */
    private final Rank _rank;
    /**
     * Card suit.
     */
    private final Suit _suit;
    /**
     * Card hash code.
     */
    private final int _hashCode;

    /**
     * Constructs a single card with a <code>Rank</code> and a <code>Suit</code>.
     * Because the Card is immutable, this constructor also calculates the hash code and stores it.
     * This constructor will throw a <code>NullPointerException</code> if either <code>Rank</code>
     * or <code>Suit</code> is <code>null</code>.
     *
     * @param r card Rank
     * @param s card Suit
     */
    public Card(final Rank r, final Suit s) {
        // check for nullity
        if (r == null) {
            throw new NullPointerException("rank is null");
        }
        if (s == null) {
            throw new NullPointerException("suit is null");
        }
        _rank = r;
        _suit = s;

        // Compute hash code when card is constructed so that it only has to be done once.
        // This can only be done because Card is immutable.
        // Hashing algorithm taken from Effective Java (2nd Ed.) by Josh Bloch.
        int result = 17;
        result = 31 * result + _rank.hashCode();
        result = 31 * result + _suit.hashCode();
        _hashCode = result;
    }

    /**
     * Returns the <code>Rank</code> of the card.
     *
     * @return the Rank of the card
     */
    public Rank getRank() {
        return _rank;
    }

    /**
     * Returns the <code>Suit</code> of the card.
     *
     * @return the Suit of the card
     */
    public Suit getSuit() {
        return _suit;
    }

    /**
     * Indicates whether the card os a face card (i.e., Jack, Queen, or King).
     *
     * @return <tt>true</tt> if card is a face card, <tt>false</tt> otherwise
     */
    public boolean isFaceCard() {
        return _rank.ordinal() >= Rank.JACK.ordinal();
    }

    /**
     * Returns a <code>String</code> representation of the card.
     *
     * @return <code>String</code> representation of the card
     */
    @Override
    public String toString() {
        // TODO: i18n
        return _rank.toString() + " of " + _suit.toString();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Returns <tt>true</tt> if the other object is also a <code>Card</code>
     * and its <code>Rank</code> and <code>Suit</code> match those of this
     * <code>Card</code>.
     *
     * @param obj the object to be compared for equality with this Card
     * @return <tt>true</tt> if the specified object is equal to this Card, <tt>false</tt> otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        // short-cut optimization
        if (this == obj) {
            return true;
        }

        // type check, also handles null reference
        if (!(obj instanceof Card)) {
            return false;
        }

        // check fields using their equals() methods
        Card c = (Card) obj;
        return this._rank.equals(c._rank) && this._suit.equals(c._suit);
    }

    /**
     * Returns the hash code for this card.
     * The hashing algorithm is taken from Effective Java (2nd Ed.) by Josh Bloch.
     *
     * @return the hash code value for this card
     */
    @Override
    public int hashCode() {
        return _hashCode;
    }

    /**
     * Compares this object with the specified object for order.  Returns a negative integer, zero,
     * or a positive integer as this object is less than, equal to, or greater than the specified object.
     * <b>NOTE:</b> This method only compares the <code>Rank</code> of the cards.  It only uses <code>Rank</code>
     * because in most card games, <code>Suit</code> is not used in comparing cards.  In this regard,
     * this method breaks the contract used by the <code>equals()</code> method, which also uses
     * <code>Suit</code> to determine equality.
     *
     * @param c the Card to be compared
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(final Card c) {
        // compare rank only
        return this._rank.compareTo(c._rank);
    }
}
