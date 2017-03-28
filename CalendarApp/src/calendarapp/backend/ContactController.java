/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.backend;

import calendarapp.ui.ContactUI;

/**
 *
 * @author mpk5206
 */
public class ContactController {
	private NavigationController parentController;
	private ContactUI contactUI;
	
	public ContactController(NavigationController parentController) {
		System.out.println("Creating the ContactController.");
		this.parentController = parentController;
		this.contactUI = new ContactUI(this);
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
		parentController.getActiveUser().addContact(contactUI.getContact());
		parentController.writeData();
		parentController.disposeContactController();
	}

	void disposeUI() {
		contactUI.dispose();
		contactUI = null;
	}
}
