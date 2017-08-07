/*
 *    File: CardTest.java
 *  Author: Robert J. Orr
 */
package com.appian.carddeck;

import org.junit.*;

import java.io.Serializable;
import java.util.List;

import static org.junit.Assert.*;

/**
 * jUnit test class for Deck
 *
 * @author Robert Orr
 * @version 1.0
 */
public class DeckTest {
    private static final int TOTAL_NUM_CARDS = Rank.values().length * Suit.values().length;
    private Deck _referenceDeck;

    @Before
    public void setUp() throws Exception {
        _referenceDeck = new Deck();
    }

    @After
    public void tearDown() throws Exception {
        _referenceDeck = null;
    }

    @Test
    public void test_deck() throws Exception {
        Deck d = new Deck();
        assertNotNull("deck is null", d);
        // check contract
        assertTrue("deck is not an IDeck", d instanceof IDeck);
        assertTrue("deck is not Serializable", d instanceof Serializable);
        assertTrue("deck has incorrect length",d.getSize() == DeckTest.TOTAL_NUM_CARDS);
    }

    @Test
    public void test_populateDeck() throws Exception {
        Deck d = new Deck();
        // empty the deck
        for (int i = 0; i < DeckTest.TOTAL_NUM_CARDS; i++) {
            d.dealOneCard();
        }

        // repopulate dek and test against reference
        d.populateDeck();
        assertTrue("deck has incorrect length",d.getSize() == DeckTest.TOTAL_NUM_CARDS);
        assertEquals("deck is not equal to reference deck", _referenceDeck, d);
    }

    @Test
    public void test_reset() throws Exception {
        Deck d = new Deck();
        // shuffle deck and deal one card to reduce deck size
        d.shuffle();
        d.dealOneCard();

        d.reset();
        assertEquals("deck is not equal to reference deck", _referenceDeck, d);
    }

    @Test
    public void test_sort() throws Exception {
        Deck d = new Deck();
        d.shuffle();
        d.sort();
        assertEquals("deck is not equal to reference deck", _referenceDeck, d);
    }

    @Test
    public void test_shuffle() throws Exception {
        // TODO: determine better measure of randomness to test the correctness of shuffle
        Deck d = new Deck();
        d.shuffle();
        assertTrue("deck has incorrect length",d.getSize() == DeckTest.TOTAL_NUM_CARDS);
        assertNotEquals("deck is equal to reference deck but should not be", _referenceDeck, d);
    }

    @Test
    public void test_dealOneCard() throws Exception {
        Deck d = new Deck();
        for (int i = DeckTest.TOTAL_NUM_CARDS - 1; i >= 0; i--) {
            Card c = d.dealOneCard();
            assertNotNull("card is null", c);
            assertEquals("deck has incorrect size", i, d.getSize());
        }

        // check that there are no more cards left
        Card c = d.dealOneCard();
        assertNull("card should be null but is not", c);
        assertEquals("deck has incorrect size", 0, d.getSize());
    }

    @Test
    public void test_getSize() throws Exception {
        Deck d = new Deck();
        assertEquals("deck has incorrect size", DeckTest.TOTAL_NUM_CARDS, d.getSize());
    }

    @Test
    public void test_getCards() throws Exception {
        Deck d = new Deck();
        List<Card> cards = d.getCards();
        assertEquals(DeckTest.TOTAL_NUM_CARDS, cards.size());
        assertFalse(cards.contains(null));
    }

    @Test
    public void test_toString() throws Exception {
        Deck d = new Deck();
        String s = d.toString();
        assertNotNull("toString() returned null", s);
        assertTrue("string has zero length", s.length() > 0);
    }

    @Test
    public void test_equals() throws Exception {
        Deck d = new Deck();
        // test against null
        assertFalse("deck failed equality test", d.equals(null));

        // test against wrong object type
        assertFalse("deck failed equality test", d.equals(new Object()));

        // test reflexivity
        assertTrue("deck failed equality test", d.equals(d));

        // test symmetry
        assertTrue("deck failed equality test", d.equals(_referenceDeck));
        assertTrue("deck failed equality test", _referenceDeck.equals(d));
        d.shuffle();
        assertFalse("deck failed equality test", d.equals(_referenceDeck));
        assertFalse("deck failed equality test", _referenceDeck.equals(d));
    }

    @Test
    public void test_hashCode() throws Exception {
        Deck d = new Deck();
        assertEquals("hash codes are not equal", _referenceDeck.hashCode(), d.hashCode());

        d.shuffle();
        assertNotEquals("hash codes are the same but should be different",
                _referenceDeck.hashCode(), d.hashCode());
    }
}
