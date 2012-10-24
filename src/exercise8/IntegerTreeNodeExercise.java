/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exercise8;

import BBK.PiJ01.common.Exercise;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class IntegerTreeNodeExercise implements Exercise {

    @Override
    public String getTitle() {
        return "Integer Tree Node testing.";
    }

    @Override
    public String getDescription() {
        return "TODO";
    }

    @Override
    public void run() {
        IntegerTreeNode top = new IntegerTreeNode(10);
        top.add(10);
        top.add(50);
        top.add(40);
        top.add(42);
        top.add(41);
        top.add(43);
        top.add(60);
        
        System.out.format("Max is %d and Min is %d\n", top.getMax(), top.getMin());
        System.out.println("Tree is: " + top);
        System.out.println("Tree depth is: " + top.depth());
        top.remove(42);
        System.out.println("Removed '42': " + top);
        top.rebalance();
        System.out.println("Rebalanced: " + top);
        
    }

}
