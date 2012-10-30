/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;

import BBK.PiJ01.common.BadInput;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class SyntaxTreeTest {
    
    public SyntaxTreeTest() {
    }

    /**
     * Test of getLeft method, of class SyntaxTree.
     */
    @Test
    public void testAdd() {
        assertEquals(5.0, evaluate("2+3"), 0.001);
        assertEquals(5.0, evaluate("+2+3"), 0.001);
    }
    
    @Test
    public void testSubtract() {
        assertEquals(2.0, evaluate("5-3"), 0.001);
        assertEquals(-13.0, evaluate("-10-3"), 0.001);
    }
    
    @Test
    public void testBrackets() {
        assertEquals(16.0, evaluate("2*(3+5)"), 0.001);
        assertEquals(16.0, evaluate("(3+5)*2"), 0.001);
        assertEquals(16.0, evaluate("(3+5)*(1+1)"), 0.001);
    }
    
    @Test
    public void testDivide() {
        assertEquals(1.5, evaluate("3/2"), 0.001);
        assertEquals(7.5, evaluate("3*5/2"), 0.001);
        assertEquals(5.5, evaluate("3+5/2"), 0.001);
    }
    
    @Test
    public void testMultiply() {
        assertEquals(15, evaluate("5*3"), 0.001);
    }
    
    @Test
    public void testImpliedMultiplication() {
        assertEquals(27, evaluate("3(4+5)"), 0.001);
        assertEquals(27, evaluate("(1+2)(4+5)"), 0.001);
    }
    
    
    @Test
    public void testPower() {
        assertEquals(25, evaluate("5^2"), 0.001);
        assertEquals(4.0, evaluate("2^(4-2)"), 0.001);
        assertEquals(2.0, evaluate("8/2^(4-2)"), 0.001);
    }
    private double evaluate(String str) {
        System.out.println(str);
        SyntaxTree instance;
        try {
            instance = new SyntaxTree(str);
            return instance.evaluate();
        } catch (BadInput ex) {
            assertTrue(false);
        }
        return 0;
    }

}
