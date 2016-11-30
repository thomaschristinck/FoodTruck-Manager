package ca.mcgill.ecse321.FoodTruckManagementSystem.view;

import java.awt.Color;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse321.FoodTruckManagementSystem.controller.EquipmentController;
import ca.mcgill.ecse321.FoodTruckManagementSystem.controller.InvalidInputException;
import ca.mcgill.ecse321.FoodTruckManagementSystem.controller.SupplyController;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Equipment;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Supply;


public class InventoryPage extends JFrame {
private static final long serialVersionUID = -8062635784771606869L;
	//UI elements for Supplies
	private JLabel errorMessage;
	private JTextField supplyNameTextField;
	private JTextField supplyQuantityField;
	private JLabel supplyNameLabel;
	private JLabel supplyQuantityLabel;
	private JLabel supplyQuantityLabel2;
	private JLabel supplyBestBeforeLabel;
	private JButton addSupplyButton;
	private JButton removeSupplyButton;
	private JButton addSupplyToInventoryButton;
	private JButton removeSupplyFromInventoryButton;
	private JComboBox<String> supplyList;
	private JLabel supplyListLabel;
	private JDatePickerImpl supplyBestBeforePicker;
	private JComboBox<String> supplyList2;
	private JLabel supplyListLabel2;
	private JTextField supplyQuantityField2;
	
	//UI elements for equipment
	private JTextField equipmentNameTextField;
	private JTextField equipmentQuantityField;
	private JLabel equipmentNameLabel;
	private JLabel equipmentQuantityLabel;
	private JLabel equipmentQuantityLabel2;
	private JButton addEquipmentButton;
	private JButton removeEquipmentButton;
	private JButton addEquipmentToInventoryButton;
	private JButton removeEquipmentFromInventoryButton;
	private JComboBox<String> equipmentList;
	private JLabel equipmentListLabel;
	private JComboBox<String> equipmentList2;
	private JLabel equipmentListLabel2;
	private JTextField equipmentQuantityField2;
	
	//Data elements for both equipment and supplies
	private String error = null;
	private Integer selectedSupply = -1;
	private HashMap<Integer, Supply> supply;
	private Integer selectedSupply2 = -1;
	private Integer selectedEquipment = -1;
	private HashMap<Integer, Equipment> equipment;
	private Integer selectedEquipment2 = -1;

	
	/*Creates new form EventRegistrationPage */
	public InventoryPage(){
		initComponents();
		refreshData();
	}

