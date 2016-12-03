<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class FoodTruckManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodTruckManager Associations
  private $supplies;
  private $items;
  private $orders;
  private $staffs;
  private $shifts;
  private $equipment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private function __construct()
  {
    $this->supplies = array();
    $this->items = array();
    $this->orders = array();
    $this->staffs = array();
    $this->shifts = array();
    $this->equipment = array();
  }

  public static function getInstance()
  {
    if(self::$theInstance == null)
    {
      self::$theInstance = new FoodTruckManager();
    }
    return self::$theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getSupply_index($index)
  {
    $aSupply = $this->supplies[$index];
    return $aSupply;
  }

  public function getSupplies()
  {
    $newSupplies = $this->supplies;
    return $newSupplies;
  }

  public function numberOfSupplies()
  {
    $number = count($this->supplies);
    return $number;
  }

  public function hasSupplies()
  {
    $has = $this->numberOfSupplies() > 0;
    return $has;
  }

  public function indexOfSupply($aSupply)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->supplies as $supply)
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

  public function getItem_index($index)
  {
    $aItem = $this->items[$index];
    return $aItem;
  }

  public function getItems()
  {
    $newItems = $this->items;
    return $newItems;
  }

  public function numberOfItems()
  {
    $number = count($this->items);
    return $number;
  }

  public function hasItems()
  {
    $has = $this->numberOfItems() > 0;
    return $has;
  }

  public function indexOfItem($aItem)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->items as $item)
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

  public function getOrder_index($index)
  {
    $aOrder = $this->orders[$index];
    return $aOrder;
  }

  public function getOrders()
  {
    $newOrders = $this->orders;
    return $newOrders;
  }

  public function numberOfOrders()
  {
    $number = count($this->orders);
    return $number;
  }

  public function hasOrders()
  {
    $has = $this->numberOfOrders() > 0;
    return $has;
  }

  public function indexOfOrder($aOrder)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->orders as $order)
    {
      if ($order->equals($aOrder))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getStaff_index($index)
  {
    $aStaff = $this->staffs[$index];
    return $aStaff;
  }

  public function getStaffs()
  {
    $newStaffs = $this->staffs;
    return $newStaffs;
  }

  public function numberOfStaffs()
  {
    $number = count($this->staffs);
    return $number;
  }

  public function hasStaffs()
  {
    $has = $this->numberOfStaffs() > 0;
    return $has;
  }

  public function indexOfStaff($aStaff)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->staffs as $staff)
    {
      if ($staff->equals($aStaff))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getShift_index($index)
  {
    $aShift = $this->shifts[$index];
    return $aShift;
  }

  public function getShifts()
  {
    $newShifts = $this->shifts;
    return $newShifts;
  }

  public function numberOfShifts()
  {
    $number = count($this->shifts);
    return $number;
  }

  public function hasShifts()
  {
    $has = $this->numberOfShifts() > 0;
    return $has;
  }

  public function indexOfShift($aShift)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->shifts as $shift)
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

  public function getEquipment_index($index)
  {
    $aEquipment = $this->equipment[$index];
    return $aEquipment;
  }

  public function getEquipment()
  {
    $newEquipment = $this->equipment;
    return $newEquipment;
  }

  public function numberOfEquipment()
  {
    $number = count($this->equipment);
    return $number;
  }

  public function hasEquipment()
  {
    $has = $this->numberOfEquipment() > 0;
    return $has;
  }

  public function indexOfEquipment($aEquipment)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->equipment as $equipment)
    {
      if ($equipment->equals($aEquipment))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfSupplies()
  {
    return 0;
  }

  public function addSupply($aSupply)
  {
    $wasAdded = false;
    if ($this->indexOfSupply($aSupply) !== -1) { return false; }
    $this->supplies[] = $aSupply;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSupply($aSupply)
  {
    $wasRemoved = false;
    if ($this->indexOfSupply($aSupply) != -1)
    {
      unset($this->supplies[$this->indexOfSupply($aSupply)]);
      $this->supplies = array_values($this->supplies);
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
      if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
      array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supplies, $index, 0, array($aSupply));
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
      if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
      array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supplies, $index, 0, array($aSupply));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addSupplyAt($aSupply, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfItems()
  {
    return 0;
  }

  public function addItem($aItem)
  {
    $wasAdded = false;
    if ($this->indexOfItem($aItem) !== -1) { return false; }
    $this->items[] = $aItem;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeItem($aItem)
  {
    $wasRemoved = false;
    if ($this->indexOfItem($aItem) != -1)
    {
      unset($this->items[$this->indexOfItem($aItem)]);
      $this->items = array_values($this->items);
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
      if($index > $this->numberOfItems()) { $index = $this->numberOfItems() - 1; }
      array_splice($this->items, $this->indexOfItem($aItem), 1);
      array_splice($this->items, $index, 0, array($aItem));
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
      if($index > $this->numberOfItems()) { $index = $this->numberOfItems() - 1; }
      array_splice($this->items, $this->indexOfItem($aItem), 1);
      array_splice($this->items, $index, 0, array($aItem));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addItemAt($aItem, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfOrders()
  {
    return 0;
  }

  public function addOrder($aOrder)
  {
    $wasAdded = false;
    if ($this->indexOfOrder($aOrder) !== -1) { return false; }
    $this->orders[] = $aOrder;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeOrder($aOrder)
  {
    $wasRemoved = false;
    if ($this->indexOfOrder($aOrder) != -1)
    {
      unset($this->orders[$this->indexOfOrder($aOrder)]);
      $this->orders = array_values($this->orders);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addOrderAt($aOrder, $index)
  {  
    $wasAdded = false;
    if($this->addOrder($aOrder))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOrders()) { $index = $this->numberOfOrders() - 1; }
      array_splice($this->orders, $this->indexOfOrder($aOrder), 1);
      array_splice($this->orders, $index, 0, array($aOrder));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveOrderAt($aOrder, $index)
  {
    $wasAdded = false;
    if($this->indexOfOrder($aOrder) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOrders()) { $index = $this->numberOfOrders() - 1; }
      array_splice($this->orders, $this->indexOfOrder($aOrder), 1);
      array_splice($this->orders, $index, 0, array($aOrder));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addOrderAt($aOrder, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfStaffs()
  {
    return 0;
  }

  public function addStaff($aStaff)
  {
    $wasAdded = false;
    if ($this->indexOfStaff($aStaff) !== -1) { return false; }
    $this->staffs[] = $aStaff;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeStaff($aStaff)
  {
    $wasRemoved = false;
    if ($this->indexOfStaff($aStaff) != -1)
    {
      unset($this->staffs[$this->indexOfStaff($aStaff)]);
      $this->staffs = array_values($this->staffs);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addStaffAt($aStaff, $index)
  {  
    $wasAdded = false;
    if($this->addStaff($aStaff))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStaffs()) { $index = $this->numberOfStaffs() - 1; }
      array_splice($this->staffs, $this->indexOfStaff($aStaff), 1);
      array_splice($this->staffs, $index, 0, array($aStaff));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveStaffAt($aStaff, $index)
  {
    $wasAdded = false;
    if($this->indexOfStaff($aStaff) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStaffs()) { $index = $this->numberOfStaffs() - 1; }
      array_splice($this->staffs, $this->indexOfStaff($aStaff), 1);
      array_splice($this->staffs, $index, 0, array($aStaff));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addStaffAt($aStaff, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfShifts()
  {
    return 0;
  }

  public function addShift($aShift)
  {
    $wasAdded = false;
    if ($this->indexOfShift($aShift) !== -1) { return false; }
    $this->shifts[] = $aShift;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeShift($aShift)
  {
    $wasRemoved = false;
    if ($this->indexOfShift($aShift) != -1)
    {
      unset($this->shifts[$this->indexOfShift($aShift)]);
      $this->shifts = array_values($this->shifts);
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
      if($index > $this->numberOfShifts()) { $index = $this->numberOfShifts() - 1; }
      array_splice($this->shifts, $this->indexOfShift($aShift), 1);
      array_splice($this->shifts, $index, 0, array($aShift));
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
      if($index > $this->numberOfShifts()) { $index = $this->numberOfShifts() - 1; }
      array_splice($this->shifts, $this->indexOfShift($aShift), 1);
      array_splice($this->shifts, $index, 0, array($aShift));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addShiftAt($aShift, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfEquipment()
  {
    return 0;
  }

  public function addEquipment($aEquipment)
  {
    $wasAdded = false;
    if ($this->indexOfEquipment($aEquipment) !== -1) { return false; }
    $this->equipment[] = $aEquipment;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEquipment($aEquipment)
  {
    $wasRemoved = false;
    if ($this->indexOfEquipment($aEquipment) != -1)
    {
      unset($this->equipment[$this->indexOfEquipment($aEquipment)]);
      $this->equipment = array_values($this->equipment);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addEquipmentAt($aEquipment, $index)
  {  
    $wasAdded = false;
    if($this->addEquipment($aEquipment))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipment()) { $index = $this->numberOfEquipment() - 1; }
      array_splice($this->equipment, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipment, $index, 0, array($aEquipment));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveEquipmentAt($aEquipment, $index)
  {
    $wasAdded = false;
    if($this->indexOfEquipment($aEquipment) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipment()) { $index = $this->numberOfEquipment() - 1; }
      array_splice($this->equipment, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipment, $index, 0, array($aEquipment));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEquipmentAt($aEquipment, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->supplies = array();
    $this->items = array();
    $this->orders = array();
    $this->staffs = array();
    $this->shifts = array();
    $this->equipment = array();
  }

}
?>