/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Location;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class will extend JPanel and will be used to have the user select a 
 * locations.
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 */
public class LocationPicker extends JPanel {
	private Location selectedLocation;
	
	public LocationPicker () {
		this.add(new JTextField("Location Picker"));
	}
	
	public LocationPicker(Location location) {
		this.selectedLocation = location;
		this.add(new JTextField("Location Picker"));
	}
	
	public Location getSelectedLocation() {
		return this.selectedLocation;
	}
	
}
