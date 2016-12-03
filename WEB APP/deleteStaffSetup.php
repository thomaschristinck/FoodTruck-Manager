<?php
require "persistence/PersistenceFoodTruckManager.php";
require 'classes/Staff.php';
session_start();
$i=0;

//retrieve data
$pm = new PersistenceFoodTruckManager();
$ftm= $pm->loadDataFromStore();
$staffs=$ftm->getStaffs();
$v=$_GET["staffmember"];
$stafflist=$ftm->getStaffs();

//find and remove staff
if($v!="-1"){
foreach($stafflist as $ss)
{
	if($ss->getName()==$v)
		$x=$ss;
}		
$ftm->removeStaff($x);
$pm->writeDataToStore($ftm);	
}
else{
	$_SESSION["errorStaffD"]="Select Staff!";
}
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content='0; url=/FTMS2/staffmenu.php'/>
</head>
</html>