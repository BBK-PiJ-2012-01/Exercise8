package BBK.PiJ01.common;


import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

/**
 *
 * @author Sam Wright <swrigh11@dcs.bbk.ac.uk>
 */
public class IOGeneric {
    /*
    *    Generic user input/output class used in many exercises.
    */
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static String getString() throws BadInput{
        //return System.console().readLine();
        try {
            return br.readLine();
        } catch(IOException e) {
            throw new BadInput("Couln't get input. IO problem?");
        }
    }
    
    public static int getInteger() throws BadInput {
        int num;
        try {
            String str = getString();
            num = Integer.parseInt(str);
        } catch(NumberFormatException e) {
            throw new BadInput();
        }
        
        return num;
    }
    
    public static double getDouble() throws BadInput {
        double num;
        try {
            String str = getString();
            num = Double.parseDouble(str);
        } catch(NumberFormatException e) {
            throw new BadInput();
        }
        return num;
    }
    
    public static String multiplyString(String input, int number_of_times) {
        StringBuffer s = new StringBuffer();
        
        for (int i=0; i<number_of_times; i++) {
                s.append(input);
        }
        return s.toString();
    }

    public static void printResult(String result, String ...spacer_str) {
        String spacer = getSingleDefault(spacer_str, "-");
        
        int longest_line_length = 1;
        
        String[] lines = result.split("\n");
        for (int l=0; l<lines.length; l++) {
            longest_line_length = Math.max(longest_line_length, lines[l].length());
        }
        
        String header_footer = multiplyString(spacer, longest_line_length);
        
        System.out.println(header_footer);
        System.out.println(result);
        System.out.println(header_footer);
    }
    
    public static <T> String listToString(T[] lst, String ...format_str) {
        StringBuffer sbuf = new StringBuffer();
        //String format = params==null ? "[,]" : params[0];
        String format = getSingleDefault(format_str, "[,]");
        assert format.length() == 3;
        
        sbuf.append(format.charAt(0));
        for (int i=0; i<lst.length; i++) {
            sbuf.append(lst[i]);
            sbuf.append(format.charAt(1));
        }
        sbuf.setCharAt(sbuf.length()-1, format.charAt(2));
        
        return sbuf.toString();
    }
    
    public static String intListToString(int[] lst, String ...params) {
        String[] str_list = new String[lst.length];
        for (int i=0; i<lst.length; i++) {
            str_list[i] = String.valueOf(lst[i]);
        }
        return listToString(str_list);
    }
    
    private static <T> T getSingleDefault(T[] params, T default_value) {
        //assert params.length == 1;
        T value = default_value;
        if (params != null && params.length > 0)
            value = params[0];
        return value;
    }
    
    /*
    private static <T> T[] getDefaults(T[] params, T[] default_values) {
        assert params.length == default_values.length;
        for (int i=0; i<params.length; i++) {
            if (params[i] == null);
                params[i] = default_values[i];
        }
        return params;
    }
    * */
    
    public static void printTitle(String title, String ...spacer_str) {
        String spacer = getSingleDefault(spacer_str, "-");
        
        String spaces_either_side = multiplyString(spacer, Math.max(39-title.length()/2, 10));
        
        System.out.println(spaces_either_side + " " + title + " " + spaces_either_side);
    }
    
    public static void printSpacers(String ...spacer_str) {
        String spacer = getSingleDefault(spacer_str, "-");
        
        System.out.println( multiplyString(spacer, 80) );
    }
    
    public static int chooseFromList(List<String> lst) throws BadInput, IndexOutOfBoundsException {
        // Returns list index of chosen item
        
        for (int i=0; i<lst.size(); i++) {
            System.out.format("[%d] %s\n", i, lst.get(i));
        }
        
        System.out.print("\nYour choice: ");
        int choice_int = getInteger();
        
        return choice_int;
    }
    
    public static int chooseFromList(String[] lst) throws BadInput {
        ArrayList<String> array_list = new ArrayList<String>();
        Collections.addAll(array_list, lst);
        return chooseFromList(array_list);
    }
}


