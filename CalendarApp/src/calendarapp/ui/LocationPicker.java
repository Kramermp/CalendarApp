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
 * @author Aaron Stricker <aps5376 @ psu.edu>
 * @version .1
 * @since .1
 */

//FIXME: GitHub Issue #17
public class LocationPicker extends Picker {
	private Location selectedLocation;
        private JTextField LocationNameTxtFld;
        private String selectedLocationString;
         ArrayList<String> locationStringList = new ArrayList<String>();
        
        private ArrayList<Location> listOfLocations;
		private JList locationJList;
        
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
            return listOfLocations.get(locationJList.getSelectedIndices()[0]);
            //return locationJList.getSelectedIndices();
	}
        
	public void createComponents(){
            this.setLayout(new BorderLayout());
            GridBagConstraints c = new GridBagConstraints( );
            
           
			for (int i = 0; i < listOfLocations.size(); i++) {
                locationStringList.add(listOfLocations.get(i).getName());
            }
            
			locationJList = new JList(locationStringList.toArray());
            
            JScrollPane scrollPane = new JScrollPane(locationJList);
			scrollPane.setPreferredSize(new Dimension(0,0));
            
//            GridBagConstraints sp = new GridBagConstraints();
//            sp.insets = new Insets(50, 50, 50, 50);
//            sp.anchor = GridBagConstraints.CENTER;
//            sp.fill = GridBagConstraints.BOTH;
//            sp.gridx = 0;
//            sp.gridwidth = 1;
//            sp.gridy = 0;
            this.add(scrollPane, BorderLayout.CENTER);     
        }
        
        public JButton createButton(String name){
            JButton button = new JButton(name);
            class ClickListener implements ActionListener{

                @Override
                public void actionPerformed(ActionEvent e) {
                    StringBuilder sb = new StringBuilder(button.getText());
                    sb.delete(0, 3);
                    String location = sb.toString();
                    
                    System.out.println("Location Set to: " + location);
                    setLocation(location);
                }
            }
            ActionListener listener = new ClickListener();
            button.addActionListener(listener);
            return button;
        }
        
        public void setLocation(String location){
            selectedLocationString = location;
        } 
        
        public void setLocation(Location location){
            selectedLocation = location;
        } 
        
        public void setSelected(String location){
            int i = locationStringList.indexOf(location);
            locationJList.setSelectedIndex(i);
        }

	public Location getSelectedItem() {
            if(locationJList.isSelectionEmpty())
                return null;
            int[] i = locationJList.getSelectedIndices();
            return listOfLocations.get(i[0]);
	}

    @Override
    public int[] getSelected() {
        return locationJList.getSelectedIndices();
    }
}
