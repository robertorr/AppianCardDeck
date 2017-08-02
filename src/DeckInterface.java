/**
 * DeckInterface.java
 *
 * Interface specifying operations that a deck of cards must implement.
 */
public interface DeckInterface {
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
