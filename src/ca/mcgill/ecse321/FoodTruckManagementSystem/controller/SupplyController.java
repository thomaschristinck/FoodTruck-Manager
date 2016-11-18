package ca.mcgill.ecse321.FoodTruckManagementSystem.controller;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Equipment;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Supply;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceXStream;

import java.sql.Date;
import java.util.Calendar;

public class SupplyController {
	public SupplyController(){
	}

	public void createSupply(String supplyName, String quantity, Date bestBefore) throws InvalidInputException{
			String error = "";
			if (supplyName == null || supplyName.trim().length() == 0)
				error = error + " Supply name cannot be empty!";
			try{
				int quant = Integer.parseInt(quantity);
				if (quant <= 0)
					error = error + " Supply must have a quantity greater than 0 to be added!";
			}
			catch(NumberFormatException e){
				error = error + " Not a valid supply quantity!";
			}
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
		
			
			Supply supply = new Supply(supplyName, Integer.parseInt(quantity), bestBefore);
			FoodTruckManager fm = FoodTruckManager.getInstance();
			fm.addSupply(supply);
			PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	public void removeSupply(Supply supply) throws InvalidInputException{
		String error = "";
		if (supply == null || supply.toString().trim().length() == 0)
			error = error + " Must select supply to remove!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
	
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.removeSupply(supply);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}

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
	
	public void removeFromSupplyInventory(Supply supply, String quantity) throws InvalidInputException{
		String error = "";
		if (supply == null)
			error = error + " Must select supply!";
		try{
			int quant = Integer.parseInt(quantity);
			if (quant <= 0){
				error = error + " Supply must have a quantity greater than 0 to be removed!";
				if (supply != null){
					if (supply.getQuantity() - quant < 0)
						error = error + " Cannot remove supply from inventory that does not exist!";
				}
			}
		}
		catch(NumberFormatException e){
			error = error + " Not a valid supply quantity!";
		}
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
	
		

		FoodTruckManager fm = FoodTruckManager.getInstance();
		//Find supply and subtract the requested quantity to that supply
		int index = fm.indexOfSupply(supply);
		int ogQuantity = fm.getSupply(index).getQuantity();
		fm.getSupply(index).setQuantity(ogQuantity - Integer.parseInt(quantity));
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
}
