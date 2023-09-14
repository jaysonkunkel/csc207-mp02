import java.io.PrintWriter;

/**
 * Takes an arbitray number of command-line expressions at once, evaluates all of them, and prints the result
 * 
 * @author Jayson Kunkel
 */
public class QuickCalculator {
  
  public static void main (String[] args) throws Exception {

    PrintWriter pen = new PrintWriter(System.out, true);

    BFCalculator bf = new BFCalculator();

    // for each expression on the command line
    for(int i = 0; i < args.length; i++){

      // store the last computed value in given register
      if(args[i].contains("STORE")){
        bf.store(args[i].charAt(args[i].length()-1));
      }
      // print the given expression and print its computed value
      else {
        pen.println(args[i] + " = " + bf.evaluate(args[i]));
      } // if ... else
    } // for

    // clean up
    pen.close();

  } // main (String[])

} // class QuickCalculator
