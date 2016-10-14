/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.FoodTruckManagementSystem.model;
import java.sql.Date;

// line 3 "../../../../../FoodTruckManager.ump"
// line 50 "../../../../../FoodTruckManager.ump"
public class Supply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supply Attributes
  private String name;
  private int quantity;
  private Date bestBefore;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Supply(String aName, int aQuantity, Date aBestBefore)
  {
    name = aName;
    quantity = aQuantity;
    bestBefore = aBestBefore;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public boolean setBestBefore(Date aBestBefore)
  {
    boolean wasSet = false;
    bestBefore = aBestBefore;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public Date getBestBefore()
  {
    return bestBefore;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bestBefore" + "=" + (getBestBefore() != null ? !getBestBefore().equals(this)  ? getBestBefore().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}