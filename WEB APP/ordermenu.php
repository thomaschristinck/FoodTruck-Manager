<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Manage Orders</title>

	</head>
	<body>
		<a href="index.php">Back to Main Menu</a>
		<h1 style="text-align: center;"><span style="color: #000080;">
		<strong>Order Menu</strong>
		</span>
		</h1>
		
	<!-- Place Order -->

		<h3>Create Order:</h3>
		<form action="createOrder.php" method="get">

		Items:
	<?php 
	session_start();
	$orderReqs=$_SESSION["orderReqs"];
	for($num=1;$num<=$orderReqs;$num++){//print itemlist select and quantity
		echo "#" . $num ;
		echo '<select id="item';
		echo "$num";
		echo '" name="item';
		echo "$num";	
		echo  '">';
		echo'<option value="-1">--Select Item--</option>';
	
		require_once "classes/Item.php";
		require_once "persistence/PersistenceFoodTruckManager.php";
	
		$pm = new PersistenceFoodTruckManager();
		$ftm = $pm->loadDataFromStore();
		$items=$ftm->getItems();
		$i=0;
		foreach ($items as $item)
		{ $name= $item->getName();
		$supplies=$item->getSupply();
		$list="";
	
		echo '<option value="';
		echo "$i";
		echo '">';
		echo "Type:$name ";
		echo "</option>";
		$i=$i+1;
		}
		echo"</select>";
		echo "Quantity:";
		echo '<input type="number" name="quantity';
		echo "$num";
		echo '"min="0" max="500" step="1" value="Enter Quantity" required> ';
	}
	?>
	
	<input type="submit" value="Go!"></form>
	Add Item Slot:
	<form action="additem.php">
	<input type="submit" value="Go!"></form>
	Remove Item Slot:
	<form action="removeitem.php">
	<input type="submit" value="Go!"></form>
	<?php 
	if (isset($_SESSION["errorOrder"])) {
	echo $_SESSION["errorOrder"];
	}
	?>
	
	<!-- View Order History -->
		<a href="orderhistory.php">View Order History!</a>
	</body>
	</html>
