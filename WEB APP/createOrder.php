<?php
require_once 'controller/Controller.php';
session_start();
$num =$_SESSION["orderReqs"];
$items=array();
$x="0";
$pm = new PersistenceFoodTruckManager();
$ftm= $pm->loadDataFromStore();
$number=count($ftm->getOrders());
// add items and quantity

	for($i=1;$i<=$num;$i++){
	$name="item".$i;
	$name=$_GET[$name];
	if($name!="-1"){
	$quant="quantity".$num;
	$newquant=$_GET[$quant];
	for($y=0;$y<$newquant;$y++){
	$items[]=$ftm->getItem_index($name);
	}
	}
	else{$x="ERROR";}
		
}
$c = new Controller();
//check error and execute
if($x=="0"){
try {
	$c->createOrder($number,date('l jS \of F Y'),date('h:i:s A'),$items);
	$_SESSION["errorOrder"] = "";
} catch (Exception $e) {
	$_SESSION["errorOrder"] = $e->getMessage();
}

$_SESSION["orderReqs"]=1;
}
else{
	$_SESSION["errorOrder"]="Select Item!";
}
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content='0; url=/FTMS2/ordermenu.php'/>
</head>
</html>

