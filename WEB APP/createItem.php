<?php
require_once 'controller/Controller.php';
session_start();
$num =$_SESSION["reqs"];
$supplies=array();
$x="0";
$pm = new PersistenceFoodTruckManager();
$ftm= $pm->loadDataFromStore();
$realname=$_GET["name"];

//retrieve supplies
	for($i=1;$i<=$num;$i++){
	$name="supply".$i;
	$name=$_GET[$name];
	if($name!="-1"){
	$supplies[]=$ftm->getSupply_index($name);
	}
	else{$x="ERROR";}
		
}
$c = new Controller();
//check error
if($x=="0"){
	try {
		$c->createItem($realname,$supplies);
		$_SESSION["errorItem"] = "";
	} catch (Exception $e) {
		$_SESSION["errorItem"] = $e->getMessage();
	}

	$_SESSION["reqs"]=1;
	}
else{
	$_SESSION["errorItem"]="Select Supply!";
}
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content='0; url=/FTMS2/itemmenu.php'/>
</head>
</html>
