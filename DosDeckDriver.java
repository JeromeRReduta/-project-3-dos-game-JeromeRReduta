import java.util.*;
public class DosDeckDriver {
    public static void main(String[] args) {
        Random rng = new Random();

        DosDeck a = new DosDeck();
        DosDeck b = new DosDeck();

        a.fillDeck();
        System.out.println(a);
        System.out.println(a.getSize());

        a.shuffle(rng);
        System.out.println(a);

        System.out.println(a.getSize());

        a.moveCard(a.getCard(0), b);

        System.out.println(a.getSize());
        System.out.println(a);
        System.out.println(b);
    }
}
