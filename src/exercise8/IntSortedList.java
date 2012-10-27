/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public interface IntSortedList {
    
    /**
     * adds a new int to the list so that the list remains sorted; 
     * a list can contain duplicates unlike a set.
     * 
     * @param value 
     */
    void add(int value);
    
    /**
     * returns true if the number is in the list, false otherwise.
     * 
     * @param value
     * @return 
     */
    boolean contains(int value);
    
    /**
     * returns a string with the values of the elements in the list
     * separated by commas.
     * 
     * @return 
     */
    String toString();
}
