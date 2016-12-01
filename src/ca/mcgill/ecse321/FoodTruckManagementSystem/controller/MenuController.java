package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
 * @version 1.0
 */
public class MenuController {
	public MenuController(){
	}
	
	/** 
	 * The addSupply method is responsible for adding a supply to an item. Note that it is
	 * permissible to add an item that is out of stock (quantity = 0) to an item, but it will not
	 * be permissible to order an item if the item uses a supply that is out of stock.
	 * 
	 * @param supply
	 * @param item
	 * @throws InvalidInputException
	 */
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
	
	/**
	 * The removeSuppply method essentially reverses the operation in the addSupply method,
	 * i.e. it removes a supply listed as an ingredient in an item. Note that a supply must be listed 
	 * for an item in order to be removed.
	 * 
	 * @param supply
	 * @param item
	 * @throws InvalidInputException
	 */
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
	
	/**
	 * The createItem class makes a new item, with a name and description. The user can enter the
	 * price in the description. If the user wishes to change the description, the item will have to
	 * be removed and a new item with the updated description will be created. Possibly add an edit feature
	 * in the next release.
	 *  
	 * @param itemName
	 * @param itemDescription
	 * @throws InvalidInputException
	 */
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
	/**
	 * The removeItem class removes an item listed in the FoodTruckManager. The user will
	 * select the item from a drop-down list of all items, and so the only possible invalid input 
	 * is null.
	 * 
	 * @param item
	 * @throws InvalidInputException
	 */
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

