package calendarapp.ui;

import calendarapp.Contact;
import calendarapp.backend.User;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * This class will be used to have the user select a contact.
 * 
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 */

//FIXME: GitHub Issue #17
public class ContactPicker extends Picker {
		private ArrayList<Contact> selectedContacts = new ArrayList<Contact>();
        private ArrayList<String> testContacts;
        private ArrayList<String> testGroups;
		private ArrayList<String> listOfContactNames;
        private ArrayList<String> listOfGroups;
	public ContactPicker() {
                this.testContacts = new ArrayList<String>();
                testContacts.add("Alex");
                testContacts.add("Ben");
                testContacts.add("Connor");
                testContacts.add("Jim");
                testContacts.add("Patrick");
                this.testGroups = new ArrayList<String>();
                testGroups.add("IST 311");
                testGroups.add("IST 301");
                testGroups.add("IST 331");
                addComponents();
	}

	public ContactPicker(User activeUser) {
		System.err.println("Not Yet Implemented.");
		this.testContacts = new ArrayList<String>();
		testContacts.add("Alex");
		testContacts.add("Ben");
		testContacts.add("Connor");
		testContacts.add("Jim");
		testContacts.add("Patrick");
		this.testGroups = new ArrayList<String>();
		testGroups.add("IST 311");
		testGroups.add("IST 301");
		testGroups.add("IST 331");
		addComponents();
	}

	public ArrayList<Contact> getContacts() {
		return this.selectedContacts;
	}
	private void addComponents(){
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            JLabel groups = new JLabel("Groups:");
            c.gridx = 0;
            c.gridwidth = 1;
            c.anchor = GridBagConstraints.EAST;
            c.insets = new Insets(10,10,10,10);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridy = 0;
            c.weightx = .75;
            c.weighty = .1;
            this.add(groups, c);
            
            c = new GridBagConstraints();
            JLabel individuals = new JLabel("Individuals:");
            c.gridx = 1;
            c.gridwidth = 2;
            c.anchor = GridBagConstraints.EAST;
            c.insets = new Insets(10,10,10,10);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridy = 0;
            c.weightx = .75;
            c.weighty = .1;
            this.add(individuals, c);
            
            for(int i = 0; i < testContacts.size(); i++){
                c = new GridBagConstraints();
                JRadioButton contactToAdd = new JRadioButton(testContacts.get(i));
                if (contactToAdd.isSelected()){
                    listOfContactNames.add(contactToAdd.getText());
                }
                c.gridx = 1;
                c.gridwidth = 2;
                c.anchor = GridBagConstraints.EAST;
                c.insets = new Insets(10,10,10,10);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridy = i + 1;
                c.weightx = .75;
                c.weighty = .1;
                this.add(contactToAdd, c);
            }
            
            for(int i = 0; i < testGroups.size(); i++){
                c = new GridBagConstraints();
                JRadioButton groupToAdd = new JRadioButton(testGroups.get(i));
                if (groupToAdd.isSelected()){
                    listOfGroups.add(groupToAdd.getText());
                }
                c.gridx = 0;
                c.gridwidth = 2;
                c.anchor = GridBagConstraints.EAST;
                c.insets = new Insets(10,10,10,10);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridy = i + 1;
                c.weightx = .75;
                c.weighty = .1;
                this.add(groupToAdd, c);
            }
            
        }

	@Override
	public int[] getSelected() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
        
}






//////////////////////////////////////////////
//~201, EventUI
//
//		JLabel contactsLbl = new JLabel("Contacts:");
//		c = new GridBagConstraints();
//		c.anchor = GridBagConstraints.EAST;
//		c.gridx = 3;
//		c.gridy = 3;
//		this.add(contactsLbl, c);
//		
//		contactArea = new ContactPicker();
//		configureContactArea(contactArea);
//		c = new GridBagConstraints();
//		c.gridx = 4;
//		c.weightx = .5;
//		c.gridy = 3;
//		c.fill = GridBagConstraints.BOTH;
//                JScrollPane contacts = new JScrollPane (contactArea);
//		this.add(contacts, c);

