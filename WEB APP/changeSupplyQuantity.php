<?php
require_once 'controller/Controller.php';
session_start();
$c = new Controller();
$pm = new PersistenceFoodTruckManager();
$ftm=$pm->loadDataFromStore();
$index=$_GET["supply"];
$quantity=$_GET["newquantity"];
//check error
if(strcmp($index, "-1")==0){
	$_SESSION["errorSupply"] = "Select Supply!";
}
else if($quantity=="0"){
	try {
		//delete
		$newsupply=$ftm->getSupply_index($index);
		$ftm->removeSupply($newsupply);
		$_SESSION["errorSupply"] = "";

	} catch (Exception $e) {
		$_SESSION["errorSupply"] = "";
	}
	
}
else if($quantity==""){
	$_SESSION["errorSupply"] = "Select New Quantity!";
}
else {
	try {
		$ftm->getSupply_index($index)->setQuantity($quantity);
		$_SESSION["errorSupply"] = "";
	} catch (Exception $e) {
		$_SESSION["errorSupply"] = "";
	}
	
}

$pm->writeDataToStore($ftm);

?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content='0; url=/FTMS2/supplymenu.php'/>
</head>
</html>