	/**
	 * The makeOrder class creates a new Order object. Each order has one item, and so if
	 * a customer orders multiples of an item, the user will have to make each order sequentially.
	 * Although this is quite simple (make an order with the selection of an item and the press of
	 * a button), it may be worth adding functionality to add the quantity of an item being ordered in
	 * the future.
	 * 
	 * Note that when an order is made, the user will be provided with feedback. If the item being ordered 
	 * is made with an ingredient that is out of stock, the order will fail and an error message will be 
	 * displayed. If the item being ordered has expired, the item will still be ordered but the user will 
	 * receive a warning message.
	 * 
	 * @param item
	 * @return
	 * @throws InvalidInputException
	 */
	public String makeOrder(Item item) throws InvalidInputException{
		FoodTruckManager fm = FoodTruckManager.getInstance();
		String error = "";
		if (item == null)
			error = error + " Must select item to add!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		if (item != null){
			String message = "";
			int index = fm.indexOfItem(item);
			
			//Ensure all supplies are in stock
			for (int i = 0; i < fm.getItem(index).getSupply().size(); i++){
				if(fm.getItem(index).getSupply(i).getQuantity() == 0){
					error = error + " Cannot make order! " + fm.getItem(index).getSupply(i).getName() + " out of stock!" ;
				}
			}
			
			//Ensure no items have expired
			Calendar c = Calendar.getInstance();
			java.util.Date utilDate = c.getTime();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			for (int i = 0; i < fm.getItem(index).getSupply().size(); i++){
				if (fm.getItem(index).getSupply(i).getBestBefore().getTime() < sqlDate.getTime())
					message = " WARNING! " + fm.getItem(index).getSupply(i).getName() + " has expired!";
				}	
			error = error.trim();
			if(error.length() > 0)
				throw new InvalidInputException(error);
		
			int orderNumber = fm.getOrders().size();
			Time orderTime = new Time(sqlDate.getTime());
			Order order = new Order(orderNumber, sqlDate, orderTime);
			order.addItemAt(item, 0);
			message = order.getItem(0).getName() + " was ordered." + message;
			fm.addOrder(order);
	
			PersistenceXStream.saveToXMLwithXStream(fm);
			message.trim();
			return message;
		}
		return "";
	}
	
	/**
	 * The removeOrder method basically undoes the last order that was made. Since the orders made
	 * is a list, it just removes the most recent item on the list. In this sense, a user could go 
	 * back any number of orders if necessary.
	 * 
	 * Note that if the user removes all orders that have been made, and attempt to remove another, the user
	 * will receive an error message. The user will receive feedback after every successful order removal, including
	 * the ordered item that was removed.
	 * 
	 * @return
	 * @throws InvalidInputException
	 */
	public String removeOrder() throws InvalidInputException{
		FoodTruckManager fm = FoodTruckManager.getInstance();
		String error = "";
		if (fm.getOrders().size() == 0)
			error = error + " No orders have been made!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		int orderNumber = fm.getOrders().size() - 1;
		String message = "The last order (" + fm.getOrder(orderNumber).getItem(0).getName() + ") was removed.";
		fm.removeOrder(fm.getOrder(orderNumber));
		
		PersistenceXStream.saveToXMLwithXStream(fm);
		return message;
	}
	
	/**
	 * The viewMenu method displays a list of all items and their descriptions. It is displayed in the form
	 * of a .txt file which the user can copy/paste into a text editor of his or her choosing. The idea is that
	 * formatting is a personal choice, and so the manager should have freedom over how the menu is formatted,
	 * but the typing work will be done by this method.
	 * 
	 * @throws IOException
	 */
	public void viewMenu() throws IOException{
		 try {
			 FoodTruckManager fm = FoodTruckManager.getInstance();
			 File file = new File("menu.txt");

			 //If file doesn't exist, then create it
			 if (!file.exists()) {
				 file.createNewFile();
			 }

			 FileWriter fw = new FileWriter(file.getAbsoluteFile());
			 BufferedWriter out = new BufferedWriter(fw);
			 out.write("\t\t\t\t\t" + "Menu");
			 out.newLine();
			 out.newLine();
			 //Write a numbered list of each item and its decription
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
	
	/**
	 * The viewStatistics method displays a summary of the food truck's menu. First, a list of
	 * each item and the ingredients it is made with is displayed. A horizontal line separates 
	 * this list from a list of all items on the food truck menu ordered from most to least popular. 
	 * The number of times each item has been ordered will also be displayed.
	 * 
	 * In future versions, this method may write to an excel file and display a graph. For now, data 
	 * about the menu will be displayed in a .txt file.
	 * 
	 * @throws IOException
	 */
	public void viewStatistics() throws IOException{
		 try {
			 FoodTruckManager fm = FoodTruckManager.getInstance();
			 File file = new File("statistics.txt");

			 //If file doesn't exist, then create it
			 if (!file.exists()) {
				 file.createNewFile();
			 }

			 FileWriter fw = new FileWriter(file.getAbsoluteFile());
			 BufferedWriter out = new BufferedWriter(fw);
			 out.write("\t\t\t\t\t" + "Statistics");
			 out.newLine();
			 out.newLine();
			 out.write("Item List (With Ingredients Listed)");
			 out.newLine();
			 out.newLine();
			 /*Write numbered list of items with all ingredients listed. If the item has no ingredients listed, a
			 warning message will be displayed.*/
			 for (int i = 0; i < fm.getItems().size(); i++) {
				 int itemNumber = i + 1;
				 out.write(itemNumber + ". " + fm.getItem(i).getName());
				 out.newLine(); 
				 out.newLine();
				 if (fm.getItem(i).hasSupply()){
					 out.write("Ingredients:");
					 out.newLine();
					 for(int j = 0; j < fm.getItem(i).getSupply().size(); j++){
						 out.write(fm.getItem(i).getSupply(j).getName());
						 out.newLine();
					 }
				 }
				 else{
					 out.write("WARNING! Item does not have supplies on ingredients list!");
					 out.newLine(); 
				 }
				 out.newLine();
			 }
			 out.write(" _____________________________________________________________________");
			 out.newLine();
			 out.newLine();
			 out.write("Menu Item Statistics (Ranked by Popularity)");
			 out.newLine();
			 
			 //Write list of menu items ordered, ranked in order from most to least popular.
			 int orderNumber[] = new int[fm.getItems().size()];
			 Item item[] = new Item[fm.getItems().size()];
			 for(int j = 0; j < fm.getItems().size(); j++){
				 for (int i = 0; i < fm.getOrders().size(); i++) {
					 /*Check if ordered item matches the nth menu item. The jth place in orderNumber corresponds to
					  * the number of times the jth item has been ordered.*/
					 if(fm.getOrder(i).getItem(0) == fm.getItem(j)){
					 	orderNumber[j]++;
					 }
				 }
				 item[j] = fm.getItem(j);
			 }
			 
			  /* Sort data. Loop through orderNumber[]. If the j - 1th entry is less than the jth entry, then put j-1th item into the
			  * jth place in item[] array. (The orderNumber[] array is sorted correspondingly).*/
			 int temp;
			 Item tempItem;
			 for(int i = 0; i < orderNumber.length; i++){
				 for (int j = 1; j < orderNumber.length - i; j++){
					 if (orderNumber[j-1] <= orderNumber[j]){
						 temp = orderNumber[j-1];
						 orderNumber[j-1] = orderNumber[j];
						 orderNumber[j] = temp;
						 tempItem = item[j-1];
						 item[j - 1] = item[j];
						 item[j] = tempItem;
					 }
				 
				 }
			 }
			 String header = String.format("%-28s%-28s\n", "Item", "Times Ordered");
			 out.newLine();
			 out.write(header);
			 out.newLine();
			 for(int j = 0; j < orderNumber.length; j++){
				 String ranking = String.format("%-28s%-28s\n", "" + Integer.toString(j + 1) + ". " + item[j].getName(), "" + Integer.toString(orderNumber[j]));
				 out.write(ranking);
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
