<?php
require_once 'C:\Users\Julien\workspace3\FTMS2\controller\Controller.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\persistence\PersistenceFoodTruckManager.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Staff.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\FoodTruckManager.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Supply.php';
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\Order.php';
//class used to print echo statements from within controller
$c=new Controller();
$pm = new PersistenceFoodTruckManager();
$ftm = $pm->loadDataFromStore();
$error="i";
try {
	$c->CreateSupply("patty","100","01/01/2017");

} catch (Exception $e) {
	$error=$e->getMessage();
}
try {
	$c->CreateSupply("cheese","100","01/01/2017");

} catch (Exception $e) {
	$error=$e->getMessage();
}
try {
	$c->CreateSupply("tomato","100","01/01/2017");

} catch (Exception $e) {
	$error=$e->getMessage();
}
try {
	$c->CreateSupply("buns","100","01/01/2017");

} catch (Exception $e) {
	$error=$e->getMessage();
}
try {
	$c->CreateSupply("fries","100","01/01/2017");

} catch (Exception $e) {
	$error=$e->getMessage();
}
try {
	$c->CreateSupply("gravy","100","01/01/2017");

} catch (Exception $e) {
	$error=$e->getMessage();
}
$ftm = $pm->loadDataFromStore();

$supply1=$ftm->getSupply_index(0);
$supply2=$ftm->getSupply_index(1);
$supply3=$ftm->getSupply_index(2);
$supply4=$ftm->getSupply_index(3);
$supply5=$ftm->getSupply_index(4);
$supply6=$ftm->getSupply_index(5);

$array1 = array($supply1,$supply2,$supply3,$supply4);
$array2 = array($supply2,$supply5,$supply6);

try {
	$c->createItem("burger",$array1);
} catch (Exception $e) {
	$error=$e->getMessage();
}
try {
	$c->createItem("burger",$array2);
} catch (Exception $e) {
	$error=$e->getMessage();
}
$ftm = $pm->loadDataFromStore();

$item=$ftm->getItem_index(0);
$item2=$ftm->getItem_index(1);
$items= array($item,$item,$item2);
try {
	$c->createOrder("1", "aaaaaa", "aaaaa", $items);

} catch (Exception $e) {
	$error=$e->getMessage();
}
?>