/** A class represents a binary operator (+, -, *, /) and two function operands
 *  @author <em> Qiwen Luo </em>
 */
public class BinaryOp extends Function{
  
  /** A class represents a binary operator (+, -, *, /) */
  enum Op{
  PLUS("+"), SUB("-"), MULT("*"), DIV("/");
  
  /** name stores a String that is a symbol of a binary operator */
  private String name;

  /**
   *  Initialize the symbol of the binary operator
   *  @param name store a symbol of a binary operator
   */
  private Op(String name) {
    this.name = name;
  }
  
  /**
   * Use a string to represent the symbol of the binary operator
   * @return the string that represents the symbol of the binary operator
   */
  public String toString(){
    return name;
  }
}
  
  /** leftOperand stores a function that represents the left operand */
  private Function leftOperand;
  /** rightOperand stores a function that represents the right operand */
  private Function rightOperand;
  /** operator stores a binary operator */
  private Op operator;
  
  /**
   *  Initialize the oerator, left operand and right operand of the BinaryOp function
   *  @param operator the binary operator (+, -, *, /)
   *  @param leftOperand the left operand of the BinaryOp function
   *  @param rightOperand the right operand of the BinaryOp function
   */
  public BinaryOp(Op operator, Function leftOperand, Function rightOperand){
    this.operator = operator;
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }
  
  /**
   * get the operator of the BinaryOp function
   * @return the operator of the BinaryOp function
   */ 
  public Op getOperator(){
    return operator;
  }
  
  /**
   * get the left operand of the BinaryOp function
   * @return the left operand of the BinaryOp function
   */ 
  public Function getLeftOperand(){
    return leftOperand;
  }
  
  /**
   * get the right operand of the BinaryOp function
   * @return the right operand of the BinaryOp function
   */ 
  public Function getRightOperand(){
    return rightOperand;
  }
  
  /**
   * Calculate the value of the BinaryOp function at the given input
   * @param input store a double that is going to be input in the BinaryOp function
   * @return the value of the BinaryOp function at the given input
   */
  @Override
  public double value(double input){
    if(getOperator() == Op.PLUS) return getLeftOperand().value(input) + getRightOperand().value(input);
    else if(getOperator() == Op.SUB) return getLeftOperand().value(input) - getRightOperand().value(input);
    else if(getOperator() == Op.MULT) return getLeftOperand().value(input) * getRightOperand().value(input);
    else
      return getLeftOperand().value(input) / getRightOperand().value(input);
  }
  
  /**
   * Calculate the value of the function which does not need an input
   * @return the value of the function if the function does not need an input,
   *         throw a UnsupportedOperationException if the function has no input and input is expected
   */ 
  @Override
  public double value(){
    if(getOperator() == Op.PLUS) return getLeftOperand().value() + getRightOperand().value();
    else if(getOperator() == Op.SUB) return getLeftOperand().value() - getRightOperand().value();
    else if(getOperator() == Op.MULT) return getLeftOperand().value() * getRightOperand().value();
    else
      return getLeftOperand().value() / getRightOperand().value();
  }
  
  /**
   * Calculate the derivative of the BinaryOp function
   * @return the derivative of the BinaryOp function
   */
  @Override
  public Function derivative(){
    if(getOperator() == Op.PLUS) return new BinaryOp(Op.PLUS,getLeftOperand().derivative(),getRightOperand().derivative());
    else if(getOperator() == Op.SUB) return new BinaryOp(Op.SUB,getLeftOperand().derivative(),getRightOperand().derivative());
    else if(getOperator() == Op.MULT) {
      BinaryOp bo1 = new BinaryOp(Op.MULT,getLeftOperand().derivative(),getRightOperand());
      BinaryOp bo2 = new BinaryOp(Op.MULT,getLeftOperand(),getRightOperand().derivative());
      return new BinaryOp(Op.PLUS,bo1,bo2);
    }
    else{ 
      BinaryOp bo1 = new BinaryOp(Op.MULT,getLeftOperand().derivative(),getRightOperand());
      BinaryOp bo2 = new BinaryOp(Op.MULT,getLeftOperand(),getRightOperand().derivative());
      BinaryOp boTop = new BinaryOp(Op.SUB,bo1,bo2);
      BinaryOp boBottom = new BinaryOp(Op.MULT,getRightOperand(),getRightOperand());
      return new BinaryOp(Op.DIV,boTop,boBottom);
    }
  }
  
  /**
   * Use a string to represent a BinaryOp function
   * @return the string that represents a BinaryOp function
   */
  @Override
  public String toString(){
    StringBuilder builder = new StringBuilder();
    if(getLeftOperand() instanceof BinaryOp) builder.append("(" + getLeftOperand() + ") ");
    else
      builder.append(getLeftOperand()+" ");
    if(getRightOperand() instanceof BinaryOp && !(getOperator()==((BinaryOp)getRightOperand()).getOperator())) builder.append(getOperator().toString()+" ("+getRightOperand()+")");
    else
      builder.append(getOperator().toString()+" "+getRightOperand());
    return builder.toString();
  }
  
  /**
   * Compare the operator and operands of two BinaryOp functions
   * @param o stores an object whose operator and operands are used to be compared with another BinaryOp function
   * @return true if o represents a BianryOp function and the two Binary functions have the same operator and both operands are equal.
   */
  @Override
  public boolean equals(Object o){
    if (o instanceof BinaryOp){
      BinaryOp bo = (BinaryOp) o;
      return getOperator()==bo.getOperator() && 
             ((getLeftOperand().equals(bo.getLeftOperand()) && getRightOperand().equals(bo.getRightOperand())) || (getLeftOperand().equals(bo.getRightOperand()) && getRightOperand().equals(bo.getLeftOperand())));
    }
    else
      return false;
  }
}