<?php
require_once "InputValidator.php";
class ArrayValidator
{
	public static function validate_input($array)
	{	$newarray=$array;
		foreach($newarray as $value){
			if (InputValidator::validate_input($value)=="-1"){
				return "EMPTY ELEMENT";
			}
		}
		return "VALIDATED";
	}
}
?>