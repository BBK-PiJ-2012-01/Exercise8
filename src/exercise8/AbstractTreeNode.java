/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exercise8;

import BBK.PiJ01.common.IOGeneric;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
abstract public class AbstractTreeNode {
    abstract protected AbstractTreeNode getLeft();
    abstract protected AbstractTreeNode getRight();
    
    public int depth() {
        if (isLeaf())
            return 0;
        if (getLeft() != null && getRight() == null)
            return getLeft().depth() + 1;
        if (getLeft() == null && getRight() != null)
            return getRight().depth() + 1;
        return Math.max(getLeft().depth(), getRight().depth()) + 1;
    }
    
    public boolean isLeaf() {
        return getLeft() == null && getRight() == null;
    }
    
    abstract String valueString();
    
    public String toLengthyString() {
        
        return String.format("%s%s%s", valueString(), 
                                        (getLeft()==null? "":" L["+getLeft()+"]"),
                                        (getRight()==null? "":" R["+getRight()+"]"));
        
        //return " [" + value + (left==null? "":left) + (right==null? "":right) + "]";
        
    }
    
    public String toCommaSeparatedString() {
        
        return String.format("%s%s%s", valueString(), 
                                        (getLeft()==null? "":","+getLeft().toCommaSeparatedString()),
                                        (getRight()==null? "":","+getRight().toCommaSeparatedString()));
        
    }
    
    public String toString() {
        LinkedList<String> string_lst = new LinkedList<String>();
        toPrettyStringList(string_lst);
        ListIterator<String> itr = string_lst.listIterator();
        
        StringBuffer sbuf = new StringBuffer("\n");
        
        while(itr.hasNext()) {
            sbuf.append(itr.next());
            sbuf.append("\n");
        }
        
        return sbuf.toString();
    }
    
    private int toPrettyStringList(LinkedList<String> string_lst) {
        LinkedList<String> left_lst = new LinkedList<String>();
        LinkedList<String> right_lst = new LinkedList<String>();
        
        int left_pos=0, right_pos=0;
        
        try {
            left_pos = getLeft().toPrettyStringList(left_lst);
        } catch (NullPointerException err) {}
        
        try {
            right_pos = getRight().toPrettyStringList(right_lst);
        } catch (NullPointerException err) {}
        
        ListIterator<String> left_itr = left_lst.listIterator();
        ListIterator<String> right_itr = right_lst.listIterator();
        
        
        int new_left_width = Math.max(valueString().length()/2+2,
                    (left_lst.isEmpty()? 0 : left_lst.getFirst().length()) );
        int new_right_width = Math.max(valueString().length()/2+2,
                    (right_lst.isEmpty()? 0 : right_lst.getFirst().length()) );
        
        StringBuffer header1_sbuf = new StringBuffer();
        StringBuffer header2_sbuf = new StringBuffer();
        
        String left_buffer="", right_buffer="", empty_line=null;
        
        
        if (!left_lst.isEmpty()) {
            left_buffer = IOGeneric.multiplyString(" ", new_left_width - left_lst.getFirst().length());
            
            header2_sbuf.append(left_buffer);
            header2_sbuf.append(IOGeneric.multiplyString(" ", left_pos));
            header2_sbuf.append("|");
            header2_sbuf.append(IOGeneric.multiplyString(" ", left_lst.getFirst().length() - left_pos));
            
            header1_sbuf.append(left_buffer);
            header1_sbuf.append(IOGeneric.multiplyString(" ", left_pos));
            header1_sbuf.append(IOGeneric.multiplyString("-", left_lst.getFirst().length() - left_pos - valueString().length()/2 - 1));
        } else {
            header1_sbuf.append(IOGeneric.multiplyString(" ", new_left_width - valueString().length()/2 - 1));
            header2_sbuf.append(IOGeneric.multiplyString(" ", new_left_width));
        }
        
        header1_sbuf.append(String.format("[%s]", valueString()));
        
        if (!right_lst.isEmpty()) {
            right_buffer = IOGeneric.multiplyString(" ", new_right_width - right_lst.getFirst().length());
            
            header2_sbuf.append(IOGeneric.multiplyString(" ", right_pos));
            header2_sbuf.append("|");
            
            header1_sbuf.append(IOGeneric.multiplyString("-", right_pos - valueString().length()/2 + 1));
        }
        
        int header1extras = new_left_width + new_right_width + 1 - header1_sbuf.length();
        int header2extras = new_left_width + new_right_width + 1 - header2_sbuf.length();
        
        header1_sbuf.append(IOGeneric.multiplyString(" ", header1extras));
        header2_sbuf.append(IOGeneric.multiplyString(" ", header2extras));
        
        string_lst.add(header1_sbuf.toString());
        string_lst.add(header2_sbuf.toString());
        
        
        StringBuffer sbuf;
        
        while (left_itr.hasNext() || right_itr.hasNext()) {
            sbuf = new StringBuffer();
            
            if (left_itr.hasNext()) {
                sbuf.append(left_buffer);
                sbuf.append(left_itr.next());
            } else {
                if (empty_line == null)
                    empty_line = IOGeneric.multiplyString(" ", new_left_width);
                sbuf.append(empty_line);
            }
            
            sbuf.append(" ");
            
            if (right_itr.hasNext()) {
                sbuf.append(right_itr.next());
                sbuf.append(right_buffer);
            } else {
                if (empty_line == null)
                    empty_line = IOGeneric.multiplyString(" ", new_right_width);
                sbuf.append(empty_line);
            }
            
            string_lst.add(sbuf.toString());
        }
        
        return new_left_width;
    }
    
    private void setPrettyListDimensions(LinkedList<String> lst, int length, int rows) {
        StringBuffer sbuf;
        ListIterator<String> itr = lst.listIterator();
        
        while (itr.hasNext()) {
            sbuf = new StringBuffer(itr.next());
            
        }
        
    }
    
}
