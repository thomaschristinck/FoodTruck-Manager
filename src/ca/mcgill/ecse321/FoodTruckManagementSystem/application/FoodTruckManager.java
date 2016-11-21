package ca.mcgill.ecse321.FoodTruckManagementSystem.application;

import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceStaffController;
import ca.mcgill.ecse321.FoodTruckManagementSystem.view.InventoryPage;
import ca.mcgill.ecse321.FoodTruckManagementSystem.view.MenuPage;
import ca.mcgill.ecse321.FoodTruckManagementSystem.view.StaffPage;

public class FoodTruckManager {
	public static void main(String[] args) {
		///load model
		PersistenceStaffController.loadStaffControllerModel();
		// Start UI
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run(){
				new StaffPage().setVisible(true);
				new InventoryPage().setVisible(true);
				new MenuPage().setVisible(true);
			}
			
		});

	}
}
