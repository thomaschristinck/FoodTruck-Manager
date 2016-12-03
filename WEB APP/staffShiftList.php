<!DOCTYPE html>
<html>
<body>
<a href="menu.php">Back to Main Menu</a>
<h1 style="text-align: center;"><span style="color: #000080;"><strong>Schedule</strong></span></h1>
<?php


require_once 'controller/Controller.php';
session_start();
$c = new Controller();
$xi=$_GET["schedule"];

if($xi=="-1"){
	$_SESSION["shiftlist"]="Select Shift!";
}
else{
$pm = new PersistenceFoodTruckManager();
$ftm= $pm->loadDataFromStore();
$staffs=$ftm->getStaffs();

$stafflist=$ftm->getStaffs();

foreach($stafflist as $ss)
{
	if($ss->getName()==$xi){
		$shifts=$ss->getShift();
		foreach ($shifts as $shift){
			$startt=$shift->getStartTime();
$endd=$shift->getEndTime();
$datee=$shift->getShiftDate();
	
	echo "start: $startt - end:$endd - date:$datee";
	echo "</br>";
	
		}
	}
}
}
?>

</body>
</html>