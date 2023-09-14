import java.io.PrintWriter;

/**
 * Contains methods for evaluating expressions and storing values in registers
 * 
 * @author Jayson Kunkel
 */
public class BFCalculator {
  
  PrintWriter pen = new PrintWriter(System.out, true);

  /** the last computed value */
  BigFraction lastComputed;

  /** the last stored fraction */
  BigFraction regFrac;

  /** an array of BigFractions stored in registers */
  // BigFraction[] storedFracs;

  /**
   * Evaluates a string of expressions of any length
   * 
   * @param exp A string with fractions and mathelatical operators, separated with a single space
   * @return The result of evaluating all expressions in the string
   */
  public BigFraction evaluate (String exp) {
    
    // separate the expressions into a character array
    String[] values = exp.split(" ");

    // total starts with first fraction
    char[] expChar = exp.toCharArray();
    BigFraction total;
    if(Character.isLetter(expChar[0])){
      total = this.regFrac;
    }
    else {
      total = new BigFraction(values[0]);
    }

    // when we reach a mathematical operator, perform the corresponding operation on the next fraction
    for(int i = 1; i < values.length; i++){
      if(i % 2 == 1){
        total = performOperation(total, values[i], values[i+1]);
       // pen.println("Total: " + total);
      } // for
    }

    this.lastComputed = total;
    return total;
  } // evaluate (String)

  /**
   * Stores the last value computed in the named register
   * 
   * @param register A character
   */
  public void store (char _register) { 

    //set the register of the last computed value to the given register
    this.regFrac = lastComputed;
    this.regFrac.register = _register;
    // pen.println(lastComputed.register);
  } // store (char)

 /**
  * Performs either addition, subtraction, multiplication, or division on two fractions total and frac 
  *
  * @param total The current total value of expressions
  * @param operation One of "+", "-", "*", "/"
  * @param frac the next fraction to be evaluated
  * @return The result of performing the operation
  */
  public BigFraction performOperation (BigFraction total, String operation, String frac) {

    char[] fracChar = frac.toCharArray();
    BigFraction f1;

    if(Character.isLetter(fracChar[0])){
      f1 = this.regFrac;
    }
    else{
      f1 = new BigFraction(frac);
    }

    // decide which operation to perform based on given operation
    if(operation.equals("+")){
      return total.add(f1);
    }
    else if (operation.equals("-")){
      return total.subtract(f1);
    }
    else if (operation.equals("*")){
      return total.multiply(f1);
    }
    else {
      return total.divide(f1);
    } // if ... else

  } // performOperation (BigFraction, String, String)

} // class BFCalculator
