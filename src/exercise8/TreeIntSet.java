/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exercise8;


public class TreeIntSet implements IntSet {
    IntegerTreeNode top;

    @Override
    public void add(int value) {
        try{
            top.add(value);
        } catch(NullPointerException err) {
            top = new IntegerTreeNode(value);
            top.setDuplicates(IntegerTreeNode.DUPLICATES.NOT_ALLOWED);
        }
    }

    @Override
    public boolean contains(int value) {
        return top.contains(value);
    }

    @Override
    public boolean containsVerbose(int value) {
        return top.contains(value, true);
    }

}
