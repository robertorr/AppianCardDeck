import com.appian.carddeck.tests.Card;
import com.appian.carddeck.tests.Deck;

public class Main {

    public static void main(String[] args) {
        Deck d = new Deck();
        System.out.println("deck: " + d.toString());
        System.out.println("hash code: " + d.hashCode());
        System.out.println("deck size: " + d.getSize());

        d.shuffle();
        System.out.println("deck: " + d.toString());
        System.out.println("hash code: " + d.hashCode());
        System.out.println("deck size: " + d.getSize());

        d.shuffle();
        System.out.println("deck: " + d.toString());
        System.out.println("hash code: " + d.hashCode());
        System.out.println("deck size: " + d.getSize());

        d.sort();
        System.out.println("deck: " + d.toString());
        System.out.println("hash code: " + d.hashCode());
        System.out.println("deck size: " + d.getSize());

        for (int i = 0; i < 55; ++i) {
            Card c = d.dealOneCard();
            if (c == null) {
                System.out.println("card " + i + " is null!");
                System.out.println("deck: " + d.toString());
                continue;
            }
            System.out.println("card " + i + ": " + c.toString());
            System.out.println("hash code: " + c.hashCode());
            System.out.println("deck size: " + d.getSize());
            System.out.println("");
        }
    }
}
