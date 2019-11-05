/** Written by Qiwen Luo   student ID: qxl216
 * A class that represents an asset where the bookshopkeeper can own shares of the assent 
 */
public class Equity extends Asset{
  
  /* An instance field that stores the symbol of the equity */
  private char equitySymbol=' ';
  /* An instance field that stores the number of shares owned of the equity */
  private double numberOfSharesOwned=0.0;
  
  /* A constructor that sets the name, the symbol and current price of the equity. And it initializes the cost basis */
  // name stores the name of the equity, equitySymbol stores the symbol of the equity and currentPrice stores thev current price of the equity
  public Equity(String name, char equitySymbol, double currentPrice){ 
    super(name,0.0);
    this.equitySymbol=equitySymbol;
    setCurrentPrice(currentPrice);
    setCostBasis(0);
  }
  
  /* get the symbol of the equity */
  public char getSymbol(){
    return equitySymbol;
  }
  
  /* get the number of shares owned of the equity */
  public double getNumberShares(){
    return numberOfSharesOwned;
  }
  
  /* change the number of shares owned of the equity*/
  // numberOfSharesOwned stores the number of shares owned of the equity
  public void setNumberShares(double numberOfSharesOwned){  
    this.numberOfSharesOwned=numberOfSharesOwned;
  }
}