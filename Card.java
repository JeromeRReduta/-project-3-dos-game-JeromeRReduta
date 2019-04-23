//********************************************************************
//  Card.java       
//
// 
//********************************************************************

// Note: Allowed to modify it to make it more generic (i.e. not
// ... have suit names) if you want
import java.util.Random;

public class Card
{

    // Edited: Removed final vars and faceName to make class more
    // ...generic/adaptable for DosCard

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

    public void setFace(int update) {
        face = update;
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

    public void setSuit(int update) {
        suit = update;
    }

    public String toString()
    {
        return face + " of " + suitName;
    }
}
