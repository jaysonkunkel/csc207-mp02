import java.io.PrintWriter;

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


    String expr = "5/6 + 5/6";
    String[] splitExpr = expr.split(" ");

    for(int i = 0; i < splitExpr.length; i++){
      pen.println(splitExpr[i]);
    }
 BFCalculator bf = new BFCalculator();
 pen.println(bf.evaluate(expr));

  }

} // class QuickCalculator
