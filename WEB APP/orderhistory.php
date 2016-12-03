<!DOCTYPE html>
	<html>
	<body>
		<a href="menu.php">Back to Main Menu</a>
		<h1 style="text-align: center;">
		<span style="color: #000080;">
		<strong>Order History</strong>
		</span>
		</h1>
		
	<?php 
	require_once "classes/Order.php";
	require_once "classes/Supply.php";
	require_once "classes/Item.php";
	require_once "persistence/PersistenceFoodTruckManager.php";

	$pm = new PersistenceFoodTruckManager();
	$ftm = $pm->loadDataFromStore();
	$orders=$ftm->getOrders();
	$i=0;
	foreach ($orders as $order)
	{	//loop through orders
		$number= $order->getOrderNumber();
		$date=$order->getOrderDate();
		$time=$order->getOrderTime();
		$items=$order->getItem();
		$itemlist="";
		foreach ($items as $item){//add up items
			$name=$item->getName();
			$itemlist=$itemlist."-$name";
		}

		//echo information
		echo "Number:$number ";
		echo "Date:$date ";
		echo "Time:$time ";
		echo "Items:$itemlist";
		echo "<br>";
		$i=$i+1;
	}
	?>
	</body>
	</html>
