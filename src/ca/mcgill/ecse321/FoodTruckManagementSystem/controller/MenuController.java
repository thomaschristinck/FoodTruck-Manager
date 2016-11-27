package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import java.sql.Date;
import java.util.*;
import java.sql.Time;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Item;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Order;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Supply;
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
	
	int orderNumbers;
	
	public void addSupply(Supply supply, Item item) throws InvalidInputException{
		String error = "";
		if (supply == null)
			error = error + " Supply cannot be empty!";
		if (item == null){
			error = error + " Item name cannot be empty!";
		}
		else{
			if(item.indexOfSupply(supply) >= 0)
				error = error + " Item already has supply listed!";
		}
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);

		FoodTruckManager fm = FoodTruckManager.getInstance();
		item.addSupply(supply);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	public void removeSupply(Supply supply, Item item) throws InvalidInputException{
		String error = "";
		if (item == null)
			error = error + " Item name cannot be empty!";
		if (supply== null)
			error = error + " Supply cannot be empty!";
		if (supply != null && item != null){
			if (!item.removeSupply(supply))
				error = error + " Supply cannot be removed from an item it isn't listed for!";
		}
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		item.removeSupply(supply);
		PersistenceXStream.saveToXMLwithXStream(fm);
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

	public void addItemToOrder(Item item) throws InvalidInputException{
		String error = "";
		if (item == null)
			error = error + " Must select item to add!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		if(fm.getOrders().size() == 0){
			Calendar c = Calendar.getInstance();
			java.util.Date utilDate = c.getTime();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			Time orderTime = new Time(sqlDate.getTime());
			Order order = new Order(orderNumbers, sqlDate, orderTime);
			order.addItem(item);
		}
		else{
			if (fm.getOrder(orderNumbers) == null){
				Calendar c = Calendar.getInstance();
				java.util.Date utilDate = c.getTime();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				Time orderTime = new Time(sqlDate.getTime());
				Order order = new Order(orderNumbers, sqlDate, orderTime);
				order.addItem(item);
			}
			else{
			fm.getOrder(orderNumbers).addItem(item);
			System.out.print("YES" + " (mc)");
			}
		}
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	

	public void removeItemFromOrder(Item item) throws InvalidInputException{
		String error = "";
		if (item == null)
			error = error + " Must select item to remove!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.getOrder(orderNumbers).removeItem(item);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	public void makeOrder() throws InvalidInputException{
		FoodTruckManager fm = FoodTruckManager.getInstance();
		String error = "";
		if (fm.getOrder(orderNumbers) == null)
			error = error + " Cannot make an order with no items!";
		if (!fm.getOrder(orderNumbers).hasItem())
			error = error + " Cannot make an order with no items!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		
		orderNumbers++;
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
}
