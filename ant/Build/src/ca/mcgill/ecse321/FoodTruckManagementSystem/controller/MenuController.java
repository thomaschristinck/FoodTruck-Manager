package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

	public void makeOrder(Item item) throws InvalidInputException{
		String error = "";
		if (item == null)
			error = error + " Must select item to add!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		int orderNumber = fm.getOrders().size();
		Calendar c = Calendar.getInstance();
		java.util.Date utilDate = c.getTime();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		Time orderTime = new Time(sqlDate.getTime());
		Order order = new Order(orderNumber, sqlDate, orderTime);
		order.addItem(item);
		fm.addOrder(order);
	
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	

	public void removeOrder() throws InvalidInputException{
		FoodTruckManager fm = FoodTruckManager.getInstance();
		String error = "";
		if (fm.getOrders().size() == 0)
			error = error + " No orders have been made!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		int orderNumber = fm.getOrders().size() - 1;
		fm.removeOrder(fm.getOrder(orderNumber));
		
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	
	public void viewMenu() throws IOException{
		 try {
			 FoodTruckManager fm = FoodTruckManager.getInstance();
			 File file = new File("filename.txt");

			 //If file doesn't exist, then create it
			 if (!file.exists()) {
				 file.createNewFile();
			 }

			 FileWriter fw = new FileWriter(file.getAbsoluteFile());
			 BufferedWriter out = new BufferedWriter(fw);
			 out.write("\t\t\t\t\t" + "Menu");
			 out.newLine();
			 out.newLine();
			 for (int i = 0; i < fm.getItems().size(); i++) {
				 int itemNumber = i + 1;
				 out.write(itemNumber + ". " + fm.getItem(i).getName());
				 out.newLine();
				 out.newLine();
				 out.write(fm.getItem(i).getDescription());
				 out.newLine(); 
				 out.newLine();
				 out.newLine();
			 }
			 out.close();
			 java.awt.Desktop.getDesktop().edit(file);
		 } catch (IOException e) {
			 throw new IOException(e);
		 }
	}
}
