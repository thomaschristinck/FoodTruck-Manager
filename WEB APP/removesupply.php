
<?php
session_start();
if($_SESSION["reqs"]>1){
$_SESSION["reqs"]=$_SESSION["reqs"]-1;
}
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content='0; url=/FTMS2/itemmenu.php'/>
</head>
</html>