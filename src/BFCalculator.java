import java.io.PrintWriter;

/**
 * Contains methods for evaluating expressions and storing values in registers
 * 
 * @author Jayson Kunkel
 */
public class BFCalculator {
  
  PrintWriter pen = new PrintWriter(System.out, true);

  /** the last computed value */
  BigFraction lastComputed = new BigFraction(0, 1);

  /** the last stored fraction */
  BigFraction regFrac;

  /** an array of BigFractions stored in registers a-z */
  BigFraction[] storedFracs = new BigFraction[26];

  /** the key which we use to re-base character values to 0 */
  final static int key = 97;

  /**
   * Evaluates a string of expressions of any length
   * 
   * @param exp A string with fractions and mathelatical operators, separated with a single space
   * @return The result of evaluating all expressions in the string
   */
  public BigFraction evaluate (String exp) throws Exception{
    
    // separate the expressions into a character array
    String[] values = exp.split(" ");

    // the total value starts with the first expression
    // if the first expression is a letter, set the total to the value stored in the register
    // otherwise, set the total to the first expression
    char[] expChar = exp.toCharArray();

    BigFraction total;

    // assume that a value exists with the register
    // throws an exception if the expression starts with an operation
    if(Character.isLetter(expChar[0])){
      total = this.storedFracs[expChar[0] - key];
    }
    else if(Character.isDigit(expChar[0])){
      total = new BigFraction(values[0]);
    }
    else{
      throw new Exception("Error: expression must start with a value");
    } // if ... else

    // check if there are two values or operations in a row
    for(int i = 0; i < values.length - 1; i++){
      char[] c1 = values[i].toCharArray();
      char[] c2 = values[i+1].toCharArray();

      // if there are two of the same thing in a row, throw an exception
      if((Character.isLetterOrDigit(c1[0]) && Character.isLetterOrDigit(c2[0])) || 
        (!Character.isLetterOrDigit(c1[0]) && !Character.isLetterOrDigit(c2[0]))){
          //System.err.println("should throw exception here");
          throw new Exception("Error: cannot have two values or operations in a row");
      } // if

    } // for


    // when we reach a mathematical operator, perform the corresponding operation on the next fraction
    for(int i = 1; i < values.length; i++){

      if(i % 2 == 1){
        //System.err.println("Should not reach here");
        total = performOperation(total, values[i], values[i+1]);
      } // for
    } // for

    // set the last computed value to the total value of the expressions
    this.lastComputed = total;
    return total;
  } // evaluate (String)

  /**
   * Stores the last value computed in the named register
   * 
   * @pre Assume that a value associated with _register exists
   * 
   * @param register A character
   */
  public void store (char _register) { 

    //set the register of the last computed value to the given register
    int val = _register - key;
    storedFracs[val] = lastComputed;
    storedFracs[val].register = _register;

  } // store (char)

 /**
  * Performs either addition, subtraction, multiplication, or division on two fractions total and frac 
  *
  * @pre if frac is a letter, assume a value associated with that letter exists
  *
  * @param total The current total value of expressions
  * @param operation One of "+", "-", "*", "/"
  * @param frac the next fraction to be evaluated
  * @return The result of performing the operation
  */
  public BigFraction performOperation (BigFraction total, String operation, String frac) {

    // if the given expression is a character, use the value stored in the register
    // otherwise use the given value
    char[] fracChar = frac.toCharArray();
    BigFraction f1;

    // assume that the register exists
    if(Character.isLetter(fracChar[0])){
        f1 = this.storedFracs[fracChar[0] - key];
    }
    else{
      f1 = new BigFraction(frac);
    } // if ... else

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
