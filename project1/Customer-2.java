/** Written by Qiwen Luo   student ID: qxl216
 * A class that represents a customer account 
 */
public class Customer extends Object{
  
  /* An instance field that stores an instance of class Stock */
  private Stock s;
  /* An instance field that stores an instance of class Bond */
  private Bond b;
  /* An instance field that stores an instance of class MutualFund */
  private MutualFund m;
  /* An instance field that stores the information of cash account */
  private CashAsset cashAccount;
  /* An instance field that stores a commission rate */
  private double commissionRate;
  
  /* A constructor that sets the information of cash account and commssion */
  // cashAccount stores the information of cash account and commissionRate stores a commission rate
  public Customer(CashAsset cashAccount, double commissionRate){
    this.cashAccount=cashAccount;
    this.commissionRate=commissionRate;
  }
  
  /* A constructor that set the information of cash account, bond, mutual fund, stock and commssion*/
  // cashAccount stores the information of cash account, b stores the information of bond, m stores the information of mutual fund, s stores the information of stock and commissionRate stores a commission rate
  public Customer(CashAsset cashAccount, Bond b, MutualFund m, Stock s, double commissionRate){
    this.cashAccount=cashAccount;
    this.b=b;
    this.m=m;
    this.s=s;
    this.commissionRate=commissionRate;
  }
  
  /* get a bond instance */
  public Bond getBond(){
    return b;
  }
  
  /* change a bond instance */
  // b stores the information of bond 
  public void setBond(Bond b){
    this.b=b;
  }
  
  /* get a mutual fund instance */
  public MutualFund getMutualFund(){
    return m;
  }
  
  /* change a mutual fund instance */
  // m stores the information of mutual fund 
  public void setMutualFund(MutualFund m){
    this.m=m;
  }
  
  /* get a stock instance */
  public Stock getStock(){
    return s;
  }
  
  /* change a stock instance */
  // s stores the information of stock
  public void setStock(Stock s){
    this.s=s;
  }
  
  /* get the cash account */
  public CashAsset getCashAccount(){
    return cashAccount;
  }
  
  /* change the cash account */
  // cashAccount stores the information of cash account
  public void setCashAccount(CashAsset cashAccount){
    this.cashAccount=cashAccount;
  }
  
  /* get the commission amount for the account */
  public double getCommissionAccount(){
    return commissionRate;
  }
  
  /* change the commission amount for the account */
  // commissionRate stores a commission rate
  public void setCommissionAccount(double commissionRate){
    this.commissionRate=commissionRate;
  }
  
  /* get the cash account balance plus the current price of the bond plus the number of shares times the current price for both the stock and the mutual fund */
  public double currentValue(){
    return cashAccount.getBalance()+b.getCurrentPrice()*b.getNumberOwned()+m.getNumberShares()*m.getCurrentPrice()+s.getNumberShares()*s.getCurrentPrice();
  }
  
  /* get the sum of the capital gains of the bond, mutual fund and stocks */
  public double getCapitalGains(){
    return b.getCapitalGains()+m.getCapitalGains()+s.getCapitalGains();
  }
  
  /* change the cash account balance by depositing money */
  // depositAmount stores the amount that the bookeeper deposits 
  public void deposit(double depositAmount){
    cashAccount.setDeposit(depositAmount);
  }
  
  /* change the cash account balance by withdrawing money */
  // withdrawAmount stores the amount that the bookeeper withdraws
  public boolean withdraw(double withdrawAmount){
    return cashAccount.withdraw(false, withdrawAmount);
  }
  
  /* sell a bond and deposit the value returned into the cash account's balance */
  public void sellBond(){
    this.deposit(b.sellBonds());
  }
  
  /* buy a bond method and withdraw the value returned into the cash account's balance */
  public boolean buyBond(){
    if(b.getCurrentPrice()>currentValue()) return false;
    else
    {
      return cashAccount.withdraw(true,b.buyBonds());
    }
  }
  
  /* deposit the interest into the cash account */
  public void payBondInterest(){
    this.deposit(b.payInterest());
  }
  
  /* call the mutual fund's sell method with this number and adds the amount returned to the cash balance.*/
  public void withdrawMutualFund(double withdrawAmount){
    this.deposit(m.sellMutualFund(withdrawAmount));
  }
  
  /* call the mutual fund's buy method to buy mutual fund and the cash account balance is reduced by the money that the bookeeper pays */
  // buyAmount stores the amount of mutual fund that is bought by the bookeeper
  public boolean buyMutualFund(double buyAmount){
    if (buyAmount>currentValue()) return false;
    else
    {
      this.withdraw(m.buyMutualFund(buyAmount));
      return true;
    }
  }
  
  /* sell stock and add the value of the stock to the cash balance.*/
  // numberOfShares stores the number of stock that the bookeeper sells 
  public void sellStockShares(int numberOfShares){
    this.deposit(s.sellStock(numberOfShares, commissionRate));
  }
  
  /* buy stock and the cash balance is reduced by the money paid for stock */
  // numberOfShares stores the number of stock that the bookeeper buys
  public boolean buyStockShares(int numberOfShares){
    if (s.buyStock(numberOfShares, commissionRate)>currentValue()) return false;
    else
    {
      this.withdraw(s.buyStock(numberOfShares, commissionRate));
      return true;
    }
    
  }
    
}