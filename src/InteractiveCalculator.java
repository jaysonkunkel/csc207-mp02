import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Repeatedly reads a user-input line, and computes and rpints the result
 * 
 * @author Jayson Kunkel
 */
public class InteractiveCalculator {
  
  public static void main (String[] args) throws Exception {

    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner scan = new Scanner(System.in);

    BFCalculator bf = new BFCalculator();

    // repeatedly scan expressions from user input
    while(true) {
      String expr = scan.nextLine();

      // exit if user is done entering expressions
      if(expr.equals("QUIT")){
        break;
      }
      // store the last computed value in the given register
      if(expr.contains("STORE")){
        bf.store(expr.charAt(expr.length()-1));
      }
      // evaluate the given expression and print the result
      else {
        pen.println(bf.evaluate(expr));
      } // if ... else
    } // while

    // clean up
    pen.close();
    scan.close();

  } // main (String[])

} // class InteractiveCalculator
