/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;

import BBK.PiJ01.common.IOGeneric;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class IntegerTreeNode extends AbstractTreeNode {

    private int value;
    private IntegerTreeNode left;
    private IntegerTreeNode right;
    private DUPLICATES duplicates = DUPLICATES.NOT_ALLOWED;

    @Override
    String valueString() {
        return String.valueOf(value);
    }

    @Override
    protected IntegerTreeNode getLeft() {
        return left;
    }

    @Override
    protected IntegerTreeNode getRight() {
        return right;
    }
    
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
                    remove(min_on_right);
                    this.value = min_on_right;
                } catch (NullPointerException err) {
                    int max_on_left = left.getMax();
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
        if (lst.isEmpty())
            return;
        
        int median_index = lst.size()/2;
        value = lst.get(median_index);
        
        if (0 < median_index) {
            left = new IntegerTreeNode();
            left.rebalance(lst.subList(0, median_index));
        }
        if (median_index+1 < lst.size()) {
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
