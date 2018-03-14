package src;

import java.io.*;
import java.util.Scanner;


public class Main {
    final int PREFIX_NOTATION = 0;
    final int INFIX_NOTATION  = 1;
    final int POSTFIX_NOTATION = 2;

    static boolean do_debug_parse = false;

    // Store input string here
    private Reader mReader;

    private int notation = PREFIX_NOTATION;

    private String userInput() {
        // TODO: refactor this function to take input from a gui calculator
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expression: ");
        String input = scanner.next();
        return input;
    }

    Main (String argv[]) {
        CalculatorJFrame jframe = new CalculatorJFrame();
        jframe.setVisible(true);
        
        if(argv.length == 0) {
            //if no input file is provided, take input from user
            mReader = new StringReader(userInput());
        } else {
            //take file input
            try {
                mReader = new FileReader(argv[0]);
            } catch(FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }

        /* Start the parser */
        parse();

    }
    
    Main (String input) {
        mReader = new StringReader(input);
    }
    
    public String parse(int notation) {
        this.notation = notation;
        return parse();
    }
    
    private String parse() {
        Object result = new Object();
        System.out.println("Notation: "+this.notation);
        try {
            switch(this.notation) {
                case 0:
                    System.out.println("infix");
                    infix_parser p0 = new infix_parser(new Lexer(mReader));
                    result = p0.parse().value;
                    break;
                case 1:
                    System.out.println("prefix");
                    prefix_parser p1 = new prefix_parser(new Lexer(mReader));
                    result = p1.parse().value;
                    break;
                case 2:
                    System.out.println("postfix");
                    postfix_parser p2 = new postfix_parser(new Lexer(mReader));
                    result = p2.parse().value;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result.toString();
    }

    static public void main(String argv[]) {
        Main main = new Main(argv);
    }
}