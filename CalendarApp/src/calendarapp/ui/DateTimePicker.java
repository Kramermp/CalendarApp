/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;
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
    private JComboBox dayComboBox;
    private JComboBox monthComboBox; 
    private JTextField yearTxtField;
    private JTextField hoursTxtField;
    private JTextField minTxtField;
    private JLabel timeLbl;
    private GregorianCalendar calendar = new GregorianCalendar();
    private JRadioButton radAMField, radPMField;
    int day, month, year, hours, min;
    String ampm = null;

    public DateTimePicker(long milleTime) {
        this.selectedDate = new Date(milleTime);
        this.calendar.setTimeInMillis(milleTime);
        addComponents();
    }

    public Calendar getDate() {
        ArrayList<String> errorMessages = new ArrayList<String>();
        month = monthComboBox.getSelectedIndex() + 1;
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
            hours = Integer.parseInt(this.hoursTxtField.getText());
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
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.DAY_OF_MONTH, day);
                    calendar.set(Calendar.HOUR, hours);
                    calendar.set(Calendar.MINUTE, min);

        return calendar;
    }

    public void setDate(Date date) {
            this.selectedDate = date;
    }

    private void addComponents(){
        this.setLayout(new GridBagLayout());
                    GridBagConstraints c = new GridBagConstraints();
                    String[] months = {"January", "Febuary", "March", "April", "May",
                            "June", "July", "August", "September", "October", "November",
                            "December"};
        monthComboBox = new JComboBox(months);
                    monthComboBox.addActionListener((ActionEvent ae) -> { 
                            DateTimePicker.this.monthUpdated();
                    });

                    monthComboBox.setSelectedIndex(calendar.get(Calendar.MONTH));
        c.gridx = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(10,10,10,10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.weightx = .75;
        c.weighty = .1;
        this.add(monthComboBox, c);
                    c = new GridBagConstraints();

                    //DayComboBox
                    String[] days = new String[31];

                    for(int i = 1; i <= 31; i++) {
                            days[i - 1] = String.valueOf(i);
                    }
                    dayComboBox = new JComboBox(days);
                    dayComboBox.addActionListener((ActionEvent ae) -> { 
                            DateTimePicker.this.dayUpdated();
                    });
        c.gridx = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(10,10,10,10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.weightx = .75;
        c.weighty = .1;
        this.add(dayComboBox, c);



        c = new GridBagConstraints();
        yearTxtField = new JTextField("2017");
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

            private void monthUpdated () {
                    //Have some way to update dayComboBox base off of month
                    System.out.println("Month updated");
                    calendar.set(Calendar.MONTH, monthComboBox.getSelectedIndex() + 1);
                    System.out.println("Month is now " + calendar.get(Calendar.MONTH));
            }

            private void dayUpdated () {
                    System.out.println("Day Updated.");
                    calendar.set(Calendar.DAY_OF_MONTH, 
                                    dayComboBox.getSelectedIndex() + 1);
                    System.out.println("Day is now" 
                                    + " " + calendar.get(Calendar.DAY_OF_MONTH));
            }		
}
