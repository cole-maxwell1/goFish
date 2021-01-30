import java.util.ArrayList; //Java's Collection Framework is really what is
import java.util.Random;
import java.util.Scanner;

public class GoFish
{
    public static final Random pickRandom = new Random();
    private static ArrayList<Card> deckOfCards; //ArrayList to store the cards in the deck
    public static Player[] Players; // an array to store the players in the game
    public static final int numberOfBooks = 13; // because there are 13 types
    public static final int cardsInSuit = 4; // because 4 of each type


    /**
     * Gets a random card from the deck of cards
     * @return a random card object
     */
    public static Card draw()
    {
        return deckOfCards.remove(pickRandom.nextInt(deckOfCards.size())); // The card drawn is returned.
    } // end draw

    /**
     * Gets the size of the deck
     * @return an int equal to the the number of cards in the deck
     */
    public static int deckSize()
    {
        return deckOfCards.size(); // The size of the deck is returned.
    } // end deck size

    /**
     * Main method that runs the game using the player and card classes.
     */
    public static void main(String[] args)
    {
        boolean play = true; // This boolean result is used to determine when the came will end. If it is ever set
                             // to false, the game will end.
        while(play) // This loop will run for as long as the game is being played.
        {
            /**
             * Creates a deck of card with 4 instances of
             * each enumerated value of a Card.
             */
            deckOfCards = new ArrayList<Card>();
            for (int i = 0; i < cardsInSuit; i++) // For as long as the
                for (Card c : Card.values()) {
                    deckOfCards.add(c);
                } // end for

            //A human player and three AI are created.
            HumanPlayer human = new HumanPlayer();
            AIPlayer ai1 = new AIPlayer();
            AIPlayer ai2 = new AIPlayer();
            AIPlayer ai3 = new AIPlayer();

            // Stores the players in an active game of Go Fish
            Players = new Player[]{human, ai1, ai2, ai3};
            boolean identifier = false;
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome to Go Fish!");
            while (!identifier) {
                System.out.println("Please insert your name to continue:");
                String line = input.nextLine();
                System.out.println("\nYou put in " + line + " for your name. Is this correct?" +
                        "\nInput Y for yes or N for no:");
                String line2 = input.nextLine();
                if (line2.toUpperCase().equals("Y")) {   // Here, the player's name is set, and a name pool is generated for the AI.
                    human.setName(line);
                    NamePool temp = new NamePool();
                    temp.makePool("Names.txt");
                    temp.distributeNames(human, ai1, ai2, ai3); // The AI receive names opposite of the player.
                    temp = null; // The name pool is deallocated from memory.
                    System.out.println(line + ", good luck and have fun! You'll be playing against " + ai1.getName() + ", "
                            + ai2.getName() + ", and " + ai3.getName() + "." + "\n");
                    identifier = true;
                }
            }

            /**
             * This while loop is the core of the game. It runs for most
             * of the game while the turn logic of the AI players runs.
             * The loop ends when all the books in the game are gone, adding
             * number of books (score) of each player.
             */
            while (Players[0].getNumBooks() + Players[1].getNumBooks() + Players[2].getNumBooks() + Players[3].getNumBooks() < numberOfBooks) {
                Players[0].haveTurn(0);
                System.out.println("----------");
                Players[1].haveTurn(1);
                System.out.println("----------");
                Players[2].haveTurn(2);
                System.out.println("----------");
                Players[3].haveTurn(3);
                System.out.println("----------");
            } // End while

            // Displays the finals scores of winner and loser
            int humanScore = Players[0].getNumBooks();
            int ai1Score = Players[1].getNumBooks();
            int ai2Score = Players[2].getNumBooks();
            int ai3Score = Players[3].getNumBooks();
            if (humanScore > ai1Score && humanScore > ai2Score && humanScore > ai3Score)
                System.out.println("Congratulations, you win!");
            else if ((humanScore == ai1Score && humanScore > ai2Score && humanScore > ai3Score) ||
                    (humanScore == ai2Score && humanScore > ai1Score && humanScore > ai3Score) ||
                    (humanScore == ai3Score && humanScore > ai1Score && humanScore > ai2Score))
                System.out.println("You tied!");
            else
                System.out.println("Oh no! You lost! :(");
            System.out.println("Scores:\nYour score: " + humanScore + "\n" + ai1.getName() + "'s score: " + ai1Score +
                    "\n" + ai2.getName() + "'s score: " + ai2Score + "\n" + ai3.getName() + "'s score: " + ai3Score);
            if(!restart())
            {
                play = false;
            } // End if
        } // End while
    } // End main

    /**
     * This method is used to restart the game.
     * @return True, which will allow the game to restart, or false, which will cause the game to end.
     */
    public static boolean restart()
    {
        Scanner input = new Scanner(System.in); // A new scanner is created to receive the player's input.
        boolean result = false; // The result is initialized as false.
        System.out.println("Would you like to play again?" + "\n Input Y for yes or N for no:");
        String line = input.nextLine().toUpperCase(); // The player's input is set to uppercase for comparison.
        switch(line) // There are three cases that the input could meet:
        {
            case("Y"): // If the player inputs Y, the result will be set to true.
                result = true;
                break;
            case("N"): // If the player inputs N, then result will remain false.
                System.out.println("Come again!");
                break;
            default: // If the players inputs something different, he/she will be asked the same question again.
                restart();
                break;
        }
        return result;
    }
} // end GoFish