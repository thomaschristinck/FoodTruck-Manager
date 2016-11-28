/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-2a9bef6 modeling language!*/

package ca.mcgill.ecse321.FoodTruckManagementSystem.model;
import java.util.*;

// line 10 "../../../../../../../../ump/tmp28017/model.ump"
// line 58 "../../../../../../../../ump/tmp28017/model.ump"
public class Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private String name;
  private String description;

  //Item Associations
  private List<Supply> supply;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(String aName, String aDescription)
  {
    name = aName;
    description = aDescription;
    supply = new ArrayList<Supply>();
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

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public Supply getSupply(int index)
  {
    Supply aSupply = supply.get(index);
    return aSupply;
  }

  public List<Supply> getSupply()
  {
    List<Supply> newSupply = Collections.unmodifiableList(supply);
    return newSupply;
  }

  public int numberOfSupply()
  {
    int number = supply.size();
    return number;
  }

  public boolean hasSupply()
  {
    boolean has = supply.size() > 0;
    return has;
  }

  public int indexOfSupply(Supply aSupply)
  {
    int index = supply.indexOf(aSupply);
    return index;
  }

  public static int minimumNumberOfSupply()
  {
    return 0;
  }

  public boolean addSupply(Supply aSupply)
  {
    boolean wasAdded = false;
    if (supply.contains(aSupply)) { return false; }
    supply.add(aSupply);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSupply(Supply aSupply)
  {
    boolean wasRemoved = false;
    if (supply.contains(aSupply))
    {
      supply.remove(aSupply);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSupplyAt(Supply aSupply, int index)
  {  
    boolean wasAdded = false;
    if(addSupply(aSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupply()) { index = numberOfSupply() - 1; }
      supply.remove(aSupply);
      supply.add(index, aSupply);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSupplyAt(Supply aSupply, int index)
  {
    boolean wasAdded = false;
    if(supply.contains(aSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupply()) { index = numberOfSupply() - 1; }
      supply.remove(aSupply);
      supply.add(index, aSupply);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSupplyAt(aSupply, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    supply.clear();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "description" + ":" + getDescription()+ "]"
     + outputString;
  }
}
