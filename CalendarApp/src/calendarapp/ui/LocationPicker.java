/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Contact;
import calendarapp.Event;
import calendarapp.Location;
import calendarapp.backend.EventController;
import calendarapp.backend.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class will extend JPanel and will be used to have the user select a 
 * locations.
 * @author Michael Kramer <mpk5206 @ psu.edu
 * @author Aaron Stricker <aps5376 @ psu.edu>
 * @version .1
 * @since .1
 */

//FIXME: GitHub Issue #17
public class LocationPicker extends Picker {
	private Location selectedLocation;
        private JTextField LocationNameTxtFld;
        private String selectedLocationString;
		private JList locationJList;
        
        private ArrayList<Location> listOfLocations;
        
	/**
         * Creates a LocationPicker from the listOfLocations.
         * <p>
         * This takes the provided ArrayList of Locations and displays these
         * Locations. There is no default selected value for this one.
         * <p>
         * @param listOfLocations; The list of Locations
         */
	public LocationPicker (ArrayList<Location> listOfLocations) {
            this.listOfLocations = listOfLocations;
            createComponents();     
	}
	
        /**
         * Creates LocationPicker from the list and selects the indicated one.
         * <p>
         * This takes the provided ArrayList of Locations and displays
         * these locations. The provided Location is selected.
         * <p>
         * @param listOfLocations; The List of Locations
         * @param location; The Location to be selected
         */
	public LocationPicker(ArrayList<Location> listOfLocations,
                Location location) {
            this.listOfLocations = listOfLocations;
            this.selectedLocation = location;
            createComponents();
	}
	
	public Location getSelectedLocation() {
            return this.selectedLocation;
	}
        
	public void createComponents(){
            this.setLayout(new BorderLayout());
            GridBagConstraints c = new GridBagConstraints( ); 
            ArrayList<String> locationList = new ArrayList<String>();
			int locationCount = listOfLocations.size();
			for (int i = 0; i < locationCount; i++) {
               locationList.add(listOfLocations.get(i).getName());
            }  
			locationJList = new JList(locationList.toArray());
			locationJList.setAlignmentY(CENTER_ALIGNMENT);
            
            JScrollPane scrollPane = new JScrollPane(locationJList);
			scrollPane.setPreferredSize(new Dimension(0,0));
            //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
            this.add(scrollPane, BorderLayout.CENTER);     
        }
        

	@Override
	public int[] getSelected() {
		return locationJList.getSelectedIndices();
	}
}
