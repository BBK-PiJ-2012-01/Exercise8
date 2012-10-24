/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;

import BBK.PiJ01.common.BadInput;
import BBK.PiJ01.common.Exercise;
import BBK.PiJ01.common.ExerciseChooser;
import java.util.ArrayList;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class Exercise8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BadInput {
        ArrayList<Exercise> exercises = new ArrayList<Exercise>();
        exercises.add(new IntegerTreeNodeExercise());
        
        ExerciseChooser ech = new ExerciseChooser(exercises);
        ech.run();
    }
}
