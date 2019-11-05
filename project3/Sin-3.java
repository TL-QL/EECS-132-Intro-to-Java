/** A class represents the sine function
 *  @author <em> Qiwen Luo </em>
 */
public class Sin extends Function{
  
  /** operand stores a function that represents an operand of a function */
  private Function operand;
  
  /**
   *  Initialize the operand of the sine function
   *  @param operand store the operand of the sine function
   */
  public Sin(Function operand){
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
   * Calculate the value of the sine function at the given input
   * @param input store a double that is going to be input in the sine function
   * @return the value of the sine function at the given input
   */
  @Override
  public double value(double input){
    if(getOperand() instanceof Variable)
      return Math.sin(input);
    else
      return Math.sin(getOperand().value(input));
  }
  
  /**
   * Calculate the value of the function which does not need an input
   * @return the value of the function if the function does not need an input,
   *         throw a UnsupportedOperationException if the function has no input and input is expected
   */
  @Override
  public double value(){
   return Math.sin(getOperand().value());
  }
  
  /**
   * Calculate the derivative of the sine function
   * @return the derivative of the sine function
   */
  @Override
  public Function derivative(){
      return new BinaryOp(BinaryOp.Op.MULT,new Cos(getOperand()),getOperand().derivative());
  }
  
  /**
   * Use a string to represent a sine function
   * @return the string that represents a sine function
   */
  @Override
  public String toString(){
    return "Sin[" + getOperand() + "]";
  }
  
  /**
   * Compare the operands of two sine functions
   * @param o stores an object whose operand is used to be compared with another sine function
   * @return true if o represents a sine function and the two sine functions have the same structure
   */
  @Override
  public boolean equals(Object o){
    if (o instanceof Sin){
      Sin s = (Sin) o;
      return getOperand().equals(s.getOperand());
    }
    else
      return false;
  }
}