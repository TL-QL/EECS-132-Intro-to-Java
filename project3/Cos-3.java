/** A class represents the cosine function
 *  @author <em> Qiwen Luo </em>
 */
public class Cos extends Function{
  
  /** operand stores a function that represents an operand of a function */
  private Function operand;
  /**
   *  Initialize the operand of the cosine function
   *  @param operand store the operand of the cosine function
   */
  public Cos(Function operand){
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
   * Calculate the value of the cosine function at the given input
   * @param input store a double that is going to be input in the cosine function
   * @return the value of the cosine function at the given input
   */
  @Override
  public double value(double input){
    if(getOperand() instanceof Variable)
      return Math.cos(input);
    else
      return Math.cos(getOperand().value(input));
  }
  
  /**
   * Calculate the value of the function which does not need an input
   * @return the value of the function if the function does not need an input,
   *         throw a UnsupportedOperationException if the function has no input and input is expected
   */ 
  @Override
  public double value(){
      return Math.cos(getOperand().value());
  }
  
  /**
   * Calculate the derivative of the cosine function
   * @return the derivative of the cosine function
   */
  @Override
  public Function derivative(){
      return new BinaryOp(BinaryOp.Op.MULT,new BinaryOp(BinaryOp.Op.MULT,new Number(-1.0), new Sin(getOperand())),getOperand().derivative());
  }
  
  /**
   * Use a string to represent a cosine function
   * @return the string that represents a cosine function
   */
  @Override
  public String toString(){
    return "Cos[" + getOperand() + "]";
  }
  
  /**
   * Compare the operands of two cosine functions
   * @param o stores an object whose operand is used to be compared with another cosine function
   * @return true if o represents a cosine function and the two cosine functions have the same structure
   */
  @Override
  public boolean equals(Object o){
    if (o instanceof Cos){
      Cos c = (Cos) o;
      return getOperand().equals(c.getOperand());
    }
    else
      return false;
  }
}