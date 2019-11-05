/** Written by Qiwen Luo   student ID: qxl216
 * A class that represents an equity that is the shares of stock in a company 
 */
public class Stock extends Equity{
  
  /* A constructor that sets the name, the symbol and the current price of the stock */
  //name stores the name of the stock, stockSymbol stores the symbol of the stock and currentPrice stores current price of the stock
  public Stock(String name, char stockSymbol, double currentPrice){  
    super(name, stockSymbol, currentPrice);
  }
  
  /* buy stock, renew the number of shares owned and cost basis, and get the amount the bookkeeper pay for the stock */
  // numberOfSharesBought stores the number of shares that the bookeeper bought and commission stores the commission that the bookeeper should pay
  public double buyStock(int numberOfSharesBought, double commission){  
    setNumberShares(getNumberShares()+numberOfSharesBought);
    setCostBasis(getCostBasis()+numberOfSharesBought*getCurrentPrice()+commission);
    return numberOfSharesBought*getCurrentPrice()+commission;
  }
  
  /* sell stock, renew the number of shares owned, the cost basis and the capital gains, and get the amount that the bookeeper can get at last */
  // numberOfSharesSold stores the number of shares that the bookeeper sold and commission stores the commission that the bookeeper pays
  public double sellStock(int numberOfSharesSold, double commission){  
    if(numberOfSharesSold>getNumberShares()) return 0;
    else
    {
      setCapitalGains(getCapitalGains()+numberOfSharesSold*getCurrentPrice()-commission-getCostBasis()*numberOfSharesSold/getNumberShares());
      setCostBasis(getCostBasis()*(1-numberOfSharesSold/getNumberShares()));
      setNumberShares(getNumberShares()-numberOfSharesSold);
      return numberOfSharesSold*getCurrentPrice()-commission;
    }
  }
  
  /* sell part of stock and make sure the number of stock remained is the whole number*/
  public double splitStock(int ratioNumerator, int ratioDenominator){
    double number=getNumberShares()*ratioNumerator*1.0/ratioDenominator;
    if(ratioNumerator==0||ratioDenominator==0)  return 0;
    else
    {
      if(number==(int)number) {
        setNumberShares(number);
        return 0;
      }
      else
      {
        setCapitalGains(getCapitalGains()+(number-(int)number)*getCurrentPrice()-getCostBasis()*(number-(int)number)/number);
        setCostBasis(getCostBasis()*(1-(number-(int)number)/number));
        setNumberShares(number-(number-(int)number));
        return (number-(int)number)*getCurrentPrice();
      }
    }
  }
}