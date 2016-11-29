package ca.mcgill.ecse321.FoodTruckManagementSystem.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


public class MainMenu extends JFrame {
private static final long serialVersionUID = -8062635784771606869L;
	//UI elements for Items
	private JButton inventoryPageButton;
	private JButton staffPageButton;
	private JButton menuPageButton;
	
	
	public MainMenu(){
		initComponents();
		refreshData();
	}

	/* This method is called from within the constructor to initialize the form*/
	private void initComponents(){
		
		//Elements for adding/removing supplies to item, adding/removing items
		inventoryPageButton = new JButton();
		staffPageButton = new JButton();
		menuPageButton = new JButton();
		
		//Global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Food Truck Manager Main Menu");
		inventoryPageButton.setText("Inventory");
		staffPageButton.setText("Staff & Scheduling");
		menuPageButton.setText("Menu & Orders");
		
		
		inventoryPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt){
				inventoryPageButtonActionPerformed(evt);
			}

			private void inventoryPageButtonActionPerformed(ActionEvent evt) {
				 MainMenu.this.dispose();
			     new InventoryPage().setVisible(true);
			}
		});
		

		staffPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt){
				staffPageButtonActionPerformed(evt);
			}

			private void staffPageButtonActionPerformed(ActionEvent evt) {
				MainMenu.this.dispose();
				new StaffPage().setVisible(true);
			}

		
		});
		
		menuPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt){
				menuPageButtonActionPerformed(evt);
			}

			private void menuPageButtonActionPerformed(ActionEvent evt) {
				 MainMenu.this.dispose();
			     new MenuPage().setVisible(true);
				
			}
		});

		
	
		//layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(staffPageButton)
								.addComponent(inventoryPageButton)
								.addComponent(menuPageButton)))
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] 
				{staffPageButton, menuPageButton, inventoryPageButton});
		
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(staffPageButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(inventoryPageButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(menuPageButton)));
		pack();
	}
	
	private void refreshData(){
		pack();
	}
}




