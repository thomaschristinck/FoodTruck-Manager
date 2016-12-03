<?php
require "persistence/PersistenceFoodTruckManager.php";
require 'classes/Shift.php';
require 'classes/Staff.php';
session_start();
$i=0;
//load data and retrieve get
$pm = new PersistenceFoodTruckManager();
$ftm= $pm->loadDataFromStore();
$v=$_GET["staffmember"];
$s=$_GET["shift"];

//find staff
$stafflist=$ftm->getStaffs();
foreach($stafflist as $ss)
{	
	if($ss->getName()==$v)
		$x=$i;
	
		$i=$i+1;
}
//check error
if($v=="-1"){
	$_SESSION["errorAssign"]="Select Staff!";
}
else if($s=="-1"){
	$_SESSION["errorAssign"]="Select Shift!";
}
//execute
else{
$new=$ftm->getShift_index($s);
$ftm->getStaff_index($x)->addShift($new);
$pm->writeDataToStore($ftm);	
$_SESSION["errorAssign"]="";
}
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content='0; url=/FTMS2/shiftmenu.php'/>
</head>
</html>