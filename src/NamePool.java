import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class NamePool
{
    private ArrayList<String> pool; // A pool of names is stored in a string-based array list.
    private final int nameLimit = 52; // The maximum amount of names that can be added to the pool is 52.

    public NamePool()
    {
        pool = new ArrayList<String>(52); // An array list with 52 slots is created.
    } // End constructor

    public int makePool(String fileName)
    {
        int counter = 0;
        try // Here, a new scanner is created with the file name listed above as an input.
        {
            Scanner data = new Scanner(new File(fileName)); // The scanner takes in a new file name and tests it for
            while (data.hasNext() && counter != nameLimit)
            { // While there are lines to read and the number of names does not exceed the name limit of 52,
                pool.add(data.next()); // names will be added to the pool
                counter++;       // and the number of entries will be increased.
            } // End while
            data.close(); // Once the loop is completed, the scanner will stop reading the file.
        }
        catch (FileNotFoundException e) // Then, it will try to catch a FileNotFoundException and print a warning
        {                               // instead of throwing the actual exception.
            System.out.println("File not found: " + e.getMessage());
        } // End catch
        return counter; // The number of names in the list is returned.
    } // End makePool

    /**
     * Distributes names from the pool to the AI players in the game.
     * @param human A human player to compare the AI to.
     * @param ai1 The first AI to be given a name.
     * @param ai2 The next AI to be given a name.
     * @param ai3 The last AI to be given a name.
     */
    public void distributeNames(Player human, AIPlayer ai1, AIPlayer ai2, AIPlayer ai3)
    {
        String hName = human.getName(); // The human player's name is set to a string to search the list for.
        if(pool.contains(hName))
        { // If the pool contains the player's name, the following will happen:
            pool.remove(hName); // The name is removed from the pool.
        } // End if
        setName(ai1); // All three AI players will
        setName(ai2); // have their names set to
        setName(ai3); // random names in the pool.
    } // End distributeNames

    // This helper method retrieves a name form the pool.
    private String retrieveName()
    {
        Random r = new Random(); // A random object is created for randomizing integers.
        int index = r.nextInt(pool.size()-1); // An index is randomized from the start to the end of the list.
        String name = null; // A string to hold the name is initialized as null.
        if(pool.size()>3) // If the pool size is greater than three, then a name can be generated.
            name = pool.get(index); // The name is retrieved from the random index created earlier and set to a string.
            pool.remove(name); // The name is then removed, since the AI should have distinct same names.
        return name; // If a name was generated, it is returned here. Otherwise, null is returned.
    } // End retrieveName

    // This helper method sets the name of an AI to whatever is retrieved from the pool.
    private void setName(AIPlayer ai)
    {
        String aiName = retrieveName(); // A name is retrieved and set to a string to be used later.
        if(aiName == null) // It is unsafe to have a null name, as it cannot be called upon by the player. Thus,
        {                  // an exception is thrown if the name is null.
            throw new IllegalArgumentException();
        } // End if
        else
        {
            ai.setName(aiName); // Otherwise, the AI's name is set to the random name without any problems.
        } // End else
    } // End setName
} // End NamePool
