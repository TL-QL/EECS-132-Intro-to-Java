/** A class represents the variable x
 *  @author <em> Qiwen Luo </em>
 */
public class Variable extends Function{
  
  /**
   * Calculate the value of the variable at the given input
   * @param input store a double that is going to be input in the variable
   * @return the value of the variable at the given input
   */
  @Override
  public double value(double input){
    return input;
  }
  
  /**
   * get the value of the variable
   * @return throw a UnsupportedOperationException
   */ 
  public double value(){
    throw new UnsupportedOperationException("miss a double input");
  }
  
  /**
   * Calculate the derivative of the variable
   * @return the derivative of the variable
   */
  @Override
  public Function derivative(){
    return new Number(1.0);
  }
  
  /**
   * Use a string to represent a variable
   * @return the string that represents a variable
   */
  @Override
  public String toString(){
    return "x";
  }
  
  /**
   * Compare the operands of two variables
   * @param o stores an object which is used to be compared with another Variable instance
   * @return true if o represents a Variable instance and the two instances have the same structure
   */
  @Override
  public boolean equals(Object o){
    if (o instanceof Variable){
      Variable v = (Variable) o;
      return (v.toString()).equals("x");
    }
    else
      return false;
  }
}