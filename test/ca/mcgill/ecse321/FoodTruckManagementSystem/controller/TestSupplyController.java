package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Supply;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceXStream;



public class TestSupplyController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		PersistenceXStream.setFilename("test" + File.separator + "ca" + File.separator + "mcgill"+ File.separator + "ecse321" + File.separator + "foodtruckmanagementsystem" + File.separator + "persistence"+ File.separator +"data.xml");
		PersistenceXStream.setAlias("supply", Supply.class);
		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
	}


	@After
	public void tearDown() throws Exception {
		//clear all registrations
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.delete();
	}

	@Test
	public void testCreateSupply() {
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getSupplies().size());
		
		String name = "Canned Black Beans, 1 kg";
		String quantity = "10";
		int quantityInt = Integer.parseInt(quantity);
		
		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		
		SupplyController sc = new SupplyController();
		try {
			sc.createSupply(name, quantity, bestBeforeDate);
		} catch (InvalidInputException e) {
			//check no error occurred
			fail();
		}
		
		checkResultSupply(name, quantityInt, bestBeforeDate, fm);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultSupply(name, quantityInt, bestBeforeDate, fm2);
	}
	
	@Test
	public void testCreateSupplyNull(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getSupplies().size());
		
		String name = null;
		String quantity = null;
		Date bestBeforeDate = null;
		String error = null;
		
		SupplyController sc = new SupplyController();
		try {
			sc.createSupply(name, quantity, bestBeforeDate);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//Check error message
		assertEquals("Supply name cannot be empty! Not a valid supply quantity! Best before date must be entered!", error);
		
		//Check for no change in memory
		assertEquals(0, fm.getSupplies().size());
	}
		
	
	@Test
	public void testCreateSupplyNameEmpty(){
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getSupplies().size());
		
		String name = "";
		String quantity = "";
		Date bestBeforeDate = null;
		String error = null;
		
		SupplyController sc = new SupplyController();
		try {
			sc.createSupply(name, quantity, bestBeforeDate);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//Check error message
		assertEquals("Supply name cannot be empty! Not a valid supply quantity! Best before date must be entered!", error);
		
		//Check for no change in memory
		assertEquals(0, fm.getSupplies().size());
	}
		
	
	@Test
	public void testRemoveSupply() {
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getSupplies().size());
		
		String name = "Potato Bag, 10 kg";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Supply supply = new Supply(name, quantity, bestBeforeDate);
		
		SupplyController sc = new SupplyController();
		try {
			sc.removeSupply(supply);
		} catch (InvalidInputException e) {
			//Check no error
			fail();
		}
		
		//Check for successful deletion
		assertEquals(0, fm.getSupplies().size());
	}
		
		
	@Test
	public void testRemoveSupplyNull() {
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getSupplies().size());
		
		Supply supply = null;
		String error = null;
		
		SupplyController sc = new SupplyController();
		try {
			sc.removeSupply(supply);
		} catch (InvalidInputException e) {
			//Get error
			error = e.getMessage();
		}
		
		//Check error is what we expect it to be
		assertEquals("Must select supply to remove!", error);
		
		//Check no change in memory
		assertEquals(0, fm.getSupplies().size());
	}
	
	@Test 
	public void testCreateSupplyExpired(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getSupplies().size());

		String name = "Bags of Flour, 10 kg";
		String quantity = "20";
		
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,16,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		
		String error = null;
		SupplyController sc = new SupplyController();
		
		try{
			sc.createSupply(name, quantity, bestBeforeDate);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Supply has already expired!", error);
		
		//Check model in memory 
		assertEquals(0, fm.getSupplies().size());
	}
	
	
	@Test
	public void testAddSupplyToInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getSupplies().size());
	
		String name = "Potato Bag, 10 kg";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Supply supply = new Supply(name, quantity, bestBeforeDate);
		fm.addSupply(supply);
		String addQuantity = "10";
		assertEquals(1, fm.getSupplies().size());
		
		SupplyController sc = new SupplyController();
		try{
			sc.addToSupplyInventory(supply, addQuantity);
		} catch (InvalidInputException e){
			//check no error
			fail();
		}
		//check model in memory
		checkResultAddSupplyToInventory(supply, quantity, addQuantity, fm);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultAddSupplyToInventory(supply, quantity, addQuantity, fm2);
	}
	
	@Test
	public void testAddNullSupplyToInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getSupplies().size());
		
		Supply supply = null;
		String addQuantity = "10";
		
		SupplyController sc = new SupplyController();
		String error = null;
		try{
			sc.addToSupplyInventory(supply, addQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Must select supply!", error);
				
		//Check model in memory 
		assertEquals(0, fm.getSupplies().size());
	}
	
	@Test
	public void testNegativeAddSupplyToInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getSupplies().size());
	
		String name = "Potato Bag, 10 kg";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Supply supply = new Supply(name, quantity, bestBeforeDate);
		fm.addSupply(supply);
		String addQuantity = "-20";
		assertEquals(1, fm.getSupplies().size());
		
		String error = null;
		SupplyController sc = new SupplyController();
		try{
			sc.addToSupplyInventory(supply, addQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Supply must have a quantity greater than 0 to be added!", error);
		
		//Check model in memory 
		assertEquals(1, fm.getSupplies().size());
		assertEquals(quantity, fm.getSupply(0).getQuantity());
	}
	
	@Test
	public void testRemoveSupplyFromInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getSupplies().size());
	
		String name = "Potato Bag, 10 kg";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Supply supply = new Supply(name, quantity, bestBeforeDate);
		fm.addSupply(supply);
		String removeQuantity = "10";
		assertEquals(1, fm.getSupplies().size());
		
		SupplyController sc = new SupplyController();
		try{
			sc.removeFromSupplyInventory(supply, removeQuantity);
		} catch (InvalidInputException e){
			//check no error
			fail();
		}
		//check model in memory
		checkResultRemoveSupplyFromInventory(supply, quantity, removeQuantity, fm);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultRemoveSupplyFromInventory(supply, quantity, removeQuantity, fm2);
	}
	
	@Test
	public void testRemoveNullSupplyFromInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getSupplies().size());
		
		String name = "Potato Bag, 10 kg";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Supply supply1 = new Supply(name, quantity, bestBeforeDate);
		fm.addSupply(supply1);
		assertEquals(1, fm.getSupplies().size());
		
		Supply supply2 = null;
		String error = null;
		String removeQuantity = "10";
		assertEquals(1, fm.getSupplies().size());
		
		SupplyController sc = new SupplyController();
		try{
			sc.removeFromSupplyInventory(supply2, removeQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Must select supply!", error);
				
		//Check model in memory 
		assertEquals(1, fm.getSupplies().size());
	}
	
	@Test
	public void testExcessiveRemoveSupplyFromInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getSupplies().size());
	
		String name = "Potato Bag, 10 kg";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Supply supply = new Supply(name, quantity, bestBeforeDate);
		fm.addSupply(supply);
		String removeQuantity = "20";
		assertEquals(1, fm.getSupplies().size());
		
		String error = null;
		SupplyController sc = new SupplyController();
		try{
			sc.removeFromSupplyInventory(supply, removeQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Cannot have less than zero supplies!", error);
		
		//Check model in memory 
		assertEquals(1, fm.getSupplies().size());
		assertEquals(quantity, fm.getSupply(0).getQuantity());
	}
	
	@Test
	public void testNegativeRemoveSupplyFromInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getSupplies().size());
	
		String name = "Potato Bag, 10 kg";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Supply supply = new Supply(name, quantity, bestBeforeDate);
		fm.addSupply(supply);
		String removeQuantity = "-20";
		assertEquals(1, fm.getSupplies().size());
		
		String error = null;
		SupplyController sc = new SupplyController();
		try{
			sc.removeFromSupplyInventory(supply, removeQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Supply must have a quantity greater than 0 to be removed!", error);
		
		//Check model in memory 
		assertEquals(1, fm.getSupplies().size());
		assertEquals(quantity, fm.getSupply(0).getQuantity());
	}
	
	
	private void checkResultSupply(String name, int quantity, Date bestBefore, FoodTruckManager fm2) 
	{
		
		assertEquals(1, fm2.getSupplies().size());
		assertEquals(name, fm2.getSupply(0).getName());
		assertEquals(quantity, fm2.getSupply(0).getQuantity());
		assertEquals(bestBefore.toString(), fm2.getSupply(0).getBestBefore().toString());
	}
	
	private void checkResultAddSupplyToInventory(Supply supply, int quantity, String addQuantity, FoodTruckManager fm2){
		int addQuantityInt = Integer.parseInt(addQuantity);
		assertEquals(1, fm2.getSupplies().size());
		assertEquals(quantity + addQuantityInt, fm2.getSupply(0).getQuantity());
	}
	
	private void checkResultRemoveSupplyFromInventory(Supply supply, int quantity, String removeQuantity, FoodTruckManager fm2){
		int removeQuantityInt = Integer.parseInt(removeQuantity);
		assertEquals(1, fm2.getSupplies().size());
		assertEquals(quantity - removeQuantityInt, fm2.getSupply(0).getQuantity());
	}
	
}


