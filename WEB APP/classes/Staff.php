<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class Staff
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Attributes
  private $name;
  private $role;

  //Staff Associations
  private $shift;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aRole)
  {
    $this->name = $aName;
    $this->role = $aRole;
    $this->shift = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setRole($aRole)
  {
    $wasSet = false;
    $this->role = $aRole;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getRole()
  {
    return $this->role;
  }

  public function getShift_index($index)
  {
    $aShift = $this->shift[$index];
    return $aShift;
  }

  public function getShift()
  {
    $newShift = $this->shift;
    return $newShift;
  }

  public function numberOfShift()
  {
    $number = count($this->shift);
    return $number;
  }

  public function hasShift()
  {
    $has = $this->numberOfShift() > 0;
    return $has;
  }

  public function indexOfShift($aShift)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->shift as $shift)
    {
      if ($shift->equals($aShift))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfShift()
  {
    return 0;
  }

  public function addShift($aShift)
  {
    $wasAdded = false;
    if ($this->indexOfShift($aShift) !== -1) { return false; }
    $this->shift[] = $aShift;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeShift($aShift)
  {
    $wasRemoved = false;
    if ($this->indexOfShift($aShift) != -1)
    {
      unset($this->shift[$this->indexOfShift($aShift)]);
      $this->shift = array_values($this->shift);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addShiftAt($aShift, $index)
  {  
    $wasAdded = false;
    if($this->addShift($aShift))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfShift()) { $index = $this->numberOfShift() - 1; }
      array_splice($this->shift, $this->indexOfShift($aShift), 1);
      array_splice($this->shift, $index, 0, array($aShift));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveShiftAt($aShift, $index)
  {
    $wasAdded = false;
    if($this->indexOfShift($aShift) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfShift()) { $index = $this->numberOfShift() - 1; }
      array_splice($this->shift, $this->indexOfShift($aShift), 1);
      array_splice($this->shift, $index, 0, array($aShift));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addShiftAt($aShift, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->shift = array();
  }

}
?>