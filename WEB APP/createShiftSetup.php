<?php
require_once 'controller/Controller.php';
session_start();
//retrieve input
$starthour=$_GET["starthour"];
$startmin=$_GET["startmin"];
$start=$starthour .":".$startmin ;
$endhour=$_GET["endhour"];
$endmin=$_GET["endmin"];
$end=$endhour .":".$endmin;
$month=$_GET["datemonth"];
$day=$_GET["dateday"];
$year=$_GET["year"];
$date=$month ."/".$day."/".$year;
$c = new Controller();
//error checking
if($starthour=="-1"){
	$_SESSION["errorShift"]="Select Start Hour!";
}
else if($endhour=="-1"){
	$_SESSION["errorShift"]="Select End Hour!";
}
else if($endmin=="-1"){
	$_SESSION["errorShift"]="Select End Minutes!";
}
else if($month=="-1"){
	$_SESSION["errorShift"]="Select Month!";
}
else if($day=="-1"){
	$_SESSION["errorShift"]="Select Day!";
}
else if($year=="-1"){
	$_SESSION["errorShift"]="Select Year!";
}
else{
	//execute create order
try {
	$c->createShift($start,$end,$date);
	$_SESSION["errorShift"] = "";
} catch (Exception $e) {
	$_SESSION["errorShift"] = $e->getMessage();
}
}
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content='0; url=/FTMS2/shiftmenu.php'/>
</head>
</html>
