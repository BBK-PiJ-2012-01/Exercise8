/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BBK.PiJ01.common;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class BadInput extends Exception {
    public BadInput(int value) {
        System.out.println("======= Input error: \"" + value + "\" =======");
    }
    
    public BadInput(String str) {
        System.out.println("======= Input error: \"" + str + "\" =======");
    }
    
    public BadInput() {
        //System.out.println("Bad input given!");
    }
}