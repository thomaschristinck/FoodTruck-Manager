package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Equipment;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceXStream;


public class EquipmentController {
	public EquipmentController(){
	}
	
	//Comment:
	public void createEquipment(String equipmentName, String quantity) throws InvalidInputException{
			String error = "";
			if (equipmentName == null || equipmentName.trim().length() == 0)
				error = error + " Equipment name cannot be empty!";
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
		
			
			Equipment equipment = new Equipment(equipmentName, Integer.parseInt(quantity));
			FoodTruckManager fm = FoodTruckManager.getInstance();
			fm.addEquipment(equipment);
			PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
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
		//Find supply and subtract the requested quantity to that supply
		int index = fm.indexOfEquipment(equipment);
		int ogQuantity = fm.getEquipment(index).getQuantity();
		fm.getEquipment(index).setQuantity(ogQuantity - Integer.parseInt(quantity));
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
}
