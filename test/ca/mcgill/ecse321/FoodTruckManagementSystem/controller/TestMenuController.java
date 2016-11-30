/*package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

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
		//clear all registrations
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.delete();
	}

	@Test
	public void testCreateItem() {
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
		
		String name = "Canned Black Beans, 1 kg";
		String quantity = "10";
		int quantityInt = Integer.parseInt(quantity);
		
		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		
		MenuController mc = new MenuController();
		try {
			mc.createItem(name, quantity);
		} catch (InvalidInputException e) {
			//check no error occurred
			fail();
		}
		
		checkResultItem(name, description, fm);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultItem(name, quantityInt, bestBeforeDate, fm2);
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
		assertEquals("Item name cannot be empty! Not a valid item quantity! Best before date must be entered!", error);
		
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
			//Get error
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
	
		String name = "Mashed Potatos";
		String description = "Generic description";
		
		Item item = new Item(name, description);
		fm.createItem(item);
		String addQuantity = "10";
		assertEquals(1, fm.getItems().size());
		
		MenuController mc = new MenuController();
		try{
			mc.addToItemInventory(item, addQuantity);
		} catch (InvalidInputException e){
			//check no error
			fail();
		}
		//check model in memory
		checkResultAddItemToInventory(item, quantity, addQuantity, fm);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultAddItemToInventory(item, quantity, addQuantity, fm2);
	}
	
	@Test
	public void testAddNullItemToInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
		
		Item item = null;
		String addQuantity = "10";
		
		MenuController mc = new MenuController();
		String error = null;
		try{
			mc.addToItemInventory(item, addQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Must select item!", error);
				
		//Check model in memory 
		assertEquals(0, fm.getItems().size());
	}
	
	@Test
	public void testNegativeAddItemToInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
	
		String name = "Potato Bag, 10 kg";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Item item = new Item(name, description);
		fm.createItem(item);
		String addQuantity = "-20";
		assertEquals(1, fm.getItems().size());
		
		String error = null;
		MenuController mc = new MenuController();
		try{
			mc.addToItemInventory(item, addQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Item must have a quantity greater than 0 to be added!", error);
		
		//Check model in memory 
		assertEquals(1, fm.getItems().size());
		assertEquals(quantity, fm.getItem(0).getQuantity());
	}
	
	@Test
	public void testRemoveItemFromInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
	
		String name = "Potato Bag, 10 kg";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Item item = new Item(name, description);
		fm.createItem(item);
		String removeQuantity = "10";
		assertEquals(1, fm.getItems().size());
		
		MenuController mc = new MenuController();
		try{
			mc.removeFromItemInventory(item, removeQuantity);
		} catch (InvalidInputException e){
			//check no error
			fail();
		}
		//check model in memory
		checkResultRemoveItemFromInventory(item, quantity, removeQuantity, fm);
		
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultRemoveItemFromInventory(item, quantity, removeQuantity, fm2);
	}
	
	@Test
	public void testRemoveNullItemFromInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
		
		String name = "Potato Bag, 10 kg";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Item supply1 = new Item(name, description);
		fm.createItem(supply1);
		assertEquals(1, fm.getItems().size());
		
		Item supply2 = null;
		String error = null;
		String removeQuantity = "10";
		assertEquals(1, fm.getItems().size());
		
		MenuController mc = new MenuController();
		try{
			mc.removeFromItemInventory(supply2, removeQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Must select item!", error);
				
		//Check model in memory 
		assertEquals(1, fm.getItems().size());
	}
	
	@Test
	public void testExcessiveRemoveItemFromInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
	
		String name = "Potato Bag, 10 kg";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Item item = new Item(name, description);
		fm.createItem(item);
		String removeQuantity = "20";
		assertEquals(1, fm.getItems().size());
		
		String error = null;
		MenuController mc = new MenuController();
		try{
			mc.removeFromItemInventory(item, removeQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Cannot have less than zero supplies!", error);
		
		//Check model in memory 
		assertEquals(1, fm.getItems().size());
		assertEquals(quantity, fm.getItem(0).getQuantity());
	}
	
	@Test
	public void testNegativeRemoveItemFromInventory(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		assertEquals(0, fm.getItems().size());
	
		String name = "Potato Bag, 10 kg";
		int quantity = 15;

		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.OCTOBER,30,9,00,0);
		Date bestBeforeDate = new Date(c.getTimeInMillis());
		
		Item item = new Item(name, description);
		fm.createItem(item);
		String removeQuantity = "-20";
		assertEquals(1, fm.getItems().size());
		
		String error = null;
		MenuController mc = new MenuController();
		try{
			mc.removeFromItemInventory(item, removeQuantity);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		//Check error
		assertEquals("Item must have a quantity greater than 0 to be removed!", error);
		
		//Check model in memory 
		assertEquals(1, fm.getItems().size());
		assertEquals(quantity, fm.getItem(0).getQuantity());
	}
	
	
	private void checkResultItem(String name, int quantity, Date bestBefore, FoodTruckManager fm2) 
	{
		
		assertEquals(1, fm2.getItems().size());
		assertEquals(name, fm2.getItem(0).getName());
		assertEquals(quantity, fm2.getItem(0).getQuantity());
		assertEquals(bestBefore.toString(), fm2.getItem(0).getBestBefore().toString());
	}
	
	private void checkResultAddItemToInventory(Item item, int quantity, String addQuantity, FoodTruckManager fm2){
		int addQuantityInt = Integer.parseInt(addQuantity);
		assertEquals(1, fm2.getItems().size());
		assertEquals(quantity + addQuantityInt, fm2.getItem(0).getQuantity());
	}
	
	private void checkResultRemoveItemFromInventory(Item item, int quantity, String removeQuantity, FoodTruckManager fm2){
		int removeQuantityInt = Integer.parseInt(removeQuantity);
		assertEquals(1, fm2.getItems().size());
		assertEquals(quantity - removeQuantityInt, fm2.getItem(0).getQuantity());
	}
	
}

*/
