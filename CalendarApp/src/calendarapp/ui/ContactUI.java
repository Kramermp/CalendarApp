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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author mpk5206
 */
public class ContactUI extends JFrame {
	private ContactController parentController;
	
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
		JTextField firstNameTxtFld = new JTextField("First Name");
		JTextField lastNameTxtFld = new JTextField("Last Name");
		JPanel buttonArea = new JPanel();
		configureButtonArea(buttonArea);
		add(buttonArea);
	}
	
	private void configureButtonArea(JPanel buttonArea) {
		buttonArea.setLayout(new GridBagLayout());
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("CancelBtn triggered.");
			ContactUI.this.dispose();
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
		return new Contact();
	}
	
	private class ContactUIWindowListener implements WindowListener {
	
		@Override
		public void windowClosed(WindowEvent we) {	
			System.out.println("ContactUI closed.");
			ContactUI.this.parentController.dispose();
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
			System.out.println("ContactUI closed.");
			ContactUI.this.parentController.dispose();
		} // Do Nothing
	}
}
