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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		c.gridx =0;
		c.gridwidth = 2;
		c.gridy = 1;
		add(middleNameTxtFld, c);
		lastNameTxtFld = new JTextField("Last Name");
		c.gridy = 2;
		add(lastNameTxtFld, c);
		c.gridy = 3;
		JPanel buttonArea = new JPanel();
		configureButtonArea(buttonArea);
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
		System.err.println("ContactUI.getContact() is a stub");
		String title = (String) titleComboBox.getSelectedItem();
		String firstName = firstNameTxtFld.getText();
		String middleName = "";
		String lastName = lastNameTxtFld.getText();
		String suffix = "";
		return new Contact(title, firstName, middleName, lastName, suffix);
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
}
