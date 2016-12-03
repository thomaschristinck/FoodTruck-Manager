<?php
require_once 'controller/Controller.php';
session_start();
$c = new Controller();
//retrieve data
$month=$_GET["datemonth"];
$day=$_GET["dateday"];
$year=$_GET["year"];
$bestbefore=$month ."/".$day."/".$year;

try {
	$c->createSupply($_GET["name"],$_GET["quantity"],$bestbefore);
	$_SESSION["errorSupply"] = "";
} catch (Exception $e) {
	$_SESSION["errorSupply"] = $e->getMessage();
}

?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content='0; url=/FTMS2/supplymenu.php'/>
</head>
</html>