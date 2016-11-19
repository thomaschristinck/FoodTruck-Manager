package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Item;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceXStream;

public class MenuController {
	public MenuController(){
	}
	public void createItem(String itemName, String quantity) throws InvalidInputException{
		String error = "";
		if (itemName == null || itemName.trim().length() == 0)
			error = error + " Item name cannot be empty!";
		try{
			int quantityInt = Integer.parseInt(quantity);
			if (quantityInt <= 0)
				error = error + " Item must have a quantity greater than 0 to be added!";
		}
		catch(NumberFormatException e){
			error = error + " Not a valid item quantity!";
		}
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
	
		Item item = new Item(itemName);
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.addItem(item);
		PersistenceXStream.saveToXMLwithXStream(fm);
}

}
