<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Manage Equipment</title>

	</head>
	<body>
	<!-- Link to Menu -->
		<a href="index.php">Back to Main Menu</a>
		<h1 style="text-align: center;">
		<span style="color: #000080;">
		<strong>Equipment Menu</strong>
		</span>
		</h1>
		
	<!-- Create Equipment -->
<?php 
//Print error
session_start();
if (isset($_SESSION["errorEquip"])) {
	echo $_SESSION["errorEquip"];
}
?>

		<h3>Create Equipment:</h3>
		<form action=createEquipment.php method="get">
Name:
		<input type="text" name="name" required>
Quantity: 
		<input type="number" name="quantity"min="0" max="500" step="1" value="Enter Quantity" required>
   		<input type="submit" value="Go!">
   		</form>
   
	<!-- Change Equipment Quantity -->

		<h3>Change Equipment Quantity:</h3>
		<form action="changeEquipmentQuantity.php" method="get">
		<select id="equipment" name="equipment">
			<option value="-1">--Select Equipment--</option>
	
<?php 
//print equipment list to select
require_once "classes/Equipment.php";
require_once "persistence/PersistenceFoodTruckManager.php";
//load data
$pm = new PersistenceFoodTruckManager();
$ftm = $pm->loadDataFromStore();
$equipments=$ftm->getEquipment();
$i=0;
//print data
foreach ($equipments as $equipment)
{ 
	$name= $equipment->getName();
	$quantity=$equipment->getQuantity();
	echo '<option value="';
	echo "$i";
	echo '">';
	echo "Type:$name -";
	echo "Quantity:$quantity -";
	echo "</option>";
	$i=$i+1;
}
?>
		</select>

New Quantity:
		<input type="number" name="newquantity" min="0" max="500" step="1" value="Enter New Quantity" required>
		<input type="submit" value="Go!">
		</form>
		
<?php 
//print error
if (isset($_SESSION["errorEquip2"])) {
	echo $_SESSION["errorEquip2"];
}
?>
	</body>
	</html>
