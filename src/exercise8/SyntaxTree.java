/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;

import BBK.PiJ01.common.BadInput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class SyntaxTree extends AbstractTreeNode {
    
    public SyntaxTree(String str) throws BadInput {
        add(str);
    }

    public static char[] operands = new char[]{'^', '/', '*', '-', '+'};
    SyntaxTree left, right;
    String value;

    @Override
    protected AbstractTreeNode getLeft() {
        return left;
    }

    @Override
    protected AbstractTreeNode getRight() {
        return right;
    }

    @Override
    String valueString() {
        return value;
    }

    private void add(String str) throws BadInput {
        str.replaceAll(" ", "");
        if (str.isEmpty())
            throw new BadInput("Received empty string");

        int nested_level = 0, chosen_operator=-1, this_operator;
        ArrayList<Integer> op_locations = new ArrayList<Integer>();
        char ch;

        for (int i = 0; i < str.length(); ++i) {
            ch = str.charAt(i);

            if (ch == '(') {
                // If no operator has been found, add locations of the '(' 
                // belonging to top-level brackets.
                if (nested_level == 0 && chosen_operator < 0 ) {
                    op_locations.add(i);
                }
                ++nested_level;
                continue;
            }

            if (ch == ')') {
                --nested_level;
                continue;
            }
            
            if (nested_level < 0)
                throw new BadInput("Found ) before finding ( : " + str);

            if (nested_level == 0) {
                this_operator = getOperator(ch);
                
                // Ignore the operand in eg. "-5" or "+6"
                if (2 <= this_operator && i == 0)
                    continue;
                
                // If this operator has priority over chosen operator
                if (this_operator > chosen_operator) {
                    chosen_operator = this_operator;
                    op_locations.clear();
                }
                
                // If this operator is the chosen operator
                if (this_operator > -1 && this_operator == chosen_operator) {
                    op_locations.add(i);
                }
            }
        }

        // Check for bad parens
        if (nested_level != 0) {
            throw new BadInput("Found non-equal numbers of ( and ) : " + str);
        }
        
        // If I found an operand, split the string at the median
        // location of that operand.
        if (chosen_operator > -1) {
            int median_op_location = op_locations.get(op_locations.size()/2);
            value = String.valueOf(operands[chosen_operator]);
            
            left = new SyntaxTree( str.substring(0, median_op_location) );
            right = new SyntaxTree( str.substring(median_op_location+1) );
            return;
        }
        
        // If str == "( ...stuff... )" then remove outer parens and try 'add' again
        if (str.charAt(0) == '(' && str.charAt(str.length()-1) == ')' 
                && op_locations.size() == 1) {
            add(str.substring(1, str.length()-1));
            return;
        }
        System.out.println("ops = " + op_locations.size()+ " : " + str);
        
        // No operands, only numbers and brackets.
        if (op_locations.size() >= 1) {
            // "(1+2)(3+4)" and "5(3+3)" are fine, but "(3+3)5(4+4)" is not.
            // ie. all but the first '(' must be preceeded by ')'
            ListIterator<Integer> left_paren_pos = op_locations.listIterator();
            left_paren_pos.next();  // ignore the first '('
            int right_paren_pos;
            while (left_paren_pos.hasNext()) {
                if ( str.charAt(left_paren_pos.next()-1) != ')' )
                    throw new BadInput("A number proceeded a paren: " + str);
            }
            
            // All '(' represent a '*(', except for the first '(' if it's at
            // the start of str.
            if (op_locations.get(0) == 0)
                op_locations.remove(0);
            
            // Finally, need to weed out equations of the form "(4+3)2".
            if (op_locations.size() == 0)
                throw new BadInput("A number proceeded a paren: " + str);
            
            // Now all op_locations actually refer to an operator.  So again we
            // choose the median operator and split accordingly.
            int median_op_location = op_locations.get(op_locations.size()/2);
            value = "*";
            left = new SyntaxTree( str.substring(0, median_op_location) );
            right = new SyntaxTree( str.substring(median_op_location) );
            return;
        }
        
        // If I've got this far, then there's no operands and no parens
        // (ignoring assumed multiplication), so it must be a number.
        // I'll just check it's a double
        Double.valueOf(str);
        value = str;

    }
    
    private int getOperator(char ch) {
        for (int j = 0; j < operands.length; ++j) {
            if (operands[j] == ch) {
                value = String.valueOf(operands[j]);
                return j;
            }
        }
        return -1;
    }

    public double evaluate() {
        if (value.equals("*")) {
            return left.evaluate() * right.evaluate();
        }
        
        if (value.equals("/")) {
            return left.evaluate() / right.evaluate();
        }
        
        if (value.equals("+")) {
            return left.evaluate() + right.evaluate();
        }
        
        if (value.equals("-")) {
            return left.evaluate() - right.evaluate();
        }
        
        if (value.equals("^")) {
            return Math.pow(left.evaluate(), right.evaluate());
        }
        
        return Double.valueOf(value);
    }
    
}
