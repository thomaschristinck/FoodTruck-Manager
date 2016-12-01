package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Equipment;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceXStream;
/**
 * The EquipmentController class is responsible for creating/removing equipment, and adding/removing a quantity
 * of equipment to the inventory. The EquipmentController will also provide functionality for allowing the user to view 
 * the equipment list as a .txt file that they may re-format in a text editor. 
 * 
 *  
 * @author thomaschristinck
 *
 */

public class EquipmentController {
	public EquipmentController(){
	}
	
	/**
	 * The createEquipment method adds a the name of a new piece of equipment to the FoodTruckManagementSystem 
	 * database. This equipment will then be accessible by the user to remove, or adjust quantity.
	 * 
	 * @param equipmentName
	 * @throws InvalidInputException
	 */
	public void createEquipment(String equipmentName) throws InvalidInputException{
			String error = "";
			if (equipmentName == null || equipmentName.trim().length() == 0)
				error = error + " Equipment name cannot be empty!";
			
			error = error.trim();
			if(error.length() > 0)
				throw new InvalidInputException(error);
		
			
			Equipment equipment = new Equipment(equipmentName, 0);
			FoodTruckManager fm = FoodTruckManager.getInstance();
			fm.addEquipment(equipment);
			PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	/**
	 * The removeEquipment method deletes a piece of equipment previously entered into the system by a
	 * user. Equipment will be selected from a drop-down menu (see view package) to reduce risk of user 
	 * input error.
	 * 
	 * @param equipment
	 * @throws InvalidInputException
	 */
	public void removeEquipment(Equipment equipment) throws InvalidInputException{
		String error = "";
		if (equipment == null || equipment.toString().trim().length() == 0)
			error = error + " Must select equipment to remove!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
	
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.removeEquipment(equipment);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}

	/**
	 * The addToEquipmentInventoryMethod adds a valid specified quantity of a selected equipment to the
	 * food truck inventory. Quantity to add cannot be less than or equal to zero, and must be a valid integer.
	 * 
	 * @param equipment
	 * @param quantity
	 * @throws InvalidInputException
	 */
	public void addToEquipmentInventory(Equipment equipment, String quantity) throws InvalidInputException{
			String error = "";
			if (equipment == null)
				error = error + " Must select equipment!";
			try{
				int quant = Integer.parseInt(quantity);
				if (quant <= 0)
					error = error + " Equipment must have a quantity greater than 0 to be added!";
			}
			catch(NumberFormatException e){
				error = error + " Not a valid equipment quantity!";
			}
			error = error.trim();
			if(error.length() > 0)
				throw new InvalidInputException(error);
		
			
	
			FoodTruckManager fm = FoodTruckManager.getInstance();
			//Find supply and add the requested quantity to that supply
			int index = fm.indexOfEquipment(equipment);
			int ogQuantity = fm.getEquipment(index).getQuantity();
			fm.getEquipment(index).setQuantity(ogQuantity + Integer.parseInt(quantity));
			PersistenceXStream.saveToXMLwithXStream(fm);
	}
	/**
	 * The removeFromEquipmentInventory method removes a specified, valid quantity of a selected equipment from
	 * the food truck inventory. Quantity to remove cannot be less than or equal to 0, and cannot result in less
	 * than 0 of a type of equipment existing in the food truck inventory.
	 * 
	 * @param equipment
	 * @param quantity
	 * @throws InvalidInputException
	 */
	public void removeFromEquipmentInventory(Equipment equipment, String quantity) throws InvalidInputException{
		String error = "";
		if (equipment == null)
			error = error + " Must select equipment!";
		try{
			int quant = Integer.parseInt(quantity);
			if (quant <= 0)
				error = error + " Equipment must have a quantity greater than 0 to be removed!";
			if (equipment != null){
				if (equipment.getQuantity() - quant < 0)
					error = error + " Cannot have less than zero of a type of equipment!";
			}
		}
		catch(NumberFormatException e){
			error = error + " Not a valid equipment quantity!";
		}
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
	
		

		FoodTruckManager fm = FoodTruckManager.getInstance();
		//Find supply and subtract the requested quantity from that supply
		int index = fm.indexOfEquipment(equipment);
		int ogQuantity = fm.getEquipment(index).getQuantity();
		fm.getEquipment(index).setQuantity(ogQuantity - Integer.parseInt(quantity));
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	/**
	 * The viewEquipmentList method simply displays a list of all equipment in the food truck inventory, as
	 * well as their quantities.
	 * @throws InvalidInputException
	 */
	public void viewEquipmentList() throws InvalidInputException{
		FoodTruckManager fm = FoodTruckManager.getInstance();
		String error = " ";
		if(fm.getSupplies().size() == 0)
			error = error + " No equipment has been added!";

		try {
			File file = new File("equipmentlist.txt");

			 //If file doesn't exist, then create it
			 if (!file.exists()) {
				 file.createNewFile();
			 }

			 FileWriter fw = new FileWriter(file.getAbsoluteFile());
			 BufferedWriter out = new BufferedWriter(fw);
			 out.write("Equipment List");
			 out.newLine();
			 out.newLine();
			 String header = String.format("%-22s%-22s\n", "Equipment", "Quantity");
			 out.write(header);
			 out.newLine();
			 // List all equipment in food truck inventory
			 for(int i = 0; i < fm.getEquipment().size(); i++){
				 int index = i + 1;
				 String shift = String.format("%-22s%-22s%-22s\n", index + ". " + fm.getEquipment(i).getName(), fm.getEquipment(i).getQuantity());
				 out.write(shift); 
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
