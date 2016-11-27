package ca.mcgill.ecse321.FoodTruckManagementSystem.view;

import java.awt.Color;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.FoodTruckManagementSystem.controller.InvalidInputException;
import ca.mcgill.ecse321.FoodTruckManagementSystem.controller.MenuController;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Item;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Order;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Supply;


public class MenuPage extends JFrame {
private static final long serialVersionUID = -8062635784771606869L;
	//UI elements for Items
	private JLabel errorMessage;
	private JLabel itemTitle;
	private JTextField itemNameTextField;
	private JTextField descriptionTextField;
	private JLabel itemNameLabel;
	private JLabel descriptionLabel;
	private JButton addToItemButton;
	private JButton removeFromItemButton;
	private JButton addItemButton;
	private JButton removeItemButton;
	private JComboBox<String> supplyList;
	private JLabel supplyListLabel;
	private JComboBox<String> itemList;
	private JLabel itemListLabel;
	
	//UI elements for Orders
	private JLabel orderTitle;
	private JButton addItemToOrderButton;
	private JButton removeItemFromOrderButton;
	private JButton makeOrderButton;
	private JComboBox<String> itemList2;
	private JLabel itemListLabel2;
	private JComboBox<String> orderItemList;
	private JLabel orderItemListLabel;
	
	//Data elements for both items and orders
	private String error = null;
	private Integer selectedSupply = -1;
	private HashMap<Integer, Supply> supply;
	private Integer selectedItem = -1;
	private Integer selectedItem2 = -1;
	private HashMap<Integer, Item> item;
	private Integer selectedOrderItem = -1;
	private HashMap<Integer, Item> orderItem;
	
	private JButton viewStatsButton;
	private JButton viewMenuButton;


	
	/* new form EventRegistrationPage */
	public MenuPage(){
		initComponents();
		refreshData();
	}

