import java.util.*;

public class DosGame {

    public static DosDeck makeDosDeck(Random rng) {
        DosDeck deck = new DosDeck();
        deck.fillDeck();
        deck.shuffle(rng);

        return deck;
    }

    public static ArrayList<DosPlayer> makeDosPlayers(Random rng, Scanner scan) {
        ArrayList<DosPlayer> players = new ArrayList<DosPlayer>();

        System.out.print("Enter player 1 name: ");

        String answer = scan.nextLine();

        for (int index = 2; !answer.toUpperCase().equals("PUMPERNICKEL"); index++) {
            players.add(new DosPlayer(answer));
            System.out.println("Enter player " + index + " name or type 'PUMPERNICKEL' to begin game:");

            answer = scan.nextLine();
        }

        return players;

    }

    public static void deal(ArrayList<DosPlayer> players, DosDeck deck) {
        for (int repeat = 0; repeat < 7; repeat++) {
            for (int index = 0; index < players.size(); index++) {
                CardHolder.moveCards(deck, players.get(index).getHand(), 1);
            }
        }
    }


    public static int pickOption(DosPlayer p, DosDeck deck, CenterRow cRow, Scanner scan) {
        System.out.println(p.displayInfo());
        System.out.println(cRow.display());
        p.getMatches(cRow);

        String input = "";

        while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")) {


            System.out.println("Choose: \n" +
                    "1: Single Number Match\n" +
                    "2: Double Number Match\n" +
                    "3: Draw Card\n")

            input = scan.nextLine();
        }

        return Integer.parseInt(input);
    }

    public static void NumMatch(DosPlayer p, DosDeck deck, CenterRow cRow, DosDeck discardPile, Scanner scan, int cardAmount) {
        Card choice = new Card(5, 5);
        int answer = 0;
        boolean done = false;
        Card[] match = new DosCard[cardAmount];
        int total = 0;


        while (total != choice.getFace() && done == false) {

            System.out.println("Pick card in center row to match or enter -1 to go back");
            System.out.println(answer);


            answer = scan.nextInt();
            if (answer >= 1) {
                choice = cRow.getCard(answer - 1, 0);

                System.out.println("Pick card(s) in hand to match with");

                for (int index = 0; index < cardAmount; index++) {
                    match[index] = p.getHand().getCard(scan.nextInt() - 1);
                }




                    for (int index = 0; index < cardAmount; index++) {
                        total += match[index].getFace();

                    }


                    if (total == choice.getFace() || total < choice.getFace() && (match[0].getFace() == -1 || match[match.length - 1].getFace() == -1)) {
                        System.out.println("Cards match #!");
                        cRow.getColorMatch(p, answer - 1, match);
                        cRow.matchCards(answer - 1, 1, p.getHand(), match);
                        cRow.moveStack(answer - 1, discardPile);
                        done = true;

                    } else {
                        System.out.println("Cards do not match #s!");
                    }

                }
            else {
                done = true;
            }




            }
        }


    public static void main(String[] args) {
        Random rng = new Random();
        Scanner scan = new Scanner(System.in);

        DosDeck deck = makeDosDeck(rng);
        ArrayList<DosPlayer> players = makeDosPlayers(rng, scan);
        CenterRow cRow = new CenterRow(deck);

        DosDeck discardPile = new DosDeck();

        deal(players, deck);
// Note: Make playGame() that plays game and returns playerChar's #
        // If that p won, return -1 * pChar's #
        // Then make method that prints out congrats msg for p#
        // (Will prob take -# and multiply it by -1 to get pChar's #
        int winner = 0;
        int choice = 0;


        for (int index = 0; index < players.size(); index++) {
            choice = pickOption(players.get(index), deck, cRow, scan);

           if (choice == 3) {
               drawCard();
           }

           else {
               NumMatch(players.get(index), deck, cRow, discardPile, scan, choice);

               System.out.println(cRow.display());
           }


        }

        //System.out.println(endMessage(winner));






    }


}
