package ca.mcgill.ecse321.FoodTruckManagementSystem.view;

import java.awt.Color;
import java.sql.Time;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse321.FoodTruckManagementSystem.controller.StaffController;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.FoodTruckManager;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Shift;
import ca.mcgill.ecse321.FoodTruckManagementSystem.model.Staff;
import ca.mcgill.ecse321.FoodTruckManagementSystem.controller.InvalidInputException;


public class StaffPage extends JFrame {
private static final long serialVersionUID = -8062635784771606869L;
	
	//UI elements
	private JLabel errorMessage;
	private JTextField staffNameTextField;
	private JTextField staffRoleTextField;
	private JLabel staffLabel;
	private JLabel staffNameLabel;
	private JLabel staffRoleLabel;
	private JButton addStaffButton;
	private JComboBox<String> staffList;
	private JButton removeStaffButton;
	private JLabel shiftLabel;
	private JComboBox<String> shiftList;
	private JDatePickerImpl shiftDatePicker;
	private JLabel shiftDateLabel;
	private JSpinner startTimeSpinner;
	private JLabel startTimeLabel;
	private JSpinner endTimeSpinner;
	private JLabel endTimeLabel;
	private JButton addShiftButton;
	private JButton removeShiftButton;
	private JButton addShiftToStaffButton;
	private JButton removeShiftFromStaffButton;
	private JLabel schedGeneratorLabel;
	private JLabel startDateLabel;
	private JLabel endDateLabel;
	private JDatePickerImpl startDatePicker;
	private JDatePickerImpl endDatePicker;
	private JButton generateSchedButton;
	private JComboBox<String> staffList2;
	private JLabel staffListLabel2;
	
	//data elements
	private String error = null;
	private Integer selectedStaff = -1;
	private HashMap<Integer, Staff> staff;
	private Integer selectedShift = -1;
	private HashMap<Integer, Shift> shift;
	
	/*Creates new form EventRegistrationPage */
	public StaffPage(){
		initComponents();
		refreshData();
	}

