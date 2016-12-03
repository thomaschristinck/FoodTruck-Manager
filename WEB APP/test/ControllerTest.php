<?php
require_once 'C:\Users\Julien\workspace3\FTMS2\controller\Controller.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\persistence\PersistenceFoodTruckManager.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Staff.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\FoodTruckManager.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Supply.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Order.php';

class ControllerTest extends PHPUnit_Framework_TestCase
{
    protected $c;
    protected $pm;
    protected $ftm;

    protected function setUp()
    {
        $this->c = new Controller();
        $this->pm = new PersistenceFoodTruckManager();
        $this->ftm = $this->pm->loadDataFromStore();
        $this->ftm->delete();
        $this->pm->writeDataToStore($this->ftm);
    }

    protected function tearDown()
    {
    }
//Staff Tests

    public function testCreateStaff() {
        $this->assertEquals(0, count($this->ftm->getStaffs()));
    
    	$name = "Oscar";
    	$role = "cook";
    
    	
    	try {
    		$this->c->createStaff($name,$role);
    	} catch (Exception $e) {
    		// check that no error occurred
    		$this->fail();
    	}
    
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(1, count($this->ftm->getStaffs()));
    	$this->assertEquals($name, $this->ftm->getStaff_index(0)->getName());
    	$this->assertEquals($role, $this->ftm->getStaff_index(0)->getRole());
    	 
    }
    
    public function testCreateStaffNameNull() {
        $this->assertEquals(0, count($this->ftm->getStaffs()));
    
    	$name = null;
    	$role="cook";
    
    	$error = "";
    	try {
    		$this->c->createStaff($name,$role);
    	} catch (Exception $e) {
			$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Staff name cannot be empty!", $error);
        // check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getStaffs()));
    }
    
    public function testCreateStaffNameEmpty() {
    	$this->assertEquals(0, count($this->ftm->getStaffs()));
    
    	$name = "";
    	$role = "cook";
    
    	$error = "";
    	try {
    		$this->c->createStaff($name,$role);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Staff name cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getStaffs()));
    }
    
    public function testCreateStaffNameSpaces() {
    	$this->assertEquals(0, count($this->ftm->getStaffs()));
    
    	$name = " ";
    	$role="cook";
    
    	$error = "";
    	try {
    		$this->c->createStaff($name,$role);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Staff name cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getStaffs()));
    }
    
    public function testCreateStaffRoleNull() {
    	$this->assertEquals(0, count($this->ftm->getStaffs()));
    
    	$name = "mike";
    	$role=null;
    
    	$error = "";
    	try {
    		$this->c->createStaff($name,$role);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Staff role cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getStaffs()));
    }
    
    public function testCreateStaffRoleEmpty() {
    	$this->assertEquals(0, count($this->ftm->getStaffs()));
    
    	$name = "mike";
    	$role= "";
    
    	$error = "";
    	try {
    		$this->c->createStaff($name,$role);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Staff role cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getStaffs()));
    }
    
    public function testCreateStaffRoleSpaces() {
    	$this->assertEquals(0, count($this->ftm->getStaffs()));
    
    	$name = "mike";
    	$role="  ";
    
    	$error = "";
    	try {
    		$this->c->createStaff($name,$role);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Staff role cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getStaffs()));
    }
    
