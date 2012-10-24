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
        top.add(5);
        top.add(15);
        top.add(14);
        top.add(16);
        
        System.out.format("Max is %d and Min is %d\n", top.getMax(), top.getMin());
        System.out.println("Tree is: " + top);
    }

}
