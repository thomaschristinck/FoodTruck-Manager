package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Supply;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceXStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * The SupplyController class is responsible for creating/removing supplies, and adding/removing a quantity
 * of a supply to the inventory. The SupplyController will also provide functionality for allowing the user to view 
 * the supply list as a .txt file that they may re-format in a text editor. 
 * 
 * Note: A small problem is the best before date implementation. The user should have the option of updating the best 
 * before date of a supply, to avoid problems with the listing of menu item ingredients. 
 *  
 * @author thomaschristinck
 * @version 1.0
 *
 */
public class SupplyController {
	public SupplyController(){
	}
	
	/**
	 * The createSupply method creates a supply on the food truck inventory supply list. Note the default 
	 * quantity is zero. The manager then has to add/remove an appropriate quantity. If the supply's best before 
	 * date has passed, an error message will appear. 
	 * 
	 * @param supplyName
	 * @param quantity
	 * @param bestBefore
	 * @throws InvalidInputException
	 */
	public void createSupply(String supplyName, Date bestBefore) throws InvalidInputException{
			String error = "";
			if (supplyName == null || supplyName.trim().length() == 0)
				error = error + " Supply name cannot be empty!";
			if  (bestBefore == null) 
				error = error + " Best before date must be entered!";
			Calendar c = Calendar.getInstance();
			java.util.Date utilDate = c.getTime();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			if  (bestBefore != null) {
				if (bestBefore.getTime() < sqlDate.getTime())
					error = error + " Supply has already expired!";
			}
			error = error.trim();
			if(error.length() > 0)
				throw new InvalidInputException(error);
		
			
			Supply supply = new Supply(supplyName, 0, bestBefore);
			FoodTruckManager fm = FoodTruckManager.getInstance();
			fm.addSupply(supply);
			PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	/**
	 * The removeSupply method removes a supply specified by the user from the list of supplies.
	 * This supply will no longer appear on the inventory list. Since the supply to be removed will 
	 * be selected from a drop-down menu, the only possible input error is a null supply. Another error
	 * is the case where the user attempts to delete a supply that is listed as an ingredient for a
	 * menu item.
	 * 
	 * @param supply
	 * @throws InvalidInputException
	 */
	public void removeSupply(Supply supply) throws InvalidInputException{
		String error = "";
		FoodTruckManager fm = FoodTruckManager.getInstance();
		for(int i = 0; i < fm.getItems().size(); i++){
			if(fm.getItem(i).indexOfSupply(supply) != -1){
				error = error + " Cannot remove supply! " + fm.getItem(i).getName() + " uses this supply!";
				break;
			}
		}
		if (supply == null || supply.toString().trim().length() == 0)
			error = error + " Must select supply to remove!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		fm.removeSupply(supply);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}

	/**
	 * The addToSupplyInventory method allows the user to specify a quantity to add to a certain supply.
	 * If the food truck has a supply and a shipment of that supply comes in, the manager can adjust the quantity
	 * of that supply. The quantity to remove must be positive, and the manager cannot remove more supplies than what 
	 * they have. This will produce an error message
	 * 
	 * @param supply
	 * @param quantity
	 * @throws InvalidInputException
	 */
	public void addToSupplyInventory(Supply supply, String quantity) throws InvalidInputException{
			String error = "";
			if (supply == null)
				error = error + " Must select supply!";
			try{
				int quant = Integer.parseInt(quantity);
				if (quant <= 0)
					error = error + " Supply must have a quantity greater than 0 to be added!";
			}
			catch(NumberFormatException e){
				error = error + " Not a valid supply quantity!";
			}
			error = error.trim();
			if(error.length() > 0)
				throw new InvalidInputException(error);
		
			
	
			FoodTruckManager fm = FoodTruckManager.getInstance();
			//Find supply and add the requested quantity to that supply
			int index = fm.indexOfSupply(supply);
			int ogQuantity = fm.getSupply(index).getQuantity();
			fm.getSupply(index).setQuantity(ogQuantity + Integer.parseInt(quantity));
			PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	/**
	 * The removeSupplyFromInventory method is similar to the above, only instead of adding a specified quantity to 
	 * the food truck inventory, this method will remove the quantity from the inventory.
	 * 
	 *  @param supply
	 *  @param quantity
	 *  @throws InvalidInputException
	 *    */
	public void removeFromSupplyInventory(Supply supply, String quantity) throws InvalidInputException{
		String error = "";
		if (supply == null)
			error = error + " Must select supply!";
		try{
			int quant = Integer.parseInt(quantity);
			if (quant <= 0)
				error = error + " Supply must have a quantity greater than 0 to be removed!";
			if (supply != null){
				if (supply.getQuantity() - quant < 0)
					error = error + " Cannot have less than zero supplies!";
			}
		}
		catch(NumberFormatException e){
			error = error + " Not a valid supply quantity!";
		}
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);

		FoodTruckManager fm = FoodTruckManager.getInstance();
		//Find supply and subtract the requested quantity from that supply
		int index = fm.indexOfSupply(supply);
		int ogQuantity = fm.getSupply(index).getQuantity();
		fm.getSupply(index).setQuantity(ogQuantity - Integer.parseInt(quantity));
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	/**
	 * The viewSupplyList method simply displays a list of all supplies in the food truck inventory, as
	 * well as their quantities and best-before dates.
	 * 
	 * @throws InvalidInputException
	 */
	public void viewSupplyList() throws InvalidInputException{
		FoodTruckManager fm = FoodTruckManager.getInstance();
		String error = " ";
		if(fm.getSupplies().size() == 0)
			error = error + " No supplies have been added!";

		try {
			File file = new File("supplylist.txt");

			 //If file doesn't exist, then create it
			 if (!file.exists()) {
				 file.createNewFile();
			 }

			 FileWriter fw = new FileWriter(file.getAbsoluteFile());
			 BufferedWriter out = new BufferedWriter(fw);
			 out.write("Supply List");
			 out.newLine();
			 out.newLine();
			 String header = String.format("%-22s%-22s%-22s\n", "Supply", "Quantity", "Best-Before Date");
			 out.write(header);
			 out.newLine();
			 // List all supplies in food truck inventory
			 for(int i = 0; i < fm.getSupplies().size(); i++){
				 int index = i + 1;
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				 String formattedDate = formatter.format(fm.getSupply(i).getBestBefore());
				 String supplyList = String.format("%-22s%-22s%-22s\n", index + ". " + fm.getSupply(i).getName(), fm.getSupply(i).getQuantity(), formattedDate);
				 out.write(supplyList); 
				 out.newLine();
				 out.newLine();
			 }
			 out.close();
			 java.awt.Desktop.getDesktop().edit(file);
		 } catch (IOException e) {
			 throw new InvalidInputException("ERROR CREATING FILE");
		 }
	}
}
