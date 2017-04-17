package calendarapp.ui;

import calendarapp.Contact;
import calendarapp.backend.User;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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
    private ArrayList<Contact> listOfContacts;
	private JList contactJList;
    private ArrayList<String> listOfContactNames;
    private ArrayList<String> listOfGroups;
    
    /**
     * Creates ContactPicker from listOfConacts
     * <p>
     * Creates a ContactPicker object from the provided ArrayList of Contacts
     * without an initially selected Contact.
     * @param listOfContacts; The list of Contacts to pick from
     */
    public ContactPicker(ArrayList<Contact> listOfContacts) {
        this.listOfContacts = listOfContacts;
        addComponents();
    }
    
    /**
     * Creates a ContactPicker from listOfContacts and select initial Contact
     * <p>
     * Create a ContactPicker Object from the provided ArrayList of Contacts
     * and selects the provided Contact as a the initially selected Contacts.
     * <p>
     * @param listOfContacts; List of all available Contacts
     * @param selectedContact; List of the selected Contacts
     */
    public ContactPicker(ArrayList<Contact> listOfContacts, 
                ArrayList<Contact> selectedContact) {
        this.listOfContacts = listOfContacts;
		System.err.println("Having a preselected contact is not supported right"
				+ " now.");
        addComponents();
    }

	private void addComponents(){
		this.setLayout(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		//JLabel individuals = new JLabel("Individuals:");
		listOfContactNames = new ArrayList<String>();
		for(int i = 0; i < listOfContacts.size(); i++) {
			listOfContactNames.add(listOfContacts.get(i).getName().getFullName(false));
		}
		contactJList = new JList(listOfContactNames.toArray());
		JScrollPane pane = new JScrollPane(contactJList);
		this.add(pane, BorderLayout.CENTER);

	}

	@Override
	public int[] getSelected() {
		return contactJList.getSelectedIndices();
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

