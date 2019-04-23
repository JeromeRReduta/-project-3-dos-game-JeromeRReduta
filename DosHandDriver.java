import java.util.*;

public class DosHandDriver {
    public static void main(String[] args) {

        DosDeck deck = new DosDeck();
        deck.fillDeck();

        CenterRow cRow = new CenterRow(deck);

        int[] list = {1, 2, 3, 4, 5};
        int[] repeat = {1, 2};
        DosHand a = new DosHand();

        a.addCards(1, list, repeat);

        System.out.println(a);

        System.out.println(a.getSingleNumberMatches(new DosCard(5, 2)));
        System.out.println(a.getDoubleNumberMatches(new DosCard(4, 2)));

        a.displayMatches(cRow);
        System.out.println(a.getMatches(cRow));



    }
}
