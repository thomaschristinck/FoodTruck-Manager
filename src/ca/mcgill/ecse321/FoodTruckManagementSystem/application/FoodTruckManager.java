package ca.mcgill.ecse321.FoodTruckManagementSystem.application;

import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.PersistenceController;

import ca.mcgill.ecse321.FoodTruckManagementSystem.view.MainMenu;
/**
 * This is the main application class. At launch, the model saved through persistence is loaded, and
 * the User Interface runs (only the main menu page is set visible. Other pages are set visible through
 * the MainMenu page).
 * 
 * @author thomaschristinck
 * @version 1.0
 */
public class FoodTruckManager {
	public static void main(String[] args) {
		///load model
		PersistenceController.loadSupplyControllerModel();
		// Start UI
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run(){
				new MainMenu().setVisible(true);
			}
			
		});

	}
}
