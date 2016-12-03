<?php

require_once 'C:\Users\Julien\workspace3\FTMS2\controller\InputValidator.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\persistence\PersistenceFoodTruckManager.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Staff.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Shift.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Supply.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Item.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Order.php';


require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Equipment.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\controller\ArrayValidator.php';



class Controller 
{
	public function _construct()
	{
		
	}
	
	public function createStaff($name,$role)
	{
		//validate input
		$name=InputValidator::validate_input($name);
		$role=InputValidator::validate_input($role);
		
		if ($name==null||strlen($name)==0)
		{
			throw new Exception("Staff name cannot be empty!");
		}
		else if($role==null||strlen($role)==0)
		{
			throw new Exception("Staff role cannot be empty!");
		}
		else 
		{
				//2 load data
				$pm = new PersistenceFoodTruckManager();
				$ftm =$pm->loadDataFromStore();
				
				//3 add staff
				$staff = new Staff($name, $role);
				$ftm->addStaff($staff);
				
				//4 write data
				$pm->writeDataToStore($ftm);
		}
	}
	
	public function createShift($startTime,$endTime,$date)
	{
		//validate input
		$startTime=InputValidator::validate_input($startTime);
		$endTime=InputValidator::validate_input($endTime);
		$date=InputValidator::validate_input($date);
		
	
		if ($startTime==null||strlen($startTime)==0)
		{
			throw new Exception("Shift start time cannot be empty!");
		}
		else if($endTime==null||strlen($endTime)==0)
		{
			throw new Exception("Shift end time cannot be empty!");
		}
		else if($date==null||strlen($date)==0)
		{
			throw new Exception("Shift date cannot be empty!");
		}
		else if($startTime[0]=='-')
		{
			throw new Exception("Select Start Hour!");
		}
		else if($startTime[3]=='-')
		{
			throw new Exception("Select Start Minutes!");
		}
		else if($endTime[0]=='-')
		{
			throw new Exception("Select End Hour!");
		}
		else if($endTime[3]=='-')
		{
			throw new Exception("Select End Minutes!");
		}
		else if($date[0]=='-')
		{
			throw new Exception("Select Month!");
		}
		else if($date[3]=='-')
		{
			throw new Exception("Select Day!");
		}
		else if($date[6]=='-')
		{
			throw new Exception("Select Year!");
		}
		else
		{
			//2 load data
			$pm = new PersistenceFoodTruckManager();
			$ftm =$pm->loadDataFromStore();
	
			//3 add staff
			$shift = new shift($startTime, $endTime,$date);
			$ftm->addShift($shift);
	
			//4 write data
			$pm->writeDataToStore($ftm);
		}
	}
	
	public function createSupply($name,$quantity,$bestBefore)
	{
		//validate input
		$name=InputValidator::validate_input($name);
		$quantity=InputValidator::validate_input($quantity);
		$bestBefore=InputValidator::validate_input($bestBefore);
		
		//extract date info
$month=$bestBefore[0];	
$day=$bestBefore[3];
$year=$bestBefore[6];

		//check for errors
		if ($name==null||strlen($name)==0)
		{
			throw new Exception("Supply name cannot be empty!");
		}
		else if($quantity==null||strlen($quantity)==0)
		{
			throw new Exception("Supply quantity cannot be empty!");
		}
		else if($bestBefore==null||strlen($bestBefore)==0)
		{
			throw new Exception("Supply expiration date cannot be empty!");
		}
		else if ($month=="-") {
			
			throw new Exception("Select Month!");
		}
		else if ($day=="-") {
			
			throw new Exception("Select Day!");
		}
		else if ($year=="-") {
				
			throw new Exception("Select Year!");
		}
		else
		{
			//2 load data
			$pm = new PersistenceFoodTruckManager();
			$ftm =$pm->loadDataFromStore();
		
			//3 add staff
			$supply = new Supply($name, $quantity,$bestBefore);
			$ftm->addSupply($supply);
		
			//4 write data
			$pm->writeDataToStore($ftm);
		}
	}
	
	public function createEquipment($name,$quantity)
	{
		//validate input
		$name=InputValidator::validate_input($name);
		$quantity=InputValidator::validate_input($quantity);
		
		if ($name==null||strlen($name)==0)
		{
			throw new Exception("Equipment name cannot be empty!");
		}
		else if($quantity==null||strlen($quantity)==0)
		{
			throw new Exception("Equipment quantity cannot be empty!");
		}
		else
		{
			//2 load data
			$pm = new PersistenceFoodTruckManager();
			$ftm =$pm->loadDataFromStore();
		
			//3 add staff
			$equipment = new Equipment($name, $quantity);
			$ftm->addEquipment($equipment);
		
			//4 write data
			$pm->writeDataToStore($ftm);
		}
	}
	
