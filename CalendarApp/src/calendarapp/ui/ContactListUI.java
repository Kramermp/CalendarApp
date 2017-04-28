/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Contact;
import calendarapp.backend.ColorPalette;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author mpk5206
 */
public class ContactListUI extends JPanel {
	private ColorPalette palette;
	private JList contactList = new JList();
	private ArrayList<Contact> contactArrayList;
	
	private static final String SORT_BY = "Sort By";
	private static final String FIRST_NAME = "First Name";
	private static final String LAST_NAME = "Last Name";
	
	public ContactListUI(ArrayList<Contact> contactArrayList) {
		this.contactArrayList = contactArrayList;
		addComponents();
		setBackground(Color.RED);
	}
	
	public ContactListUI(ArrayList<Contact> contactArrayList, 
			ColorPalette colorPalatte) {
		this.contactArrayList = contactArrayList;
		addComponents();	
		setBackground(Color.RED);
	}

	private void addComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel topButtonArea = new JPanel();
		String[] options = {SORT_BY, FIRST_NAME, LAST_NAME};
		JComboBox sortComboBox = new JComboBox(options);
		//Fixme; this needs an action listener that will sort the list when triggered
		topButtonArea.setLayout(new GridBagLayout());
		topButtonArea.setBackground(Color.cyan);
		c.gridx = 0;
		c.gridy = 1;
		topButtonArea.add(sortComboBox);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(topButtonArea, c);
		
		String[] contactStringArray = new String[contactArrayList.size()];
		for(int i = 0; i < contactArrayList.size(); i++) {
			System.out.println("Here");
			contactStringArray[i] = contactArrayList.get(i).getName().getFullName(false);
		}
		contactList = new JList(contactStringArray);
		contactList.setBackground(Color.yellow);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = 1;
		c.gridy = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		JScrollPane scrollPane = new JScrollPane(contactList);
		scrollPane.setBackground(Color.yellow);
		add(scrollPane, c);
	}
}
