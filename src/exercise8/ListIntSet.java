/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exercise8;


public class ListIntSet implements IntSet {
    SimpleLinkedList lst = new SimpleLinkedList();
    
    @Override
    public void add(int value) {
        if (!lst.contains(value))
            lst.add(value);
    }

    @Override
    public boolean contains(int value) {
        return lst.contains(value);
    }

    @Override
    public boolean containsVerbose(int value) {
        return lst.contains(value, true);
    }

    @Override
    public String toString() {
        return lst.toString();
    }
}

