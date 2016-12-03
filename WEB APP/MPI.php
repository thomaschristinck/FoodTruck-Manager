<!DOCTYPE html>
	<html>
	<body>
	<a href="menu.php">Back to Main Menu</a>
		<h1 style="text-align: center;"><span style="color: #000080;">
		<strong>Item Popularity</strong>
		</span>
		</h1>
		
	<?php
	require_once 'controller/Controller.php';
	session_start();
	$c = new Controller();
	$pm = new PersistenceFoodTruckManager();
	$ftm= $pm->loadDataFromStore();
	$orders=$ftm->getOrders();
	$found="not found";
	$itemlist=array();
	$itemcount=array();
	//add up all items from all orders as well as number of repetition
	foreach ($orders as $order){
		$items=$order->getItem();
		foreach($items as $item){
			for($i=0;$i<count($itemlist);$i++){
				if($item->getName()==$itemlist[$i]->getName()){
					$itemcount[$i]+=1;
					$found="found";
				}
			}
			if ($found=="not found") {
				$itemlist[]=$item;
				$itemcount[]=1;
			}
			$found="not found";
		}
	}

	//sort array
	$index=0;
	$ref=0;
	$biggest=0;
	$sorteditems=array();
	$sortedcounts=array();
	$key="";
	$size = count($sortedcounts);
 	   for ($i=0; $i<$size; $i++) {
   	     for ($j=0; $j<$size-1-$i; $j++) {
     	       if ($itemcount[$j+1] < $itemcount[$j]) {
      	           $tmp = $itemlist[$j];
    				 $itemlist[$j] = $itemlist[$j+1];
   					 $itemlist[$j+1] = $tmp;
   					 $tmp = $itemcount[$j];
   					 $itemcount[$j] = $itemcount[$j+1];
   					 $itemcount[$j+1] = $tmp;
           	 }
        	}
    	}
	$sorteditems=$itemlist;
	$sortedcounts=$itemcount;

	//printresults
	for($e=0;$e<count($sorteditems);$e++){
		echo "Item: ".$sorteditems[$e]->getName();
		echo " # of sales:".$sortedcounts[$e];
		echo "<br/>";
	}
	?>

	</body>
	</html>