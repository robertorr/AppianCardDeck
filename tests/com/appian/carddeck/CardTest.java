/*
 *    File: CardTest.java
 *  Author: Robert J. Orr
 */
package com.appian.carddeck;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * jUnit test class for Card
 *
 * @author Robert Orr
 * @version 1.0
 */
public class CardTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_card() throws Exception {
        Card c = new Card(Rank.ACE, Suit.SPADES);
        assertNotNull("card is null", c);
        // check contract
        assertTrue("card is not Comparable", c instanceof Comparable);
        assertTrue("card is not Serializable", c instanceof Serializable);
    }

    @Test(expected = NullPointerException.class)
    public void test_card_null_rank() throws Exception {
        new Card(null, Suit.SPADES);
    }

    @Test(expected = NullPointerException.class)
    public void test_card_null_suit() throws Exception {
        new Card(Rank.ACE, null);
    }

    @Test
    public void test_getRank() throws Exception {
        Card c = new Card(Rank.ACE, Suit.SPADES);
        assertTrue("rank is incorrect", c.getRank() == Rank.ACE);
    }

    @Test
    public void test_getSuit() throws Exception {
        Card c = new Card(Rank.ACE, Suit.SPADES);
        assertTrue("suit is incorrect", c.getSuit() == Suit.SPADES);
    }

    @Test
    public void test_isFaceCard() throws Exception {
        Card c1 = new Card(Rank.ACE, Suit.SPADES);
        assertFalse("card is not a face card", c1.isFaceCard());
        Card c2 = new Card(Rank.JACK, Suit.SPADES);
        assertTrue("card is a face card", c2.isFaceCard());
    }

    @Test
    public void test_toString() throws Exception {
        // TODO: i18n
        Card c = new Card(Rank.ACE, Suit.SPADES);
        assertEquals("Ace of Spades", c.toString());
    }

    @Test
    public void test_equals() throws Exception {
        Card c1 = new Card(Rank.ACE, Suit.SPADES);
        Card c2 = new Card(Rank.ACE, Suit.SPADES);
        assertTrue("card failed equality test", c1.equals(c1));
        assertTrue("card failed equality test", c1.equals(c2));
        assertTrue("card failed equality test", c2.equals(c1));

        Card c3 = new Card(Rank.ACE, Suit.CLUBS);
        assertFalse("card failed equality test", c1.equals(c3));
        assertFalse("card failed equality test", c1.equals(null));
        assertFalse("card failed equality test", c1.equals(new Object()));
    }

    @Test
    public void test_hashCode() throws Exception {
        // check that we get the same hash code on two invocations
        Card c = new Card(Rank.ACE, Suit.SPADES);
        assertTrue("hash codes are supposed to be the same", c.hashCode() == c.hashCode());

        List<Card> cards = new ArrayList<>(Rank.values().length * Suit.values().length);
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                cards.add(new Card(r, s));
            }
        }

        // test card hash code against all other cards
        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                assertTrue("hash codes of two different cards are the same",
                        cards.get(i).hashCode() != cards.get(j).hashCode());
            }
        }
    }

    @Test
    public void test_compareTo() throws Exception {
        Card c1 = new Card(Rank.ACE, Suit.SPADES);
        Card c2 = new Card(Rank.ACE, Suit.SPADES);
        Card c3 = new Card(Rank.ACE, Suit.CLUBS);
        Card c4 = new Card(Rank.TWO, Suit.HEARTS);

        assertTrue("comparing card to itself failed", c1.compareTo(c1) == 0);
        assertTrue("comparing card to same value failed", c1.compareTo(c2) == 0);
        assertTrue("comparing card to same rank failed", c1.compareTo(c3) == 0);
        assertTrue("comparing card to higher rank failed", c1.compareTo(c4) < 0);
        assertTrue("comparing card to lower rank failed", c4.compareTo(c1) > 0);
    }
}
