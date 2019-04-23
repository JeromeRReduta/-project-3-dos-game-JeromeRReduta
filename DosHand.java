import java.util.*;

public class DosHand extends DosDeck {

    // Counts how many matches made, helpful for displaying matches
    private int matchCount = 0;

    public DosHand() {
        super();
    }

    // Returns ArrayList of single # matches
    public ArrayList<Card[]> getSingleNumberMatches(Card c) {


        ArrayList<Card[]> cards = new ArrayList<Card[]>();

        for (int index = 0; index < super.getSize(); index++) {

            /* Instead of putting all cards in respective stack
            when matching, all cards from that stack are already
            put in discard pile.

            This will create a NullPointerException; however,
            having that there is useful, so instead will simply
            not try to match any cards to null stacks.
             */
            try {
                if (super.getCard(index).getFace() == c.getFace()) {
                    Card[] handCard = {super.getCard(index), c};
                    cards.add(handCard);
                }
            }
            catch (NullPointerException e) {
            }
        }
        return cards;
    }

    // Returns ArrayList of Double # Matches
    public ArrayList<Card[]> getDoubleNumberMatches(Card c) {
        ArrayList<Card[]> cards = new ArrayList<Card[]>();


        for (int index = 0; index < super.getSize(); index++) {

            // Same as w/ Single # Matches
            try {
                for (int i = index + 1; i < super.getSize(); i++) {
                    Card card1 = super.getCard(index);
                    Card card2 = super.getCard(i);

                    int face1 = card1.getFace();
                    int face2 = card2.getFace();
                    int total = c.getFace();

                    boolean condition1 = (index != i);
                    boolean condition2 = (face1 + face2 == total);
                    boolean condition3 = (face1 + face2 < total && (face1 == -1 || face2 == -1));


                    if (condition1 && (condition2 || condition3)) {
                        Card[] match = {card1, card2};
                        cards.add(match);


                    }
                }

            } catch (NullPointerException e) {

            }

        }
        return cards;
    }


    // Gives string rep. of matches and what stack they match with
    public void displayMatches(CenterRow cRow) {

        // Resets matchCount;
        matchCount = 0;

        String singleMatches = "SINGLE NUMBER MATCHES:\n========================\n";
        String doubleMatches = "DOUBLE NUMBER MATCHES:\n========================\n";

        for (int index = 0; index < cRow.getSize(); index ++) {
            ArrayList<Card[]> results = getSingleNumberMatches(cRow.getCard(index, 0));

            /* Adds to message the matching card, what stack it
            matches to, and what # match it is (1st match, 2nd,
            etc.)
             */

            for (int i = 0; i < results.size(); i++) {
                matchCount++;
                singleMatches += matchCount + ") " + results.get(i)[0] + " to stack " + (index+1) + "\n";
            }

        }

        for (int index = 0; index < cRow.getSize(); index++) {
            ArrayList<Card[]> results = getDoubleNumberMatches(cRow.getCard(index, 0));

            for (int i = 0; i < results.size(); i++) {
                matchCount++;

                /* Adds to message the matching cards, what stack it
            matches to, and what # match it is (1st match, 2nd,
            etc.)
             */
                doubleMatches += matchCount + ") [" + results.get(i)[0] + ", " + results.get(i)[1]
                        + "] to stack " + (index+1) + "\n";
            }
        }

        // Prints single and double matches as lists
        System.out.println(singleMatches + "\n\n\n" + doubleMatches);
    }

    // Gets matches as ArrayList of Card Arrays

    /* Note: Using ArrayList of Card Arrays so I can
    treat single and double matches in the same way
     */

    // Note: matchCount # for each match in displayMatches()
    // ... is equal to index for each match in getMatches() + 1
    public ArrayList<Card[]> getMatches(CenterRow cRow) {
        ArrayList<Card[]> cardList = new ArrayList<Card[]>();

        // single # matches
        for (int index = 0; index < cRow.getSize(); index++) {

            ArrayList<Card[]> singleMatch = getSingleNumberMatches(cRow.getCard(index, 0));


            for (int i = 0; i < singleMatch.size(); i++) {
                cardList.add(singleMatch.get(i));

            }
        }

        // double # matches
        for (int index = 0; index < cRow.getSize(); index++) {
            ArrayList<Card[]> doubleMatch = getDoubleNumberMatches(cRow.getCard(index, 0));

            for (int i = 0; i < doubleMatch.size(); i++) {
                cardList.add(doubleMatch.get(i));
            }
        }

        return cardList;
    }

    // Returns ArrayList of stackNums for stacks that matching
    // ...cards match w/

    // Note: index for a match in getMatches() = index...
    // ... for a # in getStackNums()
    public ArrayList<Integer> getStackNums(CenterRow cRow) {

        ArrayList<Integer> numList = new ArrayList<Integer>();
        for (int index = 0; index < cRow.getSize(); index++) {

            ArrayList<Card[]> singleMatch = getSingleNumberMatches(cRow.getCard(index, 0));


            for (int i = 0; i < singleMatch.size(); i++) {
                numList.add(index);

            }
        }

        for (int index = 0; index < cRow.getSize(); index++) {
            ArrayList<Card[]> doubleMatch = getDoubleNumberMatches(cRow.getCard(index, 0));

            for (int i = 0; i < doubleMatch.size(); i++) {
                numList.add(index);
            }
        }

        return numList;
    }
}
