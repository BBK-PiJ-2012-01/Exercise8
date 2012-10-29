/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exercise8;


public class SortedLinkedList extends SimpleLinkedList {
    @Override
    public void add(int value) {
        Element next = first;
        Element prev = null;
        
        if (next == null) {
            super.add(value);
            return;
        }
        
        Element new_element = new Element(value);
        
        while(next != null) {
            if (next.getValue() > value) {
                try {
                    prev.setNext(new_element);
                } catch (NullPointerException err) {
                    first = new_element;
                }
                new_element.setNext(next);
                return;
            }
            prev = next;
            next = next.getNext();
        }
        prev.setNext(new_element);
    }
}
