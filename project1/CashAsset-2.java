/** Written by Qiwen Luo   student ID: qxl216
 * A class that represents money reserves 
 */
public class CashAsset extends Object{
  
  /* An instance field that stores the current balance */
  private double currentBalance=0.0;
  /* An instance field that stores the interest rate for savings */
  private double interestRateSavings=0.0;
  /* An instance field that stores the interest rate for loans */
  private double interestRateLoans=0.0;
  /* An instance field that stores the loan limit */
  private double loanLimit=0.0;
  /* An instance field that stores the overdraft penalty */
  private double overdraftPenalty=0.0;
  /* An instance field that stores the amount of interest increased this month */
  private double interestAccrued=0.0;
  /* An instance field that stores whether the accout has been overdrafted */
  private boolean overdrafted;
  /* An instance field that stores the times of exceeding the loan limit */
  private boolean isPunished=false;
  
  /* A constructor that sets the interest rate for savings, the interest rate for loans, the loan limit and the overdraft penalty */
  // interestRateSavings stores the interest rate for savings, interestRateLoans stores the interest rate for loans,  loadLimit stores the overdraft penalty and overdraftPenalty stores the overdraft penalty 
  public CashAsset(double interestRateSavings, double interestRateLoans, double loanLimit, double overdraftPenalty){  
    this.interestRateSavings=interestRateSavings;
    this.interestRateLoans=interestRateLoans;
    this.loanLimit=loanLimit;
    this.overdraftPenalty=overdraftPenalty;
  }
  
  /* get the current balance */
  public double getBalance(){
    return currentBalance;
  }
  
  /* get the interest rate for savings */
  public double getSavingsRate(){
    return interestRateSavings;
  }
  
  /* change the interest rate for savings */
  // interestRateSavings stores the interest rate for savings
  public void setSavingsRate(double interestRateSavings){
    this.interestRateSavings=interestRateSavings;
  }

  /* get the interest rate for loans */
  public double getLoanRate(){
    return interestRateLoans;
  }
  
  /* change the interest rate for loans */
  // interestRateLoans stores the interest rate for loans
  public void setLoanRate(double interestRateLoans){
    this.interestRateLoans=interestRateLoans;
  }
  
  /* get the loan limit */
  public double getLoanLimit(){
    return loanLimit;
  }
  
  /* change the loan limit */
  // loanLimit stores the loan limit
  public void setLoanLimit(double loanLimit){
    this.loanLimit=loanLimit;
  }
  
  /* return the overdraft penalty */
  public double getOverdraftPenalty(){
    return overdraftPenalty;
  }
  
  /* change the overdraft penalty */
  // overdraftPenalty stores the overdraft penalty
  public void setOverdraftPenalty(double overdraftPenalty){
    this.overdraftPenalty=overdraftPenalty;
  }
  
  /* change the current balance by depositing money */
  // deposit stores the deposit the bookeeper saved
  public void setDeposit(double deposit){
    currentBalance=getBalance()+deposit;
  }
  
  /* change the current balance by withdrwing money */
  // overdrafted stores whether the accout has been overdrafted and withdraw the money that the bookeeper gets
  public boolean withdraw(boolean overdrafted, double withdraw){
    if(overdrafted==false && withdraw>getBalance()) return false;
    else
    {
      currentBalance=getBalance()-withdraw;
      return true;
    }
  }
  
  /* calculate the money that the bookkeeper can get from or pay for the bank daily */
  public void processDay(){
    if (getBalance()>0) interestAccrued+=getBalance()*getSavingsRate()/365;
    else if (getBalance()<0) interestAccrued+= getBalance()*interestRateLoans/365;
    if((getBalance()+interestAccrued)<-getLoanLimit()) isPunished=true;
  }
  
  /* calculate the money that the bookkeeper can get from or pay for the bank monthly */
  public void processMonth(){
    currentBalance=getBalance()+interestAccrued;
    interestAccrued=0;
    if(getBalance()<0 && isPunished)  currentBalance=getBalance()-getOverdraftPenalty();
  }
  
  /* get the current accrued monthly interest amount */
  public double getAccruedInterest(){
    return interestAccrued; 
  }
  
  /* change the accrued interest for the month */
  // interestAccrueud stores the amount of interest increased this month
  protected void setAccruedInterest(double interestAccrued){
    this.interestAccrued=interestAccrued;
  }
}
