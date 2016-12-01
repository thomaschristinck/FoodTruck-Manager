package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Shift;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Staff;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceXStream;

/**
 * The StaffController class is responsible for adding/removing staff members, adding/removing a
 * shift, and assigning a shift to a staff member. The StaffController will also provide functionality
 * for allowing the user to view a staff member's schedule as a .txt file that they may re-format in a 
 * text editor of their choice.
 *  
 * @author thomaschristinck
 * @version 1.0
 */
public class StaffController {
	public StaffController ()
	{
	}
	/**
	 * The createStaff method adds a staff member to the Food Truck Management System. A staff
	 * member must have a name and a role. The name is allowed to be a number (if the manager wishes
	 * to use a numerical staff naming system) or a String.
	 * 
	 * @param name
	 * @param role
	 * @throws InvalidInputException
	 */
	public void createStaff(String name, String role) throws InvalidInputException
	{
		String error = "";
		if (name == null || name.trim().length() == 0) 
			error = error + " Staff name cannot be empty!";
		
		if (role == null || role.trim().length() == 0) 
			error = error + " Staff member must have a role!";
			error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		Staff staff = new Staff(name, role);
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.addStaff(staff);
		PersistenceXStream.saveToXMLwithXStream(fm);	
	}
	
	/**
	 * The removeStaff method allows removal of a staff member previously entered in the FoodTruckManagementSystem.
	 * Staff selection will be achieved through a user removing the staff member through a drop down menu, and
	 * so there is limited opportunity for errors to occur (aside from the case where no staff member is selected
	 * to remove).
	 * 
	 * @param staff
	 * @throws InvalidInputException
	 */
	public void removeStaff(Staff staff) throws InvalidInputException
	{
		if (staff == null || staff.toString().trim().length() == 0) 
			throw new InvalidInputException("Must select staff member to remove!");
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.removeStaff(staff);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	/**
	 * The createShift method adds a shift time slot. The shift start time must be before the shift end time.
	 * Currently there is no functionality for adding repeat shifts (every Friday from 2-8pm); however, this
	 * could be added in later versions. As of version 1.0 the manager has to enter each individual shift manually.
	 * 
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @throws InvalidInputException
	 */
	public void createShift(Date date, Time startTime, Time endTime) throws InvalidInputException
	{
		String error = " ";
		if(date == null)
			error = error + " Shift date cannot be empty!";
		if(startTime == null)
			error = error + " Shift start time cannot be empty!";
		if(endTime == null)
			error = error + " Shift end time cannot be empty!";
		if (endTime != null && startTime != null && endTime.getTime() < startTime.getTime())
			error = error + " Shift end time cannot be before shift start time!";
			error=error.trim();
		if (error.length() > 0)
			throw new InvalidInputException(error);
		
		
		Shift s = new Shift(startTime, endTime, date);
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.addShift(s);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	/**
	 * The removeShift method allows the manager to remove a time slot previously saved as a shift. This
	 * will be achieved through a drop-down menu to limit the possibility of errors.
	 * @param shift
	 * @throws InvalidInputException
	 */
	public void removeShift(Shift shift) throws InvalidInputException
	{
		if (shift == null || shift.toString().trim().length() == 0) 
			throw new InvalidInputException("Must select shift to remove!");
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.removeShift(shift);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	/**
	 * The addShiftToStaff method allows for adding a previously created shift to a previously added
	 * staff member. Because these fields are all pre-established, they will be selected by a drop-down 
	 * menu. If a staff member already has the selected shift assigned to them, an error will occur.
	 * 
	 * @param staff
	 * @param shift
	 * @throws InvalidInputException
	 */
	public void addShiftToStaff(Staff staff, Shift shift) throws InvalidInputException
	{
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		String error = "";
		if (staff == null)
			error = error + " Staff member must be selected!";
		else if (!fm.getStaffs().contains(staff))
			error = error + " Staff does not exist!";
		if (shift == null)
			error = error + " Shift must be selected!";
		else if (!fm.getShifts().contains(shift))
			error = error + " Shift does not exist!";
		if (staff != null && shift != null){
			if (shift.addStaff(staff) == false)
				error = error + " Shift is already assigned to selected staff!";
		}
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
	
		shift.addStaff(staff);
		fm.addShift(shift);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	/**
	 * Similar to adding a shift to a staff member, a shift can be removed from a staff member through 
	 * removeShiftFromStaff. Both fields (staff member, shift) will be selected from a drop-down menu and if the 
	 * selected staff does not have the selected shift assigned to them, an error message will appear.
	 * 
	 * @param staff
	 * @param shift
	 * @throws InvalidInputException
	 */
	public void removeShiftFromStaff(Staff staff, Shift shift) throws InvalidInputException
	{
		String error = "";
		if (staff == null)
			error = error + " Staff member must be selected!";
		if (shift == null)
			error = error + " Shift must be selected!";
		if (staff != null && shift != null){
			if (shift.removeStaff(staff) == false)
				error = error + " Shift must be assigned to selected staff in order to unassign it!";
		}
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		shift.removeStaff(staff);
		fm.addShift(shift);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	/**
	 * The viewSchedule method displays the schedule of a staff member as a .txt file. The schedule generated shows all of the
	 * selected staff member's shifts over a period of time determined by the user. 
	 * 
	 * @param staff
	 * @param startDate
	 * @param endDate
	 * @throws InvalidInputException
	 */
	public void viewSchedule(Staff staff, Date startDate, Date endDate) throws InvalidInputException{
		//Input errors
		String error = " ";
		if(staff == null)
			error = error + " Must select staff!";
		if(startDate == null)
			error = error + " Start date cannot be empty!";
		if(endDate == null)
			error = error + " End date cannot be empty!";
		if (startDate != null && startDate != null && endDate.getTime() < startDate.getTime())
			error = error + " Schedule end date cannot be before shift start date!";
			error=error.trim();
		if (error.length() > 0)
			throw new InvalidInputException(error);
		
		try {
			FoodTruckManager fm = FoodTruckManager.getInstance();
			File file = new File("staffschedule.txt");

			 //If file doesn't exist, then create it
			 if (!file.exists()) {
				 file.createNewFile();
			 }

			 FileWriter fw = new FileWriter(file.getAbsoluteFile());
			 BufferedWriter out = new BufferedWriter(fw);
			 out.write(staff.getName() + "'s " +  "Schedule from " +  startDate.toString() + " to " + endDate.toString());
			 out.newLine();
			 out.newLine();
			 String header = String.format("%-22s%-22s%-22s\n", "Date", "Start Time", "End Time");
			 out.write(header);
			 out.newLine();
			 Calendar start = Calendar.getInstance();
			 start.setTime(startDate);
			 Calendar end = Calendar.getInstance();
			 end.setTime(endDate);
			 /*Search through all dates specified and if a shift on a date has the specified staff member, then
			  * the shift start time and end time should be specified.
			 */
			 for (java.util.Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
				 for(int i = 0; i < fm.getShifts().size(); i++){
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
					 String formattedDate = formatter.format(date);
					 Date sqlDate = fm.getShift(i).getShiftDate();
					 java.util.Date utilDate = new Date(sqlDate.getTime());
					 String formattedShiftDate = formatter.format(utilDate);
					 //Removing shift from staff is a problem; find another way to determine if staff member has a shift assigned.
					 if (formattedShiftDate.equals(formattedDate) && fm.getShift(i).containsStaff(staff)){
						 String shift = String.format("%-22s%-22s%-22s\n", formattedDate, fm.getShift(i).getStartTime().toString(), fm.getShift(i).getEndTime().toString());
						 out.write(shift);
					 }
				 }
			 }
			 out.close();
			 java.awt.Desktop.getDesktop().edit(file);
		 } catch (IOException e) {
			 throw new InvalidInputException("ERROR CREATING FILE");
		 }
	}
	
}
