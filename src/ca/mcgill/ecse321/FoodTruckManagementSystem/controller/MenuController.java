package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Item;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceXStream;

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

}
