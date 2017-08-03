/**
 * IDeck.java
 *
 * Interface specifying operations that a deck of cards must implement.
 */

package com.appian.carddeck;

public interface IDeck {
    /**
     *
     */
    void shuffle();

    /**
     *
     * @return
     */
    Card dealOneCard();
}
