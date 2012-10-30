/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;

import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class ListIntSetTest extends IntSetTest {
    
    @Override
    public IntSet createInstance() {
        return new ListIntSet();
    }
    
    public ListIntSetTest() {
    }

    /**
     * Test of add method, of class ListIntSet.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        
        ListIntSet instance = new ListIntSet();
        instance.add(0);
        instance.add(1);
        instance.add(1);
        instance.add(2);
        
        Element e = instance.lst.first;
        LinkedList<Integer> output_lst = new LinkedList<Integer>();
        
        while(e != null) {
            output_lst.add(e.value);
            e = e.next;
        }
        
        assertEquals(output_lst.size(), 3);
    }

}
