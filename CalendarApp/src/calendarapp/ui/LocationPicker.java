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
public class LocationPicker extends JPanel {
	private Location selectedLocation;
        private JTextField LocationNameTxtFld;
        private String selectedLocationString;
        
	
	public LocationPicker () {
            createComponents();     
	}
	
	public LocationPicker(Location location) {
            this.selectedLocation = location;
            createComponents();
	}
	
	public Location getSelectedLocation() {
            return this.selectedLocation;
	}
	public void createComponents(){
            this.setLayout(new GridBagLayout( ));
            GridBagConstraints c = new GridBagConstraints( );
            
            LocationNameTxtFld = new JTextField("Location Picker");
            LocationNameTxtFld.setHorizontalAlignment(JTextField.CENTER);
            
            c.insets = new Insets(10, 600, 10, 600);
            c.anchor = GridBagConstraints.NORTH;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = .50;
            c.gridx = 0;
            c.gridwidth = 1;
            c.gridy = 0;
            c.weighty = .1;
            this.add(LocationNameTxtFld, c);
            
            User testUser = new User();
            ArrayList<String> locationList = testUser.getLocationList();
            
            JPanel panel = new JPanel();
            GridBagConstraints b = new GridBagConstraints( );
            b.insets = new Insets(10, 10, 10, 10);
            b.anchor = GridBagConstraints.NORTH;
            b.fill = GridBagConstraints.HORIZONTAL;
            b.weightx = .50;
            b.gridwidth = 1;
            b.weighty = .1;
            
            
            for (int i = 0; i < locationList.size(); i++) {
                b.gridx = i;
                b.gridy = i;
                panel.add(createButton("" + (i + 1) + ": " + locationList.get(i)), b);
            }
            
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            
            GridBagConstraints sp = new GridBagConstraints();
            sp.insets = new Insets(50, 50, 50, 50);
            sp.anchor = GridBagConstraints.CENTER;
            sp.fill = GridBagConstraints.BOTH;
            sp.weightx = .50;
            sp.gridx = 0;
            sp.gridwidth = 1;
            sp.gridy = 0;
            sp.weighty = .1;
            
            this.add(scrollPane, sp);

            
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
}
