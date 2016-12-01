package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Item;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Supply;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceXStream;

public class TestMenuController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		PersistenceXStream.setFilename("test" + File.separator + "ca" + File.separator + "mcgill"+ File.separator + "ecse321" + File.separator + "foodtruckmanagementsystem" + File.separator + "persistence"+ File.separator +"data.xml");
		PersistenceXStream.setAlias("item", Item.class);
		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
	}


	@After
	public void tearDown() throws Exception {
		//Clear the FoodTruckManager
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.delete();
	}

	@Test
	public void testCreateItem() {
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
		
		String name = "Canned Black Beans, 1 kg";
		String description = "Generic description";
		
		MenuController mc = new MenuController();
		try {
			mc.createItem(name, description);
		} catch (InvalidInputException e) {
			//Check no error occurred
			fail();
		}
		
		checkResultItem(name, description, fm);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultItem(name, description, fm2);
	}
	
	@Test
	public void testCreateItemNull(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
		
		String name = null;
		String description = null;
		String error = null;
		
		MenuController mc = new MenuController();
		try {
			mc.createItem(name, description);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//Check error message
		assertEquals("Item name cannot be empty! Item description cannot be empty!", error);
		
		//Check for no change in memory
		assertEquals(0, fm.getItems().size());
	}
		
	
	@Test
	public void testCreateItemNameEmpty(){
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
		
		String name = "";
		String description = "";
		String error = null;
		
		MenuController mc = new MenuController();
		try {
			mc.createItem(name, description);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//Check error message
		assertEquals("Item name cannot be empty! Item description cannot be empty!", error);
		
		//Check for no change in memory
		assertEquals(0, fm.getItems().size());
	}
		
	
	@Test
	public void testRemoveItem() {
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
		
		String name = "Mashed Potatos";
		String description = "Generic description";
		
		Item item = new Item(name, description);
		
		MenuController mc = new MenuController();
		try {
			mc.removeItem(item);
		} catch (InvalidInputException e) {
			//Check no error
			fail();
		}
		
		//Check for successful deletion
		assertEquals(0, fm.getItems().size());
	}
		
		
	@Test
	public void testRemoveItemNull() {
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
		
		Item item = null;
		String error = null;
		
		MenuController mc = new MenuController();
		try {
			mc.removeItem(item);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//Check error is what we expect it to be
		assertEquals("Must select item to remove!", error);
		
		//Check no change in memory
		assertEquals(0, fm.getItems().size());
	}
	
	
	
	@Test
	public void testAddSupplyToItem(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
	
		String supplyName = "Yellow Potatos";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Supply supply = new Supply(supplyName, quantity, bestBeforeDate);
		
		String name = "Mashed Potatos";
		String description = "Generic description";
		
		Item item = new Item(name, description);
		fm.addItem(item);
		fm.addSupply(supply);
		
		assertEquals(1, fm.getItems().size());
		assertEquals(1, fm.getSupplies().size());
		
		MenuController mc = new MenuController();
		try{
			mc.addSupply(supply, item);
		} catch (InvalidInputException e){
			//Check no error
			fail();
		}
		
		//Check model in memory
		checkResultItem(name, description, fm);
		checkResultSupply(supplyName, quantity, bestBeforeDate, fm);
		assertEquals(supply, fm.getItem(0).getSupply(0));
		
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//Check file contents
		checkResultItem(name, description, fm2);
		checkResultSupply(supplyName, quantity, bestBeforeDate, fm2);
	}
	
	@Test
	public void testSupplyAlreadyAddedToItem(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
	
		String supplyName = "Yellow Potatos";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Supply supply = new Supply(supplyName, quantity, bestBeforeDate);
		
		String name = "Mashed Potatos";
		String description = "Generic description";
		
		Item item = new Item(name, description);
		fm.addItem(item);
		fm.addSupply(supply);
		
		assertEquals(1, fm.getItems().size());
		assertEquals(1, fm.getSupplies().size());
		String error = null;
		MenuController mc = new MenuController();
		try{
			mc.addSupply(supply, item);
		} catch (InvalidInputException e){
			//Check no error
			fail();
		}
		try{
			mc.addSupply(supply, item);
		} catch (InvalidInputException e){
			//Check error
			error = e.getMessage();
		}
		
		//Check model in memory
		checkResultItem(name, description, fm);
		checkResultSupply(supplyName, quantity, bestBeforeDate, fm);
		assertEquals(supply.getName(), fm.getItem(0).getSupply(0).getName());
		assertEquals(supply.getQuantity(), fm.getItem(0).getSupply(0).getQuantity());
		assertEquals(supply.getBestBefore().toString(), fm.getItem(0).getSupply(0).getBestBefore().toString());
		assertEquals(error, "Item already has supply listed!");
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//Check file contents
		checkResultItem(name, description, fm2);
		checkResultSupply(supplyName, quantity, bestBeforeDate, fm2);
		assertEquals(supply.getName(), fm2.getItem(0).getSupply(0).getName());
		assertEquals(supply.getQuantity(), fm2.getItem(0).getSupply(0).getQuantity());
		assertEquals(supply.getBestBefore().toString(), fm2.getItem(0).getSupply(0).getBestBefore().toString());
		assertEquals(error, "Item already has supply listed!");
	}
	
	@Test
	public void testAddNullSupplyToItem(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
		
		String name = "Mashed Potatos";
		String description = "Generic description";
		
		Item item = new Item(name, description);
		Supply supply = null;
		
		MenuController mc = new MenuController();
		String error = null;
		try{
			mc.addSupply(supply, item);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Supply cannot be empty!", error);
				
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//Check model in memory 
		assertEquals(0, fm2.getItem(0).getSupply().size());
	}
	
	@Test
	public void testRemoveSupplyFromItem(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
	
		String name = "Mashed Potatos";
		String description = "Generic description";
		
		String supplyName = "Yellow Potatos";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Supply supply = new Supply(supplyName, quantity, bestBeforeDate);
		Item item = new Item(name, description);
		
		fm.addItem(item);
		fm.addSupply(supply);
		assertEquals(1, fm.getItems().size());
		assertEquals(1, fm.getSupplies().size());
		
		MenuController mc = new MenuController();
		try{
			mc.addSupply(supply, item);
		} catch (InvalidInputException e){
			//check no error
			fail();
		}
		try{
			mc.removeSupply(supply, item);
		} catch (InvalidInputException e){
			//check no error
			fail();
		}
		//Check model in memory
		assertEquals(0, fm.getItem(0).getSupply().size());
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//Check file contents
		assertEquals(0, fm2.getItem(0).getSupply().size());
	}
	
	@Test
	public void testRemoveNullSupplyFromItem(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
		
		String name = "Mashed Potatos";
		String description = "Generic description";
		
		Item item = new Item(name, description);
		Supply supply = null;
		
		MenuController mc = new MenuController();
		String error = null;
		fm.addItem(item);
		PersistenceXStream.saveToXMLwithXStream(fm);
		assertEquals(item, fm.getItem(0));
		assertEquals(0, fm.getItem(0).getSupply().size());
		assertEquals(1, fm.getItems().size());
		
		try{
			mc.removeSupply(supply, item);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Supply cannot be empty!", error);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
				
		//Check model in memory 
		assertEquals(1, fm2.getItems().size());
	}
	
	@Test
	public void testMakeOrder(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getOrders().size());
	
		String name = "Mashed Potatos";
		String description = "Generic description";
		
		Item item = new Item(name, description);
		fm.addItem(item);
		assertEquals(1, fm.getItems().size());
		
		MenuController mc = new MenuController();
		try{
			mc.makeOrder(item);
		} catch (InvalidInputException e){
			fail();
		}
		
		//Check model in memory
		assertEquals(1, fm.getOrders().size());
		assertEquals(item, fm.getOrder(0).getItem(0));
				
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
				
		//Check file contents
		assertEquals(1, fm2.getOrders().size());
		assertEquals(item.getName(), fm2.getOrder(0).getItem(0).getName());
	}
	
	@Test
	public void testMakeOrderSupplyStockEmpty(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getOrders().size());
	
		String name = "Mashed Potatos";
		String description = "Generic description";
		
		Item item = new Item(name, description);
		
		String supplyName = "Yellow Potatos";
		int quantity = 0;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Supply supply = new Supply(supplyName, quantity, bestBeforeDate);
		item.addSupply(supply);
		fm.addItem(item);
		assertEquals(1, fm.getItems().size());
		assertEquals(1, fm.getItem(0).getSupply().size());
		
		PersistenceXStream.saveToXMLwithXStream(fm);
		String error = null;
		MenuController mc = new MenuController();
		try{
			mc.makeOrder(item);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check model in memory
		assertEquals(0, fm.getOrders().size());
		assertEquals(item.getName(), fm.getItem(0).getName());
		assertEquals(item.getDescription(), fm.getItem(0).getDescription());
		assertEquals(error, "Cannot make order! " + fm.getItem(0).getSupply(0).getName() + " out of stock!");
				
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
				
		//Check file contents
		assertEquals(0, fm2.getOrders().size());
		assertEquals(item.getName(), fm2.getItem(0).getName());
		assertEquals(item.getDescription(), fm2.getItem(0).getDescription());
		assertEquals(error, "Cannot make order! " + fm2.getItem(0).getSupply(0).getName() + " out of stock!");			
	}
	
	@Test
	public void testMakeNullOrder(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getOrders().size());
		
		Item item = null;
		assertEquals(0, fm.getItems().size());
		
		MenuController mc = new MenuController();
		String error = null;
		try{
			mc.makeOrder(item);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Must select item to add!", error);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
				
		//Check model in memory 
		assertEquals(0, fm2.getOrders().size());
	}
	
	@Test
	public void testRemoveOrder(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getOrders().size());
	
		String name = "Mashed Potatos";
		String description = "Generic description";
		
		Item item = new Item(name, description);
		fm.addItem(item);
		assertEquals(1, fm.getItems().size());
		
		MenuController mc = new MenuController();
		try {
			mc.makeOrder(item);
		} catch (InvalidInputException e) {
			fail();
		}
		assertEquals(item.getName(), fm.getOrder(0).getItem(0).getName());
		try{
			mc.removeOrder();
		} catch (InvalidInputException e){
			fail();
		}
		
		//Check model in memory
		assertEquals(0, fm.getOrders().size());
		assertEquals(item, fm.getItem(0));
				
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
				
		//Check file contents
		assertEquals(0, fm2.getOrders().size());
		assertEquals(item.getName(), fm2.getItem(0).getName());
	}
	
	@Test
	public void testRemoveExcessOrder(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getOrders().size());
	
		String name = "Mashed Potatos";
		String description = "Generic description";
		
		Item item = new Item(name, description);
		fm.addItem(item);
		assertEquals(1, fm.getItems().size());
		
		MenuController mc = new MenuController();
		try {
			mc.makeOrder(item);
		} catch (InvalidInputException e) {
			fail();
		}
		assertEquals(item.getName(), fm.getOrder(0).getItem(0).getName());
		try{
			mc.removeOrder();
		} catch (InvalidInputException e){
			fail();
		}
		//Check model in memory. Then remove excess item from order queue
		assertEquals(0, fm.getOrders().size());
		assertEquals(item, fm.getItem(0));
		String error = null;
		try{
			mc.removeOrder();
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check model in memory
		assertEquals(error, "No orders have been made!");
		assertEquals(0, fm.getOrders().size());
				
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
				
		//Check file contents
		assertEquals(error, "No orders have been made!");
		assertEquals(0, fm2.getOrders().size());
				
	}
	
	
	private void checkResultItem(String name, String description, FoodTruckManager fm2) 
	{
		
		assertEquals(1, fm2.getItems().size());
		assertEquals(name, fm2.getItem(0).getName());
		assertEquals(description, fm2.getItem(0).getDescription());
	}
	
	private void checkResultSupply(String name, int quantity, Date bestBefore, FoodTruckManager fm2) 
	{
		assertEquals(1, fm2.getSupplies().size());
		assertEquals(name, fm2.getSupply(0).getName());
		assertEquals(quantity, fm2.getSupply(0).getQuantity());
		assertEquals(bestBefore.toString(), fm2.getSupply(0).getBestBefore().toString());
	}
}


