/** An abstract class represents the function
 *  @author <em> Qiwen Luo </em>
 */
public abstract class Function extends Object{
  
  /**
   * Calculate the value of the function at the given input
   * @param input store a double that is going to be input in the function
   * @return the value of the cosine function at the given input
   */ 
  public abstract double value(double input);
  
  /**
   * Calculate the value of the function which does not need an input
   * @return the value of the function if the function does not need an input,
   *         throw a UnsupportedOperationException if the function has no input and input is expected
   */ 
  public abstract double value();
  
  /**
   * get the derivative of the function
   * @return the derivative of the function
   */ 
  public abstract Function derivative();
  
}