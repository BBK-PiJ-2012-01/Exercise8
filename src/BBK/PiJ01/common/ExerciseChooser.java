/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BBK.PiJ01.common;

import java.util.*;


/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class ExerciseChooser {
    private List<Exercise> exercises;
    private List<String> titles = new ArrayList<String>();
    private String question;
    
    boolean running = false;
    
    public ExerciseChooser(List<Exercise> new_exercises) throws BadInput {
        if (new_exercises.size() < 1)
            throw new BadInput("Must have at least one exercise to choose from!");
        
        exercises = new_exercises;
        exercises.add(0, new QuitExercise());
        
        for (Exercise ex : exercises) {
            titles.add(ex.getTitle());
        }
    }
    
    public void run() {
        question = String.format("Choose which exercise to run [%d -> %d]?", 0, exercises.size()-1);
        Exercise chosen;
        
        while (true) {
            IOGeneric.printSpacers(" ");
            IOGeneric.printSpacers("=");
            IOGeneric.printTitle(question, "=");
            IOGeneric.printSpacers("=");
            
            
            try {
                
                chosen = exercises.get( IOGeneric.chooseFromList(titles) );
            } catch(IndexOutOfBoundsException e) {
                System.out.println("That's not an option!");
                continue;
            } catch(BadInput e) {
                System.out.format("I don't understand.  "
                        + "Type a number between %d and %d.\n", 0, exercises.size()-1);
                continue;
            }
            
            IOGeneric.printSpacers(" ");
            IOGeneric.printTitle(chosen.getTitle(), "=");
            IOGeneric.printResult(chosen.getDescription(), "-");

            if (chosen instanceof QuitExercise)
                return;
            else
                chosen.run();
        }
    }
}


class QuitExercise implements Exercise {
    public String getTitle() {
        return "Quit";
    }
    
    public String getDescription() {
        return "Goodbye.";
    }
    
    public void run() {}
}