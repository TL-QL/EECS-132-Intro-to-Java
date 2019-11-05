/** Written by Qiwen Luo   student ID: qxl216
 * A class represents an equity that is the shares of a mutual fund 
 */
public class MutualFund extends Equity{
  
  /* An instance field that stores the load of the fund */
  private double fundLoad=0.0;
  
  /* A constructor that sets the name, the symbol and the current price of the fund */
  //name stores the name of the fund, mutualFundSymbol stores the symbol of the fund and currentPrice stores current price of the fund
  public MutualFund (String name, char mutualFundSymbol, double currentPrice){  
    super(name, mutualFundSymbol,currentPrice);
  }
  
  /* get the load of the mutual fund */
  public double getLoad(){
    return fundLoad;
  }
  
  /* change the load of the mutual fund */
  // fundLoad stores the load of the mutual fund
  public void setLoad(double fundLoad){  
    this.fundLoad=fundLoad;
  }
  
  /* buy mutual fund, renew the number of shares owned and cost basis, and get the amount of money that the bookkeeper is investing in the mutual fund */
  // amount stores the amount of money that the bookkeeper is investing in the mutual fund
  public double buyMutualFund(double amount){  
    if(amount<=0) return 0;
    else
    {
      setNumberShares(getNumberShares()+amount*(1-fundLoad)/getCurrentPrice());
      setCostBasis(getCostBasis()+amount);
      return amount;
    }
  }
  
  /* sell mutual fund, renew the number of shares owned, cost basis and capital gains and get the amount of money that the bookkeeper is withdrawing from the mutual fund */
  // amount stores the amount of money that the bookkeeper is withdrawing from the mutual fund
  public double sellMutualFund(double amount){  
    if(amount<=0||amount>getCurrentPrice()*getNumberShares()) return 0;
    else
    {
      setCapitalGains(getCapitalGains()+amount-getCostBasis()*(amount/(getCurrentPrice()*getNumberShares())));
      setCostBasis(getCostBasis()-getCostBasis()*(amount/(getCurrentPrice()*getNumberShares())));
      setNumberShares(getNumberShares()-amount/getCurrentPrice());
      return amount;
    }
  }
  
}