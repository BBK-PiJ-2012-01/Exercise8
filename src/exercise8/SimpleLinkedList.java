/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exercise8;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class SimpleLinkedList {
    Element first;
    
    public void add(int value) {
        Element new_first = new Element(value);
        new_first.setNext(first);
        first = new_first;
    }
    
    public boolean contains(int value, boolean ...verbose) {
        assert verbose.length <= 1;
        boolean print_verbosity = false;
        if (verbose.length == 1 && verbose[0])
            print_verbosity = true;
        
        Element next = first;
        while(next != null) {
            if (print_verbosity)
                System.out.format("Checking element containing %d\n", next.value);
            if (next.getValue() == value)
                return true;
            next = next.getNext();
        }
        return false;
    }
    
    @Override
    public String toString() {
        StringBuffer sbuf = new StringBuffer();
        Element next = first;
        
        while (next != null) {
            sbuf.append(next.getValue());
            next = next.getNext();
            if (next != null)
                sbuf.append(",");
        }
        return sbuf.toString();
    }
}


class Element {
    Element next;
    int value;
    
    public Element(int value) {
        this.value = value;
    }
    
    public void setNext(Element next) {
        this.next = next;
    }
    
    public Element getNext() {
        return next;
    }
    
    public int getValue() {
        return value;
    }
}