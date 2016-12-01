package ca.mcgill.ecse321.FoodTruckManagementSystem.persistence;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Staff;


public class TestPersistence {


	@Before
	public void setUp() throws Exception {
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		//Create staff members
		Staff sf1 = new Staff("Martin Picard", "Chef");
		Staff sf2 = new Staff("Nathalie Vincent", "Manager");
		
		//Add to instance of FoodTruckManager
		fm.addStaff(sf1);
		fm.addStaff(sf2);
		
	}

	@After
	public void tearDown() throws Exception {
		//Clear the manager
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.delete();
	}

	@Test
	public void test() {
		//Save model
		FoodTruckManager fm = FoodTruckManager.getInstance();
		PersistenceXStream.setFilename("test"+File.separator+"ca"+File.separator+"mcgill"+File.separator+"ecse321"+File.separator+"FoodTruckManagementSystem"+File.separator+
				"persistence"+File.separator+"data.xml");
		PersistenceXStream.setAlias("staff", Staff.class);
		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
		if(!PersistenceXStream.saveToXMLwithXStream(fm))
			fail("Could not save file.");
		
		//Clear the model in memory
		fm.delete();
		assertEquals(0, fm.getStaffs().size());
		
		//Load model
		fm = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		if (fm == null)
			fail("Could not load the file.");
		
		// Check staff were saved 
		assertEquals(2, fm.getStaffs().size());
		assertEquals("Martin Picard", fm.getStaff(0).getName());
		assertEquals("Chef", fm.getStaff(0).getRole());
		assertEquals("Nathalie Vincent", fm.getStaff(1).getName());
		assertEquals("Manager", fm.getStaff(1).getRole());
	}

}
