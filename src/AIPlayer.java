import java.util.*;

class AIPlayer extends Player
{
    /**
     * The AI's turn checklist. It checks for books, checks the size of the hand, gets its logic of which cards to ask for, asks for that, increments its turn timer,
     * and at the end goes fishing.
     */
    public void haveTurn(int turn)
    {
        boolean playing;
        do{
            System.out.println("It is " + getName() + "'s turn.");
            Card book = checkForBooks();
            if(book != null)
                System.out.println("Your opponent got a book of " + book + "s...");
            if (hand.size() == 0)
            {
                System.out.print("Your opponent's hand is empty.");
                break;
            }
            Card request = aiRequestLogic();
            Player target = aiRequestTarget(GoFish.Players[turn]);
            System.out.println("Your opponent asks " + target.getName() + " for cards by the name of " + request);
            playing = askFor(request, target);
            if (playing)
                System.out.println("Your opponent took " + target.getName() + "'s " + request + "s!");
        } while(playing);

        System.out.println("Your opponent goes fishing.");
        fish();
    } // End haveTurn

    /**
     * The AI's strategy is to ask for a random card that it has at least one of.
     */
    private Card aiRequestLogic()
    {
        Random randomCard = new Random(); // A placeholder is set aside for generating a random integer.
        return hand.get(randomCard.nextInt(hand.size())); // A card at a randomized index will be returned.
    } // End aiRequestLogic

    /**
     * Creates a player for the ai to ask for cards from
     * @param ai the ai asking for a target
     * @return a player that is not the ai
     */
    private Player aiRequestTarget(Player ai){
        // generates a random player index
        int index = (int) (Math.random() * GoFish.Players.length);

        // prevents the logic from outputting and index that is equal
        // to the player object
        while (GoFish.Players[index]==ai){
            index = (int) (Math.random() * GoFish.Players.length);
        }// end while

        return GoFish.Players[index];
    } // End aiRequestTarget
} // End AIPlayer