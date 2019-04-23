// DosDeck.java

import java.util.*;
public class DosDeck extends CardStack implements CardHolder {

    /* Create: 3 each 1, 3, 4, 5, for every color
        2 each 6-10 for every color
        2 wild cards for # for every suit
        12 wild cards for every color
     */
    Random rng = new Random();

    public DosDeck() {
        super();
    }

    // Adds (repeat) copies of cards w/ faces listed in (list)
    // ...and suits listed in (colors)
    public void addCards(int repeat, int[] list, int[] colors) {
        for (int suit = 0; suit < colors.length; suit++) {
            for (int loop = 0; loop < repeat; loop++) {
                for (int index = 0; index < list.length; index++) {
                    super.addCard(new DosCard(list[index], colors[suit]));
                }
            }
        }
    }
    // Made as shortcut to fill deck w/ all 108 cards in 1 method
    public void fillDeck() {
        int[] lowNum = {1, 3, 4, 5};
        int[] highNum = {6, 7, 8, 9, 10, -1};
        int[] any2 = {2};

        int[] colors = {1, 2, 3, 4};
        int[] wild = {5};

        addCards(3, lowNum, colors);
        addCards(2, highNum, colors);
        addCards(12, any2, wild);
    }

    // Shuffles deck
    public void shuffle()
    {
        int choice = 0;

        for (int index = 0; index < super.getSize(); index ++) {
            choice = rng.nextInt(super.getSize());
            super.swap(index, choice);
        }
    }

    public String toString()
    {
        String result = "";
        for(int i=0; i<super.getSize(); i++)
        {
            result+="[" + super.getCard(i).toString()+"] ";
        }
        return result;
    }
}


