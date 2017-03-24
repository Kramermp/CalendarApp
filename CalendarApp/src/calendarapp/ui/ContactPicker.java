/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class will be used to have the user select a contact.
 * 
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 */
public class ContactPicker extends JPanel {
	
	public ContactPicker() {
		this.add(new JTextField("Contact Picker"));
	}
	
}
