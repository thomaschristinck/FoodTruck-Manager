package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import java.sql.Date;
import java.util.*;
import java.sql.Time;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Item;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Order;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceXStream;
/**
 * The MenuController class is responsible for creating/removing menu items, adding/removing a
 * menu item to/from an order, and placing an order. The MenuController will also provide functionality
 * for allowing the user to view the menu as a .txt file that they may re-format in a text editor, as well
 * as functionality for allowing the user to view menu statistics (how often an item is ordered, item 
 * popularity, etc.).
 *  
 * @author thomaschristinck
 *
 */
public class MenuController {
	public MenuController(){
	}
	public void createItem(String itemName, String itemDescription) throws InvalidInputException{
		String error = "";
		if (itemName == null || itemName.trim().length() == 0)
			error = error + " Item name cannot be empty!";
		if (itemName == null || itemName.trim().length() == 0)
			error = error + " Item description cannot be empty!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
	
		Item item = new Item(itemName, itemDescription);
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.addItem(item);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}

	public void removeItem(Item item) throws InvalidInputException{
		String error = "";
		if (item == null)
			error = error + " Must select item to remove!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.removeItem(item);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	int orderNumbers = 0;
	List<Item> items;
	public void addItemToOrder(Item item) throws InvalidInputException{
		String error = "";
		if (item == null)
			error = error + " Must select item to add!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		items.add(item);
	}
	
	public void removeItemFromOrder(Item item) throws InvalidInputException{
		String error = "";
		if (item == null)
			error = error + " Must select item to remove!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		items.remove(item);
	}
	
	public void makeOrder(List<Item> items) throws InvalidInputException{
		String error = "";
		if (items.size() == 0)
			error = error + " Cannot place an order with no items!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		int orderNumber = fm.getOrders().size() + 1;
		Calendar c = Calendar.getInstance();
		Time orderTime = (Time) c.getTime();
		java.util.Date utilDate = c.getTime();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Order order = new Order(orderNumber, sqlDate, orderTime);
		
		for(int i = 0; i < items.size(); i++){
			order.addItem(items.remove(0));
		}
		
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
}
