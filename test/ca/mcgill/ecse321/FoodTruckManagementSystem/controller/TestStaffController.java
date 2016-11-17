package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Staff;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Shift;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceXStream;



public class TestStaffController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		PersistenceXStream.setFilename("test" + File.separator + "ca" + File.separator + "mcgill"+ File.separator + "ecse321" + File.separator + "foodtruckmanagementsystem" + File.separator + "persistence"+ File.separator +"data.xml");
		PersistenceXStream.setAlias("staff", Staff.class);
		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
	}


	@After
	public void tearDown() throws Exception {
		//clear all registrations
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.delete();
	}

	@Test
	public void testCreateStaff() {
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getStaffs().size());
		
		String name = "Huck";
		String role = "Cook";
		
		StaffController sc = new StaffController();
		try {
			sc.createStaff(name, role);
		} catch (InvalidInputException e) {
			//check no error occurred
			fail();
		}
		
		checkResultStaff(name, role, fm);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultStaff(name, role, fm2);
	}
	
	@Test
	public void testCreateStaffNull(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getStaffs().size());
		
		String name = null;
		String role = null;
		String error = null;
		
		StaffController sc = new StaffController();
		try{
			sc.createStaff(name, role);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Staff name cannot be empty! Staff member must have a role!", error);
		
		//check no change in memory
		assertEquals(0, fm.getStaffs().size());
		
	}
	
	@Test
	public void testCreateStaffNameEmpty(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getStaffs().size());
		
		String name = " ";
		String role = " ";
		String error = null;
		StaffController sc = new StaffController();
		try{
			sc.createStaff(name, role);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Staff name cannot be empty! Staff member must have a role!", error);
		
		//check no change in memory
		assertEquals(0, fm.getStaffs().size());
	}
	
	@Test
	public void testCreateStaffSpaces(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getStaffs().size());
		
		String name = " ";
		String role = "Chef";
		String error = null;
		StaffController sc = new StaffController();
		try{
			sc.createStaff(name, role);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Staff name cannot be empty!", error);
		
		//check no change in memory
		assertEquals(0, fm.getStaffs().size());
	}
	
	@Test
	public void testRemoveStaff() {
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getStaffs().size());
		
		String name = "Huck";
		String role = "Cook";
		Staff staff = new Staff(name, role);
		StaffController sc = new StaffController();
		try {
			sc.removeStaff(staff);
		} catch (InvalidInputException e) {
			//Check no error occurred
			fail();
		}
	
		//Check staff was deleted
		assertEquals(0, fm.getStaffs().size());
	}
	
	@Test
	public void testRemoveStaffNull() {
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getStaffs().size());
		
		Staff staff = null;
		String error = null;
		StaffController sc = new StaffController();
		try {
			sc.removeStaff(staff);
		} catch (InvalidInputException e) {
			//Get error
			error = e.getMessage();
		}
		
		//Check error is what we expect it to be
		assertEquals("Must select staff member to remove!", error);
		//Check no change in memory
		assertEquals(0, fm.getStaffs().size());
	}
	@Test
	public void testCreateShift() {
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getShifts().size());
		
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,30,9,00,0);
		Date shiftDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,  Calendar.OCTOBER,30,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		
		StaffController sc = new StaffController();
		try{
			sc.createShift(shiftDate, startTime, endTime);
		} catch (InvalidInputException e){
			//check no error
			fail();
		}
		//check model in memory
		checkResultShift(startTime, endTime, shiftDate, fm);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultShift(startTime, endTime, shiftDate, fm2);
	}
	//Edit code below here
	@Test 
	public void testCreateShiftNull(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getShifts().size());
	
		Date shiftDate = null;
		Time startTime = null;
		Time endTime = null;
		
		String error = null;
		StaffController sc = new StaffController();
		try{
			sc.createShift(shiftDate, startTime, endTime);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		//check error
		assertEquals("Shift date cannot be empty! Shift start time cannot be empty! Shift end time cannot be empty!", error);
		
		//check model in memory 
		assertEquals(0, fm.getShifts().size());

	}
	
	@Test 
	public void testCreateShiftEndTimeBeforeStartTime(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getShifts().size());

		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,16,9,00,0);
		Date ShiftDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,  Calendar.OCTOBER,16,8,59,0);
		Time endTime = new Time(c.getTimeInMillis());
		
		String error = null;
		StaffController sc = new StaffController();
		try{
			sc.createShift(ShiftDate, startTime, endTime);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Shift end time cannot be before shift start time!", error);
		
		//check model in memory 
		assertEquals(0, fm.getShifts().size());
	}
	
	@Test
	public void testRemoveShift() {
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getStaffs().size());
		
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,30,9,00,0);
		Date shiftDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,  Calendar.OCTOBER,30,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		
		Shift shift = new Shift(startTime, endTime, shiftDate);
		StaffController sc = new StaffController();
		try{
			sc.removeShift(shift);
		} catch (InvalidInputException e){
			//check no error
			fail();
		}
		
		//Check shift was deleted
		assertEquals(0, fm.getShifts().size());
	}
	
	@Test
	public void testRemoveShiftNull() {
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getStaffs().size());
		
		Shift shift = null;
		
		String error = null;
		StaffController sc = new StaffController();
		try{
			sc.removeShift(shift);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error is what we expect it to be
		assertEquals("Must select shift to remove!", error);
		//Check no change in memory
		assertEquals(0, fm.getStaffs().size());
	}
	
	@Test
	public void testAssignShiftToStaff(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getShifts().size());
		
		String name = "Jimmy";
		String role = "Cook";
		Staff staff = new Staff(name, role);
		fm.addStaff(staff);
		assertEquals(1, fm.getStaffs().size());
		
		
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,30,9,00,0);
		Date shiftDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,  Calendar.OCTOBER,30,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		Shift shift = new Shift(startTime, endTime, shiftDate);
		fm.addShift(shift);
		assertEquals(1, fm.getShifts().size());
		
		StaffController sc = new StaffController();
		try{
			sc.addShiftToStaff(staff, shift);
		} catch (InvalidInputException e){
			//check no error
			fail();
		}
		//check model in memory
		checkResultAddShiftToStaff(name, role, shiftDate, startTime, endTime, fm);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultAddShiftToStaff(name, role, shiftDate, startTime, endTime, fm2);
	}
	
	@Test
	public void testUnassignShiftFromStaff(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getShifts().size());
		
		String name = "Jimmy";
		String role = "Cook";
		Staff staff = new Staff(name, role);
		fm.addStaff(staff);
		assertEquals(1, fm.getStaffs().size());
		
		
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,30,9,00,0);
		Date shiftDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,  Calendar.OCTOBER,30,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		Shift shift = new Shift(startTime, endTime, shiftDate);
		fm.addShift(shift);
		assertEquals(1, fm.getShifts().size());
		
		shift.addStaff(staff);
		StaffController sc = new StaffController();
		try{
			sc.removeShiftFromStaff(staff, shift);
		} catch (InvalidInputException e){
			//check no error
			fail();
		}
		//Check model in memory
		assertEquals(1, fm.getStaffs().size());
		assertEquals(1, fm.getShifts().size());
		assertEquals(0, fm.getShift(0).numberOfStaff());
		//Check file contents
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, fm2.getStaffs().size());
		assertEquals(1, fm2.getShifts().size());
		assertEquals(0, fm2.getShift(0).numberOfStaff());
	}
	
	@Test
	public void testReAssignShifttoStaff(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getShifts().size());
		
		String error = null;
		
		String name = "Jimmy";
		String role = "Cook";
		Staff staff = new Staff(name, role);
		fm.addStaff(staff);
		assertEquals(1, fm.getStaffs().size());
		
		
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,30,9,00,0);
		Date shiftDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,  Calendar.OCTOBER,30,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		Shift shift = new Shift(startTime, endTime, shiftDate);
		fm.addShift(shift);
		assertEquals(1, fm.getShifts().size());
		shift.addStaff(staff);
		StaffController sc = new StaffController();
		try{
			sc.addShiftToStaff(staff, shift);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		//Check error is what we expect it to be
		assertEquals("Shift is already assigned to selected staff!", error);
		//Check no change in memory
		assertEquals(1, fm.getShift(0).numberOfStaff());
	}
	
	@Test
	public void testUnassignShiftFromStaffAlreadyNotAssigned(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getShifts().size());
		assertEquals(0, fm.getStaffs().size());
		
		String error = null;
		
		String name = "Jimmy";
		String role = "Cook";
		Staff staff = new Staff(name, role);
		fm.addStaff(staff);
		assertEquals(1, fm.getStaffs().size());
		
		
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,30,9,00,0);
		Date shiftDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,  Calendar.OCTOBER,30,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		Shift shift = new Shift(startTime, endTime, shiftDate);
		fm.addShift(shift);
		assertEquals(1, fm.getShifts().size());
		assertEquals(false, fm.getShift(0).hasStaff());
		
		StaffController sc = new StaffController();
		try{
			sc.removeShiftFromStaff(staff, shift);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		//Check error is what we expect it to be
		assertEquals("Shift must be assigned to selected staff in order to unassign it!", error);
		//Check no change in memory
		assertEquals(false, fm.getShift(0).hasStaff());
		assertEquals(1, fm.getShifts().size());
		assertEquals(1, fm.getStaffs().size());
		
	}
	
	@Test
	public void testAssignNullShiftToStaff()
	{
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getShifts().size());
		
		Staff Staff = null;
		assertEquals(0, fm.getStaffs().size());
		
		Shift Shift = null;
		assertEquals(0, fm.getShifts().size());
		
		String error = null;
		StaffController sc = new StaffController();
		try{
			sc.addShiftToStaff(Staff, Shift);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Staff member must be selected! Shift must be selected!", error);
		
		//check model in memory 
		assertEquals(0, fm.getShifts().size());
		assertEquals(0, fm.getStaffs().size());
	}
	
	@Test
	public void testAssignShiftToStaffStaffAndShiftDoNotExist(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getShifts().size());
		
		String name = "Jimmy";
		String role = "Cook";
		Staff staff = new Staff(name, role);
		assertEquals(0, fm.getStaffs().size());
		
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,30,9,00,0);
		Date shiftDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,  Calendar.OCTOBER,30,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		Shift shift = new Shift(startTime, endTime, shiftDate);
		assertEquals(0, fm.getShifts().size());
		
		String error = null;
		StaffController sc = new StaffController();
		try{
			sc.addShiftToStaff(staff, shift);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Staff does not exist! Shift does not exist!", error);
		
		//check file contents
		assertEquals(0, fm.getShifts().size());
		assertEquals(0, fm.getStaffs().size());
	}
	
	
	private void checkResultStaff(String name, String role, FoodTruckManager fm2) 
	{
		assertEquals(1, fm2.getStaffs().size());
		assertEquals(name, fm2.getStaff(0).getName());
		assertEquals(role, fm2.getStaff(0).getRole());
	}
	private void checkResultShift(Time startTime, Time endTime, Date date, FoodTruckManager fm2) 
	{
		assertEquals(0, fm2.getStaffs().size());
		assertEquals(1, fm2.getShifts().size());
		assertEquals(date.toString(), fm2.getShift(0).getShiftDate().toString());
		assertEquals(startTime.toString(), fm2.getShift(0).getStartTime().toString());
		assertEquals(endTime.toString(), fm2.getShift(0).getEndTime().toString());
		assertEquals(0, fm2.getShift(0).getStaff().size());
	}
	private void checkResultAddShiftToStaff(String name, String role, Date date, Time startTime, Time endTime, FoodTruckManager fm2) 
	{
		assertEquals(1, fm2.getStaffs().size());
		assertEquals(name, fm2.getStaff(0).getName());
		assertEquals(role, fm2.getStaff(0).getRole());
		assertEquals(1, fm2.getShifts().size());
		assertEquals(date.toString(), fm2.getShift(0).getShiftDate().toString());
		assertEquals(startTime.toString(), fm2.getShift(0).getStartTime().toString());
		assertEquals(endTime.toString(), fm2.getShift(0).getEndTime().toString());
		assertEquals(1, fm2.getShift(0).getStaff().size());
		assertEquals(fm2.getStaff(0), fm2.getShift(0).getStaff(0));
	}
}

