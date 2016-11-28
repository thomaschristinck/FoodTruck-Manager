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
 */
public class StaffController {
	public StaffController ()
	{
	}
	
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
	
	public void removeStaff(Staff staff) throws InvalidInputException
	{
		if (staff == null || staff.toString().trim().length() == 0) 
			throw new InvalidInputException("Must select staff member to remove!");
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.removeStaff(staff);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
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
	
	public void removeShift(Shift shift) throws InvalidInputException
	{
		if (shift == null || shift.toString().trim().length() == 0) 
			throw new InvalidInputException("Must select shift to remove!");
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.removeShift(shift);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
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
	public void viewSchedule(Staff staff, Date startDate, Date endDate) throws InvalidInputException{
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
			 Calendar start = Calendar.getInstance();
			 start.setTime(startDate);
			 Calendar end = Calendar.getInstance();
			 end.setTime(endDate);
			 /*Search through all dates specified and if a shift on a date has the specified staff member, then
			  * the shift start time and end time should be specified.
			 */
			System.out.println("START " + start.toString());
			System.out.println("END " + end.toString());
			 for (java.util.Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
				 for(int i = 0; i < fm.getShifts().size(); i++){
					 System.out.println("First" + String.valueOf(fm.getShift(i).getShiftDate() == date));
					 System.out.println("Second" + String.valueOf(fm.getShift(i).removeStaff(staff)));
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					 String formattedDate = formatter.format(date);
					 Date sqlDate = fm.getShift(i).getShiftDate();
					 java.util.Date utilDate = new Date(sqlDate.getTime());
					 String formattedShiftDate = formatter.format(utilDate);
					 System.out.println("SHIFT:" + formattedDate);
					 System.out.println("Date:" + formattedShiftDate);
					 System.out.println();
					 //Removing shift from staff is a problem; find another way to determine if staff member has a shift assigned.
					 if (formattedShiftDate.equals(formattedDate) && fm.getShift(i).removeStaff(staff)){
						 String shift = String.format("%-22s%-22s%-22s\n", date.toString(), fm.getShift(i).getStartTime().toString(), fm.getShift(i).getStartTime().toString());
						 out.write(shift);
						 out.newLine();
						 out.newLine(); 
						 System.out.println("IF ENTRY");
					 }
				 }
			 }
			 out.close();
			 java.awt.Desktop.getDesktop().edit(file);
		 } catch (IOException e) {
			 //fail()?
		 }
	}
	
}
