package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.FoodTruckManagementSystem.controller.InvalidInputException;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Equipment;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceXStream;



public class TestEquipmentController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		PersistenceXStream.setFilename("test" + File.separator + "ca" + File.separator + "mcgill"+ File.separator + "ecse321" + File.separator + "foodtruckmanagementsystem" + File.separator + "persistence"+ File.separator +"data.xml");
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
	}


	@After
	public void tearDown() throws Exception {
		//Clear all registrations
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.delete();
	}

	@Test
	public void testCreateEquipment() {
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getEquipment().size());
		
		String name = "Large Forks";
		String quantity = "10";
		int quantityInt = Integer.parseInt(quantity);
		
		EquipmentController ec = new EquipmentController();
		try {
			ec.createEquipment(name, quantity);
		} catch (InvalidInputException e) {
			//check no error occurred
			fail();
		}
		
		checkResultEquipment(name, quantityInt, fm);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultEquipment(name, quantityInt, fm2);
	}
	
	@Test
	public void testCreateEquipmentNull(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getEquipment().size());
		
		String name = null;
		String quantity = null;
		String error = null;
		
		EquipmentController ec = new EquipmentController();
		try {
			ec.createEquipment(name, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//Check error message
		assertEquals("Equipment name cannot be empty! Not a valid equipment quantity!", error);
		
		//Check for no change in memory
		assertEquals(0, fm.getEquipment().size());
	}
		
	
	@Test
	public void testCreateEquipmentNameEmpty(){
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getEquipment().size());
		
		String name = "";
		String quantity = "";
		String error = null;
		
		EquipmentController ec = new EquipmentController();
		try {
			ec.createEquipment(name, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//Check error message
		assertEquals("Equipment name cannot be empty! Not a valid equipment quantity!", error);
		
		//Check for no change in memory
		assertEquals(0, fm.getEquipment().size());
	}
		
	
	@Test
	public void testRemoveEquipment() {
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getEquipment().size());
		
		String name = "Large Spoons";
		int quantity = 50;
		
		Equipment equipment = new Equipment(name, quantity);
		
		EquipmentController ec = new EquipmentController();
		try {
			ec.removeEquipment(equipment);
		} catch (InvalidInputException e) {
			//Check no error
			fail();
		}
		
		//Check for successful deletion
		assertEquals(0, fm.getEquipment().size());
	}
		
		
	@Test
	public void testRemoveEquipmentNull() {
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getEquipment().size());
		
		Equipment equipment = null;
		String error = null;
		
		EquipmentController ec = new EquipmentController();
		try {
			ec.removeEquipment(equipment);
		} catch (InvalidInputException e) {
			//Get error
			error = e.getMessage();
		}
		
		//Check error is what we expect it to be
		assertEquals("Must select equipment to remove!", error);
		
		//Check no change in memory
		assertEquals(0, fm.getEquipment().size());
	}
	
	@Test
	public void testAddEquipmentToInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getEquipment().size());
	
		String name = "Potato Bag, 10 kg";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Equipment equipment = new Equipment(name, quantity);
		fm.addEquipment(equipment);
		String addQuantity = "10";
		assertEquals(1, fm.getEquipment().size());
		
		EquipmentController ec = new EquipmentController();
		try{
			ec.addToEquipmentInventory(equipment, addQuantity);
		} catch (InvalidInputException e){
			//check no error
			fail();
		}
		//check model in memory
		checkResultAddEquipmentToInventory(equipment, quantity, addQuantity, fm);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultAddEquipmentToInventory(equipment, quantity, addQuantity, fm2);
	}
	
	@Test
	public void testAddNullEquipmentToInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getEquipment().size());
		
		Equipment equipment = null;
		String addQuantity = "10";
		
		EquipmentController ec = new EquipmentController();
		String error = null;
		try{
			ec.addToEquipmentInventory(equipment, addQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Must select equipment!", error);
				
		//Check model in memory 
		assertEquals(0, fm.getEquipment().size());
	}
	
	@Test
	public void testNegativeAddEquipmentToInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getEquipment().size());
	
		String name = "Potato Bag, 10 kg";
		int quantity = 15;
		
		Equipment equipment = new Equipment(name, quantity);
		fm.addEquipment(equipment);
		String addQuantity = "-20";
		assertEquals(1, fm.getEquipment().size());
		
		String error = null;
		EquipmentController ec = new EquipmentController();
		try{
			ec.addToEquipmentInventory(equipment, addQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Equipment must have a quantity greater than 0 to be added!", error);
		
		//Check model in memory 
		assertEquals(1, fm.getEquipment().size());
		assertEquals(quantity, fm.getEquipment(0).getQuantity());
	}
	
	@Test
	public void testRemoveEquipmentFromInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getEquipment().size());
	
		String name = "Potato Bag, 10 kg";
		int quantity = 15;
		
		Equipment equipment = new Equipment(name, quantity);
		fm.addEquipment(equipment);
		String removeQuantity = "10";
		assertEquals(1, fm.getEquipment().size());
		
		EquipmentController ec = new EquipmentController();
		try{
			ec.removeFromEquipmentInventory(equipment, removeQuantity);
		} catch (InvalidInputException e){
			//check no error
			fail();
		}
		//check model in memory
		checkResultRemoveEquipmentFromInventory(equipment, quantity, removeQuantity, fm);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultRemoveEquipmentFromInventory(equipment, quantity, removeQuantity, fm2);
	}
	
	@Test
	public void testRemoveNullEquipmentFromInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getEquipment().size());
		
		String name = "Potato Bag, 10 kg";
		int quantity = 15;
		
		Equipment equipment1 = new Equipment(name, quantity);
		fm.addEquipment(equipment1);
		assertEquals(1, fm.getEquipment().size());
		
		Equipment equipment2 = null;
		String error = null;
		String removeQuantity = "10";
		assertEquals(1, fm.getEquipment().size());
		
		EquipmentController ec = new EquipmentController();
		try{
			ec.removeFromEquipmentInventory(equipment2, removeQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Must select equipment!", error);
				
		//Check model in memory 
		assertEquals(1, fm.getEquipment().size());
	}
	
	@Test
	public void testExcessiveRemoveEquipmentFromInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getEquipment().size());
	
		String name = "Butter Knives";
		int quantity = 15;
		
		Equipment equipment = new Equipment(name, quantity);
		fm.addEquipment(equipment);
		String removeQuantity = "20";
		assertEquals(1, fm.getEquipment().size());
		
		String error = null;
		EquipmentController ec = new EquipmentController();
		try{
			ec.removeFromEquipmentInventory(equipment, removeQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Cannot have less than zero of a type of equipment!", error);
		
		//Check model in memory 
		assertEquals(1, fm.getEquipment().size());
		assertEquals(quantity, fm.getEquipment(0).getQuantity());
	}
	
	@Test
	public void testNegativeRemoveEquipmentFromInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getEquipment().size());
	
		String name = "Potato Bag, 10 kg";
		int quantity = 15;
		
		Equipment equipment = new Equipment(name, quantity);
		fm.addEquipment(equipment);
		String removeQuantity = "-20";
		assertEquals(1, fm.getEquipment().size());
		
		String error = null;
		EquipmentController ec = new EquipmentController();
		try{
			ec.removeFromEquipmentInventory(equipment, removeQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Equipment must have a quantity greater than 0 to be removed!", error);
		
		//Check model in memory 
		assertEquals(1, fm.getEquipment().size());
		assertEquals(quantity, fm.getEquipment(0).getQuantity());
	}
	
	
	private void checkResultEquipment(String name, int quantity, FoodTruckManager fm2) 
	{
		
		assertEquals(1, fm2.getEquipment().size());
		assertEquals(name, fm2.getEquipment(0).getName());
		assertEquals(quantity, fm2.getEquipment(0).getQuantity());
	}
	
	private void checkResultAddEquipmentToInventory(Equipment equipment, int quantity, String addQuantity, FoodTruckManager fm2){
		int addQuantityInt = Integer.parseInt(addQuantity);
		assertEquals(1, fm2.getEquipment().size());
		assertEquals(quantity + addQuantityInt, fm2.getEquipment(0).getQuantity());
	}
	
	private void checkResultRemoveEquipmentFromInventory(Equipment equipment, int quantity, String removeQuantity, FoodTruckManager fm2){
		int removeQuantityInt = Integer.parseInt(removeQuantity);
		assertEquals(1, fm2.getEquipment().size());
		assertEquals(quantity - removeQuantityInt, fm2.getEquipment(0).getQuantity());
	}
	
}