	/* This method is called from within the constructor to initialize the form*/
	private void initComponents(){
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		//Elements for staff list
		staffList = new JComboBox<String>(new String[0]);
		staffList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedStaff = cb.getSelectedIndex();
			}
		});
		staffLabel = new JLabel();
		
		//Elements for Staff List 2 (for the schedule builder)
		staffList2 = new JComboBox<String>(new String[0]);
		staffList2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedStaff = cb.getSelectedIndex();
			}
		});
		staffListLabel2 = new JLabel();
		
		//Elements for shift list
		shiftList = new JComboBox<String>(new String[0]);
		shiftList.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedShift = cb.getSelectedIndex();
			}
		});
		shiftLabel = new JLabel();
		
		//elements for staff
		staffNameTextField = new JTextField();
		staffNameLabel = new JLabel();
		staffNameTextField = new JTextField();
		staffNameLabel = new JLabel();
		staffRoleTextField = new JTextField();
		staffRoleLabel = new JLabel();
		staffRoleTextField = new JTextField();
		staffRoleLabel = new JLabel();
		addStaffButton = new JButton();
		removeStaffButton = new JButton();
		
		//elements for shift
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		shiftDatePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		shiftDateLabel = new JLabel();
		startTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
		startTimeSpinner.setEditor(startTimeEditor); //will only show current time
		startTimeLabel = new JLabel();
		endTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
		endTimeSpinner.setEditor(endTimeEditor); //will only show current time
		endTimeLabel = new JLabel();
		addShiftButton = new JButton();
		removeShiftButton = new JButton();
		addShiftToStaffButton = new JButton();
		removeShiftFromStaffButton = new JButton();
		
		//Elements for schedule
		SqlDateModel model2 = new SqlDateModel();
		SqlDateModel model3 = new SqlDateModel();
		Properties s = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		Properties e = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		schedGeneratorLabel = new JLabel();
		startDateLabel = new JLabel();
		endDateLabel = new JLabel();
		JDatePanelImpl startDatePanel = new JDatePanelImpl(model2, s);
		startDatePicker = new JDatePickerImpl(startDatePanel, new DateComponentFormatter());
		JDatePanelImpl endDatePanel = new JDatePanelImpl(model3, e);
		endDatePicker = new JDatePickerImpl(endDatePanel, new DateComponentFormatter());
		generateSchedButton = new JButton();
		staffListLabel2 = new JLabel();
		
		
		//global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Staff Manager");
		staffLabel.setText("Select Staff:");
		removeStaffButton.setText("Remove Staff");
		removeStaffButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				removeStaffButtonActionPerformed(evt);
			}
		});
		
		staffNameLabel.setText("Name:");
		staffRoleLabel.setText("Role:");
		addStaffButton.setText("Add Staff");
		addStaffButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addStaffButtonActionPerformed(evt);
			}
		});
		
		shiftDateLabel.setText("Date:");
		startTimeLabel.setText("Start Time:");
		endTimeLabel.setText("End Time:");
		addShiftButton.setText("Add Shift");
		addShiftButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addShiftButtonActionPerformed(evt);
			}
		});
		shiftLabel.setText("Select Shift:");
		removeShiftButton.setText("Remove Shift");
		removeShiftButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				removeShiftButtonActionPerformed(evt);
			}
		});
		addShiftToStaffButton.setText("Assign Shift to Staff");
		addShiftToStaffButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				addShiftToStaffButtonActionPerformed(evt);
			}
		});
		removeShiftFromStaffButton.setText("Unassign Shift");
		removeShiftFromStaffButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				removeShiftFromStaffButtonActionPerformed(evt);
			}
		});
		schedGeneratorLabel.setText("Select Parameters for Schedule:");
		startDateLabel.setText("Start Date:");
		endDateLabel.setText("End Date:");
		staffListLabel2.setText("Select Staff:");
		generateSchedButton.setText("Generate Schedule");
		generateSchedButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				generateSchedButtonActionPerformed(evt);
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
								.addComponent(staffNameLabel)
								.addComponent(staffRoleLabel)
								.addComponent(addStaffButton)
								.addComponent(staffLabel)
								.addComponent(shiftLabel)
								.addComponent(addShiftToStaffButton)
								.addComponent(schedGeneratorLabel)
								.addComponent(staffListLabel2)
								.addComponent(startDateLabel)
								.addComponent(generateSchedButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(staffNameTextField, 200, 200, 400)
								.addComponent(staffRoleTextField, 200, 200, 400)
								.addComponent(staffList)
								.addComponent(shiftList)
								.addComponent(removeShiftFromStaffButton)
								.addComponent(staffList2)
								.addComponent(startDatePicker))
						.addGroup(layout.createParallelGroup()
								.addComponent(shiftDateLabel)
								.addComponent(startTimeLabel)
								.addComponent(endTimeLabel)
								.addComponent(addShiftButton)
								.addComponent(shiftLabel)
								.addComponent(removeStaffButton)
								.addComponent(removeShiftButton)
								.addComponent(endDateLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(shiftDatePicker)
								.addComponent(startTimeSpinner)
								.addComponent(endTimeSpinner)
								.addComponent(endDatePicker)))
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] 
				{addStaffButton, addShiftButton, removeShiftButton, staffNameTextField, staffRoleTextField});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] 
				{addShiftToStaffButton, removeShiftFromStaffButton, removeStaffButton, staffLabel, addStaffButton});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(staffNameLabel)
						.addComponent(staffNameTextField)
						.addComponent(shiftDateLabel)
						.addComponent(shiftDatePicker))
				.addGroup(layout.createParallelGroup()
						.addComponent(staffRoleLabel)						
						.addComponent(staffRoleTextField)
						.addComponent(startTimeLabel)
						.addComponent(startTimeSpinner))
				.addGroup(layout.createParallelGroup()
						.addComponent(addStaffButton)
						.addComponent(endTimeLabel)
						.addComponent(endTimeSpinner))
				.addGroup(layout.createParallelGroup()
						.addComponent(addShiftButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(staffLabel)
						.addComponent(staffList)
						.addComponent(removeStaffButton))
				.addGroup(layout.createParallelGroup()
					.addComponent(shiftLabel)
					.addComponent(shiftList)
					.addComponent(removeShiftButton))
				.addGroup(layout.createParallelGroup()
					.addComponent(addShiftToStaffButton)
					.addComponent(removeShiftFromStaffButton))
				.addComponent(schedGeneratorLabel)
				.addGroup(layout.createParallelGroup()
					.addComponent(staffListLabel2)
					.addComponent(staffList2))
				.addGroup(layout.createParallelGroup()
					.addComponent(startDateLabel)
					.addComponent(startDatePicker)
					.addComponent(endDateLabel)
					.addComponent(endDatePicker))
				.addComponent(generateSchedButton)
				);
		pack();
	}
	
	private void refreshData(){
		FoodTruckManager fm = FoodTruckManager.getInstance();
		//error
		errorMessage.setText(error);
		if (error == null || error.length() == 0){
			//Staff list
			staff = new HashMap<Integer, Staff>();
			staffList.removeAllItems();
			Iterator<Staff> pIt = fm.getStaffs().iterator();
			Integer index = 0;
			while(pIt.hasNext()){
				Staff p = pIt.next();
				staff.put(index,  p);
				staffList.addItem("" + p.getName() + " (" + p.getRole() + ")");
				index++;
			}
			selectedStaff = -1;
			staffList.setSelectedIndex(selectedStaff);
			
			//Staff list 2 (schedule builder staff list)
			staff = new HashMap<Integer, Staff>();
			staffList2.removeAllItems();
			Iterator<Staff> sIt = fm.getStaffs().iterator();
			index = 0;
			while(sIt.hasNext()){
				Staff s = sIt.next();
				staff.put(index,  s);
				staffList2.addItem("" + s.getName() + " (" + s.getRole() + ")");
				index++;
			}
			selectedStaff = -1;
			staffList2.setSelectedIndex(selectedStaff);
			
			
			//Shift list
			shift = new HashMap<Integer, Shift>();
			shiftList.removeAllItems();
			Iterator<Shift> shIt = fm.getShifts().iterator();
			index = 0;
			while(shIt.hasNext()){
				Shift s = shIt.next();
				shift.put(index,  s);
				shiftList.addItem(shiftToString(s));
				index++;
			}
			selectedShift = -1;
			shiftList.setSelectedIndex(selectedShift);
			
		}
		//Staff text fields empty
		staffNameTextField.setText("");
		staffRoleTextField.setText("");
		
		//Shift empty fields
		shiftDatePicker.getModel().setValue(null);
		startTimeSpinner.setValue(new Date());
		endTimeSpinner.setValue(new Date());
		
		//Schedule empty fields
		startDatePicker.getModel().setValue(null);
		endDatePicker.getModel().setValue(null);
		
		//Size of window changes depending on whether there is an error message
		pack();
		
	}
	private void removeStaffButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		StaffController sc = new StaffController();
		error = null;
		try {
			sc.removeStaff(staff.get(selectedStaff));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		//Update visuals
		refreshData();
	}
	private void removeShiftButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call controller
		StaffController sc = new StaffController();
		error = null;
		try{
			sc.removeShift(shift.get(selectedShift));
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		//Update visuals
		refreshData();
	}
	
	private void addStaffButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		StaffController sc = new StaffController();
		error = null;
		try {
			sc.createStaff(staffNameTextField.getText(), staffRoleTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		//Update visuals
		refreshData();
	}
	private void addShiftButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		StaffController sc = new StaffController();
		//JSpinner returns date and time, force the same date for start/end time so only times differ
		Calendar calendar = Calendar.getInstance();
		calendar.setTime((Date) startTimeSpinner.getValue());
		calendar.set(2000,1,1);
		Time startTime = new Time(calendar.getTime().getTime());
		calendar.setTime((Date) endTimeSpinner.getValue());
		calendar.set(2000,1,1);
		Time endTime = new Time(calendar.getTime().getTime());
		error = null;
		try {
			sc.createShift((java.sql.Date) shiftDatePicker.getModel().getValue(), startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		//Update visuals
		refreshData();
	}
	private void addShiftToStaffButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		StaffController sc = new StaffController();
		//JSpinner returns date and time, force the same date for start/end time so only times differ
		
		error = null;
		try {
			sc.addShiftToStaff(staff.get(selectedStaff), shift.get(selectedShift));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		//Update visuals
		refreshData();
	}
	private void removeShiftFromStaffButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		StaffController sc = new StaffController();
		//JSpinner returns date and time, force the same date for start/end time so only times differ
		
		error = null;
		try {
			sc.removeShiftFromStaff(staff.get(selectedStaff), shift.get(selectedShift));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		//Update visuals
		refreshData();
	}

	private void generateSchedButtonActionPerformed(java.awt.event.ActionEvent evt){
		//Call the controller
		StaffController sc = new StaffController();
		
		error = null;
		try {
			//Edit later
			sc.removeShiftFromStaff(staff.get(selectedStaff), shift.get(selectedShift));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		//Update visuals
		refreshData();
	}
	private String shiftToString(Shift shift){
		String date = shift.getShiftDate().toString();
		String[] yearMonthDay = date.split("-");
		int monthInt = Integer.parseInt(yearMonthDay[1]);
		date = yearMonthDay[2] + " " + getMonth(monthInt) + ", " + yearMonthDay[0];
		
		String startTime = shift.getStartTime().toString();
		String[] sHourMinuteSec = startTime.split(":");
		startTime = sHourMinuteSec[0] + ":" + sHourMinuteSec[1];

		String endTime = shift.getEndTime().toString();
		String[] eHourMinuteSec = endTime.split(":");
		endTime = eHourMinuteSec[0] + ":" + eHourMinuteSec[1];
		
		String returnString;
		returnString = date + " (" + startTime + "-" + endTime + ")";
 		return returnString;
		
	}
	
	public String getMonth(int month) {
	    return new DateFormatSymbols().getMonths()[month-1];
	}

}


