/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class TreeIntSetTest extends IntSetTest {
    
    @Override
    public IntSet createInstance() {
        return new TreeIntSet();
    }
    
}
