/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;

import BBK.PiJ01.common.IOGeneric;
import com.sun.tools.example.debug.bdi.MethodNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class IntegerTreeNode {

    private int value;
    private IntegerTreeNode left;
    private IntegerTreeNode right;
    private DUPLICATES duplicates = DUPLICATES.NOT_ALLOWED;
    
    public static enum DUPLICATES {
        NOT_ALLOWED, ADD_LEFT, ADD_RIGHT
    }

    public IntegerTreeNode(int value) {
        this.value = value;
    }
    
    private IntegerTreeNode() {}

    public void add(int value) {
        if (value > this.value)
            addRight(value);
        else if (value < this.value)
            addLeft(value);
        else if (duplicates == DUPLICATES.ADD_LEFT)
            addLeft(value);
        else if (duplicates == DUPLICATES.ADD_RIGHT)
            addRight(value);
    }
    
    private void addLeft(int value) {
        try {
            left.add(value);
        } catch (NullPointerException err) {
            left = new IntegerTreeNode(value);
            left.setDuplicates(duplicates);
        }
    }
    
    private void addRight(int value) {
        try {
            right.add(value);
        } catch (NullPointerException err) {
            right = new IntegerTreeNode(value);
            right.setDuplicates(duplicates);
        }
    }
    
    public void setDuplicates(DUPLICATES duplicates) {
        this.duplicates = duplicates;
    }

    public boolean contains(int value, boolean ...verbose) {
        assert verbose.length <= 1;
        boolean print_verbosity = false;
        if (verbose.length == 1 && verbose[0]) {
            System.out.format("Checking element containing %d\n", this.value);
            print_verbosity = true;
        }
        
        if (value == this.value) {
            return true;
        }

        try {
            if (value > this.value) {
                return right.contains(value, print_verbosity);
            }
            if (value < this.value) {
                return left.contains(value, print_verbosity);
            }
        } catch (NullPointerException err) {}
        return false;
    }
    
    public int getMax() {
        try{
            return right.getMax();
        } catch (NullPointerException err) {
            return value;
        }
    }
    
    public int getMin() {
        try{
            return left.getMin();
        } catch (NullPointerException err) {
            return value;
        }
    }
    
    public String toString() {
        
        return String.format("%d%s%s", value, 
                                        (left==null? "":" L["+left+"]"),
                                        (right==null? "":" R["+right+"]"));
        
        //return " [" + value + (left==null? "":left) + (right==null? "":right) + "]";
        
    }
    
    public String toCommaSeparatedString() {
        
        return String.format("%d%s%s", value, 
                                        (left==null? "":","+left.toCommaSeparatedString()),
                                        (right==null? "":","+right.toCommaSeparatedString()));
        
    }
    
    public int depth() {
        if (isLeaf())
            return 0;
        if (left != null && right == null)
            return left.depth() + 1;
        if (left == null && right != null)
            return right.depth() + 1;
        return Math.max(left.depth(), right.depth()) + 1;
    }
    
    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    public IntegerTreeNode getParent(int value) {
        try {
            if (value > this.value) {
                if (right.value == value)
                    return right;
                return right.getParent(value);
            } else if (value < this.value) {
                if (left.value == value)
                    return left;
                return left.getParent(value);
            }
        } catch (NullPointerException err) {
            return null;
        }
        
        System.out.println("Tried to find the top element's parent.");
        return null;
    }
    
    public void remove(int value) {
        if (value == this.value) {
            if (isLeaf()) {
                try {
                    getParent(value).remove(value);
                } catch (NullPointerException err) {
                    System.out.println("Can't remove the only element in the tree!");
                }
            } else {
                try {
                    int min_on_right = right.getMin();
                    System.out.println("min_from " + right.value + " = " + min_on_right);
                    remove(min_on_right);
                    this.value = min_on_right;
                } catch (NullPointerException err) {
                    int max_on_left = left.getMax();
                    System.out.println("max_from " + left.value + " = " + max_on_left);
                    remove(max_on_left);
                    this.value = max_on_left;
                }
            }
        } else if (right != null && value == right.value) {
            if (right.isLeaf())
                right = null;
            else
                right.remove(value);
        } else if (left != null && value == left.value) {
            if (left.isLeaf())
                left = null;
            else
                left.remove(value);
        } else {
            getParent(value).remove(value);
        }
    }
    
    public void rebalance() {
        List<Integer> lst = serialise();
        left = null;
        right = null;
        
        Collections.sort(lst);
        rebalance(lst);
    }
    
    private void rebalance(List<Integer> lst) {
        if (lst.size() == 0)
            return;
        
        int median_index = lst.size()/2;
        value = lst.get(median_index);
        
        Integer[] total_lst = lst.toArray(new Integer[0]);
        //System.out.println("\ntotal_list = " + IOGeneric.listToString(total_lst, "[,]"));
        
        if (0 < median_index) {
            left = new IntegerTreeNode();
            //return values.toArray(new String[0]);
            Integer[] left_lst = lst.subList(0, median_index).toArray(new Integer[0]);
            System.out.println("\ntotal_list = "+IOGeneric.listToString(total_lst, "[,]")+ "   left_list = " + IOGeneric.listToString(left_lst, "[,]"));
            left.rebalance(lst.subList(0, median_index));
        }
        if (median_index+1 < lst.size()) {
            Integer[] left_lst = lst.subList(median_index+1, lst.size()).toArray(new Integer[0]);
            System.out.println("\ntotal_list = "+IOGeneric.listToString(total_lst, "[,]")+ "   right_list = " + IOGeneric.listToString(left_lst, "[,]"));
            right = new IntegerTreeNode();
            right.rebalance(lst.subList(median_index+1, lst.size()));
        }
            
    }
    
    public ArrayList<Integer> serialise() {
        ArrayList<Integer> lst = new ArrayList<Integer>();
        serialise(lst);
        return lst;
    }
    
    private void serialise(ArrayList<Integer> lst) {
        lst.add(value);
        if (left != null)
            left.serialise(lst);
        if (right != null)
            right.serialise(lst);
    }
}
