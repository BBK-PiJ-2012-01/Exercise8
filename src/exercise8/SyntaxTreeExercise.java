/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exercise8;

import BBK.PiJ01.common.BadInput;
import BBK.PiJ01.common.Exercise;
import BBK.PiJ01.common.IOGeneric;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class SyntaxTreeExercise implements Exercise {

    @Override
    public String getTitle() {
        return "Syntax Tree";
    }

    @Override
    public String getDescription() {
        return "Takes an equation then prints the syntax tree.\n"
                + "Press enter without entering an equation to quit.";
    }

    @Override
    public void run() {
        String str = "";
        SyntaxTree tree;
        
        do {
            try {
                System.out.print("Enter equation: ");
                str = IOGeneric.getString();
                tree = new SyntaxTree(str);
                System.out.print(tree);
                System.out.println(str + " = " + tree.evaluate() + "\n");
            } catch (BadInput ex) {
                System.out.println("\nI don't understand.  Try again!");
                continue;
            }
        } while (!str.isEmpty());
    }

}
