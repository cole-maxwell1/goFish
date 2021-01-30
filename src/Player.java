import java.util.ArrayList;

/**
 * An abstract class that has the properties of a Go Fish player
 */
public abstract class Player
{
    public String name; // This string stores the player's name.
    public ArrayList<Card> hand = new ArrayList<>(); // Meanwhile, this array list stores the player's cards.
    private int numBooks; // This integer field keeps track of the player's score, based on the number of their books.

    public Player()
    { // Initially, the player will fish for 7 cards to create their hand.
        for(int i=0; i < 8; i++)
            fish();
    } // end constructor

    /**
     * Gets the name of a player.
     * @return A string containing the player's name.
     */
    public String getName()
    {
        return name; // The name field is returned.
    } // end getName

    /**
     * Sets the name of a player.
     * @param playerName A string to set the player name to.
     */
    public void setName(String playerName)
    {
        name = playerName; // The name field is set to a specified string.
    }

    /**
     * Checks if a card has been given to another player
     * @param cardType a card value (ace, one, jack, king, etc...)
     * @return True is the players hand contains the
     * cardType, otherwise false
     */

    public boolean hasCard(Card cardType)
    { // If the hand contains a specific type of card, true will be returned. Otherwise, false will be returned.
        return hand.contains(cardType);
    }// End hasCard

    /**
     * Returns an array of cards to give to another player given a card type to look for
     * @param cardType a card value
     * @return an array of the cards that are being given
     */
    public ArrayList<Card> giveCard(Card cardType)
    {
        ArrayList<Card> givenCardArray = new ArrayList<>();
        // adds cards being given to givenCardArray
        for (int i = 0; i < hand.size(); i++)
            if (hand.get(i) == cardType)
                givenCardArray.add(hand.get(i));
        // removes card from players hand
        for (int c = 0; c < givenCardArray.size(); c++)
            hand.remove(cardType);
        return givenCardArray;
    }// end giveCard

    /**
     * Asks the next player for a given card type
     * @param cardType a card value
     * @return a boolean representing if the next player has the card type asked for
     */
    public boolean askFor(Card cardType, Player target)
    {
        boolean result = false; // A result is initialized as false. If the asking player does not receive cards from
                                // the other player, this will be returned as is.
        if (target.hasCard(cardType))
        {   // If the other player has a card or cards of a given type, the asking player will be given the cards for
            for(Card c: target.giveCard(cardType)) // for as long as the other player has them.
                hand.add(c);
            result = true; // In this case, true is returned, as the player did receive cards.
        } // End if
        return result; // The result is returned at the very end.
    } // End askFor

    /**
     * Allows a player to fish for a card if needed, and then adds the card to the player's hand.
     * @return A card, which is either the card taken from the deck or null if the deck is empty.
     */
    public Card fish()
    {
        Card goFish = null; // A card is initialized as null. It will be returned if the deck is empty.
        if (GoFish.deckSize() > 0)
        { // If the deck size is greater than zero, the following will occur:
            goFish = GoFish.draw(); // The goFish card is set to the card drawn from the deck.
            hand.add(goFish); // The card is then added to the player's hand.
        } // End if
        else
        { // If the deck is at 0, then it is clearly empty. The player will be notified as follows:
            System.out.println("The deck is empty!"); // A statement will be printed out to the console.
        } // End else
        return goFish; // The card, regardless of its value, will be returned at the very end.
    } // End fish

    /**
     * Gets the number of books that a player has won.
     * Acts as the score keeping mechanism for a player.
     * @return The score of the player in "books"
     */
    public int getNumBooks()
    {
        return numBooks; // The number of books, which is the same as the player's score, is returned.
    } // end getNumBooks

    /**
     * Checks to see if the player's hand contains a "book" or
     * four cards of the same value.
     *
     * @return The book of cards that was found, else null
     */
    public Card checkForBooks() {
        // finds books
        for (Card c : hand) {
            int num = 0;
            for (Card d : hand)
                if (c == d)
                    num++;
            // removes found books from the hand
            if (num == 4) {
                for (int i = 0; i < 4; i++)
                    hand.remove(c);
                numBooks++;
                return c;
            } // end if
        } // end for
        return null;
    } // end checkForBooks

    /**
     * A placeholder method that will serve as a means of
     * instituting any type of player's turn in a game
     * of Go Fish.
     * @param turn The turn number
     */
    public abstract void haveTurn(int turn);

} // End Player