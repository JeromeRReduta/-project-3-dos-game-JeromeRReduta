import java.util.*;

public class CenterRow implements CardHolder{
    ArrayList<DosDeck> centerRow = new ArrayList<DosDeck>();

    public CenterRow(DosDeck deck) {
        addCard(deck);
        addCard(deck);
    }

    public int getSize() {
        return centerRow.size();
    }

    public Card getCard(int row, int index) {
        return centerRow.get(row).getCard(index);
    }

    public void addCard(DosDeck draw) {
        DosDeck newCards = new DosDeck();

        CardHolder.moveCards(draw, newCards, 1);
        centerRow.add(newCards);
    }

    public void matchCards(int stackNum, int limit, DosDeck draw, Card[] cards) {
        for (int index = 0; index < limit; index++ ) {
            CardHolder.moveCards(draw, centerRow.get(index), 1);
        }
    }



    public void moveStack(int index, CardStack dest) {
        DosDeck stack = centerRow.get(index);

        while (stack.hasMoreCards()) {
            CardHolder.moveCards(stack, dest, 1);
        }
    }

    public void refresh(DosDeck draw, CardStack newLocation) {
        int index = 0;
        while (index < centerRow.size()) {
            if (centerRow.get(index).getSize() == 0) {
                moveStack(index, newLocation);
                centerRow.remove(index);
            }

            else {
                index++;
            }
        }

        while (centerRow.size() < 2) {
            DosDeck cards = new DosDeck();
            CardHolder.moveCards(draw, cards, 1);
            centerRow.add(cards);
        }

    }

    public boolean getColorMatch(Player p, int rowNum, Card[] cards) {
        String cardColor = getCard(rowNum, 0).getSuitName();
        String matchColor;



        for (int index = 0; index < cards.length; index++) {
            matchColor = cards[index].getSuitName();

            if (!cardColor.equals(matchColor) || !matchColor.equals("ANY")) {
                System.out.println("Colors don't match!");
                return false;
            }

        }

        System.out.println("All colors match!");

        p.setPoints(cards.length);

        return true;

    }

    public String display() {
        String message = "Center Row:\t";

        for (int index = 0; index < centerRow.size(); index++) {

            if (centerRow.get(index).getSize() == 0) {
                message += "[matched]";
            }

            else {
                message += "[" + centerRow.get(index).getCard(0) + "]";
            }

        }
        return message;
    }

    public String toString() {
        String message = "Stacks from left to right, Cards in each stack from top to bottom: \n\n";

        for (int index = 0; index < centerRow.size(); index++) {

            message += "STACK " + (index + 1) + ":\t";


            message += centerRow.get(index) + "\n";
            }
        return message;
        }
    }

