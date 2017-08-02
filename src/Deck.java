
import java.util.ArrayList;
import java.util.List;

/**
 * Deck.java
 *
 * Class representing a traditional deck of cards comprised of 52 cards with four suits and thirteen ranks.
 */
public class Deck implements DeckInterface {

    private List<Card> _cards;

    /**
     *
     */
    public Deck() {
        _cards = new ArrayList<>(52);
    }

    /**
     *
     */
    public void sort() {

    }

    /**
     *
     */
    @Override
    public void shuffle() {

    }

    /**
     *
     * @return
     */
    @Override
    public Card dealOneCard() {
        return null;
    }

    /**
     *
     * @return
     */
    public int getNumberOfCardsRemaining() {
        return 0;
    }

    /**
     *
     * @return
     */
    public List<Card> getCards() {
        return null;
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
}
