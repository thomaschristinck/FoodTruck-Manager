<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Manage Supply</title>

	</head>
	<body>
	
	<a href="index.php">Back to Main Menu</a>
<h1 style="text-align: center;"><span style="color: #000080;"><strong>Supply Menu</strong></span></h1>
<!-- Create Supply -->
<h3>Create Supply</h3>
<form action="createSupply.php" method="get">
Name:
	<input type="text" name="name" required>
	<br/>
Quantity: <input type="number" name="quantity"
   min="0" max="500" step="1" value="Enter Quantity">
   <br/>
Best Before:<select id="datemonth"  name="datemonth">
		<option value="-1">--Select Month--</option>
		<option value="01">01</option>
		<option value="02">02</option>
		<option value="03">03</option>
		<option value="04">04</option>
		<option value="05">05</option>
		<option value="06">06</option>
		<option value="07">07</option>
		<option value="08">08</option>
		<option value="09">09</option>
		<option value="10">10</option>
		<option value="11">11</option>
		<option value="12">12</option>	
	</select>
	/
	<select id="dateday" name="dateday">
		<option value="-1">--Select Day--</option>
		
		<option value="01">01</option>
		<option value="02">02</option>
		<option value="03">03</option>
		<option value="04">04</option>
		<option value="05">05</option>
		<option value="06">06</option>
		<option value="07">07</option>
		<option value="08">08</option>
		<option value="09">09</option>
		<option value="10">10</option>
		<option value="11">11</option>
		<option value="12">12</option>
		<option value="13">13</option>
		<option value="14">14</option>
		<option value="15">15</option>
		<option value="16">16</option>
		<option value="17">17</option>
		<option value="18">18</option>
		<option value="19">19</option>
		<option value="20">20</option>
		<option value="21">21</option>
		<option value="22">22</option>
		<option value="23">23</option>
		<option value="24">24</option>
		<option value="25">25</option>
		<option value="26">26</option>
		<option value="27">27</option>
		<option value="28">28</option>
		<option value="29">29</option>
		<option value="30">30</option>
		<option value="31">31</option>
	</select>
	/
	<select id="dateyear" name="year">
		<option value="-1">--Select Year--</option>
		<option value="2016">2016</option>
		<option value="2017">2017</option>
		<option value="2018">2018</option>
		<option value="2019">2019</option>
		<option value="2020">2020</option>
		<option value="2021">2021</option>
		<option value="2022">2022</option>
		<option value="2023">2023</option>
		<option value="2024">2024</option>
		<option value="2025">2025</option>
		<option value="2026">2026</option>
		<option value="2027">2027</option>
		<option value="2028">2028</option>	
		<option value="2029">2029</option>
		<option value="2030">2030</option>
		<option value="2031">2031</option>
		<option value="2032">2032</option>
	</select>
	<input type="submit" value="Go!">
</form>
<?php 
session_start();
if (isset($_SESSION["errorSupply"])) {
	echo $_SESSION["errorSupply"];
}
?>
<!-- Change Supply Quantity -->
<h3>Change Supply Quantity:</h3>
<form action="changeSupplyQuantity.php" method="get">
<select id="supply" name="supply">
	<option value="-1">--Select Supply--</option>
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

	echo '<option value="';
	echo "$i";
	echo '">';
	echo "Type:$name -";
	echo "Quantity:$quantity -";
	echo "Best Before:$bb";
	echo "</option>";
$i=$i+1;
}
	
?>
</select>
New Quantity:
<input type="number" name="newquantity"
   min="0" max="500" step="1" value="Enter New Quantity" required>
<input type="submit" value="Go!">
</form>
<br/>
<!-- Supply List -->
	<a href="supplyList.php">View Supply Inventory!</a>

	</body>
	</html>
