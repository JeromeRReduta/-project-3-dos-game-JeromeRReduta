public class DosHand extends DosDeck {

    public DosHand() {
        super();
    }

    public String getSingleNumberMatches(Card c) {
        String message = "";
        for (int index = 0; index < super.getSize(); index++) {
            Card handCard = super.getCard(index);
            if (handCard.getFace() == c.getFace()) {
                message += handCard + "\n\n";
            }
        }

        return message;
    }

    public String getDoubleNumberMatches(Card c) {
        String message = "";

        for (int index = 0; index < super.getSize(); index++) {
            for (int i = index + 1; i < super.getSize(); i++) {
                Card card1 = super.getCard(index);
                Card card2 = super.getCard(i);

                if (index != i && card1.getFace() + card2.getFace() == c.getFace()) {
                    message += card1 + " AND " + card2 + "\n";

                }
            }

        }
        return message;
    }
}
