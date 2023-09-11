import java.math.BigInteger;
/**
 * A simple implementation of Fractions.
 * 
 * @author Samuel A. Rebelsky
 * @author Jayson Kunkel
 *         Adapted from the Class 6 lab - collaboration between myself and Chloe Kelly
 * @version 1.2 of August 2023
 */
public class BigFraction {
  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+
  /*
   * (1) Denominators are always positive. Therefore, negative fractions are represented 
   * with a negative numerator. Similarly, if a fraction has a negative numerator, it 
   * is negative.
   * 
   * (2) Fractions are not necessarily stored in simplified form. To obtain a fraction 
   * in simplified form, one must call the `simplify` method.
   */

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   * 
   * @param num a BigInteger
   * @param denom a BigInteger
   */
  public BigFraction(BigInteger num, BigInteger denom) {
    this.num = num;
    this.denom = denom;
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   * 
   * @param num an integer
   * @param denom an integer
   */
  public BigFraction(int num, int denom) {
    this.num = BigInteger.valueOf(num);
    this.denom = BigInteger.valueOf(denom);
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   * @param str A string of the form a/b, where a and b are integers
   */
  public BigFraction(String str) {
    //Scanner scan = new Scanner(System.in);
    //this.num = BigInteger.valueOf(2);
    //this.denom = BigInteger.valueOf(7);

    int indexOfSlash = str.indexOf("/");
    String numStr = str.substring(0, indexOfSlash);
    String denomStr = str.substring(indexOfSlash+1, str.length());

    this.num = BigInteger.valueOf(Integer.parseInt(numStr));
    this.denom = BigInteger.valueOf(Integer.parseInt(denomStr));
    //this.num = parseInt(numStr);

  } // BigFraction

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Express this fraction as a double.
   * 
   * @return the given fraction in double form
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add the fraction `addMe` to this fraction.
   * 
   * @param addMe
   * @return the result of adding 'addMe' to this fraction
   */
  public BigFraction add(BigFraction addMe) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the
    // product of this object's denominator
    // and addMe's denominator
    resultDenominator = this.denom.multiply(addMe.denom);
    // The numerator is more complicated
    resultNumerator = (this.num.multiply(addMe.denom)).add(addMe.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);

  }// add(BigFraction)

  /**
   * Subtract the fraction 'subtractMe' from this fraction.
   *  
   * @param subtractMe
   * @return the result of subtracting 'subtractMe' from this fraction
   */
  public BigFraction subtract(BigFraction subtractMe) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    BigInteger negNum = subtractMe.num.multiply(BigInteger.valueOf(-1/1));

    resultDenominator = this.denom.multiply(subtractMe.denom);
    resultNumerator = (this.num.multiply(subtractMe.denom)).add(negNum.multiply(this.denom));

    return new BigFraction(resultNumerator, resultDenominator);

  } // subtract (BigFraction)

  /**
   * Multiply the fraction 'multiplyMe' to this fraction
   * 
   * @param multiplyMe
   * @return the result of multiplying 'multiplyMe' by this fraction
   */
  public BigFraction multiply (BigFraction multiplyMe){
    BigInteger resultNumerator;
    BigInteger resultDenominator; 

    resultNumerator = this.num.multiply(multiplyMe.num);
    resultDenominator = this.denom.multiply(multiplyMe.denom);

    return new BigFraction (resultNumerator, resultDenominator);

  } // multiply (BigFraction)

  /**
   * Divide this fraction by the fraction 'divideMe'
   * 
   * @param divideMe
   * @return the result of dividing this fraction by 'divideMe'
   */
  public BigFraction divide (BigFraction divideMe) {
    BigInteger resultNumerator; 
    BigInteger resultDenominator;

    resultNumerator = this.num.multiply(divideMe.denom);
    resultDenominator = this.denom.multiply(divideMe.num);

    return new BigFraction(resultNumerator, resultDenominator);

  } // divide (BigFraction)

  /**
   * Simplifies this fraction to the greatest extent
   *
   */
  public BigFraction simplify() {

    if(this.num.equals(BigInteger.ZERO) ){
      return new BigFraction("0/0");
    }

    BigInteger resultNumerator;
    BigInteger resultDenominator;

    BigInteger gcd = this.num.gcd(this.denom);

    resultNumerator = this.num.divide(gcd);
    resultDenominator = this.denom.divide(gcd);

    return new BigFraction(resultNumerator, resultDenominator);

  }  // simplify()

  /**
   * Get the fractional part of this fraction
   * 
   * @pre this fraction is positive
   */
  public BigFraction fractional(){
    return new BigFraction(this.num.mod(this.denom), this.denom);
  } // fractional()


  /**
   * Get the denominator of this fraction.
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()
  
  /**
   * Get the numerator of this fraction.
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()
  
  /**
   * Convert this fraction to a string for ease of printing.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero

    // Special case: It's one
    if(this.num.equals(BigInteger.ONE) && this.denom.equals(BigInteger.ONE)){
      return "1";
    } // if it's one

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()

} // class BigFraction