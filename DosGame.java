// DosGame.java.

/* To do:
    Set-up:
            Deck:
                Create full deck
                Display cards
                Shuffle cards
                Display cards
            Players:

                Ask players for names
                Instantiate 1 player/name
                Deal each player 7 cards, alternating
                Display each player's info

            Center Row:
                Deal 2 cards to center row (note: put in instantiation)
                Display 2 cards from Center Row
            Discard Pile:
                Instantiate empty discard pile

    Game:
        Display center row
        Display single and double # matches
        If there is > 1 match, MUST play at least 1 of matches
        If no match, must draw a card
            Card randomly chosen to be moved from hand to Center Row

    End turn:

        Show player's bonus pts
        Refresh Center Row
        Display Center Row
        If player has bonus points, randomly put cards from hand to Center Row
        Display Center Row
        Go to Next player

    Check for end:
        Decide winner if:
            - player has 0 cards in hand - returns player's index
            - player has >= 5 pts in runningTotal - returns player's index
            - deck has 0 cards (in this case, player w/
                    least cards wins) - returns -1

    Avengers - End game:
        Gets input from checkForEnd()

        if (input > 0) {
            player w/ that index printed as winner
         }

         if (input == -1) {
             get Measurable class from Canvas
             print player w/ MINIMUM cards in hand as winner

             if (>1 person w/ minimum cards)
              {it's tie among those people
              }

            }





 */

import java.util.*;

public class DosGame {

    // Creates filled, shuffled DosDeck
    public static DosDeck setUpDeck() {
        DosDeck deck = new DosDeck();
        deck.fillDeck();

        System.out.println(deck);
        deck.shuffle();
        System.out.println(deck);

        return deck;
    }

    // Creates array of players - 1 player/name
    public static DosPlayer[] setUpPlayers(Scanner scan, DosDeck deck) {

        // Asks for # of players, makes array for that many players
        System.out.println("How many players?");
        // Thanks to https://stackoverflow.com/questions/8232980/java-for-loop-skips-runs-twice
        // For loop was running twice - this taught me how to fix that

        DosPlayer[] players = new DosPlayer[scan.nextInt()];

        // scan.nextLine() after scan.nextInt() is essential
        scan.nextLine();

        // Creates players w/ input name
        for (int index = 0; index < players.length; index++) {
            System.out.println("Enter name: ");
            players[index] = new DosPlayer(scan.nextLine());
        }

        // Deals 7 cards, alternating, per player
        for (int index = 0; index < 7; index++) {
            for (int i = 0; i < players.length; i++) {
                CardHolder.moveCard(deck, players[i].getHand());
            }
        }

        // Displays info of all players
        for (int index = 0; index < players.length; index++) {
            System.out.println(players[index].displayInfo());
        }

        return players;
    }

    // Creates CenterRow and displays info
    public static CenterRow setUpCenterRow(DosDeck deck) {
        CenterRow cRow = new CenterRow(deck);
        System.out.println(cRow.display());

        return cRow;
    }

    // Player p plays 1 turn
    public static void playTurn(Scanner scan, DosPlayer p, DosDeck deck, CenterRow cRow, CardStack discard) {
        // Displays p  and centerRow info
        System.out.println(p.displayInfo());
        System.out.println(cRow.display());

        DosHand hand = p.getHand();

        // Displays p's matching cards w/ cards in centerRow
        hand.displayMatches(cRow);

        ArrayList<Card[]> options = hand.getMatches(cRow);
        ArrayList<Integer> stackNums = hand.getStackNums(cRow);

        // Case: p has matching cards - p MUST pick one match
        if (options.size() > 0) {
            System.out.println("Choose a match");
            int choice = scan.nextInt();
            String repeat = "";
            scan.nextLine();

            // Checks if inputted card(s) color match
            cRow.getColorMatch(p, stackNums.get(choice-1), options.get(choice-1));

            // Matches cards w/ stack
            cRow.matchCards(stackNums.get(choice-1), hand, options.get(choice-1));

            // Whole stack moved to discard pile (stack can
            // ...only be matched w/ once/turn
            cRow.moveStack(stackNums.get(choice-1), discard);

            // Case: Matched card, and have more options left
            // Matching another card is optional, however
            // ...will draw a card if you have no matches left
            // ...so pay attention!
            if (options.size() > 0) {

                System.out.println("Match another card? [0] No [1] Yes");

                repeat = scan.nextLine();

            }

            if (repeat.equals("1")) {
                playTurn(scan, p, deck, cRow, discard);
            }


        }

        // Case: No matches - draw card, random card from
        // ...hand added to centerRow
        else {
            CardHolder.moveCard(deck, hand);

            int last = hand.getSize()-1;

            System.out.println("Card drawn: " + hand.getCard(last));

            cRow.addRandomCard(hand);

            System.out.println("Random card added from hand to center row\n\n");
        }

        // Display: p's info, centerRow's info, p's name
        // ...ending turn
        System.out.println(p.displayInfo());
        System.out.println(cRow.display());
        System.out.println(p.getName() + " ends turn.");
        System.out.println("======================\n\n");

    }

