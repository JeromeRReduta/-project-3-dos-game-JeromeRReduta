// CenterRow.java

import java.util.*;

public class CenterRow implements CardHolder{
    ArrayList<DosDeck> centerRow = new ArrayList<DosDeck>();

    // Initializes w/ 2 cards drawn from deck in centerRow
    public CenterRow(DosDeck deck) {
        addCard(deck);
        addCard(deck);
    }

    // Returns # of stacks in center row
    public int getSize() {
        return centerRow.size();
    }

    // Returns a certain card in a certain row
    public Card getCard(int row, int index) {
        return centerRow.get(row).getCard(index);
    }

    // Adds a stack to centerRow and adds top card to it
    public void addCard(DosDeck draw) {
        DosDeck newCards = new DosDeck();

        CardHolder.moveCard(draw, newCards);
        centerRow.add(newCards);
    }

    // addCard() but w/ random card instead
    public void addRandomCard(DosDeck draw) {
        DosDeck newCards = new DosDeck();

        CardHolder.randomMoveCard(draw, newCards);
        centerRow.add(newCards);
    }

    // Takes designated cards and puts them into corresponding
    // ...numbered stack
    public void matchCards(int stackNum, DosDeck deck, Card[] cards) {

        CardHolder.moveCardArray(cards, deck, centerRow.get(stackNum));
    }

    // Moves all cards in one stack into dest, leaving empty/null
    // ... pile (will be displayed as [matched]

    public void moveStack(int index, CardStack dest) {
        DosDeck stack = centerRow.get(index);

        while (stack.hasMoreCards()) {
            CardHolder.moveCard(stack, dest);
        }
    }

    // Removes all empty/matched stacks and refills centerRow
    // ...until there are 2 stacks
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
            CardHolder.moveCard(draw, cards);
            centerRow.add(cards);
        }

    }

    /* If all colors in a matched stack match (wild color
    cards are considered to match w/ card in centerRow),
    player gets bonus pts = to cards they put in stack.

    Also gives message
     */
    public boolean getColorMatch(Player p, int rowNum, Card[] cards) {
        String cardColor = getCard(rowNum, 0).getSuitName();
        String matchColor;



        for (int index = 0; index < cards.length; index++) {
            matchColor = cards[index].getSuitName();

            // Case: card and match color are different or
            // ...the cared is not an "ANY" color

            if (!cardColor.equals(matchColor) && !matchColor.equals("ANY")) {
                return false;
            }

        }

        // Case: matching colors or card is "ANY" color

        System.out.println("All colors match! " + p.getName() + " earns " + cards.length + " bonus points!");

        p.setPoints(cards.length);

        return true;

    }

    // Give string representation of top cards in stacks of
    // ...centerRow
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

