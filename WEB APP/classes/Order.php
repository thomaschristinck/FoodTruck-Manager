<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private $orderNumber;
  private $orderDate;
  private $orderTime;

  //Order Associations
  private $item;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aOrderNumber, $aOrderDate, $aOrderTime)
  {
    $this->orderNumber = $aOrderNumber;
    $this->orderDate = $aOrderDate;
    $this->orderTime = $aOrderTime;
    $this->item = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setOrderNumber($aOrderNumber)
  {
    $wasSet = false;
    $this->orderNumber = $aOrderNumber;
    $wasSet = true;
    return $wasSet;
  }

  public function setOrderDate($aOrderDate)
  {
    $wasSet = false;
    $this->orderDate = $aOrderDate;
    $wasSet = true;
    return $wasSet;
  }

  public function setOrderTime($aOrderTime)
  {
    $wasSet = false;
    $this->orderTime = $aOrderTime;
    $wasSet = true;
    return $wasSet;
  }

  public function getOrderNumber()
  {
    return $this->orderNumber;
  }

  public function getOrderDate()
  {
    return $this->orderDate;
  }

  public function getOrderTime()
  {
    return $this->orderTime;
  }

  public function getItem_index($index)
  {
    $aItem = $this->item[$index];
    return $aItem;
  }

  public function getItem()
  {
    $newItem = $this->item;
    return $newItem;
  }

  public function numberOfItem()
  {
    $number = count($this->item);
    return $number;
  }

  public function hasItem()
  {
    $has = $this->numberOfItem() > 0;
    return $has;
  }

  public function indexOfItem($aItem)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->item as $item)
    {
      if ($item->equals($aItem))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfItem()
  {
    return 0;
  }

  public function addItem($aItem)
  {
    $wasAdded = false;
   
    $this->item[] = $aItem;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeItem($aItem)
  {
    $wasRemoved = false;
    if ($this->indexOfItem($aItem) != -1)
    {
      unset($this->item[$this->indexOfItem($aItem)]);
      $this->item = array_values($this->item);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addItemAt($aItem, $index)
  {  
    $wasAdded = false;
    if($this->addItem($aItem))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfItem()) { $index = $this->numberOfItem() - 1; }
      array_splice($this->item, $this->indexOfItem($aItem), 1);
      array_splice($this->item, $index, 0, array($aItem));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveItemAt($aItem, $index)
  {
    $wasAdded = false;
    if($this->indexOfItem($aItem) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfItem()) { $index = $this->numberOfItem() - 1; }
      array_splice($this->item, $this->indexOfItem($aItem), 1);
      array_splice($this->item, $index, 0, array($aItem));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addItemAt($aItem, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->item = array();
  }

}
?>