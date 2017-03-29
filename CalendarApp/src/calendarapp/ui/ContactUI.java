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
	
	private void createWindow() {
		//This method will create the physical JFrame
		Dimension windowSize = new Dimension(375, 667);
		this.setPreferredSize(windowSize);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.pack();
		this.setLocation((screenSize.width/2) - this.getWidth()/2, 
				screenSize.height/2 - this.getHeight()/2);
	}
	
	private void addComponents() {
		//This method will add the components to the JFramer
		setLayout(new GridBagLayout());
		String[] titles = {"", "Mr.", "Mrs.", "Miss"};
		titleComboBox = new JComboBox(titles);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		add(titleComboBox, c);
		firstNameTxtFld = new JTextField("First Name");
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		add(firstNameTxtFld, c);
		middleNameTxtFld = new JTextField("Middle Name");
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 1;
		add(middleNameTxtFld, c);
		lastNameTxtFld = new JTextField("Last Name");
		c.gridy = 2;
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
		c.gridx = 0;
		c.gridwidth = 2;
		c.weightx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		JScrollPane emailPane = new JScrollPane(emailList);
		emailPane.setPreferredSize(new Dimension(50, 50));
		add(emailPane, c);
		
		emailTxtFld = new JTextField("Enter Email", 20);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		add(emailTxtFld, c);
		
		JButton addEmailBtn = new JButton("Add Email");
		addEmailBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("AddEmailBtn Triggered.");
			System.out.println("This should be updated to validate the email.");
			ContactUI.this.emailListModel.addElement(ContactUI.this.emailTxtFld.getText());
		});
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		add(addEmailBtn, c);
		
		JButton removeEmailBtn = new JButton("Remove Email");
		removeEmailBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("RemoveEmailBtn Triggered.");
			int[] selection = emailList.getSelectedIndices();
			int selectCount = selection.length;
			for(int i = selectCount - 1; i >= 0; i--) {
				emailListModel.remove(selection[i]);
			}
		});
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 6;
		add(removeEmailBtn, c);
		
		JLabel phoneNumbers = new JLabel("PhoneNumbers");
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 7;
		c.anchor = GridBagConstraints.WEST;
		
		phoneNumberListModel = new DefaultListModel();
		phoneNumberList = new JList(phoneNumberListModel);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridwidth = 2;
		c.weightx = 1;
		c.gridy = 8;
		c.anchor = GridBagConstraints.CENTER;
		JScrollPane phoneNumberPane = new JScrollPane(phoneNumberList);
		phoneNumberPane.setPreferredSize(new Dimension(50, 50));
		add(phoneNumberPane, c);
		
		phoneNumberTxtFld = new JTextField("Enter PhoneNumber", 20);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 9;
		c.anchor = GridBagConstraints.WEST;
		add(phoneNumberTxtFld, c);
		
		JButton addPhoneNumberBtn = new JButton("Add PhoneNumber");
		addPhoneNumberBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("AddPhoneNumberBtn Triggered.");
			System.out.println("This should be updated to validate the phoneNumber.");
			ContactUI.this.phoneNumberListModel.addElement(ContactUI.this.phoneNumberTxtFld.getText());
		});
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 10;
		add(addPhoneNumberBtn, c);
		
		JButton removePhoneNumberBtn = new JButton("Remove PhoneNumber");
		removePhoneNumberBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("RemovePhoneNumberBtn Triggered.");
			int[] selection = phoneNumberList.getSelectedIndices();
			int selectCount = selection.length;
			for(int i = selectCount - 1; i >= 0; i--) {
				phoneNumberListModel.remove(selection[i]);
			}
		});
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 10;
		add(removePhoneNumberBtn, c);
		
		
		
		JPanel buttonArea = new JPanel();
		configureButtonArea(buttonArea);
		c.gridy = 11;
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
		String firstName = firstNameTxtFld.getText();
		String middleName = "";
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
	
	private class ContactUIWindowListener implements WindowListener {
	
		@Override
		public void windowClosed(WindowEvent we) {	
//			System.out.println("ContactUI closed.");
//			ContactUI.this.parentController.dispose();
		} // Do Nothing
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
		} // Do Nothing
	}
	
//	private class EmailTableModel extends AbstractTableModel {
//		private Object[][] data;
//		private String[] columnNames = {"Email"};
//		private Abstract
//		
//		@Override
//		public int getRowCount() {
//			return
//		}
//
//		@Override
//		public int getColumnCount() {
//			return columnNames.length;
//		}
//
//		@Override
//		public Object getValueAt(int i, int i1) {
//			return data[i][i1];
//		}
//		
//	}
//	
//	private class PhoneTableModel extends AbstractTableModel {
//		private Object[][] data;
//		private String[] columnNames = {"Phone Number"};
//		
//		@Override
//		public int getRowCount() {
//			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//		}
//
//		@Override
//		public int getColumnCount() {
//			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//		}
//
//		@Override
//		public Object getValueAt(int i, int i1) {
//			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//		}
//		
//	}
}
