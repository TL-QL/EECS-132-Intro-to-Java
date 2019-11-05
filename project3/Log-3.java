/** A class represents the natural logarithm function
 *  @author <em> Qiwen Luo </em>
 */
public class Log extends Function{

  /** operand stores a function that represents an operand of a function */
  private Function operand;
  
  /**
   *  Initialize the operand of the natural logarithm function
   *  @param operand store the operand of the logarithm function
   */
  public Log(Function operand){
    this.operand = operand;
  }
  
  /**
   * get the operand of the function
   * @return the operand of the function
   */ 
  public Function getOperand(){
      return operand;
  }
  
  /**
   * Calculate the value of the natural logarithm function at the given input
   * @param input store a double that is going to be input in the natural logarithm function
   * @return the value of the natural logarithm function at the given input
   */
  @Override
  public double value(double input){
    if(getOperand() instanceof Variable)
      return Math.log(input);
    else
      return Math.log(getOperand().value(input));
  }
  
  /**
   * Calculate the value of the function which does not need an input
   * @return the value of the function if the function does not need an input,
   *         throw a UnsupportedOperationException if the function has no input and input is expected
   */
  @Override
  public double value(){
      return Math.log(getOperand().value());
  }
  
  /**
   * Calculate the derivative of the natural logarithm function
   * @return the derivative of the natural logarithm function
   */
  @Override
  public Function derivative(){
      return new BinaryOp(BinaryOp.Op.MULT,new Polynomial(getOperand(),-1.0),getOperand().derivative());
  }
  
  /**
   * Use a string to represent a natural logarithm function
   * @return the string that represents a natural logarithm function
   */
  @Override
  public String toString(){
    return "Log[" + getOperand() + "]";
  }
  
  /**
   * Compare the operands of two natural logarithm functions
   * @param o stores an object whose operand is used to be compared with another natural logarithm function
   * @return true if o represents a natural logarithm function and the two natural logarithm functions have the same structure
   */
  @Override
  public boolean equals(Object o){
    if (o instanceof Log){
      Log l = (Log) o;
      return getOperand().equals(l.getOperand());
    }
    else
      return false;
  }
}