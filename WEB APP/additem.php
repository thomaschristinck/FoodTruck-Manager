<?php
session_start();
$_SESSION["orderReqs"]=$_SESSION["orderReqs"]+1;
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content='0; url=/FTMS2/ordermenu.php'/>
</head>
</html>