<?php 
session_start();

//reset all session variables to default
$_SESSION["reqs"]="1";
$_SESSION["orderReqs"]="1";
$_SESSION["errorStaffName"]="";
$_SESSION["errorItem"]="";
$_SESSION["shiftlist"]="";
$_SESSION["errorAssign"]="";
$_SESSION["errorShift"]="";
$_SESSION["errorSupply"]="";
$_SESSION["errorEquip2"]="";
$_SESSION["errorEquip"]="";
$_SESSION["errorOrder"]="";
$_SESSION["deleteitem"]="";

?>

<!-- Load main menu -->
<!DOCTYPE html>
	<html>
		<head>
			<meta http-equiv="refresh" content='0; url=/FTMS2/menu.php'/>
	</head>
</html>