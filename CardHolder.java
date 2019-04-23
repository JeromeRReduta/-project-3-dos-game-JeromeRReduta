// CardHolder.java

// Honestly I thought this would be useful, not sure if it
// ... ended up that way

public interface CardHolder {

    // Moves top card from origin to dest
    public static void moveCard(CardStack origin, CardStack dest) {

        dest.addCard(origin.deal());

    }

    // Moves random card from origin to dest
    public static void randomMoveCard(CardStack origin, CardStack dest) {
        dest.addCard(origin.randomDeal());
    }

    // Moves array of cards from origin to dest
    public static void moveCardArray(Card[] cards, CardStack origin, CardStack dest) {
        for (int index = 0; index < cards.length; index++) {
            dest.addCard(cards[index]);
            origin.removeCard(cards[index]);
        }
    }
}
