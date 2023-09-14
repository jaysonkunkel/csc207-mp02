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

      while(true) {
        String expr = scan.nextLine();
        if(expr.equals("QUIT")){
          break;
        }
        if(expr.contains("STORE")){
          bf.store(expr.charAt(expr.length()-1));
        }
        else {
          pen.println(bf.evaluate(expr));
      }
    }
    pen.close();
    scan.close();

  } // main (String[])

} // class InteractiveCalculator
