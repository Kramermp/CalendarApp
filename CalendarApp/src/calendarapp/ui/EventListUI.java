/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Event;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 */
public class EventListUI extends JPanel {
	private static final String SORT_BY = "Sort By";
	private static final String EVENTNAME = "Event Name";
	private static final String EVENTTAG = "Event Tag";
	private static final String EVENTPRIORTY = "Event Priority";
	private static final String EVENTSTARTDATE = "Event Start Day";
	
	private NavigationUI parentController;
	private JList eventList;
	private ArrayList<Event> eventArrayList;
	
	public EventListUI (NavigationUI parentController, ArrayList<Event> eventArrayList) {
		this.parentController = parentController;
		this.eventArrayList = eventArrayList;
		addComponents();
	}
	
		private void addComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel topButtonArea = new JPanel();
		String[] options = {SORT_BY, EVENTNAME, EVENTTAG, EVENTPRIORTY, EVENTSTARTDATE};
		JComboBox sortComboBox = new JComboBox(options);
		sortComboBox.addActionListener((ActionEvent ae) -> { 
			int selectedIndex = sortComboBox.getSelectedIndex();
			switch(selectedIndex) {
				case 0:
					//Do Nothing
					break;
				case 1:
					System.out.println("Sorting by eventName");
					parentController.sortEvents("eventName");
					break;
				case 2:
					parentController.sortEvents("eventTag");
					break;	
				case 3:
					parentController.sortEvents("eventPriority");
					break;
				case 4:
					parentController.sortEvents("eventStartDate");
					break;
			}
		});
		//Fixme; this needs an action listener that will sort the list when triggered
		topButtonArea.setLayout(new GridBagLayout());
		//topButtonArea.setBackground(Color.cyan);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5,5,10,5);
		topButtonArea.add(sortComboBox, c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(topButtonArea, c);
		
		String[] eventStringArray = new String[eventArrayList.size()];
		for(int i = 0; i < eventArrayList.size(); i++) {
			//System.out.println("Here");
			eventStringArray[i] = eventArrayList.get(i).getEventName();
		}
		eventList = new JList(eventStringArray);
		//contactList.setBackground(Color.yellow);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = 1;
		c.gridy = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		JScrollPane scrollPane = new JScrollPane(eventList);
		//scrollPane.setBackground(Color.yellow);
		add(scrollPane, c);
		setBackground(Color.RED);
	}
	
	public void update() {
		removeAll();
		addComponents();
		revalidate();
	}
}
