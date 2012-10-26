/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public interface IntSet {
    /**
     * Adds a new int to the set; if it is there already, nothing happens
     * 
     * @param value 
     */
    void add(int value);
    
    /**
     * Returns true if the number is in the set; false otherwise.
     * 
     * @param value
     * @return 
     */
    boolean contains(int value);
    
    /**
     * Returns the same values as the former method, but for every 
     * element that is checked prints its value on screen.
     * 
     * @param value
     * @return 
     */
    boolean containsVerbose(int value);
    
    /**
     * Returns a string with the values of the elements in the set 
     * separated by commas.
     * 
     * @return 
     */
    String toString();
}
