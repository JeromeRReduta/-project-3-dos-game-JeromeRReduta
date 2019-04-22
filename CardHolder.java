public interface CardHolder {

    public static void moveCards(CardStack origin, CardStack dest, int amount) {
        for (int i = 0; i < amount; i++) {
            dest.addCard(origin.deal());
        }
    }
}
