<?php
require_once  'C:\Users\Julien\workspace3\FTMS2\persistence\PersistenceFoodTruckManager.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\FoodTruckManager.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Staff.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Shift.php';

class PersistenceFoodTruck extends PHPUnit_Framework_TestCase{
	
	protected $pm;
	
	protected function setUp()
	{
		$this->pm=new PersistenceFoodTruckManager();
	}
	
	protected function tearDown()
	{
		
	}
	
	public function testPersistence()
	{
		
		//1. create test data
		//staff
		$ftm= FoodTruckManager::getInstance();
		$staff = new Staff("Timmy","cook");
		$ftm->addStaff($staff);
		
		//shift
		$shift = new Shift("0900", "1300","01/01/2017" );
		$staff->addShift($shift);
		
		//2. Write all of data
		$this->pm->writeDataToStore($ftm);
		
		//3. clear data from mem
		$ftm->delete();
		$this->assertEquals(0,count($ftm->getStaffs()));
		
		//4. Load back in
		$ftm=$this->pm->loadDataFromStore();
		
		//5. check we got it back
		//staff
		$this->assertEquals(1,count($ftm->getStaffs()));
		$myStaff=$ftm->getStaff_index(0);
		$this->assertEquals("Timmy",$myStaff->getName());
		$this->assertEquals("cook",$myStaff->getRole());
		
		//shift
		$this->assertEquals(1,count($myStaff->getShift()));
		$myshift=$myStaff->getShift_index(0);
		$this->assertEquals("0900",$myshift->getStartTime());
		$this->assertEquals("1300",$myshift->getEndTime());
		$this->assertEquals("01/01/2017",$myshift->getShiftDate());
	
	}}