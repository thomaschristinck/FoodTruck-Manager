<?php

require_once 'controller/Controller.php';
session_start();
$c = new Controller();
$pm = new PersistenceFoodTruckManager();
$ftm=$pm->loadDataFromStore();
$index=$_GET["equipment"];
$quantity=$_GET["newquantity"];
//check error
if(strcmp($index, "-1")==0){
	$_SESSION["errorEquip2"] = "Select Equipment!";
}
else if($quantity=="0"){
	//delete
	try {
		$newequipment=$ftm->getEquipment_index($index);
		$ftm->removeEquipment($newequipment);
		$_SESSION["errorStaffName"] = "";

	} catch (Exception $e) {
		$_SESSION["errorStaffName"] = "";
	}

}
else if($quantity==""){
	//set error
	$_SESSION["errorStaffName"] = "Select New Quantity!";
}
else {
	try {
		$ftm->getEquipment_index($index)->setQuantity($quantity);
		$_SESSION["errorStaffName"] = "";
	} catch (Exception $e) {
		$_SESSION["errorStaffName"] = "";
	}

}

$pm->writeDataToStore($ftm);

?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content='0; url=/FTMS2/equipmentmenu.php'/>
</head>
</html>