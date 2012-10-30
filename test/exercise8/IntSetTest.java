/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class IntSetTest {

    public IntSet createInstance() {
        return null;
    }

    /**
     * Test of add method, of class IntSet.
     */
    @Test
    public void testAddContains() {
        
        System.out.println("add");
        IntSet instance;

        instance = createInstance();
        // JUnit tried to instantiate this class, but it is only
        // meant to be extended!
        if (instance == null)
            return;

        instance.add(0);
        instance.add(1);
        instance.add(1);
        instance.add(2);

        System.out.println("contains 0?");
        assertEquals(instance.contains(0), true);
        System.out.println("contains 1?");
        assertEquals(instance.contains(1), true);
        System.out.println("contains 2?");
        assertEquals(instance.contains(2), true);
        System.out.println("contains 3? " + instance.contains(3));
        assertEquals(instance.contains(3), false);
        System.out.println("good.");
    }
}
