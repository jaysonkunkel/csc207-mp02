import java.io.PrintWriter;

/**
 * Takes an arbitray number of command-line expressions at once, evaluates all of them, and prints the result
 * 
 * @author Jayson Kunkel
 */
public class QuickCalculator {
  
  public static void main (String[] args) throws Exception {

    PrintWriter pen = new PrintWriter(System.out, true);

    // BigFraction f1 = new BigFraction(3, 10);
    // BigFraction f2 = new BigFraction(3, 10);

    // pen.println("First fraction: " + f1);
    // pen.println("Second fraction: " + f2);
    // pen.println("Sum: " + (f1.add(f2)));
    // pen.println("Difference: " + (f1.subtract(f2)));
    // pen.println("Product: " + (f1.multiply(f2)));
    // pen.println("Dividend: " + f1.divide(f2));

    // pen.println(f1.add(f2).simplify());
    // pen.println(f1.subtract(f2).simplify());
    // pen.println(f1.multiply(f2).simplify());
    // pen.println(f1.divide(f2).simplify());

    BFCalculator bf = new BFCalculator();

    for(int i = 0; i < args.length; i++){

      if(args[i].contains("STORE")){
        bf.store(args[i].charAt(args[i].length()-1));
      }
      else {
        pen.println(args[i] + " = " + bf.evaluate(args[i]));
      }
      //pen.println(bf.lastComputed);
    }

    pen.close();
  

  } // main (String[])

} // class QuickCalculator
