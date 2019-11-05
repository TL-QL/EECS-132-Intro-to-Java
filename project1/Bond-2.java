/** Written by Qiwen Luo   student ID: qxl216
 * A class that represents an asset that is a loan from a government or corporation 
 */
public class Bond extends Asset{
  
  /* An instance field that stores principal of the bond */
  private double bondPrincipal=0.0;
  /* An instance field that stores interest rate of the bond */
  private double interestRate=0.0;
  /* An instance field that stores the number of bonds */
  private int numberOfBonds=0;
  
  /* A constructor that sets the name, the pricipal and interest rate of the bond. And it initializes the current price of the bond */
  // name stores name of the bond, bondPrincipal stores principal of the bond,interestRate stores interest rate
  public Bond(String name, double bondPrincipal, double interestRate){  
    super(name,0.0);
    this.bondPrincipal=bondPrincipal;
    this.interestRate=interestRate;
    setCurrentPrice(bondPrincipal);
  }
  
  /* get the principal of the bond */
  public double getPrincipal(){
    return bondPrincipal;
  }
  
  /* get the interest rate of the bond */
  public double getInterestRate(){
    return interestRate;
  }
  
  /* change the interest rate of the bond */
  // interestRate stores the interest rate of the bond
  public void setInterestRate(double interestRate){  
    this.interestRate=interestRate;
  }
  
  /* get the interest that the bookkeeper can get from the bond */
  public double payInterest(){
    return interestRate*bondPrincipal;
  }
  
  /* purchase a bond, renew the cost basis and return the current price of the bond */
  public double buyBonds(){
    setCostBasis(getCostBasis()+getCurrentPrice());
    numberOfBonds++;
    return getCurrentPrice();
  }
  
  /* sell a bond, renew the capital gains and return the currentPrice of the bond */
  public double sellBonds(){
    if(numberOfBonds==0) return 0;
    else{
      setCapitalGains(getCapitalGains()+getCurrentPrice()-getCostBasis()/numberOfBonds);
      setCostBasis(getCostBasis()-getCostBasis()/numberOfBonds);
      numberOfBonds--;
      return getCurrentPrice();
    }
  }
  
  /* get the number of bonds that is owned by the bookkeeper */
  public int getNumberOwned(){
    return numberOfBonds;
  } 
}