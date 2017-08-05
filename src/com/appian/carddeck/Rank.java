/*
 *    File: Rank.java
 *  Author: Robert J. Orr
 */
package com.appian.carddeck;

/**
 * Enumeration representing the thirteen traditional ranks of a deck of playing cards.
 *
 * @author Robert Orr
 * @version 1.0
 */
public enum Rank {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING;

    // TODO: i18n

    /**
     * Returns a string representation of the Rank, in capitalized form.
     *
     * @return string representation of Rank
     */
    @Override
    public String toString() {
        return Character.toUpperCase(this.name().charAt(0)) + this.name().substring(1).toLowerCase();
    }
}
