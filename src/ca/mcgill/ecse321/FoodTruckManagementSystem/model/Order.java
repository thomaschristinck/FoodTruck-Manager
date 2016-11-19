/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.FoodTruckManagementSystem.model;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 14 "../../../../../FoodTruckManager.ump"
// line 61 "../../../../../FoodTruckManager.ump"
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
  private List<Item> item;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(int aOrderNumber, Date aOrderDate, Time aOrderTime)
  {
    orderNumber = aOrderNumber;
    orderDate = aOrderDate;
    orderTime = aOrderTime;
    item = new ArrayList<Item>();
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

  public Item getItem(int index)
  {
    Item aItem = item.get(index);
    return aItem;
  }

  public List<Item> getItem()
  {
    List<Item> newItem = Collections.unmodifiableList(item);
    return newItem;
  }

  public int numberOfItem()
  {
    int number = item.size();
    return number;
  }

  public boolean hasItem()
  {
    boolean has = item.size() > 0;
    return has;
  }

  public int indexOfItem(Item aItem)
  {
    int index = item.indexOf(aItem);
    return index;
  }

  public static int minimumNumberOfItem()
  {
    return 0;
  }

  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (item.contains(aItem)) { return false; }
    item.add(aItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(Item aItem)
  {
    boolean wasRemoved = false;
    if (item.contains(aItem))
    {
      item.remove(aItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addItemAt(Item aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItem()) { index = numberOfItem() - 1; }
      item.remove(aItem);
      item.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(Item aItem, int index)
  {
    boolean wasAdded = false;
    if(item.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItem()) { index = numberOfItem() - 1; }
      item.remove(aItem);
      item.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    item.clear();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "orderNumber" + ":" + getOrderNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "orderDate" + "=" + (getOrderDate() != null ? !getOrderDate().equals(this)  ? getOrderDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderTime" + "=" + (getOrderTime() != null ? !getOrderTime().equals(this)  ? getOrderTime().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}
