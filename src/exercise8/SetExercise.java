/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exercise8;

import BBK.PiJ01.common.Exercise;
import BBK.PiJ01.common.IOGeneric;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class SetExercise implements Exercise {

    @Override
    public String getTitle() {
        return "Trees and LinkedLists as sets.";
    }

    @Override
    public String getDescription() {
        return "Demonstrates two implementations of a set, based on binary\n"
                + "trees and linked lists.";
    }

    @Override
    public void run() {
        TreeIntSet tree_set = new TreeIntSet();
        TreeIntSet tree_set_rebalanced = new TreeIntSet();
        ListIntSet lst_set = new ListIntSet();
        
        for (int i=0; i<20; i++) {
            tree_set.add(i);
            tree_set_rebalanced.add(i);
            lst_set.add(i);
        }
        
        tree_set_rebalanced.rebalance();
        
        IOGeneric.printTitle("Checking for 12 in linked list set", "-");
        System.out.println(lst_set);
        lst_set.containsVerbose(12);
        
        IOGeneric.printTitle("Checking for 12 in tree set", "-");
        System.out.println(tree_set);
        tree_set.containsVerbose(12);
        
        IOGeneric.printTitle("Checking for 12 in rebalanced tree_set", "-");
        System.out.println(tree_set_rebalanced);
        tree_set_rebalanced.containsVerbose(12);
    }

}
