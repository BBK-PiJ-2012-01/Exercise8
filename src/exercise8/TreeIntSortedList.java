/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exercise8;


public class TreeIntSortedList implements IntSortedList {
    IntegerTreeNode lst;
    
    @Override
    public void add(int value) {
        try {
            lst.add(value);
        } catch (NullPointerException err) {
            lst = new IntegerTreeNode(value);
            lst.setDuplicates(IntegerTreeNode.DUPLICATES.ADD_LEFT);
        }
    }

    @Override
    public boolean contains(int value) {
        return lst.contains(value);
    }
    
    @Override
    public String toString() {
        return lst.toString();
    }
    
    public void rebalance() {
        lst.rebalance();
    }

}
