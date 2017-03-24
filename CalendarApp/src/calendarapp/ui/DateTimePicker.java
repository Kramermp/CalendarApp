/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class will extend JPanel and will be used to get user input about a 
 * Date and time.
 * 
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @since .1
 * @version .1
 */
public class DateTimePicker extends JPanel {
	
	public DateTimePicker( ) {
		this.add(new JTextField("DateTimePicker"));
	}
}
