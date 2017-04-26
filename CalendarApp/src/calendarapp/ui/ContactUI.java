/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Contact;
import calendarapp.backend.ContactController;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mpk5206
 */
public class ContactUI extends JFrame {
	private ContactController parentController;
	private Contact sourceContact;
	
	private int windowWidth;
	int windowHeight;
	
	private JComboBox titleComboBox;
	private JTextField firstNameTxtFld;
	private JTextField middleNameTxtFld;
	private JTextField lastNameTxtFld;
	private JTextField emailTxtFld;
	private JList emailList;
	private DefaultListModel emailListModel;
	private JTextField phoneNumberTxtFld;
	private JList phoneNumberList;
	private DefaultListModel phoneNumberListModel;
	
	public ContactUI (ContactController parentController) {
		System.out.println("Creating the ContactUI.");
		this.parentController = parentController;
		createWindow();
		addComponents();
		this.addWindowListener(new ContactUIWindowListener());
		System.out.println("Finished creating the ContactUI.");
	}
	
	public ContactUI (ContactController parentController, 
			Contact sourceContact) {
		System.out.println("Creating a ContactUI with sourceContact.");
		System.err.println("This is not supported yet.");
		this.parentController = parentController;
		this.sourceContact = sourceContact;
		createWindow();
		addComponents();
		populateFields();
		this.addWindowListener(new ContactUIWindowListener());
		System.out.println("Finished creating the ContactUI.");
	}
	
	private void createWindow() {
		//This method will create the physical JFrame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		windowHeight = (int) screenSize.getHeight() * 2 / 3; // 66% of screen height
		windowWidth = (int) screenSize.getWidth() / 5; //20% of screen width
		Dimension windowSize = new Dimension(windowWidth, windowHeight);
		this.setPreferredSize(windowSize);
		this.pack();
		this.setLocation((screenSize.width/2) - this.getWidth()/2, 
				screenSize.height/2 - this.getHeight()/2); // Centers Window
	}
	
	private void addComponents() {
		//This method will add the components to the JFramer
		Insets txtFldInsets = new Insets(0, getWidth() / 20 , getHeight() / 100,
				getWidth() / 20);
		setLayout(new GridBagLayout());
		String[] titles = {"", "Mr.", "Mrs.", "Miss"};
		titleComboBox = new JComboBox(titles);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, getWidth()/20, getHeight() / 100, 20);
		c.gridx = 0;
		c.anchor = GridBagConstraints.EAST;
		c.gridy = 0;
		add(titleComboBox, c);
		
		firstNameTxtFld = new JTextField("First Name");
		c = new GridBagConstraints();
		c.insets = new Insets(0, 0, getHeight() / 100, getWidth() / 20);
		c.gridx = 1;
		c.weightx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(firstNameTxtFld, c);
		
		middleNameTxtFld = new JTextField("Middle Name");
		middleNameTxtFld.setHorizontalAlignment(JTextField.CENTER);
		c = new GridBagConstraints();
		c.insets = txtFldInsets;
		c.gridx = 0;
		c.gridwidth = 2;
		c.weightx = .5;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(middleNameTxtFld, c);
		
		lastNameTxtFld = new JTextField("Last Name");
		lastNameTxtFld.setHorizontalAlignment(JTextField.CENTER);
		c = new GridBagConstraints();
		c.insets = txtFldInsets;
		c.gridx = 0;
		c.gridwidth = 2;
		c.weightx = .5;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(lastNameTxtFld, c);
		
		JLabel emails = new JLabel("Emails");
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		
		emailListModel = new DefaultListModel();
		emailList = new JList(emailListModel);
		c = new GridBagConstraints();
		c.insets = txtFldInsets;
		c.gridx = 0;
		c.gridwidth = 2;
		c.weightx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		JScrollPane emailPane = new JScrollPane(emailList);
		emailPane.setPreferredSize(new Dimension(50, 50));
		add(emailPane, c);
		
		emailTxtFld = new JTextField("Enter Email", 20);
		emailTxtFld.setHorizontalAlignment(JTextField.CENTER);
		c = new GridBagConstraints();
		c.insets = txtFldInsets;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 5;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		add(emailTxtFld, c);
		
		JPanel emailBtnArea = new JPanel(new GridBagLayout ());
		
