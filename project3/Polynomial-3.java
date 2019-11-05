/** A class represents a function raised to a power
 *  @author <em> Qiwen Luo </em>
 */
public class Polynomial extends Function{
  
  /** operand stores a function that represents an operand of a function */
  private Function operand;
  /** power stores a double that represents the power of the polynomial */
  private double power = 0.0;
  
  /**
   *  Initialize the operand and the power of the polynomial
   *  @param operand store the operand of the polynomial
   *  @param power store a double that represents the power of the polynomial
   */
  public Polynomial(Function operand, double power){
    this.operand = operand;
    this.power = power;
  }
  
  /**
   * get the operand of the function
   * @return the operand of the function
   */ 
  public Function getOperand(){
      return operand;
  }
  
  /**
   * get the power of the polynomial
   * @return the value of the power of the polynomial
   */ 
  public double getPower(){
    return power;
  }
  
  /**
   * Calculate the value of the polynomial at the given input
   * @param input store a double that is going to be input in the polynomial
   * @return the value of the polynomial at the given input
   */
  @Override
  public double value(double input){
    if(getOperand() instanceof Variable)
      return Math.pow(input, power);
    else
      return Math.pow(getOperand().value(input),power);
  }
  
  /**
   * Calculate the value of the function which does not need an input
   * @return the value of the function if the function does not need an input,
   *         throw a UnsupportedOperationException if the function has no input and input is expected
   */ 
  @Override
  public double value(){
      return Math.pow(getOperand().value(),power);
  }
  
  /**
   * Calculate the derivative of the polynomial
   * @return the derivative of the polynomial
   */
  @Override
  public Function derivative(){
      return new BinaryOp(BinaryOp.Op.MULT,new BinaryOp(BinaryOp.Op.MULT,new Number(getPower()),new Polynomial(getOperand(),power-1.0)),getOperand().derivative());
  }
  
  /**
   * Use a string to represent a polynomial
   * @return the string that represents a polynomial
   */
  @Override
  public String toString(){
    if(operand instanceof BinaryOp) return "(" + getOperand() + ")" + "^" + getPower();
    else
      return getOperand() + "^" + getPower();
  }
  
  /**
   * Compare the operands and the power of two polynomials
   * @param o stores an object whose operand and power are used to be compared with another polynomial
   * @return true if o represents a polynomial and the two polynomials have the same structure
   */
  @Override
  public boolean equals(Object o){
    if (o instanceof Polynomial){
      Polynomial p = (Polynomial) o;
      return getOperand().equals(p.getOperand()) && getPower() == p.getPower();
    }
    else
      return false;
  }
}