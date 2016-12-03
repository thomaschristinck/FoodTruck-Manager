
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Manage Staff</title>
	</head>
	<body>
	<?php
	require_once "classes/Staff.php";
	require_once "persistence/PersistenceFoodTruckManager.php";
	session_start();
	?>
	<a href="index.php">Back to Main Menu</a>
		<h1 style="text-align: center;"><span style="color: #000080;"><strong>Staff Menu</strong></span></h1>
	<!-- Add Staff section -->
	<h3>Add Staff:</h3>
	<form action="addStaffSetup.php" method="get">
		<span class="name_error"> 
Name:<input type="text" name="staff_name" required/>


		</span>
		<span class="role_error">

Role:<input type="text" name="staff_role" required/>
	
<?php 
if (isset($_SESSION["errorStaffName"])) {
	echo $_SESSION["errorStaffName"];
}
?>
		</span>
		<input type="submit" value="Create Staff Member!"/>
	</form>


<!-- Delete Staff section -->
<h3>Delete Staff Member:</h3>

	<form action="deleteStaffsetup.php" method="get">
		<select id="staffmember" name="staffmember">
		<option value="-1">--Select Staff Member--</option>
<?php 
require_once "persistence/PersistenceFoodTruckManager.php";
require_once "classes/Staff.php";

$pm = new PersistenceFoodTruckManager();
$ftm = $pm->loadDataFromStore();
$staffs2=$ftm->getStaffs();
$i=1;
foreach ($staffs2 as $staffman){
	$namee=$staffman->getName();
	$role=$staffman->getRole();
	echo '<option value="';
	echo "$namee";
	echo '">';
	echo "$namee $role";
	echo "</option>";
	$i=$i+1;
}
?>

	</select>
	<?php 
if (isset($_SESSION["errorStaffD"])) {
	echo $_SESSION["errorStaffD"];
}
?>
	<input type="submit" value="Delete Staff Member">
	</form>
	</body>
	</html>