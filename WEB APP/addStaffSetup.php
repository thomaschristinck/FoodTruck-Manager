<?php
require_once 'controller/Controller.php';
session_start();
$c = new Controller();
try {
	$c->createStaff($_GET["staff_name"],$_GET["staff_role"]);
	$_SESSION["errorStaffName"] = "";
} catch (Exception $e) {
	$_SESSION["errorStaffName"] = $e->getMessage();
}
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content='0; url=/FTMS2/staffmenu.php'/>
</head>
</html>