		JButton addEmailBtn = new JButton("Add Email");
		addEmailBtn.setPreferredSize(new Dimension(50, 10));
		addEmailBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("AddEmailBtn Triggered.");
			System.out.println("This should be updated to validate the email.");
			emailListModel.addElement(emailTxtFld.getText());
		});
		c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 0, windowWidth/ 100);
		c.gridx = 0;
		c.weightx = 1;
		c.gridy = 0;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		emailBtnArea.add(addEmailBtn, c);
		
		JButton removeEmailBtn = new JButton("Remove Email");
		removeEmailBtn.setPreferredSize(new Dimension(50, 10));
		removeEmailBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("RemoveEmailBtn Triggered.");
			int[] selection = emailList.getSelectedIndices();
			int selectCount = selection.length;
			for(int i = selectCount - 1; i >= 0; i--) {
				emailListModel.remove(selection[i]);
			}
		});
		c = new GridBagConstraints();
		c.insets = new Insets(0, windowWidth/ 100, 0, 0);
		c.gridx = 1;
		c.weightx = 1;
		c.gridy = 0;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		emailBtnArea.add(removeEmailBtn, c);
		
		c = new GridBagConstraints();
		c.insets = txtFldInsets;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 6;
		c.weighty = .1;
		c.fill = GridBagConstraints.BOTH;
		add(emailBtnArea, c);
		
		JLabel phoneNumbers = new JLabel("PhoneNumbers");
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 7;
		c.anchor = GridBagConstraints.WEST;
		
		phoneNumberListModel = new DefaultListModel();
		phoneNumberList = new JList(phoneNumberListModel);
		c = new GridBagConstraints();
		c.insets = txtFldInsets;
		c.gridx = 0;
		c.gridwidth = 2;
		c.weightx = 1;
		c.gridy = 8;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		JScrollPane phoneNumberPane = new JScrollPane(phoneNumberList);
		phoneNumberPane.setPreferredSize(new Dimension(50, 50));
		add(phoneNumberPane, c);
		
		phoneNumberTxtFld = new JTextField("Enter PhoneNumber", 20);
		phoneNumberTxtFld.setHorizontalAlignment(JTextField.CENTER);
		c = new GridBagConstraints();
		c.insets = txtFldInsets;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 9;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		add(phoneNumberTxtFld, c);
		
		JPanel phoneNumberBtnArea = new JPanel(new GridBagLayout());
		JButton addPhoneNumberBtn = new JButton("Add PhoneNumber");
		addPhoneNumberBtn.setPreferredSize(new Dimension(50, 10));
		addPhoneNumberBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("AddPhoneNumberBtn Triggered.");
			System.out.println("This should be updated to validate the"
					+ " phoneNumber.");
			phoneNumberListModel.addElement(phoneNumberTxtFld.getText());
		});
		c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 0, windowWidth/ 100);
		c.gridx = 0;
		c.weightx = 1;
		c.gridy = 0;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.EAST;
		phoneNumberBtnArea.add(addPhoneNumberBtn, c);
		
		JButton removePhoneNumberBtn = new JButton("Remove PhoneNumber");
		removePhoneNumberBtn.setPreferredSize(new Dimension(50, 10));
		removePhoneNumberBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("RemovePhoneNumberBtn Triggered.");
			int[] selection = phoneNumberList.getSelectedIndices();
			int selectCount = selection.length;
			for(int i = selectCount - 1; i >= 0; i--) {
				phoneNumberListModel.remove(selection[i]);
			}
		});
		c = new GridBagConstraints();
		c.insets = new Insets(0, windowWidth/ 100, 0, 0);
		c.gridx = 1;
		c.weightx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		phoneNumberBtnArea.add(removePhoneNumberBtn, c);
		
		c = new GridBagConstraints();
		c.insets = txtFldInsets;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 10;
		c.weighty = .1;
		c.fill = GridBagConstraints.BOTH;
		add(phoneNumberBtnArea, c);
		
		
		JPanel buttonArea = new JPanel();
		configureButtonArea(buttonArea);
		c.insets = txtFldInsets;
		c.gridy = 11;
		c.anchor = GridBagConstraints.NORTH;
		c.weighty = 1;
		add(buttonArea, c);
	}
	
	private void configureButtonArea(JPanel buttonArea) {
		buttonArea.setLayout(new GridBagLayout());
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("CancelBtn triggered.");
			ContactUI.this.parentController.dispose();
		});
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		buttonArea.add(cancelBtn, c);
		
		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("SaveBtn triggered.");
			ContactUI.this.parentController.submit();
		});
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		buttonArea.add(saveBtn, c);
	}
	
	public Contact getContact() {
		String title = (String) titleComboBox.getSelectedItem();
                System.out.println(title);
		String firstName = firstNameTxtFld.getText();
		String middleName = middleNameTxtFld.getText();
		String lastName = lastNameTxtFld.getText();
		String suffix = "";
		ArrayList<String> emailArrayList = new ArrayList<>();
		for(int i = 0 ; i < emailListModel.size(); i++) {
			emailArrayList.add((String) emailListModel.get(i));
		}
		
		ArrayList<String> phoneNumberArrayList = new ArrayList<>();
		for(int i = 0 ; i < phoneNumberListModel.size(); i++) {
			phoneNumberArrayList.add((String) phoneNumberListModel.get(i));
		}
		return new Contact(title, firstName, middleName, lastName, suffix, 
				emailArrayList, phoneNumberArrayList);
	}
	public void populateFields() {
            firstNameTxtFld.setText(sourceContact.getName().getFirstName());
            middleNameTxtFld.setText(sourceContact.getName().getMiddleName());
            lastNameTxtFld.setText(sourceContact.getName().getLastName());            
            titleComboBox.setSelectedItem(sourceContact.getName().getTitle());
//            for(int i = 0; i < sourceContact.getPhoneNumbers().size(); i++){
//                phoneNumberListModel.addElement(sourceContact.getPhoneNumbers().get(i));
//            }
//            for(int i = 0; i < sourceContact.getEmailList().size(); i++){
//                emailListModel.addElement(sourceContact.getEmailList().get(i));
//            }
	}
	
	private class ContactUIWindowListener implements WindowListener {
	
		@Override
		public void windowClosed(WindowEvent we) {	} // Do Nothing
		@Override
		public void windowIconified(WindowEvent we) { }// Do Nothing
		@Override
		public void windowDeiconified(WindowEvent we) { } // Do Nothing
		@Override
		public void windowActivated(WindowEvent we) { } // Do Nothing
		@Override
		public void windowDeactivated(WindowEvent we) { } // Do Nothing
		@Override
		public void windowOpened(WindowEvent we) { } // Do Nothing
		@Override
		public void windowClosing(WindowEvent we) { 
			System.out.println("ContactUI closing.");
			ContactUI.this.parentController.dispose();
		}
	}
}
