/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	private Date selectedDate;
        private JTextField dayTxtField, monthTxtField, yearTxtField, hoursTxtField, minTxtField;
        private JLabel timeLbl;
        private JRadioButton radAMField, radPMField;
        int day, month, year, hours, min;
        String ampm = null;
        
	public DateTimePicker(long nanoTime) {
		this.selectedDate = new Date(nanoTime);
		addComponents();
	}

	public Date getDate() {
            ArrayList<String> errorMessages = new ArrayList<String>();
            if (this.dayTxtField.getText() == null){
                errorMessages.add("Invalid day");
            }
            else{
                day = Integer.parseInt(this.dayTxtField.getText());
            }
            
            if (this.monthTxtField.getText() == null){
                errorMessages.add("Invalid month");
            }
            else{
                month = Integer.parseInt(this.monthTxtField.getText());
            }
            
            if (this.yearTxtField.getText() == null){
                errorMessages.add("Invalid year");
            }
            else{
                year = Integer.parseInt(this.yearTxtField.getText());
            }
            
            if (this.hoursTxtField.getText() == null){
                errorMessages.add("Invalid hours value");
            }
            else{
                int hours = Integer.parseInt(this.hoursTxtField.getText());
            }
            
            if (this.minTxtField.getText() == null){
                errorMessages.add("Invalid minutes value");
            }
            else{
                min = Integer.parseInt(this.minTxtField.getText());
            }
            if(radAMField.isSelected())
                ampm = "AM";
            else if(radPMField.isSelected())
                ampm = "PM";
            else
                errorMessages.add("Choose AM or PM");
            
            if (ampm == "PM"){
                hours += 12;
            }
            if(errorMessages.size() > 0){
                System.out.println(errorMessages);
                return null;
            }
            selectedDate = new Date(year, month, day, hours, min);
            return selectedDate;
	}
	
	public void setDate(Date date) {
		this.selectedDate = date;
	}
        
        private void addComponents(){
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            dayTxtField = new JTextField("Day");
            c.gridx = 0;
            c.gridwidth = 1;
            c.anchor = GridBagConstraints.EAST;
            c.insets = new Insets(10,10,10,10);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridy = 0;
            c.weightx = .75;
            c.weighty = .1;
            this.add(dayTxtField, c);
            
            c = new GridBagConstraints();
            monthTxtField = new JTextField("Month");
            c.gridx = 1;
            c.gridwidth = 2;
            c.anchor = GridBagConstraints.EAST;
            c.insets = new Insets(10,10,10,10);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridy = 0;
            c.weightx = .75;
            c.weighty = .1;
            this.add(monthTxtField, c);
            
            c = new GridBagConstraints();
            yearTxtField = new JTextField("Year");
            c.gridx = 3;
            c.gridwidth = 2;
            c.anchor = GridBagConstraints.EAST;
            c.insets = new Insets(10,10,10,10);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridy = 0;
            c.weightx = .75;
            c.weighty = .1;
            this.add(yearTxtField, c);
            
            c = new GridBagConstraints();
            timeLbl = new JLabel("Time: ");
            c.gridx = 0;
            c.anchor = GridBagConstraints.CENTER;
            c.insets = new Insets(10,10,10,10);
            c.gridy = 1;
            this.add(timeLbl, c);
            
            c = new GridBagConstraints();
            hoursTxtField = new JTextField("Hours");
            c.gridx = 1;
            c.gridwidth = 2;
            c.anchor = GridBagConstraints.CENTER;
            c.insets = new Insets(10,10,10,10);
            c.gridy = 1;
            c.weightx = .75;
            c.weighty = .1;
            this.add(hoursTxtField, c);
            
            c = new GridBagConstraints();
            minTxtField = new JTextField("Minutes");
            c.gridx = 3;
            c.gridwidth = 2;
            c.anchor = GridBagConstraints.CENTER;
            c.insets = new Insets(10,10,10,10);
            c.gridy = 1;
            c.weightx = .75;
            c.weighty = .1;
            this.add(minTxtField, c);
            
            c = new GridBagConstraints();
            radAMField = new JRadioButton("AM");
            c.gridx = 5;
            c.anchor = GridBagConstraints.CENTER;
            c.gridy = 1;
            c.weightx = .1;
            c.weighty = .1;
            this.add(radAMField, c);
            
            c = new GridBagConstraints();
            radPMField = new JRadioButton("PM");
            c.gridx = 6;
            c.anchor = GridBagConstraints.CENTER;
            c.gridy = 1;
            c.weightx = .1;
            c.weighty = .1;
            this.add(radPMField, c);
        }
}
