package src;

import java.io.*;
import java.util.Scanner;


public class Main {
    private void userInput() {
        CalculatorJFrame jframe = new CalculatorJFrame();
        jframe.setVisible(true);
    }

    // this constructor is only used when it is constructed by itself
    // usually this is from the main() method.
    private Main (String argv[]) {
        if(argv.length == 0) {
            // if no input file is provided, take input from user 
            userInput();
        } else {
            // take file input
            try {
                Reader reader = new FileReader(argv[0]);
                Calculator calc = new Calculator(reader);
                calc.parse(Calculator.PREFIX_NOTATION);
            } catch(FileNotFoundException e) {
                e.printStackTrace();
                System.err.println("Couldn't find file '"+argv[0]+"'!");
            }
        }
    }

    static public void main(String argv[]) {
        Main main = new Main(argv);
    }
}