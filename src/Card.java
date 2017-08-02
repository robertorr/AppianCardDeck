/**
 * Card.java
 *
 * Class representing a playing card.
 */
public final class Card implements Comparable<Card> {
    private Rank _rank;
    private Suit _suit;

    /**
     *
     * @param r
     * @param s
     */
    void Card(Rank r, Suit s) {
        _rank = r;
        _suit = s;
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
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "";
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return 0;
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public int compareTo(Card c) {
        return 0;
    }
}
