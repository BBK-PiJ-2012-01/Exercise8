/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exercise8;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class SyntaxTree extends AbstractTreeNode {
    
    public static String[] operands = new String[]{"/","*","+","-"};
    
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
    
    public void add(String str) {
        absorbSpaces(str);
        String temp_values[] = new String[3];
        
        if ( isDigit(str.charAt(0)) ) {
            int end_of_number;
            boolean decimal_found = false;
            for (end_of_number=0; end_of_number<str.length(); end_of_number++) {
                // Look for decimal...
                if ( isDigit(str.charAt(end_of_number)))
                    break;
            }
            temp_values[0] = str.substring(0, end_of_number);
        }
        for (int i=0; i<str.length(); i++) {
            
            if (str.charAt(i))
        }
        str.indexOf("/");
        String[] div_splits = str.split(str);
    }
    
    private boolean isDigit(char ch) {
        return (ch > '0' && ch < '9');
    }
    
    private void absorbSpaces(String str) {
        int next_non_space = 0;
        while (next_non_space < str.length()) {
            if (str.charAt(next_non_space) != ' ') 
                break;
        }
        
        str = str.substring(next_non_space);
    }

}
