
<?php
require_once 'C:\Users\Julien\workspace3\FTMS2\classes\FoodTruckManager.php';

class PersistenceFoodTruckManager {
	private $filename;
	function __construct($filename = 'data.txt') {
		$this->filename = $filename;
	}
	function loadDataFromStore() {
		if (file_exists($this->filename)) {
			$str = file_get_contents($this->filename);
			$rm = unserialize($str);
		} else {
			$rm = FoodTruckManager::getInstance();
		}
		return $rm;
	}
	function writeDataToStore($rm) {
		$str = serialize($rm);
		file_put_contents($this->filename, $str);
	}
}
?>
	