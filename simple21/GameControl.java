package simple21;

import java.util.Random;
import java.util.Scanner;

/**
 * This is a simplified version of a common card game, "21".
 */
public class GameControl {

    /**
     * Human player.
     */
    HumanPlayer human;

    /**
     * Computer player.
     */
    ComputerPlayer player1;

    /**
     * Computer player.
     */
    ComputerPlayer player2;

    /**
     * Computer player.
     */
    ComputerPlayer player3;

    /**
     * A random number generator to be used for returning random "cards" in a card
     * deck.
     */
    Random random = new Random();

    /**
     * The main method just creates a GameControl object and calls its run method.
     * 
     * @param args Not used.
     */
    public static void main(String args[]) {
        new GameControl().run();
    }

    /**
     * Prints a welcome method, then calls methods to perform each of the following
     * actions: - Create the players (one of them a Human) - Deal the initial two
     * cards to each player - Control the play of the game - Print the final results
     */
    public void run() {

        Scanner scanner = new Scanner(System.in);
        // Students: your code goes here.
        System.out.println("Welcome to Simple 21!");
        System.out.println("You'll play against 3 other players (computers).");
        System.out.println("Try to get as close to 21 as possible, without going over.");

        System.out.println("What is your name? ");
        String humanPlayerName = scanner.nextLine();
        // Call Humanplayere class and define player name

        createPlayers(humanPlayerName);

        // Deal initial cards
        deal();

        // Control of the game
        controlPlay(scanner);

        // Close the game
        if (checkAllPlayersHavePassed()) {
            // Call final result
            printResults();
            scanner.close();
        }
    }

    /**
     * Creates one human player with the given humansName, and three computer
     * players with hard-coded names.
     * 
     * @param humansName for human player
     */
    public void createPlayers(String humansName) {
        // Students: your code goes here.
        human = new HumanPlayer(humansName);
        player1 = new ComputerPlayer("Player 1");
        player2 = new ComputerPlayer("Player 2");
        player3 = new ComputerPlayer("Player 3");
    }

    /**
     * Deals two "cards" to each player, one hidden, so that only the player who
     * gets it knows what it is, and one face up, so that everyone can see it.
     * (Actually, what the other players see is the total of each other player's
     * cards, not the individual cards.)
     */

    public void deal() {
        // Students: your code goes here.
        // Player takes one hidden card
        human.takeHiddenCard(nextCard());
        human.takeVisibleCard(nextCard());

        // Player 1
        player1.takeHiddenCard(nextCard());
        player1.takeVisibleCard(nextCard());

        // player 2
        player2.takeHiddenCard(nextCard());
        player2.takeVisibleCard(nextCard());

        // player 3
        player3.takeHiddenCard(nextCard());
        player3.takeVisibleCard(nextCard());

    }

    /**
     * Returns a random "card", represented by an integer between 1 and 10,
     * inclusive. The odds of returning a 10 are four times as likely as any other
     * value (because in an actual deck of cards, 10, Jack, Queen, and King all
     * count as 10).
     * 
     * Note: The java.util package contains a Random class, which is perfect for
     * generating random numbers.
     * 
     * @return a random integer in the range 1 - 10.
     */
    public int nextCard() {
        // Students: your code goes here.
        int result = (Math.random() < 0.4) ? 10 : random.nextInt(9 - 1 + 1) + 1;
        return result;
    }

    /**
     * Gives each player in turn a chance to take a card, until all players have
     * passed. Prints a message when a player passes. Once a player has passed, that
     * player is not given another chance to take a card.
     * 
     * @param scanner to use for user input
     */
    public void controlPlay(Scanner scanner) {
        // Students: your code goes here.
        while (checkAllPlayersHavePassed() == false) {

            // Human
            if (human.passed == false) {
                Boolean humanOfferCard = human.offerCard(human, player1, player2, player3, scanner);
                if (humanOfferCard == false) {
                    System.out.println(human.name + " passes.");
                } else {
                    human.takeVisibleCard(nextCard());
                }
            }

            if (player1.passed == false) {
                Boolean player1OfferCard = player1.offerCard(human, player1, player2, player3);
                if (player1OfferCard == false) {
                    System.out.println(player1.name + " passes.");
                } else {
                    player1.takeVisibleCard(nextCard());
                }
            }

            if (player2.passed == false) {
                Boolean player2OfferCard = player2.offerCard(human, player1, player2, player3);
                if (player2OfferCard == false) {
                    System.out.println(player2.name + " passes.");
                } else {
                    player2.takeVisibleCard(nextCard());
                }
            }

            if (player3.passed == false) {
                Boolean playe3OfferCard = player3.offerCard(human, player1, player2, player3);
                if (playe3OfferCard == false) {
                    System.out.println(player3.name + " passes.");
                } else {
                    player3.takeVisibleCard(nextCard());
                }
            }

        }
    }

    /**
     * Checks if all players have passed.
     * 
     * @return true if all players have passed
     */
    public boolean checkAllPlayersHavePassed() {
        // Students: your code goes here.
        if (human.passed && player1.passed && player2.passed && player3.passed) {
            // if (human.passed) {
            return true;
        }

        return false;
    }

    /**
     * Prints a summary at the end of the game. Displays how many points each player
     * had, and if applicable, who won.
     */
    public void printResults() {
        // Students: your code goes here.
        System.out.println("Game Over.");
        System.out.println(human.name + " has " + human.getScore() + " total point(s).");
        System.out.println(player1.name + " has " + player1.getScore() + " total point(s).");
        System.out.println(player2.name + " has " + player2.getScore() + " total point(s).");
        System.out.println(player3.name + " has " + player3.getScore() + " total point(s).");
        printWinner();
    }

    /**
     * Determines who won the game, and prints the results.
     */
    public void printWinner() {

        // Students: your code goes here.
        int[] playersScore = { human.getScore(), player1.getScore(), player2.getScore(), player3.getScore() };
        String[] playersName = { human.name, player1.name, player2.name, player3.name };

        int winners = 0;
        int maxValue = 0;
        int playerIndex = -1;

        for (int i = 0; i < playersScore.length; i++) {
            if (playersScore[i] <= 21) {
                maxValue = Math.max(maxValue, playersScore[i]);
            }
        }

        for (int i = 0; i < playersScore.length; i++) {
            if (maxValue == playersScore[i]) {
                playerIndex = i;
                winners++;
            }
        }

        if (playerIndex < 0 || winners > 1) {
            System.out.println("Tie, nobody wins.");
        } else {
            System.out.println(playersName[playerIndex] + " wins with " + playersScore[playerIndex] + " point(s).");
        }
    }
}
