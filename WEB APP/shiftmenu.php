<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Manage Shifts</title>

	</head>
	<body>
	<?php
	require_once "classes/Shift.php";
	require_once "persistence/PersistenceFoodTruckManager.php";
	session_start();
	?>
	<!-- Create Shift section -->
	<a href="index.php">Back to Main Menu</a>
	<h1 style="text-align: center;"><span style="color: #000080;"><strong>Shift Menu</strong></span></h1>

	<h3>Create Shift:</h3>
	<!-- Print Calendar and hours -->
	<form action="createShiftSetup.php" method="get">
	<span class="start_error"></span>
	Start Time:<span class="end_error"></span>
	<select id="starthour" name="starthour">
		<option value="-1">--Select Hour--</option>
		<option value="00">00</option>
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
		
	</select>
	:<select id="startmin" name="startmin">
		<option value="-1">--Select Minutes--</option>
		<option value="00">00</option>
		<option value="05">05</option>
		<option value="10">10</option>
		<option value="15">15</option>
		<option value="20">20</option>
		<option value="25">25</option>
		<option value="30">30</option>
		<option value="35">35</option>
		<option value="40">40</option>
		<option value="45">45</option>
		<option value="50">50</option>
		<option value="55">55</option>
	</select>
	<br/>	
	<br/>
	End Time:<span class="end_error">
	<select id="endhour"  name="endhour">
		<option value="-1">--Select Hour--</option>
		<option value="00">00</option>
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
		
	</select>
	:<select id="endmin" name="endmin">
		<option value="-1">--Select Minutes--</option>
		<option value="00">00</option>
		<option value="05">05</option>
		<option value="10">10</option>
		<option value="15">15</option>
		<option value="20">20</option>
		<option value="25">25</option>
		<option value="30">30</option>
		<option value="35">35</option>
		<option value="40">40</option>
		<option value="45">45</option>
		<option value="50">50</option>
		<option value="55">55</option>
	</select></span>
	<span class="date_error">
	<br/>
	<br/>
	Date:<select id="datemonth"  name="datemonth">
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
	<?php 

	if (isset($_SESSION["errorShift"])) {
		echo $_SESSION["errorShift"];

	}
	?>
	</span>
	<input type="submit" value="Create shift!">
	</form>

	<!-- assign Shift to Staff -->
		<h3>Assign Shift to Staff:</h3>
		<form action="addShiftToStaff.php" method="get">
		<select id="staffmember" name="staffmember">

		<option value="-1">--Select Staff Member--</option>
	<?php 
	require_once "persistence/PersistenceFoodTruckManager.php";
	require_once "classes/Staff.php";
	$pm = new PersistenceFoodTruckManager();
	$ftm = $pm->loadDataFromStore();
	$staffs2=$ftm->getstaffs();
	$i=1;
	foreach ($staffs2 as $staffman)
	{
		$namee=$staffman->getName();
		echo '<option value="';
		echo "$namee";
		echo '">';
		echo "$namee";
		echo "</option>";
		$i=$i+1;
	}
	?>
	</select>
	<select id="shift" name="shift">
	<option value="-1">--Select Shift--</option>
	<?php 
	
	require_once "persistence/PersistenceFoodTruckManager.php";
	require_once "classes/Shift.php";
	$pm = new PersistenceFoodTruckManager();
	$ftm = $pm->loadDataFromStore();
	$shift2=$ftm->getshifts();
	$i=0;
	foreach ($shift2 as $shiftt)
	{
		$startt=$shiftt->getStartTime();
		$endd=$shiftt->getEndTime();
		$datee=$shiftt->getShiftDate();
		echo '<option value="';
		echo "$i";
		echo '">';
		echo "start: $startt - end:$endd - date:$datee";
		echo "</option>";
		$i=$i+1;
	}
	
	?>
	</select>
	<input type="submit" value="Go!">
	</form>
	<?php 

	if (isset($_SESSION["errorAssign"])) {
		echo $_SESSION["errorAssign"];
	}?>
	
	<!-- Display Staff Schedule -->
	
	<h3>Display Staff Schedule:</h3>
	<form action="staffShiftList.php" method="get">
	<select id="schedule" name="schedule">
		<option value="-1">--Select Staff Member--</option>
	<?php 
	require_once "persistence/PersistenceFoodTruckManager.php";
	require_once "classes/Shift.php";
	require_once "classes/Staff.php";

	$pm = new PersistenceFoodTruckManager();
	$ftm = $pm->loadDataFromStore();
	$staffs=$ftm->getStaffs();
	$i=0;
	foreach ($staffs as $staff)
	{ 
		$name= $staff->getName();
		echo '<option value="';
		echo "$name";
		echo '">';
		echo "$name";
		echo "</option>";
	}
	?>
	
		</select>
	
	<?php 
	if (isset($_SESSION["shitlist"])) {
		echo $_SESSION["shiftlist"];
	}?>
		<input type="submit" value="See Schedule!">
		</form>
	</body>
	</html>
