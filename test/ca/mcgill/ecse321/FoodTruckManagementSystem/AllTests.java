package ca.mcgill.ecse321.FoodTruckManagementSystem;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.FoodTruckManagementSystem.controller.TestEquipmentController;
import ca.mcgill.ecse321.FoodTruckManagementSystem.controller.TestMenuController;
import ca.mcgill.ecse321.FoodTruckManagementSystem.controller.TestStaffController;
import ca.mcgill.ecse321.FoodTruckManagementSystem.controller.TestSupplyController;
import ca.mcgill.ecse321.FoodTruckManagementSystem.persistence.TestPersistence;
//Test suite with all tests (see imported classes)
@RunWith(Suite.class)
@SuiteClasses({TestPersistence.class, TestStaffController.class, TestEquipmentController.class, TestSupplyController.class, TestMenuController.class })
public class AllTests {

}
