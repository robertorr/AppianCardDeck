/*
 *    File: Suit.java
 *  Author: Robert J. Orr
 */
package com.appian.carddeck;

/**
 * Enumeration representing the four traditional suits of a deck of playing cards (clubs, diamonds, hearts, spades).
 * Suit ordinality is the de facto standard established by the card game bridge.
 */
public enum Suit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;

    /**
     * Returns a string representation of the Suit, in capitalized form.
     *
     * @return string representation of Suit
     */
    @Override
    public String toString() {
        return Character.toUpperCase(this.name().charAt(0)) + this.name().substring(1).toLowerCase();
    }
}
