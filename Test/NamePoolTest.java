import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NamePoolTest
{
    NamePool testPool = new NamePool(); // A new name pool is created.

    @Test
    void makePool()
    {
        assertEquals(0, testPool.makePool("48484848.txt")); // Note: Three things should occur.
        // Firstly, an exception should be caught. Secondly, a statement should be printed to the console. Lastly, 0
        // should be returned.
        assertEquals(3, testPool.makePool("Test.txt")); // A pool is made with "Test.txt." It should
                                                                        // return 3.
        NamePool testPool2 = new NamePool(); // Another test pool is created for testing the main file, "Names.txt."
        assertEquals(52, testPool2.makePool("Names.txt")); // 52 should be returned.
        NamePool testPool3 = new NamePool(); // Another test pool is creating for testing a file with over 52 names.
        assertEquals(52, testPool3.makePool("Test2.txt")); // 52 should be returned.
    } // End makePool
} // End NamePoolTest