// Shift tests

    public function testCreateShift() {
    	$this->assertEquals(0, count($this->ftm->getShifts()));
        
    	$start = "0010";
    	$end = "0200";
    	$date="01/01/2017";
    
    	 
    	try {
    		$this->c->createShift($start,$end,$date);
    	} catch (Exception $e) {
    		// check that no error occurred
    		$this->fail();
    	}
    
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(1, count($this->ftm->getShifts()));
    	$this->assertEquals($start, $this->ftm->getShift_index(0)->getStartTime());
    	$this->assertEquals($end, $this->ftm->getShift_index(0)->getEndTime());
    	$this->assertEquals($date, $this->ftm->getShift_index(0)->getShiftDate());
    	 
    
    }
    
    public function testCreateShiftStartNull() {
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    
    	$start = null;
    	$end="0500";
    	$date="01/01/2016";
    
    	$error = "";
    	try {
    		$this->c->createShift($start,$end,$date);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Shift start time cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateShiftStartEmpty() {
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    
    	$start = "";
    	$end="0500";
    	$date="01/01/2016";
    
    	$error = "";
    	try {
    		$this->c->createShift($start,$end,$date);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Shift start time cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateShiftStartSpaces() {
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    
    	$start = "   ";
    	$end="0500";
    	$date="01/01/2016";
    
    	$error = "";
    	try {
    		$this->c->createShift($start,$end,$date);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Shift start time cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateShiftStartHourUnselected()
    {	$this->assertEquals(0, count($this->ftm->getShifts()));
    	$start="-1:00";
    	$end="10:30";
    	$date="06/06/2016";
    	
    	$error = "";
    	try {
    		$this->c->createShift($start,$end,$date);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    	
    	// check error
    	$this->assertEquals("Select Start Hour!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateShiftStartMinUnselected()
    {	$this->assertEquals(0, count($this->ftm->getShifts()));
    $start="00:-1";
    $end="10:30";
    $date="06/06/2016";
     
    $error = "";
    try {
    	$this->c->createShift($start,$end,$date);
    } catch (Exception $e) {
    	$error = $e->getMessage();
    }
     
    // check error
    $this->assertEquals("Select Start Minutes!", $error);
    // check file contents
    $this->ftm = $this->pm->loadDataFromStore();
    $this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateShiftEndHourUnselected()
    {	$this->assertEquals(0, count($this->ftm->getShifts()));
    $start="10:10";
    $end="-1:30";
    $date="06/06/2016";
     
    $error = "";
    try {
    	$this->c->createShift($start,$end,$date);
    } catch (Exception $e) {
    	$error = $e->getMessage();
    }
     
    // check error
    $this->assertEquals("Select End Hour!", $error);
    // check file contents
    $this->ftm = $this->pm->loadDataFromStore();
    $this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateShiftEndMinUnselected()
    {	$this->assertEquals(0, count($this->ftm->getShifts()));
    $start="10:00";
    $end="10:-1";
    $date="06/06/2016";
     
    $error = "";
    try {
    	$this->c->createShift($start,$end,$date);
    } catch (Exception $e) {
    	$error = $e->getMessage();
    }
     
    // check error
    $this->assertEquals("Select End Minutes!", $error);
    // check file contents
    $this->ftm = $this->pm->loadDataFromStore();
    $this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateShiftMonthUnselected()
    {	$this->assertEquals(0, count($this->ftm->getShifts()));
    $start="10:10";
    $end="10:30";
    $date="-1/06/2016";
     
    $error = "";
    try {
    	$this->c->createShift($start,$end,$date);
    } catch (Exception $e) {
    	$error = $e->getMessage();
    }
     
    // check error
    $this->assertEquals("Select Month!", $error);
    // check file contents
    $this->ftm = $this->pm->loadDataFromStore();
    $this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateShiftDayUnselected()
    {	$this->assertEquals(0, count($this->ftm->getShifts()));
    $start="10:10";
    $end="10:30";
    $date="01/-1/2016";
     
    $error = "";
    try {
    	$this->c->createShift($start,$end,$date);
    } catch (Exception $e) {
    	$error = $e->getMessage();
    }
     
    // check error
    $this->assertEquals("Select Day!", $error);
    // check file contents
    $this->ftm = $this->pm->loadDataFromStore();
    $this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateShiftYearUnselected()
    {	$this->assertEquals(0, count($this->ftm->getShifts()));
    $start="10:10";
    $end="10:30";
    $date="01/06/-1";
     
    $error = "";
    try {
    	$this->c->createShift($start,$end,$date);
    } catch (Exception $e) {
    	$error = $e->getMessage();
    }
     
    // check error
    $this->assertEquals("Select Year!", $error);
    // check file contents
    $this->ftm = $this->pm->loadDataFromStore();
    $this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    
    public function testCreateShiftEndNull() {
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    
    	$start = "0100";
    	$end=null;
    	$date="01/01/2016";
    
    	$error = "";
    	try {
    		$this->c->createShift($start,$end,$date);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Shift end time cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateShiftEndEmpty() {
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    
    	$start = "0100";
    	$end="";
    	$date="01/01/2016";
    
    	$error = "";
    	try {
    		$this->c->createShift($start,$end,$date);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Shift end time cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateShiftEndSpaces() {
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    
    	$start = "0100";
    	$end="    ";
    	$date="01/01/2016";
    
    	$error = "";
    	try {
    		$this->c->createShift($start,$end,$date);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Shift end time cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateShiftDateNull() {
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    
    	$start = "0100";
    	$end="0200";
    	$date=null;
    
    	$error = "";
    	try {
    		$this->c->createShift($start,$end,$date);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Shift date cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateShiftDateEmpty() {
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    
    	$start = "0100";
    	$end="0200";
    	$date="";
    
    	$error = "";
    	try {
    		$this->c->createShift($start,$end,$date);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Shift date cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    }
   
    public function testCreateShiftDateSpaces() {
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    
    	$start = "0100";
    	$end="0200";
    	$date="    ";
    
    	$error = "";
    	try {
    		$this->c->createShift($start,$end,$date);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Shift date cannot be empty!", $error);
    	// check file contents
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->ftm->getShifts()));
    }
    
    public function testCreateSupply()
    {
    	$this->assertEquals(0, count($this->ftm->getSupplies()));
    	$error="";
    	try {
    		$this->c->CreateSupply("fries","3","01/01/2017");
    		
    	} catch (Exception $e) {
    		$error=$e->getMessage();
    	}
    	$this->assertEquals($error,"");
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(1,count($this->ftm->getSupplies()));
    	$this->assertEquals("fries",$this->ftm->getSupply_index(0)->getName());
    	$this->assertEquals("3",$this->ftm->getSupply_index(0)->getQuantity());
    	$this->assertEquals("01/01/2017",$this->ftm->getSupply_index(0)->getBestBefore());
    	
    }
    
    public function testCreateEquipment()
    {
    	$this->assertEquals(0, count($this->ftm->getEquipment()));
    	$error="";
    	try {
    		$this->c->createEquipment("pans","3");
    
    	} catch (Exception $e) {
    		$error=$e->getMessage();
    	}
    	$this->assertEquals($error,"");
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(1,count($this->ftm->getEquipment()));
    	$this->assertEquals("pans",$this->ftm->getEquipment_index(0)->getName());
    	$this->assertEquals("3",$this->ftm->getEquipment_index(0)->getQuantity());
    	
    	 
    }
    
    public function testCreateItem()
    {
    	$this->assertEquals(0, count($this->ftm->getItems()));
    	$error="i";
    	try {
    		$this->c->CreateSupply("fries","3","01/01/2017");
    	
    	} catch (Exception $e) {
    		$error=$e->getMessage();
    	}
    	try {
    		$this->c->CreateSupply("burgers","3","01/01/2017");
    	
    	} catch (Exception $e) {
    		$error=$e->getMessage();
    	}
    	
    	$this->ftm = $this->pm->loadDataFromStore();
    	$supply1=$this->ftm->getSupply_index(0);
    	$supply2=$this->ftm->getSupply_index(1);
    	$array = array($supply1,$supply2);
    	try {
    		$this->c->createItem("burger",$array);
    	} catch (Exception $e) {
    		$error=$e->getMessage();
    	}
    	$this->assertEquals($error,"i");
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(1,count($this->ftm->getItems()));
    	$this->assertEquals("burger",$this->ftm->getItem_index(0)->getName());
    	$item=$this->ftm->getItem_index(0);
    	$this->assertEquals("burgers",$item->getSupply_index(1)->getName());
    	 
    
    }
    
   public function testCreateOrder()
    {	$this->assertEquals(0, count($this->ftm->getItems()));
    	$error="i";
    	//create supply
    	try {
    		$this->c->CreateSupply("fries","3","01/01/2017");
    	
    	} catch (Exception $e) {
    		$error=$e->getMessage();
    	}
    	try {
    		$this->c->CreateSupply("burgers","3","01/01/2017");
    	
    	} catch (Exception $e) {
    		$error=$e->getMessage();
    	}
    	
    	$this->ftm = $this->pm->loadDataFromStore();
    	$supply1=$this->ftm->getSupply_index(0);
    	$supply2=$this->ftm->getSupply_index(1);
    	$array = array($supply1,$supply2);
    	
    	//create item
    	try {
    		$this->c->createItem("burger",$array);
    	} catch (Exception $e) {
    		$error=$e->getMessage();
    	}
    	$this->assertEquals($error,"i");
    	$this->ftm = $this->pm->loadDataFromStore();
    	$this->assertEquals(1,count($this->ftm->getItems()));
    	$this->assertEquals("burger",$this->ftm->getItem_index(0)->getName());
    	$item=$this->ftm->getItem_index(0);
    	$this->assertEquals("burgers",$item->getSupply_index(1)->getName());
    	 
    	$items= array($item);
    	$this->assertEquals("burger",$items[0]->getName());
    	
    	try {
    		$this->c->createOrder("1", "aaaaaa", "aaaaa", $items);
    		
    	} catch (Exception $e) {
    		$error=$e->getMessage();
    	}
    	$this->ftm = $this->pm->loadDataFromStore();
    	 
    	$this->assertEquals($error,"i");
    	 
    	$this->assertEquals(1,count($this->ftm->getOrders()));
    	$this->assertEquals("burger",$this->ftm->getOrder_index(0)->getItem_index(0)->getName());
    	 
    }
    
    public function testSupplyDeduction()
    {	$this->assertEquals(0, count($this->ftm->getItems()));
    $error="i";
    try {
    	$this->c->CreateSupply("patty","100","01/01/2017");
    	 
    } catch (Exception $e) {
    	$error=$e->getMessage();
    }
    try {
    	$this->c->CreateSupply("cheese","100","01/01/2017");
    	 
    } catch (Exception $e) {
    	$error=$e->getMessage();
    }
    try {
    	$this->c->CreateSupply("tomato","100","01/01/2017");
    
    } catch (Exception $e) {
    	$error=$e->getMessage();
    }
     
    $this->ftm = $this->pm->loadDataFromStore();
    $supply1=$this->ftm->getSupply_index(0);
    $supply2=$this->ftm->getSupply_index(1);
    $supply3=$this->ftm->getSupply_index(2);
    $array = array($supply1,$supply2,$supply3);
    try {
    	$this->c->createItem("burger",$array);
    } catch (Exception $e) {
    	$error=$e->getMessage();
    }
    $this->assertEquals($error,"i");
    $this->ftm = $this->pm->loadDataFromStore();
    $this->assertEquals(1,count($this->ftm->getItems()));
    
    $this->assertEquals("burger",$this->ftm->getItem_index(0)->getName());
    $item=$this->ftm->getItem_index(0);
    $this->assertEquals("cheese",$item->getSupply_index(1)->getName());
    
    $items= array($item,$item,$item);
    $this->assertEquals("burger",$items[0]->getName());
     
    try {
    	$this->c->createOrder("1", "aaaaaa", "aaaaa", $items);
    
    } catch (Exception $e) {
    	$error=$e->getMessage();
    }
    $this->ftm = $this->pm->loadDataFromStore();
    
    $this->assertEquals($error,"i");
   
    $this->assertEquals(1,count($this->ftm->getOrders()));
    $this->assertEquals("burger",$this->ftm->getOrder_index(0)->getItem_index(0)->getName());
    $this->assertEquals("97",$this->ftm->getSupply_index(0)->getQuantity());
    //$this->assertEquals("97",$this->ftm->getSupply_index(1)->getQuantity());
    $this->assertEquals("97",$this->ftm->getSupply_index(2)->getQuantity());
     $this->assertEquals(3,count($this->ftm->getOrder_index(0)->getItem()));
    }
     
}
?>
