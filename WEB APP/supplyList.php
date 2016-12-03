<!DOCTYPE html>
<html>
<body>
<a href="menu.php">Back to Main Menu</a>
<h1 style="text-align: center;"><span style="color: #000080;"><strong>Supply Inventory</strong></span></h1>
<?php 

require_once "classes/Supply.php";
require_once "persistence/PersistenceFoodTruckManager.php";

$pm = new PersistenceFoodTruckManager();
$ftm = $pm->loadDataFromStore();
$supplies=$ftm->getSupplies();
$i=0;
foreach ($supplies as $supply)
{ $name= $supply->getName();
$quantity=$supply->getQuantity();
$bb=$supply->getBestbefore();

	
	echo "Type:$name -";
	echo "Quantity:$quantity -";
	echo "Best Before:$bb";
	echo "<br/>";

$i=$i+1;
}

?>
</body>
</html>