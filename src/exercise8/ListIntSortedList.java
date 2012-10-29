/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exercise8;


public class ListIntSortedList implements IntSortedList {
    SortedLinkedList lst = new SortedLinkedList();
    
    @Override
    public void add(int value) {
        lst.add(value);
    }

    @Override
    public boolean contains(int value) {
        return lst.contains(value);
    }
    
    @Override
    public String toString() {
        return lst.toString();
    }

}
