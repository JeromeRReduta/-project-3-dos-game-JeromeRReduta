public class DosPlayer extends Player {

    private DosHand hand = new DosHand();

    public DosPlayer(String name) {
        super(name);
    }

    public void getMatches(CenterRow cRow) {

        String singleMatches = "SINGLE NUMBER MATCHES:\n========================\n";
        String doubleMatches = "DOUBLE NUMBER MATCHES:\n========================\n";

        for (int index = 0; index < cRow.getSize(); index ++) {
            singleMatches += hand.getSingleNumberMatches(cRow.getCard(index, 0));
            doubleMatches += hand.getDoubleNumberMatches(cRow.getCard(index, 0));

        }

        System.out.println(singleMatches + "\n\n\n" + doubleMatches);
    }

    public DosDeck getHand() {
        return hand;
    }

    public String displayInfo() {
        return super.getName() + "'s TURN:\n\n" + "HAND: \n" + hand + "\n";
    }
}