	/* This method is called from within the constructor to initialize the form*/
	private void initComponents(){
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		//Elements for supply list
		supplyList = new JComboBox<String>(new String[0]);
		supplyList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSupply = cb.getSelectedIndex();
			}
		});
		supplyListLabel = new JLabel();
		
		supplyList2 = new JComboBox<String>(new String[0]);
		supplyList2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb2 = (JComboBox<String>) evt.getSource();
				selectedSupply2 = cb2.getSelectedIndex();
			}
		});
		supplyListLabel2 = new JLabel();
		
		
		//Elements for equipment list
		equipmentList = new JComboBox<String>(new String[0]);
		equipmentList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedEquipment = cb.getSelectedIndex();
			}
		});
		equipmentListLabel = new JLabel();
		
		equipmentList2 = new JComboBox<String>(new String[0]);
		equipmentList2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb2 = (JComboBox<String>) evt.getSource();
				selectedEquipment2 = cb2.getSelectedIndex();
			}
		});
		equipmentListLabel2 = new JLabel();
		
		
		//Elements for adding/removing a supply
		supplyNameTextField = new JTextField();
		supplyNameLabel = new JLabel();
		supplyQuantityField = new JTextField();
		supplyQuantityLabel = new JLabel();
		supplyBestBeforeLabel = new JLabel();
		addSupplyButton = new JButton();
		removeSupplyButton = new JButton();
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		supplyBestBeforePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
	
		
		//Elements for adding/removing Supply to/from inventory
		addSupplyToInventoryButton = new JButton();
		removeSupplyFromInventoryButton = new JButton();
		supplyQuantityLabel2 = new JLabel();
		supplyQuantityField2 = new JTextField();
	
		//Elements for adding/removing Equipment
		equipmentNameTextField = new JTextField();
		equipmentQuantityField = new JTextField();
		equipmentNameLabel = new JLabel();
		equipmentQuantityLabel = new JLabel();
		addEquipmentButton = new JButton();
		removeEquipmentButton = new JButton();
		
		//Elements for adding/removing Equipment to/from inventory
		equipmentQuantityLabel2 = new JLabel();
		addEquipmentToInventoryButton = new JButton();
		removeEquipmentFromInventoryButton = new JButton();
		equipmentQuantityField2 = new JTextField();
		
		//Global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Inventory Manager");
		supplyNameLabel.setText("Supply:");
		supplyQuantityLabel.setText("Quantity:");
		supplyBestBeforeLabel.setText("Best Before:");
		supplyListLabel.setText("Select Supply:");
		supplyListLabel2.setText("Select Supply:");
		addSupplyButton.setText("Add Supply");
		removeSupplyButton.setText("Remove Supply");
		addSupplyToInventoryButton.setText("Add Supply to Inventory");
		removeSupplyFromInventoryButton.setText("Remove From Inventory");
		
		equipmentNameLabel.setText("Equipment:");
		equipmentQuantityLabel.setText("Quantity:");
		equipmentListLabel.setText("Select Equipment:");
		equipmentListLabel.setText("Select Equipment:");
		equipmentListLabel2.setText("Select Equipment to Remove:");
		equipmentQuantityLabel2.setText("Quantity:");
		addEquipmentButton.setText("Add Equipment");
		removeEquipmentButton.setText("Remove Equipment");
		addEquipmentToInventoryButton.setText("Add Equipment to Inventory");
		removeEquipmentFromInventoryButton.setText("Remove From Inventory");
		
		addSupplyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addSupplyButtonActionPerformed(evt);
			}
		});
		
		removeSupplyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				removeSupplyButtonActionPerformed(evt);
			}
		});
		
	
		addSupplyToInventoryButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addSupplyToInventoryButtonActionPerformed(evt);
			}
		});

		removeSupplyFromInventoryButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				removeSupplyFromInventoryButtonActionPerformed(evt);
			}
		});
		
		addEquipmentToInventoryButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addEquipmentToInventoryButtonActionPerformed(evt);
			}
		});
		
		removeEquipmentFromInventoryButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				removeEquipmentFromInventoryButtonActionPerformed(evt);
			}
		});
		
		addEquipmentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addEquipmentButtonActionPerformed(evt);
			}
		});
		
		removeEquipmentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				removeEquipmentButtonActionPerformed(evt);
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
								.addComponent(supplyListLabel)
								.addComponent(supplyQuantityLabel)
								.addComponent(addSupplyToInventoryButton)
								.addComponent(supplyNameLabel)
								.addComponent(supplyQuantityLabel2)
								.addComponent(supplyBestBeforeLabel)
								.addComponent(addSupplyButton)
								.addComponent(supplyListLabel2)
								.addComponent(removeSupplyButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(supplyList)
								.addComponent(supplyQuantityField)
								.addComponent(removeSupplyFromInventoryButton)
								.addComponent(supplyNameTextField)
								.addComponent(supplyQuantityField2, 25, 25, 50)
								.addComponent(supplyBestBeforePicker)
								.addComponent(supplyList2))
						.addGroup(layout.createParallelGroup()
								.addComponent(equipmentListLabel)
								.addComponent(equipmentQuantityLabel)
								.addComponent(addEquipmentToInventoryButton)
								.addComponent(equipmentNameLabel)
								.addComponent(equipmentQuantityLabel2)
								.addComponent(addEquipmentButton)
								.addComponent(equipmentListLabel2)
								.addComponent(removeEquipmentButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(equipmentList)
								.addComponent(equipmentQuantityField)
								.addComponent(removeEquipmentFromInventoryButton)
								.addComponent(equipmentNameTextField)
								.addComponent(equipmentQuantityField2, 25, 25, 50)
								.addComponent(equipmentList2)))
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] 
				{supplyNameLabel, supplyBestBeforeLabel, supplyQuantityLabel});
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] 
				{addEquipmentButton, removeEquipmentButton, addSupplyButton, removeSupplyButton});
		
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(supplyListLabel)
						.addComponent(supplyList)
						.addComponent(equipmentListLabel)
						.addComponent(equipmentList))
				.addGroup(layout.createParallelGroup()
						.addComponent(supplyQuantityLabel)
						.addComponent(supplyQuantityField)
						.addComponent(equipmentQuantityLabel)
						.addComponent(equipmentQuantityField))
				.addGroup(layout.createParallelGroup()
						.addComponent(addSupplyToInventoryButton)
						.addComponent(removeSupplyFromInventoryButton)
						.addComponent(addEquipmentToInventoryButton)
						.addComponent(removeEquipmentFromInventoryButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(supplyNameLabel)
						.addComponent(supplyNameTextField)
						.addComponent(equipmentNameLabel)
						.addComponent(equipmentNameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(supplyQuantityLabel2)						
						.addComponent(supplyQuantityField2)
						.addComponent(equipmentQuantityLabel2)						
						.addComponent(equipmentQuantityField2))
				.addGroup(layout.createParallelGroup()
						.addComponent(supplyBestBeforeLabel)
						.addComponent(supplyBestBeforePicker))
				.addGroup(layout.createParallelGroup()
						.addComponent(addSupplyButton)
						.addComponent(addEquipmentButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(supplyListLabel2)
						.addComponent(supplyList2)
						.addComponent(equipmentListLabel2)
						.addComponent(equipmentList2))
				.addGroup(layout.createParallelGroup()
						.addComponent(removeSupplyButton)
						.addComponent(removeEquipmentButton)));
		pack();
	}
	
	private void refreshData(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		//Set error message if there is one
		errorMessage.setText(error);
		if (error == null || error.length() == 0){
			//Supply list 1
			supply = new HashMap<Integer, Supply>();
			supplyList.removeAllItems();
			Iterator<Supply> sIt = fm.getSupplies().iterator();
			Integer index = 0;
			while(sIt.hasNext()){
				Supply s = sIt.next();
				supply.put(index,  s);
				String listText = String.format("%-20s %s", s.getName() + " (" + s.getQuantity() + ")", "BB: " + bestBeforeToString(s.getBestBefore()));
				supplyList.addItem(listText);
				index++;
			}
			selectedSupply = -1;
			supplyList.setSelectedIndex(selectedSupply);
			
			//Supply list 1
			supplyList2.removeAllItems();
			Iterator<Supply> suIt = fm.getSupplies().iterator();
			Integer i = 0;
			while(suIt.hasNext()){
				Supply s = suIt.next();
				supply.put(i,  s);
				String listText = String.format("%-20s %s", s.getName() + " (" + s.getQuantity() + ")", "BB: " + bestBeforeToString(s.getBestBefore()));
				supplyList2.addItem(listText);
				i++;
			}
			selectedSupply2 = -1;
			supplyList2.setSelectedIndex(selectedSupply2);
			
			//Equipment List 1
			equipment = new HashMap<Integer, Equipment>();
			equipmentList.removeAllItems();
			Iterator<Equipment> eIt = fm.getEquipment().iterator();
			Integer j = 0;
			while(eIt.hasNext()){
				Equipment e = eIt.next();
				equipment.put(j,  e);
				equipmentList.addItem(e.getName() + " (" + e.getQuantity() + ")");
				j++;
			}
			selectedEquipment = -1;
			equipmentList.setSelectedIndex(selectedEquipment);
			
			//Equipment List 2
			equipmentList2.removeAllItems();
			Iterator<Equipment> eqIt = fm.getEquipment().iterator();
			Integer k = 0;
			while(eqIt.hasNext()){
				Equipment e = eqIt.next();
				equipment.put(k,  e);
				equipmentList2.addItem(e.getName() + " (" + e.getQuantity() + ")");
				k++;
			}
			selectedEquipment2 = -1;
			equipmentList2.setSelectedIndex(selectedEquipment2);
		}
		
		//Supply text fields empty
		supplyNameTextField.setText("");
		supplyQuantityField.setText("");
		supplyQuantityField2.setText("");
		
		//Equipment text fields empty
		equipmentNameTextField.setText("");
		equipmentQuantityField.setText("");
		equipmentQuantityField2.setText("");
		
		//Size of window changes depending on whether there is an error message
		pack();
		
	}
	
	private void addSupplyButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		SupplyController sc = new SupplyController();
		error = null;
		try {
			sc.createSupply(supplyNameTextField.getText(), supplyQuantityField2.getText(), (java.sql.Date) supplyBestBeforePicker.getModel().getValue());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 

		//Update visuals
		refreshData();
	}
	
	private void removeSupplyButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		SupplyController sc = new SupplyController();
		error = null;
		try {
			sc.removeSupply(supply.get(selectedSupply2));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 

		//Update visuals
		refreshData();
	}
	
	private void addSupplyToInventoryButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		SupplyController sc = new SupplyController();
		error = null;
		try {
			sc.addToSupplyInventory(supply.get(selectedSupply), supplyQuantityField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 
		//Update visuals
		refreshData();
	}
	
	private void removeSupplyFromInventoryButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		SupplyController sc = new SupplyController();
		error = null;
		try {
			sc.removeFromSupplyInventory(supply.get(selectedSupply), supplyQuantityField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 
		
		//Update visuals
		refreshData();
	}

	private void addEquipmentButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		EquipmentController ec = new EquipmentController();
		error = null;
		try {
			ec.createEquipment(equipmentNameTextField.getText(), equipmentQuantityField2.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 
		//Update visuals
		refreshData();
	}
	
	private void removeEquipmentButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		EquipmentController ec = new EquipmentController();
		error = null;
		try {
			ec.removeEquipment(equipment.get(selectedEquipment2));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 
		//Update visuals
		refreshData();
	}
	
	private void addEquipmentToInventoryButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		EquipmentController ec = new EquipmentController();
		error = null;
		try {
			ec.addToEquipmentInventory(equipment.get(selectedEquipment), equipmentQuantityField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 
		
		//Update visuals
		refreshData();
	}
	
	private void removeEquipmentFromInventoryButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		EquipmentController ec = new EquipmentController();
		error = null;
		try {
			ec.removeFromEquipmentInventory(equipment.get(selectedEquipment), equipmentQuantityField.getText());
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



