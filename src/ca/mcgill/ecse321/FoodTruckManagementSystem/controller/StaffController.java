package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import java.sql.Date;
import java.sql.Time;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Shift;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Staff;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceXStream;


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
	
}
