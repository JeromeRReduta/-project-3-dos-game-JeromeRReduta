// DosPlayer.java

import java.util.*;

public class DosPlayer extends Player {

    // Player holds their own hand
    private DosHand hand = new DosHand();

    public DosPlayer(String name) {
        super(name);
    }

    // Static method - used in case deck empties before a player wins

    // Returns minimum hand size of all players
    public static int getMinSize(DosPlayer[] players) {
        int min = players[0].getHand().getSize();

        for (int index = 1; index < players.length; index++) {

            int next = players[index].getHand().getSize();

            if (next < min) {
                min = next;
            }
        }

        return min;
    }

    // Static method - used in case deck empties before a player wins

    // Has players w/ min hand size win, other player lose
    // Prints winning players, their hand size, and their bonus pts

    public static void getWonLost(DosPlayer[] players, int min) {
        ArrayList<DosPlayer> won = new ArrayList<DosPlayer>();
        ArrayList<DosPlayer> lost = new ArrayList<DosPlayer>();

        // Puts players w/ min hand size in won, others in lost
        for (int index = 0; index < players.length; index++) {

            int size = players[index].getHand().getSize();

            if (size == min) {
                won.add(players[index]);
            } else {
                lost.add(players[index]);
            }
        }

        // Players in won, win - also prints:
            // info, hand size, bonus pts
        String message = "Winner(s): ";
        for (int index = 0; index < won.size(); index++) {
            DosPlayer p = won.get(index);

            p.won();

            System.out.println(p);
            System.out.println("Cards left: " + p.getHand().getSize());
            System.out.println("Total bonus points: " + p.getTotalPoints());
        }

        System.out.println("====================================\n");

        for (int index = 0; index < lost.size(); index++) {
            DosPlayer p = lost.get(index);

            p.lost();
        }
    }

    // Lets DosPlayer access its hand's funcs
    public DosHand getHand() {
        return hand;
    }

    // Displays DosPlayer's name and hand
    public String displayInfo() {
        return super.getName() + "'s TURN:\n" + "HAND:\t" + hand + "\n";
    }





}
