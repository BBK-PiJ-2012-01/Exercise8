/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;

import BBK.PiJ01.common.BadInput;
import java.util.ArrayList;
import java.util.LinkedList;

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
        /*
         * Plan:
         *      for-loop:
         *          if ch == '(' then ++nested_level and ++total_nests
         *          if ch == ')' then --nested_level
         *          if nested_level == 0 && ch == '+' then op_list.add(ch_location), this.value = '+'
         *          if this.value == null && ch == '-' then op_list.add(ch_location), this.value = '-'
         *          if this.value == null && ch == '*' then op_list.add(ch_location), this.value = '*'
         *          if this.value == null && ch == '/' then op_list.add(ch_location), this.value = '/'
         * 
         *      if nested_level != 0 then throw new BadInput()
         * 
         *      if this.value != null then split string at median location in op_list,
         *          with left and right taking their components.
         *          NB. in "-5 - 6" we'd like to avoid the first '-' being taken,
         *              so split at median erring on the right side.
         * 
         *      else if this.value == null and total_nests == 1 and charAt(0) = '(' and charAt(end) = ')'
         *              then this.add( str.substring(1, end-1) ); return;
         * 
         *      else if total_nest = 0 and str.isDouble() then this.value = str
         *          
         */

        str.replaceAll(" ", "");
        if (str.isEmpty())
            throw new BadInput("Received empty string");

        int nested_level = 0, chosen_operator=-1, this_operator;
        ArrayList<Integer> op_locations = new ArrayList<Integer>();
        char ch;

        for (int i = 0; i < str.length(); ++i) {
            ch = str.charAt(i);

            if (ch == '(') {
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
            int median_operator_location;
            median_operator_location = op_locations.get(op_locations.size()/2);
            value = String.valueOf(operands[chosen_operator]);
            
            left = new SyntaxTree( str.substring(0, median_operator_location) );
            right = new SyntaxTree( str.substring(median_operator_location+1) );
            return;
        }
        
        // If str == "( stuff... )" then remove outer parens and try 'add' again
        // NB. This also catches "(...)(...)", so assumed multiplication is caught
        // here and will throw a BadInput when adding "...)(...".
        if (str.charAt(0) == '(' && str.charAt(str.length()-1) == ')') {
            add(str.substring(1, str.length()-1));
            return;
        }
        
        // If I've got this far, then there's no operands and no parens
        // (ignoring assumed multiplication), so it must be a number.
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
