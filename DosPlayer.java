public class DosPlayer extends Player {

    DosHand hand = new DosHand();

    public DosPlayer(String name) {
        super(name);
    }

    public void getMatches(CardStack centerRow) {

        String singleMatches = "SINGLE NUMBER MATCHES:\n========================\n";
        String doubleMatches = "DOUBLE NUMBER MATCHES:\n========================\n";

        for (int index = 0; index < centerRow.getSize(); index ++) {
            singleMatches += hand.getSingleNumberMatches(centerRow.getCard(index));
            doubleMatches += hand.getDoubleNumberMatches(centerRow.getCard(index));

        }

        System.out.println(singleMatches + "\n\n\n" + doubleMatches);
    }

    public CardStack getHand() {
        return hand;
    }

    public void moveCard(Card c, CardStack newLocation) {
        hand.moveCard(c, newLocation);
    }

    public String displayInfo() {
        return super.getName() + "\n" + "HAND: \n" + hand;
    }
}
