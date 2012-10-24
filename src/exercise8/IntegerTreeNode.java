/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class IntegerTreeNode {

    private int value;
    private IntegerTreeNode left;
    private IntegerTreeNode right;

    public IntegerTreeNode(int value) {
        this.value = value;
    }

    public void add(int value) {
        if (value > this.value) {
            if (right == null) {
                right = new IntegerTreeNode(value);
            } else {
                right.add(value);
            }
        } else {
            if (left == null) {
                left = new IntegerTreeNode(value);
            } else {
                left.add(value);
            }
        }
    }

    public boolean contains(int value) {
        if (value == this.value) {
            return true;
        }

        try {
            if (value > this.value) {
                return right.contains(value);
            }

            return left.contains(value);
        } catch (NullPointerException err) {
            return false;
        }
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
        return String.format("%d L[%s] R[%s]", value, 
                                        (left==null? "":left),
                                        (right==null? "":right));
    }
}