    // Sequence of events after ending turn:
    // 1) Print how many bonus points p has
        // - If p gets color match, 1 bonus pt for card
        // ...that matched color

    // 2) Refresh centerRow - remove empty rows, then add
    // ...cards to centerRow until row has 2 stacks

    // 3) Add # of cards = to bonus pts in centerRow
    public static void endTurn(DosPlayer p, DosDeck deck, CenterRow cRow, CardStack discard) {

        // Display pts
        int pts = p.getPoints();
        System.out.println(p.getName() + " has " + pts + " bonus points.");

        // Refresh centerRow
        cRow.refresh(deck, discard);

        // Display centerRow
        System.out.println("Center Row refreshed:\n "
                + cRow.display());

        // Case: p has bonus pts - adds random card from hand
        // ... to centerRow
        if (pts > 0) {
            for (int index = 0; index < pts; index++) {
                cRow.addRandomCard(p.getHand());
            }

            System.out.println(pts + " cards randomly added to Center Row from hand");
        }

    }
    /* Check for end:
    Decide winner if:
            - player has 0 cards in hand
            - player has >= 5 pts in runningTotal
            - deck has 0 cards (in this case, player w/
            least cards wins) */

    /* message:
        0 = keep going
        # > 0 = player w/ index # wins
        -1 = deck empty
     */

    public static int checkForEnd(DosPlayer p, DosDeck deck, int index) {

        // Default case - go to next round
        int message = 0;

        // Case: p's hand empty - p wins
        if (p.getHand().getSize() == 0) {
            System.out.println(p.getName() + " has run out of cards!");
            message = index;
        }

        // Case: p has >= total bonus pts - p wins
        else if (p.getTotalPoints() >= 5) {
            System.out.println(p.getName() + " has >= 5 bonus points!");
            message = index;
        }

        // Case: deck empty - give message that deck is empty
        else if (!deck.hasMoreCards()) {
            message = -1;
        }

        return message;
    }

    // Gives endgame screen depending on inputted message

    public static void endGame(DosPlayer[] players, int message) {

        // Case: 1 player won
        if (message > 0) {
            DosPlayer p = players[message];
            System.out.println(p.getName() + " wins!");

        }

        // Case: Deck empty (multiple players may win)
        else {
            int min = DosPlayer.getMinSize(players);
            DosPlayer.getWonLost(players, min);


        }
    }

    // Program
    public static void main(String[] args) {

        // Creating objects and var
        Scanner scan = new Scanner(System.in);
        DosDeck deck = setUpDeck();
        DosPlayer[] players = setUpPlayers(scan, deck);
        CenterRow cRow = setUpCenterRow(deck);
        DosDeck discard = new DosDeck();

        // Index of winner

        // 0 = no-one yet, # > 0 = player w/ index #
        int winner = 0;

        // Index of person playing this round
        int index = 0;

        // Game:
        while (winner == 0) {
            playTurn(scan, players[index], deck, cRow, discard);
            endTurn(players[index], deck, cRow, discard);
            System.out.println(checkForEnd(players[index], deck, index));

            // Goes from 0 to players.length - 1 and loops back
            index = (index + 1)%players.length;
        }

        // End of game:
        endGame(players, index);
    }
}