/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.Reader;
import java.io.StringReader;

/**
 *
 * @author nischal
 */
public class Calculator {
    final static int INFIX_NOTATION  = 0;
    final static int PREFIX_NOTATION = 1;
    final static int POSTFIX_NOTATION = 2;

    static boolean do_debug_parse = false;

    // Store input string here
    private Reader mReader;

    private int notation = PREFIX_NOTATION;

        
    // use this constructor to take input without a file
    public Calculator (String input) {
        this.mReader = new StringReader(input);
    }
    public Calculator (Reader reader) {
        this.mReader = reader;
    }
    public String parse(int notation) {
        this.notation = notation;
        return parse();
    }
    private String parser(Reader reader) {
        this.mReader = reader;
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
}
