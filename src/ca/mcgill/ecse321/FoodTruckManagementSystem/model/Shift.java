/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.FoodTruckManagementSystem.model;
import java.sql.Time;
import java.sql.Date;
import java.util.*;

// line 28 "../../../../../FoodTruckManager.ump"
// line 70 "../../../../../FoodTruckManager.ump"
public class Shift
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Shift Attributes
  private Time startTime;
  private Time endTime;
  private Date shiftDate;

  //Shift Associations
  private List<Staff> staff;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Shift(Time aStartTime, Time aEndTime, Date aShiftDate)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    shiftDate = aShiftDate;
    staff = new ArrayList<Staff>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setShiftDate(Date aShiftDate)
  {
    boolean wasSet = false;
    shiftDate = aShiftDate;
    wasSet = true;
    return wasSet;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public Date getShiftDate()
  {
    return shiftDate;
  }

  public Staff getStaff(int index)
  {
    Staff aStaff = staff.get(index);
    return aStaff;
  }

  public List<Staff> getStaff()
  {
    List<Staff> newStaff = Collections.unmodifiableList(staff);
    return newStaff;
  }

  public int numberOfStaff()
  {
    int number = staff.size();
    return number;
  }

  public boolean hasStaff()
  {
    boolean has = staff.size() > 0;
    return has;
  }

  public int indexOfStaff(Staff aStaff)
  {
    int index = staff.indexOf(aStaff);
    return index;
  }

  public static int minimumNumberOfStaff()
  {
    return 0;
  }

  public boolean addStaff(Staff aStaff)
  {
    boolean wasAdded = false;
    if (staff.contains(aStaff)) { return false; }
    staff.add(aStaff);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStaff(Staff aStaff)
  {
    boolean wasRemoved = false;
    if (staff.contains(aStaff))
    {
      staff.remove(aStaff);
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
      if(index > numberOfStaff()) { index = numberOfStaff() - 1; }
      staff.remove(aStaff);
      staff.add(index, aStaff);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStaffAt(Staff aStaff, int index)
  {
    boolean wasAdded = false;
    if(staff.contains(aStaff))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStaff()) { index = numberOfStaff() - 1; }
      staff.remove(aStaff);
      staff.add(index, aStaff);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStaffAt(aStaff, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    staff.clear();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "shiftDate" + "=" + (getShiftDate() != null ? !getShiftDate().equals(this)  ? getShiftDate().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}