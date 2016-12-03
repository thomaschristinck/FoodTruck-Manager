<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Manage Menu Items</title>

	</head>
	<body>
	<!-- Link to Menu -->
		<a href="index.php">Back to Main Menu</a>
		<h1 style="text-align: center;"><span style="color: #000080;">
		<strong>Item Menu</strong>
		</span>
		</h1>
	
	<!-- Create Item -->
		<h3>Create Item:</h3>
		<form action="createItem.php" method="get">
	Item Name:
		<input type="text" name="name" required>
	Supplies:

	<?php //print supplies in select
	session_start();
	$reqs=$_SESSION["reqs"];//finds number of needed supplies
	for($num=1;$num<=$reqs;$num++){//prints number of select filled with supply list
	echo "#" . $num ;
	echo '<select id="supply';
	echo "$num";
	echo '" name="supply';
	echo "$num";	
	echo  '">';	
	echo'<option value="-1">--Select Supply--</option>';
	
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
	
	echo '<option value="';
	echo "$i";
	echo '">';
	echo "Type:$name -";
	echo "Quantity:$quantity -";
	echo "Best Before:$bb";
	echo "</option>";
	$i=$i+1;
	}
	echo"</select>";
	}
	?>
	
		<input type="submit" value="Go!"></form>
	Add Supply Slot:
		<form action="addsupply.php">
		<input type="submit" value="Go!"></form>
	Remove Supply Slot:
		<form action="removesupply.php">
		<input type="submit" value="Go!"></form>
	<?php 
	if (isset($_SESSION["errorItem"])) {
	echo $_SESSION["errorItem"];
	}
	?>
	<!-- Delete Item -->
		<h3>Delete Item:</h3>

		<form action="deleteItem.php" method="get">
		<select id="item" name="item">
			<option value="-1">--Select Item--</option>
	<?php 
	require_once "persistence/PersistenceFoodTruckManager.php";
	require_once "classes/Item.php";
	//prints list of items in select
	$pm = new PersistenceFoodTruckManager();
	$ftm = $pm->loadDataFromStore();
	$items=$ftm->getItems();
	$i=1;
	foreach ($items as $item){
	$list="";
	$namee=$item->getName();
	$supplies=$item->getSupply();
	foreach ($supplies as $supply){
		
		$list=$list . $supply->getName(). ",";
	}
	echo '<option value="';
	echo "$namee";
	echo '">';
	echo "$namee ";
	echo "Supplies:$list";
	echo "</option>";
	$i=$i+1;
}
?>
		</select>
		<br>
		<input type="submit" value="Delete Item">
		</form>
		<a href="MPI.php">View Most Popular Items!</a>
	</body>
	</html>
	