/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.backend;

import calendarapp.Contact;
import calendarapp.ui.ContactUI;
import javax.swing.JFrame;

/**
 *
 * @author mpk5206
 */
public class ContactController {
	private NavigationController parentController;
	private ContactUI contactUI;
	private Contact sourceContact;
	
	public ContactController(NavigationController parentController) {
		System.out.println("Creating the ContactController.");
		this.parentController = parentController;
		this.contactUI = new ContactUI(this);
		this.contactUI.setVisible(true);
		System.out.println("Finished creathing the ContactController.");
	}
	
	public ContactController(NavigationController parentController, Contact sourceContact) {
		System.out.println("Creating the ContactController.");
		this.parentController = parentController;
		this.sourceContact = sourceContact;
		this.contactUI = new ContactUI(this, sourceContact);
		this.contactUI.setVisible(true);
		System.out.println("Finished creathing the ContactController.");
	}
	
	public void dispose() {
		if(contactUI != null) {
			System.out.println("The ContactController is disposing of the"
				+ " ContactUI.");
			contactUI.dispose();
			contactUI = null;
			parentController.disposeContactController();
		} else {
			System.out.println("The ContactController recieved a request to"
				+ " to dispose of the ContactUI but there was not and existing"
				+ " ContactUI.");
		}
	}
	
	public boolean hasUI() {
		return contactUI != null;
	}
	
	public void submit() {
		if(sourceContact == null) {
			parentController.getActiveUser().addContact(contactUI.getContact());
			parentController.writeData();
			parentController.updateContactTable();
			parentController.disposeContactController();
		} else {
			parentController.getActiveUser().removeContact(sourceContact);
			parentController.getActiveUser().addContact(contactUI.getContact());
			parentController.writeData();
			parentController.updateContactTable();
			parentController.disposeContactController();
		}
	}

	void disposeUI() {
		contactUI.dispose();
		contactUI = null;
		parentController.updateContactTable();
	}

	void getFocus() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	void bringUIToTop() {
		contactUI.setState(JFrame.NORMAL);
		contactUI.toFront();
		contactUI.repaint();
	}
}
