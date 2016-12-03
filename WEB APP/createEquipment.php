
<?php
require_once 'controller/Controller.php';
session_start();
$c = new Controller();

try {
	$c->createEquipment($_GET["name"],$_GET["quantity"]);
	$_SESSION["errorEquip"] = "";
} catch (Exception $e) {
	$_SESSION["errorEquip"] = $e->getMessage();
}
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content='0; url=/FTMS2/equipmentmenu.php'/>
</head>
</html>