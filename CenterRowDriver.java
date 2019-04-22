public class CenterRowDriver {
    public static void main(String[] args) {

        DosDeck deck = new DosDeck();

        deck.fillDeck();
        Card[] cards = {new DosCard(2, 1), new DosCard(3, 2), new DosCard(4, 5)};

        CenterRow a = new CenterRow();
        for (int index = 0; index < 5; index++) {
            a.addCard(deck);
            a.matchCards(index, 3, deck, cards);

        }

        System.out.println(a);
        System.out.println(a.display());
        a.moveStack(0, deck);

        System.out.println(a);

        a.matchCards(0, 1, deck, cards);
        System.out.println(a);

        a.refresh(deck, deck);

        System.out.println(a);


    }
}
