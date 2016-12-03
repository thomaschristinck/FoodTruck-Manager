<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class Shift
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Shift Attributes
  private $startTime;
  private $endTime;
  private $shiftDate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aStartTime, $aEndTime, $aShiftDate)
  {
    $this->startTime = $aStartTime;
    $this->endTime = $aEndTime;
    $this->shiftDate = $aShiftDate;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setStartTime($aStartTime)
  {
    $wasSet = false;
    $this->startTime = $aStartTime;
    $wasSet = true;
    return $wasSet;
  }

  public function setEndTime($aEndTime)
  {
    $wasSet = false;
    $this->endTime = $aEndTime;
    $wasSet = true;
    return $wasSet;
  }

  public function setShiftDate($aShiftDate)
  {
    $wasSet = false;
    $this->shiftDate = $aShiftDate;
    $wasSet = true;
    return $wasSet;
  }

  public function getStartTime()
  {
    return $this->startTime;
  }

  public function getEndTime()
  {
    return $this->endTime;
  }

  public function getShiftDate()
  {
    return $this->shiftDate;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>