package ca.mcgill.ecse321.FoodTruckManagementSystem.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Equipment;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Item;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Order;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Shift;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Staff;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Supply;

public class PersistenceStaffController {
	private static void initializeXStream(){
		PersistenceXStream.setFilename("foodtruckmanager.xml");
		PersistenceXStream.setAlias("staff", Staff.class);
		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("supply", Supply.class);
		PersistenceXStream.setAlias("order", Order.class);
		PersistenceXStream.setAlias("item", Item.class);
		
		
	}
	
	public static void loadStaffControllerModel() {
		FoodTruckManager fm = FoodTruckManager.getInstance();
		PersistenceStaffController.initializeXStream();
		FoodTruckManager fm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		if (fm2 != null){
			//unfortunately, this creates a second copy of the RegistrationManager object though it is a singleton,
			//copy loaded model into singleton instance, as it will be used throughout the application
			Iterator<Staff> sIt = fm2.getStaffs().iterator();
			while(sIt.hasNext())
				fm.addStaff(sIt.next());
			Iterator<Shift> shIt = fm2.getShifts().iterator();
			while(shIt.hasNext())
				fm.addShift(shIt.next());
			Iterator<Supply> spIt = fm2.getSupplies().iterator();
			while(spIt.hasNext())
				fm.addSupply(spIt.next());
			Iterator<Equipment> eIt = fm2.getEquipment().iterator();
			while(eIt.hasNext())
				fm.addEquipment(eIt.next());
			Iterator<Order> oIt = fm2.getOrders().iterator();
			while(oIt.hasNext())
				fm.addOrder(oIt.next());
			Iterator<Item> iIt = fm2.getItems().iterator();
			while(iIt.hasNext())
				fm.addItem(iIt.next());
		}
	}
}

