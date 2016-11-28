/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.FoodTruckManagementSystem.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 41 "../../../../../FoodTruckManager.ump"
// line 81 "../../../../../FoodTruckManager.ump"
public class FoodTruckManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static FoodTruckManager theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodTruckManager Associations
  private List<Supply> supplies;
  private List<Item> items;
  private List<Order> orders;
  private List<Staff> staffs;
  private List<Shift> shifts;
  private List<Equipment> equipment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private FoodTruckManager()
  {
    supplies = new ArrayList<Supply>();
    items = new ArrayList<Item>();
    orders = new ArrayList<Order>();
    staffs = new ArrayList<Staff>();
    shifts = new ArrayList<Shift>();
    equipment = new ArrayList<Equipment>();
  }

  public static FoodTruckManager getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new FoodTruckManager();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Supply getSupply(int index)
  {
    Supply aSupply = supplies.get(index);
    return aSupply;
  }

  public List<Supply> getSupplies()
  {
    List<Supply> newSupplies = Collections.unmodifiableList(supplies);
    return newSupplies;
  }

  public int numberOfSupplies()
  {
    int number = supplies.size();
    return number;
  }

  public boolean hasSupplies()
  {
    boolean has = supplies.size() > 0;
    return has;
  }

  public int indexOfSupply(Supply aSupply)
  {
    int index = supplies.indexOf(aSupply);
    return index;
  }

  public Item getItem(int index)
  {
    Item aItem = items.get(index);
    return aItem;
  }

  public List<Item> getItems()
  {
    List<Item> newItems = Collections.unmodifiableList(items);
    return newItems;
  }

  public int numberOfItems()
  {
    int number = items.size();
    return number;
  }

  public boolean hasItems()
  {
    boolean has = items.size() > 0;
    return has;
  }

  public int indexOfItem(Item aItem)
  {
    int index = items.indexOf(aItem);
    return index;
  }

  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }

  public Staff getStaff(int index)
  {
    Staff aStaff = staffs.get(index);
    return aStaff;
  }

  public List<Staff> getStaffs()
  {
    List<Staff> newStaffs = Collections.unmodifiableList(staffs);
    return newStaffs;
  }

  public int numberOfStaffs()
  {
    int number = staffs.size();
    return number;
  }

  public boolean hasStaffs()
  {
    boolean has = staffs.size() > 0;
    return has;
  }

  public int indexOfStaff(Staff aStaff)
  {
    int index = staffs.indexOf(aStaff);
    return index;
  }

  public Shift getShift(int index)
  {
    Shift aShift = shifts.get(index);
    return aShift;
  }

  public List<Shift> getShifts()
  {
    List<Shift> newShifts = Collections.unmodifiableList(shifts);
    return newShifts;
  }

  public int numberOfShifts()
  {
    int number = shifts.size();
    return number;
  }

  public boolean hasShifts()
  {
    boolean has = shifts.size() > 0;
    return has;
  }

  public int indexOfShift(Shift aShift)
  {
    int index = shifts.indexOf(aShift);
    return index;
  }

  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipment.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipment()
  {
    List<Equipment> newEquipment = Collections.unmodifiableList(equipment);
    return newEquipment;
  }

  public int numberOfEquipment()
  {
    int number = equipment.size();
    return number;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipment.indexOf(aEquipment);
    return index;
  }

  public static int minimumNumberOfSupplies()
  {
    return 0;
  }

  public boolean addSupply(Supply aSupply)
  {
    boolean wasAdded = false;
    if (supplies.contains(aSupply)) { return false; }
    supplies.add(aSupply);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSupply(Supply aSupply)
  {
    boolean wasRemoved = false;
    if (supplies.contains(aSupply))
    {
      supplies.remove(aSupply);
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
      if(index > numberOfSupplies()) { index = numberOfSupplies() - 1; }
      supplies.remove(aSupply);
      supplies.add(index, aSupply);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSupplyAt(Supply aSupply, int index)
  {
    boolean wasAdded = false;
    if(supplies.contains(aSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupplies()) { index = numberOfSupplies() - 1; }
      supplies.remove(aSupply);
      supplies.add(index, aSupply);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSupplyAt(aSupply, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfItems()
  {
    return 0;
  }

  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    items.add(aItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(Item aItem)
  {
    boolean wasRemoved = false;
    if (items.contains(aItem))
    {
      items.remove(aItem);
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
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(Item aItem, int index)
  {
    boolean wasAdded = false;
    if(items.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfOrders()
  {
    return 0;
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    orders.add(aOrder);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    if (orders.contains(aOrder))
    {
      orders.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfStaffs()
  {
    return 0;
  }

  public boolean addStaff(Staff aStaff)
  {
    boolean wasAdded = false;
    if (staffs.contains(aStaff)) { return false; }
    staffs.add(aStaff);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStaff(Staff aStaff)
  {
    boolean wasRemoved = false;
    if (staffs.contains(aStaff))
    {
      staffs.remove(aStaff);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addStaffAt(Staff aStaff, int index)
  {  
    boolean wasAdded = false;
    if(addStaff(aStaff))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStaffs()) { index = numberOfStaffs() - 1; }
      staffs.remove(aStaff);
      staffs.add(index, aStaff);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStaffAt(Staff aStaff, int index)
  {
    boolean wasAdded = false;
    if(staffs.contains(aStaff))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStaffs()) { index = numberOfStaffs() - 1; }
      staffs.remove(aStaff);
      staffs.add(index, aStaff);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStaffAt(aStaff, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfShifts()
  {
    return 0;
  }

  public boolean addShift(Shift aShift)
  {
    boolean wasAdded = false;
    if (shifts.contains(aShift)) { return false; }
    shifts.add(aShift);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeShift(Shift aShift)
  {
    boolean wasRemoved = false;
    if (shifts.contains(aShift))
    {
      shifts.remove(aShift);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addShiftAt(Shift aShift, int index)
  {  
    boolean wasAdded = false;
    if(addShift(aShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShifts()) { index = numberOfShifts() - 1; }
      shifts.remove(aShift);
      shifts.add(index, aShift);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveShiftAt(Shift aShift, int index)
  {
    boolean wasAdded = false;
    if(shifts.contains(aShift))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShifts()) { index = numberOfShifts() - 1; }
      shifts.remove(aShift);
      shifts.add(index, aShift);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addShiftAt(aShift, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfEquipment()
  {
    return 0;
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    equipment.add(aEquipment);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (equipment.contains(aEquipment))
    {
      equipment.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipment.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    supplies.clear();
    items.clear();
    orders.clear();
    staffs.clear();
    shifts.clear();
    equipment.clear();
  }

}