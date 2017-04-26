/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Michael Kramer
 * @verion .1
 * @since .1
 */
public class CalendarUI extends JPanel {
	private Random rng = new Random();
	private GregorianCalendar calendar;
	private GregorianCalendar displayCalendar;
	private JPanel topArea;
	private JPanel bottomArea;
	private JLabel monthLabel;
	private int selectedMonth;
	
	public CalendarUI(GregorianCalendar calendar) {
		setLayout(new GridBagLayout());
		System.out.println(calendar);
		this.calendar = calendar;
		this.displayCalendar = calendar;
		this.selectedMonth = calendar.get(Calendar.MONTH);
		addComponents();
	}

	private void addComponents() {
		topArea = new JPanel();
		buildTopArea();
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = .1;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(topArea, c);
		
		bottomArea = new JPanel();
		buildBottomArea();
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		add(bottomArea, c);
		
	}

	private void buildTopArea() {
		topArea.setLayout(new GridBagLayout());
		monthLabel = new JLabel(getMonthName(calendar.get(Calendar.MONTH)));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.weighty = 1;
		topArea.add(monthLabel, c);
		
	}

	private void buildBottomArea() {
		bottomArea.setLayout(new GridBagLayout());
		int dayNumber = handleFirstWeek(bottomArea);
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		int date = calendar.get(Calendar.DAY_OF_MONTH);
		for(int i = 1; i < 5; i++) {
			c.gridy = i;
			for(int j = 0; j < 7; j++) {
				c.gridx = j;
				DayPanel dayPanel = new DayPanel(dayNumber);
				//dayPanel.setBackground(new Color(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255)));
				bottomArea.add(dayPanel, c);
				dayNumber++;
			}
		}
		handleLastWeek(bottomArea, dayNumber);
	}
	
	private int handleFirstWeek(JPanel bottomArea) {
		int date = 1;
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		GregorianCalendar test = (GregorianCalendar) displayCalendar.clone();
		test.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(test.get(Calendar.DAY_OF_WEEK));
		//This loop handles the first week
		int previousMonth = test.get(Calendar.MONTH) - 1;
		if(previousMonth == 0) {
			previousMonth = 12;
		}
		
		int previousMonthDayCount = getDayCount(previousMonth);
		System.out.println(previousMonthDayCount);
		//This loop handles the end of the previous month
		for(int i = test.get(Calendar.DAY_OF_WEEK) - 1; i > 0; i--) {
			DayPanel dayPanel = new DayPanel(previousMonthDayCount - i + 1); //I'm not sure why this plus 1 is need but it doesn't work without it
			c.gridx = 6 - i;
			c.gridy = 0;
			bottomArea.add(dayPanel, c);
		}
		//This loop handles the start of the month
		for(int i = test.get(Calendar.DAY_OF_WEEK); i < 8; i++) {
			DayPanel dayPanel = new DayPanel(date);
			c.gridx = i - 1;
			c.gridy= 0;
			bottomArea.add(dayPanel, c);
			date++;
		}
		return date;
	}
	
	private void handleLastWeek(JPanel bottomArea, int date) {
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		GregorianCalendar test = (GregorianCalendar) displayCalendar.clone();
		int dayCount = getDayCount(test.get(Calendar.MONTH));
		int j = 0;
		System.out.println(date);
		System.out.println(dayCount);
		//This loop handles the remainder of the month
		for(int i = date; i <= dayCount; i++) {
			System.out.println("Looped");
			DayPanel dayPanel = new DayPanel(date);
			c.gridx = 0 + j;
			c.gridy = 7;
			bottomArea.add(dayPanel, c);
			j++;
		}
		//Handles the start of the next month
		for(int i = 1; j < 7; i++) {
			System.out.println("Looped");
			DayPanel dayPanel = new DayPanel(i);
			c.gridx = 0 + j;
			c.gridy = 7;
			bottomArea.add(dayPanel, c);
			j++;
		}

	}
	
	private String getMonthName(int monthNumber) {
		switch (monthNumber) {
			case (Calendar.JANUARY): 
				return "January";
			case (Calendar.FEBRUARY):
				return "February";
			case (Calendar.MARCH):
				return "March";
			case (Calendar.APRIL):
				return "April";
			case (Calendar.MAY):
				return "May";
			case (Calendar.JUNE):
				return "June";
			case (Calendar.JULY):
				return "July";
			case (Calendar.AUGUST):
				return "August";
			case (Calendar.SEPTEMBER):
				return "September";
			case (Calendar.OCTOBER):
				return "October";
			case (Calendar.NOVEMBER):
				return "November";
			case(Calendar.DECEMBER):
				return "December";		
		}
		return "This should never happen.";
	}
	
	private int getDayCount(int monthNumber) {
		switch (monthNumber) {
			case(Calendar.APRIL):
			case(Calendar.JUNE):
			case(Calendar.SEPTEMBER):
			case(Calendar.NOVEMBER):
				return 30;
			case(Calendar.JANUARY):
			case(Calendar.MARCH):
			case(Calendar.MAY):
			case(Calendar.JULY):
			case(Calendar.AUGUST):
			case(Calendar.OCTOBER):
			case(Calendar.DECEMBER):
				return 31;
			case(Calendar.FEBRUARY):
				if(displayCalendar.isLeapYear(displayCalendar.get(Calendar.YEAR))) {
					return 29;
				} else {
					return 28;
				}
		}
		return 0;
	}
}
