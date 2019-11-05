/** Written by Qiwen Luo   student ID: qxl216
 * A class that represents any tangible property 
 */
public class Asset extends Object {
  
  /* An instance field that stores asset name */
  private String name="";
  /* An instance field that stores description of the asset */
  private String description="";
  /* An instance field that stores cost basis for the asset */
  private double costBasis=0.0;
  /* An instance field that stores current price of the asset */
  private double currentPrice=0.0;
  /* An instance field that stores capital gains of the asset */
  private double capitalGains;
  
  /* A constructor that sets asset name and cost basis for the asset and initializes the value of capitalGains */
  // name stores the name for the asset
  public Asset(String name, double costBasis){ 
    this.name=name;
    this.costBasis=costBasis;
    capitalGains=0;
  }
  
  /* get the name of the asset */
  public String getName(){
    return name;
  }
  
  /* change the name of the asset */
  // name stores a new name of the asset
  public void setName(String name){  
    this.name=name;
  }
  
  /* get the description of the asset */
  public String getDescription(){
    return description;
  }
  
  /* change the description of the asset */
  // description stores the description of the asset 
  public void setDescription(String description){ 
    this.description=description;
  }
  
  /* get the cost basis of the asset */
  public double getCostBasis(){
    return costBasis;
  }
  
  /* change the cost basis of the asset */
  // costBasis stores the cost basis of the asset
  protected void setCostBasis(double costBasis){  
    this.costBasis=costBasis;
  }
  
  /* get the current price of the asset */
  public double getCurrentPrice(){
    return currentPrice;
  }
  
  /* change current price of the asset */
  // currentPrice stores the current price of the asset
  public void setCurrentPrice(double currentPrice){  
    this.currentPrice=currentPrice;
  }
  
 /* get the capital gains of the asset */
  public double getCapitalGains(){
    return capitalGains;
  }
  
 /* change capital Gains of the asset */
 // capitalGains stores capital gains of the asset
  protected void setCapitalGains(double capitalGains){  
    this.capitalGains=capitalGains;
  } 
}