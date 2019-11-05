/** A class represents the natural exponential function
 *  @author <em> Qiwen Luo </em>
 */
public class Exp extends Function{
  
   /** operand stores a function that represents an operand of a function */
  private Function operand;
  
  /**
   *  Initialize the operand of the natural exponential function
   *  @param operand store the operand of the natural exponential function
   */
  public Exp(Function operand){
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
   * Calculate the value of the natural exponential function at the given input
   * @param input store a double that is going to be input in the natural exponential function
   * @return the value of the natural exponential function at the given input
   */
  @Override
  public double value(double input){
    if(getOperand() instanceof Variable)
      return Math.exp(input);
    else
      return Math.exp(getOperand().value(input));
  }
  
  /**
   * Calculate the value of the function which does not need an input
   * @return the value of the function if the function does not need an input,
   *         throw a UnsupportedOperationException if the function has no input and input is expected
   */ 
  @Override
  public double value(){
      return Math.exp(getOperand().value());
  }
  
  /**
   * Calculate the derivative of the natural exponential function
   * @return the derivative of the natural exponential function
   */
  @Override
  public Function derivative(){
      return new BinaryOp(BinaryOp.Op.MULT,new Exp(getOperand()),getOperand().derivative());
  }
  
  /**
   * Use a string to represent a natural exponential function
   * @return the string that represents a natural exponential function
   */
  @Override
  public String toString(){
    return "Exp[" + getOperand() + "]";
  }
  
  /**
   * Compare the operands of two natural exponential functions
   * @param o stores an object whose operand is used to be compared with another natural exponential function
   * @return true if o represents a natural exponential function and the two natural exponential functions have the same structure
   */
  @Override
  public boolean equals(Object o){
    if (o instanceof Exp){
      Exp e = (Exp) o;
      return getOperand().equals(e.getOperand());
    }
    else
      return false;
  }
}