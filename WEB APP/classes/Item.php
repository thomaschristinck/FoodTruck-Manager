<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private $name;
  private $itemQuantity;

  //Item Associations
  private $supply;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aItemQuantity)
  {
    $this->name = $aName;
    $this->itemQuantity = $aItemQuantity;
    $this->supply = array();
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

  public function setItemQuantity($aItemQuantity)
  {
    $wasSet = false;
    $this->itemQuantity = $aItemQuantity;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getItemQuantity()
  {
    return $this->itemQuantity;
  }

  public function getSupply_index($index)
  {
    $aSupply = $this->supply[$index];
    return $aSupply;
  }

  public function getSupply()
  {
    $newSupply = $this->supply;
    return $newSupply;
  }

  public function numberOfSupply()
  {
    $number = count($this->supply);
    return $number;
  }

  public function hasSupply()
  {
    $has = $this->numberOfSupply() > 0;
    return $has;
  }

  public function indexOfSupply($aSupply)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->supply as $supply)
    {
      if ($supply->equals($aSupply))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfSupply()
  {
    return 0;
  }

  public function addSupply($aSupply)
  {
    $wasAdded = false;
    if ($this->indexOfSupply($aSupply) !== -1) { return false; }
    $this->supply[] = $aSupply;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSupply($aSupply)
  {
    $wasRemoved = false;
    if ($this->indexOfSupply($aSupply) != -1)
    {
      unset($this->supply[$this->indexOfSupply($aSupply)]);
      $this->supply = array_values($this->supply);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addSupplyAt($aSupply, $index)
  {  
    $wasAdded = false;
    if($this->addSupply($aSupply))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupply()) { $index = $this->numberOfSupply() - 1; }
      array_splice($this->supply, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supply, $index, 0, array($aSupply));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveSupplyAt($aSupply, $index)
  {
    $wasAdded = false;
    if($this->indexOfSupply($aSupply) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupply()) { $index = $this->numberOfSupply() - 1; }
      array_splice($this->supply, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supply, $index, 0, array($aSupply));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addSupplyAt($aSupply, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->supply = array();
  }

}
?>