	/* This method is called from within the constructor to initialize the form*/
	private void initComponents(){
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		//Elements for item lists
		supplyList = new JComboBox<String>(new String[0]);
		supplyList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSupply = cb.getSelectedIndex();
				System.out.println("SUPPLY LIST INDEX:" + cb.getSelectedIndex());
			}
		});
		supplyListLabel = new JLabel();
		
		
		itemList = new JComboBox<String>(new String[0]);
		itemList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb2 = (JComboBox<String>) evt.getSource();
				selectedItem = cb2.getSelectedIndex();
				System.out.println("ITEM LIST INDEX:" + cb2.getSelectedIndex());
			}
		});
		itemListLabel = new JLabel();
		
		
		//Elements for order lists
		itemList2 = new JComboBox<String>(new String[0]);
		itemList2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb3 = (JComboBox<String>) evt.getSource();
				selectedItem2 = cb3.getSelectedIndex();
			}
		});
		itemListLabel2 = new JLabel();
		
		orderItemList = new JComboBox<String>(new String[0]);
		orderItemList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb4 = (JComboBox<String>) evt.getSource();
				selectedOrderItem = cb4.getSelectedIndex();
			}
		});
		orderItemListLabel = new JLabel();
		
		
		//Elements for adding/removing supplies to item, adding/removing items
		itemTitle = new JLabel();
		itemNameTextField = new JTextField();
		itemNameLabel = new JLabel();
		descriptionTextField = new JTextField();
		descriptionLabel = new JLabel();
		addToItemButton = new JButton();
		removeFromItemButton = new JButton();
		addItemButton = new JButton();
		removeItemButton = new JButton();
		
		//Elements for adding/removing items to/from orders, making orders
		orderTitle = new JLabel();
		addItemToOrderButton = new JButton();
		removeItemFromOrderButton = new JButton();
		makeOrderButton = new JButton();
	
		//Elements for view
		viewStatsButton = new JButton();
		viewMenuButton = new JButton();
		
		//Global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Menu Manager");
		itemTitle.setText("Items");
		itemNameLabel.setText("Item Name:");
		descriptionLabel.setText("Item Description:");
		supplyListLabel.setText("Select Supply:");
		addToItemButton.setText("Add to Item");
		removeFromItemButton.setText("Remove From Item");
		addItemButton.setText("Add Item");
		itemListLabel.setText("Select Item:");
		removeItemButton.setText("Remove Item");
		
		orderTitle.setText("Orders");
		itemListLabel2.setText("Select Item:");
		addItemToOrderButton.setText("Add to Order");
		orderItemListLabel.setText("Select Item:");
		removeItemFromOrderButton.setText("Remove from Order");
		makeOrderButton.setText("Make Order");
		viewStatsButton.setText("View Statistics");
		viewMenuButton.setText("View Menu");
		
		
		addToItemButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addToItemButtonActionPerformed(evt);
			}
		});
		
		removeFromItemButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				removeFromItemButtonActionPerformed(evt);
			}
		});
		
	
		addItemButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addItemButtonActionPerformed(evt);
			}
		});

		removeItemButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				removeItemButtonActionPerformed(evt);
			}
		});
		
		addItemToOrderButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addItemToOrderButtonActionPerformed(evt);
			}
		});
		
		removeItemFromOrderButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				removeItemFromOrderButtonActionPerformed(evt);
			}
		});
		
		makeOrderButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				makeOrderButtonActionPerformed(evt);
			}
		});
	
	
	
		//layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(itemTitle)
								.addComponent(itemNameLabel)
								.addComponent(descriptionLabel)
								.addComponent(addItemButton)
								.addComponent(itemListLabel)
								.addComponent(supplyListLabel)
								.addComponent(addToItemButton)
								.addComponent(removeFromItemButton)
								.addComponent(removeItemButton)
								.addComponent(viewMenuButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(itemNameTextField)
								.addComponent(descriptionTextField)
								.addComponent(itemList)
								.addComponent(supplyList)
								.addComponent(viewStatsButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(orderTitle)
								.addComponent(itemListLabel2)
								.addComponent(addItemToOrderButton)
								.addComponent(orderItemListLabel)
								.addComponent(removeItemFromOrderButton)
								.addComponent(makeOrderButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(itemList2)
								.addComponent(orderItemList)))
				);
		
		/*layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] 
				{addToItemButton, removeFromItemButton, addItemButton, removeItemButton});
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] 
				{addItemToOrderButton, removeItemFromOrderButton, makeOrderButton});*/
		
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(itemTitle)
						.addComponent(orderTitle))
				.addGroup(layout.createParallelGroup()
						.addComponent(itemNameLabel)
						.addComponent(itemNameTextField)
						.addComponent(itemListLabel2)
						.addComponent(itemList2))
				.addGroup(layout.createParallelGroup()
						.addComponent(descriptionLabel)
						.addComponent(descriptionTextField)
						.addComponent(addItemToOrderButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(addItemButton)
						.addComponent(orderItemListLabel)
						.addComponent(orderItemList))
				.addGroup(layout.createParallelGroup()
						.addComponent(itemListLabel)
						.addComponent(itemList)
						.addComponent(removeItemFromOrderButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(supplyListLabel)
						.addComponent(supplyList)
						.addComponent(makeOrderButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(addToItemButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(removeFromItemButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(removeItemButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(viewMenuButton)
						.addComponent(viewStatsButton)));
		pack();
	}
	
	private void refreshData(){
		FoodTruckManager fm = FoodTruckManager.getInstance();

		//Set error message if there is one
		errorMessage.setText(error);
		if (error == null || error.length() == 0){
			
			//Supply list
			supply = new HashMap<Integer, Supply>();
			supplyList.removeAllItems();
			Iterator<Supply> sIt = fm.getSupplies().iterator();
			Integer index = 0;
			while(sIt.hasNext()){
				Supply s = sIt.next();
				supply.put(index,  s);
				supplyList.addItem(s.getName() + " (" + s.getQuantity() + ") " + "BB: " + bestBeforeToString(s.getBestBefore()));
				index++;
			}
			selectedSupply = -1;
			supplyList.setSelectedIndex(selectedSupply);
			
			//Item list
			item = new HashMap<Integer, Item>();
			itemList.removeAllItems();
			Iterator<Item> iIt = fm.getItems().iterator();
			Integer j = 0;
			while(iIt.hasNext()){
				Item itt = iIt.next();
				item.put(j, itt);
				itemList.addItem("" + itt.getName());
				j++;
			}
			selectedItem = -1;
			itemList.setSelectedIndex(selectedItem);
			
			
			//Item list 2
			item = new HashMap<Integer, Item>();
			itemList2.removeAllItems();
			Iterator<Item> itIt = fm.getItems().iterator();
			Integer k = 0;
			while(itIt.hasNext()){
				Item itt = itIt.next();
				item.put(k, itt);
				itemList2.addItem("" + itt.getName());
				k++;
			}
			selectedItem2 = -1;
			itemList2.setSelectedIndex(selectedItem2);
			
			//Order Item List 
			orderItem = new HashMap<Integer, Item>();
			//Iterator<Supply> suIt = fm.getSupplies().iterator();
			int orderNumber = fm.numberOfOrders();
			if(orderNumber > 0){
				orderItemList.removeAllItems();
				Order order = fm.getOrder(orderNumber);
				for(int p = 0; p < order.getItem().size(); p++){
					Item s = order.getItem(p);
					orderItem.put(p, s);
					orderItemList.addItem("" + s.getName());
				}
			selectedOrderItem = -1;
			orderItemList.setSelectedIndex(selectedOrderItem);
			}
			else{
				orderItemList = null;
			}
			
		//Item text fields empty
		itemNameTextField.setText("");
		descriptionTextField.setText("");
		
		//Size of window changes depending on whether there is an error message
		pack();
		}
	}
	
	private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		MenuController mc = new MenuController();
		error = null;
		try {
			mc.createItem(itemNameTextField.getText(), descriptionTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 

		//Update visuals
		refreshData();
	}
	
	private void removeItemButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		MenuController mc = new MenuController();
		error = null;
		try {
			mc.removeItem(item.get(selectedItem));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 

		//Update visuals
		refreshData();
	}
	
	private void removeFromItemButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		MenuController mc = new MenuController();
		error = null;
		try {
			mc.removeSupply(supply.get(selectedSupply), item.get(selectedItem));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 
		//Update visuals
		refreshData();
	}
	
	private void addToItemButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		MenuController mc = new MenuController();
		error = null;
		try {
			mc.addSupply(supply.get(selectedSupply), item.get(selectedItem));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 
		
		//Update visuals
		refreshData();
	}

	private void addItemToOrderButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		MenuController mc = new MenuController();
		error = null;
		try {
			mc.addItemToOrder(item.get(selectedItem2));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 
		//Update visuals
		refreshData();
	}
	
	private void removeItemFromOrderButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		MenuController mc = new MenuController();
		error = null;
		try {
			mc.removeItemFromOrder(orderItem.get(selectedOrderItem));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 
		//Update visuals
		refreshData();
	}
	
	private void makeOrderButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		MenuController mc = new MenuController();
		error = null;
		try {
			mc.makeOrder();
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 
		//Update visuals
		refreshData();
	}
	
	public String bestBeforeToString(Date bestBefore){
		String date = bestBefore.toString();
		String[] yearMonthDay = date.split("-");
		date = yearMonthDay[2] + "/" + yearMonthDay[1] + "/" + yearMonthDay[0];
		return date;
	}

}