	public function createItem($name,$supplies){
		
		//validate input
		$name=InputValidator::validate_input($name);
		
		
		$message="hi";
		
		
		if($name==null||strlen($name)==0)
		{
			throw new Exception("Item name cannot be empty!");
		}
		
		else if($message=="EMPTY ELEMENT")
		{
		throw new Exception("Enter all supplies!");
		}
		else
		{
			//2 load data
			$pm = new PersistenceFoodTruckManager();
			$ftm =$pm->loadDataFromStore();
		
			//3 add sitem
			$item = new Item($name,"0");
			foreach ($supplies as $supply){
				$item->addSupply($supply);
			}
			$ftm->addItem($item);
		
			//4 write data
			$pm->writeDataToStore($ftm);
		}
	}
	
	public function createOrder($number,$date,$time,$items){
	
		//validate input
		$number=InputValidator::validate_input($number);
		$date=InputValidator::validate_input($date);
		$time=InputValidator::validate_input($time);
		
		//initiate variables
		$sameitemcount=0;
		$previousitem="";
		$maxitemcount=0;
		$itemcountarray=array();
		$i=0;
		
		//for loop to add similar items and store that number in array followed by actual item 
		for($i=0;$i<count($items);$i++){
			if($i==0){
				$sameitemcount=1;
				$previousitem=$items[$i];
				if($i==count($items)-1){
					$itemcountarray[]=$sameitemcount;
					$itemcountarray[]=$items[$i];
				}
			}
			else if ($previousitem==$items[$i]){
				$sameitemcount++;
				if($i==count($items)-1){
					$itemcountarray[]=$sameitemcount;
					$itemcountarray[]=$items[$i];
				}
			}
			else{
				$itemcountarray[]=$sameitemcount;
				$itemcountarray[]=$items[$i-1];
				$sameitemcount=1;;
				$previousitem=$items[$i];
				if($i==count($items)-1){
					$itemcountarray[]=$sameitemcount;
					$itemcountarray[]=$items[$i];
				}
					}		
		}
		
		//for loop to add up all needed supplies
		$supplyarray=array();
		$countarray=array();
		$supplyindex=-1;
		$j=1;
		$ii="i";
		$y="0";
		$added="not added";
	
		for($j=1;$j<count($itemcountarray);$j=$j+2){
			$supplies=$itemcountarray[$j]->getSupply();
			foreach($supplies as $supply){
				
				$added="not added";
				if($supplyarray!=null)
				{
				for($f=0;$f<count($supplyarray);$f++)
				{
					if($supplyarray[$f]->getName()==$supply->getName())
					{
						$countarray[$f]=$countarray[$f]+$itemcountarray[$j-1];
						
						$added="added";
					}
					
				}
				if($added=="not added"){
					$supplyarray[]=$supply;
					$countarray[]=$itemcountarray[$j-1];
				}
				}
				else
				{
						$supplyarray[]=$supply;
						$countarray[]=$itemcountarray[$j-1];
			}
		}
		}
		
		foreach ($supplyarray as $s){
			echo $s->getName();
		}
		foreach ($countarray as $s){
			echo $s;
		}
		
		//for loop to verify that supply stock is big enough to complete order
		$pm = new PersistenceFoodTruckManager();
		$ftm =$pm->loadDataFromStore();
		$supplies2=$ftm->getSupplies();
		$check="no error";
		for($r=0;$r<count($supplyarray);$r++){
			foreach($supplies2 as $supply2){
				$supname=$supply2->getName();
				if($supname==$supplyarray[$r]->getName() && $supply2->getQuantity()<$countarray[$r]){
					
					$check="Error, not enough ". $supname .". Please adjust Quantity before proceeding!"	;			}
			}
		}
		//error check
		if($check!="no error"){
			throw new Exception($check);
		}
		else if($number==null||strlen($number)==0)
		{
			throw new Exception("Order Number cannot be empty!");
		}
	    elseif ($items[0]==null||strlen($items[0]->getName())==0){
	    	throw new Exception("Empty Item");
	    }
		else if($date==null||strlen($date)==0)
		{
			throw new Exception("Order date cannot be empty!");
		}
		else if($time==null||strlen($time)==0)
		{
			throw new Exception("Order time cannot be empty!");
		}
		else
		{
			//2 load data
			$pm = new PersistenceFoodTruckManager();
			$ftm =$pm->loadDataFromStore();
	
			//3 add order
			$order = new Order($number,$date,$time);
			//3.1 add items
			foreach($items as $item){
				
				$order->addItem($item);
			}
			$ftm->addOrder($order);
			//3.2 substract supplies
			for($r=0;$r<count($supplyarray);$r++){
				for($rr=0;$rr<count($supplies2);$rr++){
					$supname=$supplies2[$rr]->getName();
					if($supname==$supplyarray[$r]->getName()){
							
						$ftm->getSupply_index($rr)->setQuantity($ftm->getSupply_index($rr)->getQuantity()-$countarray[$r]);}
				}
			}
			//4 write data
			$pm->writeDataToStore($ftm);
		}
	}
	
	
	
}

?>

