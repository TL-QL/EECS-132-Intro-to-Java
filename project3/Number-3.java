/** A class represents a number as a double
 *  @author <em> Qiwen Luo </em>
 */
public class Number extends Function{
  
  /** number stores a double that represents a number */
  private double number = 0.0;
  
  /**
   *  Initialize the value of the number
   *  @param number store the value of the number
   */
  public Number(double number){
    this.number = number;
  }
  
  /**
   * get the value of the number no matter what value is input
   * @param input store a double
   * @return the value of the number no matter what value is input
   */ 
  @Override
  public double value(double input){
    return number;
  }
  
  /**
   * get the value of the number
   * @param input store a double that is going to be input in the natural logarithm function
   * @return the value of the number
   */ 
  public double value(){
    return number;
  }
  
  /**
   * Calculate the derivative of the number (constant)
   * @return the derivative of the number (constant)
   */ 
  @Override
  public Function derivative(){
    return new Number(0.0);
  }
  
  /**
   * Use a string to represent a number
   * @return the string that represents a number
   */ 
  @Override
  public String toString(){
    return "" + value();
  }
  
   /**
   * Compare the values of two numbers
   * @param o stores an object whose value is used to be compared with another number
   * @return true if o represents a number and the two numbers have the same value
   */
  @Override
  public boolean equals(Object o){
    if (o instanceof Number){
      Number n = (Number) o;
      return (Math.abs(this.value() - n.value())) < Math.pow(10.0,-6.0);
    }
    else
      return false;
  }
}