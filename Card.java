//********************************************************************
//  Card.java       
//
// 
//********************************************************************

// Note: Allowed to modify it to make it more generic (i.e. not
// ... have suit names) if youw ant
import java.util.Random;

public class Card
{
    private final static int NUM_FACES = 13;
    private final static int NUM_SUITS = 4;

    protected int face, suit;
    private String suitName;


    //-----------------------------------------------------------------
    //  Creates a card of the specified suit and face value.
    //-----------------------------------------------------------------
    public Card(int faceValue, int suitValue)
    {
        face = faceValue;
        suit = suitValue;
    }

    //-----------------------------------------------------------------
    //  Returns the face (numeric value) of this card.
    //-----------------------------------------------------------------
    public int getFace()
    {
        return face;
    }

    //-----------------------------------------------------------------
    //  Returns the suit (numeric value) of this card.
    //-----------------------------------------------------------------
    public int getSuit()
    {
        return suit;
    }

    //-----------------------------------------------------------------
    //  Returns the string representation of this card, including
    //  both face and suit.
    //-----------------------------------------------------------------

    public String getSuitName() {return suitName;}

    public void setSuitName(String update) {
        suitName = update;
    }
    public String toString()
    {
        return face + " of " + suitName;
    }
}
