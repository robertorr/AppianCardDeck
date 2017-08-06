/*
 *    File: IDeck.java
 *  Author: Robert J. Orr
 */
package com.appian.carddeck.tests;

/**
 * Interface specifying operations that a deck of cards must implement.
 */
public interface IDeck {
    /**
     * Shuffle returns no value, but results in the cards in the deck being randomly permuted.
     */
    void shuffle();

    /**
     * This function returns one card from the deck to the caller.
     * Specifically, a call to <code>shuffle()</code> followed by 52 calls to <code>dealOneCard()</code>
     * results in the caller being provided all 52 cards of the deck in a random order.
     * If the caller then makes a 53rd call to <code>dealOneCard()</code>, no card is dealt and
     * <code>null</code> is returned.
     *
     * @return A single <code>Card</code> from the deck
     */
    Card dealOneCard();
}
