import com.appian.carddeck.Card;
import com.appian.carddeck.Deck;
import com.appian.carddeck.Rank;
import com.appian.carddeck.Suit;

public class Main {

    public static void main(String[] args) {
        // basic sanity checks

        Deck d = new Deck();
        System.out.println("deck: " + d.toString());
        System.out.println("hash code: " + d.hashCode());
        System.out.println("deck size: " + d.getSize());

        // check shuffle() method
        d.shuffle();
        System.out.println("deck: " + d.toString());
        System.out.println("hash code: " + d.hashCode());
        System.out.println("deck size: " + d.getSize());

        d.shuffle();
        System.out.println("deck: " + d.toString());
        System.out.println("hash code: " + d.hashCode());
        System.out.println("deck size: " + d.getSize());

        // check sort()
        d.sort();
        System.out.println("deck: " + d.toString());
        System.out.println("hash code: " + d.hashCode());
        System.out.println("deck size: " + d.getSize());

        // check dealOneCard()
        int num_deals = Rank.values().length * Suit.values().length + 2;
        for (int i = 0; i < num_deals; ++i) {
            Card c = d.dealOneCard();
            if (c == null) {
                System.out.println("card " + (i + 1) + " is null!");
                System.out.println("deck: " + d.toString());
                System.out.println("hash code: " + d.hashCode());
                System.out.println("deck size: " + d.getSize());
                System.out.println("");
                continue;
            }
            System.out.println("card " + (i + 1) + ": " + c.toString());
            System.out.println("hash code: " + c.hashCode());
            System.out.println("deck size: " + d.getSize());
            System.out.println("");
        }
    }
}
