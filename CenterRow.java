import java.util.*;

public class CenterRow {
    ArrayList<DosDeck> centerRow = new ArrayList<DosDeck>();

    public CenterRow() {
    }

    public void addCard(DosDeck draw) {
        DosDeck newCards = new DosDeck();

        draw.moveCard(draw.getCard(0), newCards);
        centerRow.add(newCards);
    }

    public void matchCards(int stackNum, int limit, DosDeck draw, Card[] cards) {
        for (int index = 0; index < limit; index++ ) {
            draw.moveCard(cards[index], centerRow.get(stackNum));
        }
    }



    public void moveStack(int index, CardStack newLocation) {
        DosDeck stack = centerRow.get(index);

        while (stack.hasMoreCards()) {
            stack.moveCard(stack.getCard(0), newLocation);
        }
    }

    public void refresh(CardStack draw, CardStack newLocation) {
        int index = 0;
        while (index < centerRow.size()) {
            if (centerRow.get(index).getSize() > 1) {
                moveStack(index, newLocation);
                centerRow.remove(index);
            }

            else {
                index++;
            }
        }

        while (centerRow.size() < 2) {
            DosDeck cards = new DosDeck();
            draw.moveCard(draw.getCard(0), cards);
            centerRow.add(cards);
        }

    }

    public String display() {
        String message = "Center Row:\t";

        for (int index = 0; index < centerRow.size(); index++) {
            message += "[" + centerRow.get(index).getCard(0) + "]";
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

