<?php
require "persistence/PersistenceFoodTruckManager.php";
require 'classes/Item.php';

$i=0;
$pm = new PersistenceFoodTruckManager();
$ftm= $pm->loadDataFromStore();
$items=$ftm->getItems();
$v=$_GET["item"];

if($v!="-1"){
foreach($items as $item)
{
	//retrieve selected items
	if($item->getName()==$v)
		$x=$item;
}
$ftm->removeItem($x);
$pm->writeDataToStore($ftm);	
}
else{
	$_SESSION["deleteitem"]="Select Item!";
}
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content='0; url=/FTMS2/itemmenu.php'/>
</head>
</html>
