/**
 * Card.java
 *
 * Class representing a playing card.
 */

package com.appian.carddeck;

public class Card implements Comparable<Card> {
    private Rank _rank;
    private Suit _suit;
    private int _hashCode;

    /**
     *
     * @param r
     * @param s
     */
    public Card(Rank r, Suit s) {
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
     *
     * @return
     */
    public Rank getRank() {
        return _rank;
    }

    /**
     *
     * @return
     */
    public Suit getSuit() {
        return _suit;
    }

    /**
     *
     * @return
     */
    public boolean isFaceCard() {
        return _rank.ordinal() >= Rank.JACK.ordinal();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return _rank.toString() + " of " + _suit.toString();
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        // short-cut optimization
        if (this == obj) {
            return true;
        }

        // type check, also handles null reference
        if (!(obj instanceof Card)) {
            return false;
        }

        // check fields using their equals() methods
        Card c = (Card)obj;
        return this._rank.equals(c._rank) && this._suit.equals(c._suit);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return _hashCode;
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public int compareTo(Card c) {
        // compare rank first
        int result = this._rank.compareTo(c._rank);
        // if rank is equal, compare suit
        if (result == 0) {
            result = this._suit.compareTo(c._suit);
        }
        return result;
    }
}
