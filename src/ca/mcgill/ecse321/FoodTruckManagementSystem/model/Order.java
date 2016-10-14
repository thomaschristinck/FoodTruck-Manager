/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.FoodTruckManagementSystem.model;
import java.sql.Date;
import java.sql.Time;

// line 15 "../../../../../FoodTruckManager.ump"
// line 60 "../../../../../FoodTruckManager.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private int orderNumber;
  private Date orderDate;
  private Time orderTime;

  //Order Associations
  private Item item;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(int aOrderNumber, Date aOrderDate, Time aOrderTime, Item aItem)
  {
    orderNumber = aOrderNumber;
    orderDate = aOrderDate;
    orderTime = aOrderTime;
    if (!setItem(aItem))
    {
      throw new RuntimeException("Unable to create Order due to aItem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOrderNumber(int aOrderNumber)
  {
    boolean wasSet = false;
    orderNumber = aOrderNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrderDate(Date aOrderDate)
  {
    boolean wasSet = false;
    orderDate = aOrderDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrderTime(Time aOrderTime)
  {
    boolean wasSet = false;
    orderTime = aOrderTime;
    wasSet = true;
    return wasSet;
  }

  public int getOrderNumber()
  {
    return orderNumber;
  }

  public Date getOrderDate()
  {
    return orderDate;
  }

  public Time getOrderTime()
  {
    return orderTime;
  }

  public Item getItem()
  {
    return item;
  }

  public boolean setItem(Item aNewItem)
  {
    boolean wasSet = false;
    if (aNewItem != null)
    {
      item = aNewItem;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    item = null;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "orderNumber" + ":" + getOrderNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "orderDate" + "=" + (getOrderDate() != null ? !getOrderDate().equals(this)  ? getOrderDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderTime" + "=" + (getOrderTime() != null ? !getOrderTime().equals(this)  ? getOrderTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "item = "+(getItem()!=null?Integer.toHexString(System.identityHashCode(getItem())):"null")
     + outputString;
  }
}