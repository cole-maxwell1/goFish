import java.util.Scanner;

class HumanPlayer extends Player
{
    /**
     * The player's turn. Goes through the things a normal human player would do in Go Fish - check for books, create books, check your hand, ask for cards, and draw
     */
    public void haveTurn(int turn)
    {
        Scanner scn = new Scanner(System.in);
        boolean playing = true; // This will remain true until the player's input is received
        do{
            Card book = checkForBooks();
            if(book != null)
                System.out.println("You got a book of " + book + "s!");

            if (hand.size() == 0)
            {
                System.out.print("Your hand is empty, you must ");
                break;
            }
            else
            {
                System.out.print("Your hand:");
                displayHand();
                System.out.println();
            }

            System.out.println("Which opponent will you ask for cards?");

            Player target;
            String opponent = scn.next().toUpperCase();
            if(contains(opponent) && !(opponent.equals(GoFish.Players[0].getName()))) {
                int nameIndex = 1;
                while (!opponent.equals(GoFish.Players[nameIndex].getName().toUpperCase())) {
                    nameIndex++;
                    if (nameIndex > 3)
                        nameIndex = 1;
                }// end while
                target = GoFish.Players[nameIndex];
            } // End if
            else {
                System.out.println(opponent + " is not present in this game. Please try again:");
                continue;
            } // End else

            System.out.println("What card will you ask "+ target.getName() +" for?");

            Card requestOfPlayer;
            try{
                requestOfPlayer = Card.valueOf(scn.next().toUpperCase());
            }
            catch(IllegalArgumentException e){ // If what you said is not in Card
                System.out.println("Card not present in this deck. Try again:");
                continue;
            }

            if(!hand.contains(requestOfPlayer))
            {
                System.out.println("You may not ask for a card you have none of. Try again:");
                continue;
            }

            System.out.println("You ask "+ target.getName() +" for a " + requestOfPlayer);
            playing = askFor(requestOfPlayer, target); //If you get card(s), askFor returns true and loops
            if (playing)
                System.out.println("You took "+ target.getName() + "'s " + requestOfPlayer + "s!");
        } while(playing);
        System.out.println("Go fish!");
        Card drawn = fish();
        if(drawn != null)
            System.out.println("You drew a " + drawn + "!");
    } // end haveTurn

    /**
     * Displays the player's hand
     */
    public void displayHand(){
        for(Card cardInHand: hand)
            System.out.print(cardInHand + " ");
    }// end displayHand

    /**
     * @param playerName as a String
     * @return True if the player is found in the game
     */

    public boolean contains(String playerName){
        boolean found = false;
        for(int index = 1; index < GoFish.Players.length; index++){
            if (playerName.equals(GoFish.Players[index].getName().toUpperCase()))
                found = true;
        }
        return found;
    }
} // End HumanPlayer