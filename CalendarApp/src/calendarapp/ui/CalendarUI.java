/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Event;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.swing.JButton;
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
	private ArrayList<Event> eventList;
	
	public CalendarUI(GregorianCalendar calendar, ArrayList<Event> eventList) {
		System.out.println("Building the calendarUI.");
		setLayout(new GridBagLayout());
		this.calendar = calendar;
		this.eventList = eventList;
		System.out.println("This User has " + this.eventList.size() + " events.");
		this.displayCalendar = calendar;
		this.selectedMonth = calendar.get(Calendar.MONTH);
		addComponents();
		System.out.println("Finished building the calendarUI.");
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
		buildBottomArea(bottomArea);	
	}

	private void buildTopArea() {
		topArea.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton previousMonthBtn = new JButton("Previous Month");
		previousMonthBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("Previous month Button was triggered.");
			int currentMonth = displayCalendar.get(Calendar.MONTH);
			if(currentMonth == 0) {
				displayCalendar.set(Calendar.YEAR, displayCalendar.get(Calendar.YEAR) - 1);
				displayCalendar.set(Calendar.MONTH, Calendar.DECEMBER);
			} else {
				displayCalendar.set(Calendar.MONTH, displayCalendar.get(Calendar.MONTH) - 1);
			}
			System.out.println("Navigating to " + getMonthName(displayCalendar.get(Calendar.MONTH)));
			monthLabel.setText(getMonthName(displayCalendar.get(Calendar.MONTH)));
			bottomArea.removeAll();
			buildBottomArea(bottomArea);
		});
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.weighty = 1;
		c.weightx =0;
		c.anchor = GridBagConstraints.WEST;
		topArea.add(previousMonthBtn, c);
		
		monthLabel = new JLabel(getMonthName(displayCalendar.get(Calendar.MONTH)));
		JPanel labelArea = new JPanel();
		labelArea.setLayout(new GridBagLayout());
		labelArea.add(monthLabel);
		//labelArea.setBackground(Color.RED);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.weighty = 1;
		c.weightx = 1;
		topArea.add(labelArea, c);
			
		JButton nextMonthBtn = new JButton("NextMonth");
		nextMonthBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("Next month Button was triggered.");
			int currentMonth = displayCalendar.get(Calendar.MONTH);
			if(currentMonth == 11) {
				displayCalendar.set(Calendar.YEAR, displayCalendar.get(Calendar.YEAR) + 1);
				displayCalendar.set(Calendar.MONTH, Calendar.JANUARY);
			} else {
				displayCalendar.set(Calendar.MONTH, displayCalendar.get(Calendar.MONTH) + 1);
			}
			System.out.println("Navigating to " + getMonthName(displayCalendar.get(Calendar.MONTH)));
			monthLabel.setText(getMonthName(displayCalendar.get(Calendar.MONTH)));
			bottomArea.removeAll();
			buildBottomArea(bottomArea);
		});
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 2;
		c.weighty = 1;
		c.weightx =0;
		c.anchor = GridBagConstraints.EAST;
		topArea.add(nextMonthBtn, c);
		JPanel dayArea = new JPanel();
		dayArea.setLayout(new GridBagLayout());
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		dayArea.add(new JLabel("Sunday"), c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		dayArea.add(new JLabel("Monday"), c);
		
		c = new GridBagConstraints();
		c.gridx = 2;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		dayArea.add(new JLabel("Tuesday"), c);
		
		c = new GridBagConstraints();
		c.gridx = 3;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		dayArea.add(new JLabel("Wednesday"), c);
		
		c = new GridBagConstraints();
		c.gridx = 4;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		dayArea.add(new JLabel("Thursday"), c);
		
		c = new GridBagConstraints();
		c.gridx = 5;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		dayArea.add(new JLabel("Friday"), c);
		
		c = new GridBagConstraints();
		c.gridx = 6;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		dayArea.add(new JLabel("Saturday"), c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 4;
		c.gridy = 1;
		topArea.add(dayArea, c);
		
		
	}
	
	private void buildBottomArea(JPanel bottomArea) {
		if(bottomArea == null) {
			bottomArea = new JPanel();
		}
		GregorianCalendar test = (GregorianCalendar) displayCalendar.clone();
		
		bottomArea.setLayout(new GridBagLayout());
		int dayNumber = handleFirstWeek(bottomArea);
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		int date = calendar.get(Calendar.DAY_OF_MONTH);
		int dayCount = getDayCount(test.get(Calendar.MONTH));
		int i = 1;
		int j = 0;
		boolean nextMonth = false;
		for(i = 1; i < 6; i++) {
			c.gridy = i;
			for(j = 0; j < 7; j++) {
				c.gridx = j;
				DayPanel dayPanel;
				if(!nextMonth)
					dayPanel = new DayPanel(dayNumber, test.get(Calendar.MONTH),
							test.get(Calendar.YEAR), DayPanel.ACTIVE);
				else
					dayPanel = new DayPanel(dayNumber, test.get(Calendar.MONTH),
						test.get(Calendar.YEAR), DayPanel.NOT_ACTIVE);
				//dayPanel.setBackground(new Color(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255)));
				checkDay(dayPanel);
				bottomArea.add(dayPanel, c);
				dayNumber++;
				if(dayNumber > dayCount) {
					nextMonth = true;
					if(test.get(Calendar.MONTH) == 11) {
						test.set(Calendar.MONTH, 0);
						test.set(Calendar.YEAR, (test.get(test.YEAR) + 1));
					} else {
						test.set(Calendar.MONTH, (test.get(Calendar.MONTH) + 1));
					}
					dayNumber = 1; //Wrapping into nextMonth Starts  dates back to 1
				}
			}

		}
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		add(bottomArea, c);
	}
	
	private int handleFirstWeek(JPanel bottomArea) {
		int date = 1;
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		GregorianCalendar test = (GregorianCalendar) displayCalendar.clone();
		test.set(Calendar.DAY_OF_MONTH, 1);
		//System.out.println(test.get(Calendar.DAY_OF_WEEK));
		//This loop handles the first week
		int previousMonth = test.get(Calendar.MONTH) - 1;
		if(previousMonth == 0) {
			previousMonth = 11;
		}
		
		int previousMonthDayCount = getDayCount(previousMonth);
		System.out.println(previousMonthDayCount);
		//This loop handles the end of the previous month
		
		for(int i = 0; i < test.get(Calendar.DAY_OF_WEEK) -1; i++) {
			DayPanel dayPanel = new DayPanel(previousMonthDayCount - i, test.get(Calendar.MONTH) - 1, test.get(Calendar.YEAR), DayPanel.NOT_ACTIVE); 
			c.gridx = test.get(Calendar.DAY_OF_WEEK) - 2 - i;//I'm not entirly sure why it is a -2
			c.gridy = 0;
			checkDay(dayPanel);
			bottomArea.add(dayPanel, c);
		}
		//This loop handles the start of the month
		for(int i = test.get(Calendar.DAY_OF_WEEK); i < 8; i++) {
			DayPanel dayPanel = new DayPanel(date, test.get(Calendar.MONTH), test.get(Calendar.YEAR), DayPanel.ACTIVE);
			c.gridx = i - 1;
			c.gridy= 0;
			checkDay(dayPanel);
			bottomArea.add(dayPanel, c);
			date++;
		}
		return date;
	}
	
	
	private void clearBottomArea() {
		bottomArea.removeAll();
		buildBottomArea(bottomArea);
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
	
	private void checkDay(DayPanel dayPanel) {
		System.out.println("Checking day " + dayPanel.getFull());
		ArrayList<Event> tempEventList= new ArrayList<Event>();
		for(int i = 0 ; i < eventList.size(); i++) {
			Event testEvent = eventList.get(i);
			Calendar testEventDate = testEvent.getEventStartDate();
			if(testEventDate.get(Calendar.DATE) == dayPanel.getDate()) {
				System.out.println("Found an event on the same day");
				if(testEventDate.get(Calendar.MONTH)  == dayPanel.getMonth()) {
					System.out.println("found event on same month");
					if(testEventDate.get(Calendar.YEAR) == dayPanel.getYear()) {
						dayPanel.addEvent(testEvent);
					}
				}
			}
		}
	}
	
	public void updateCalendar() {
		System.out.println("Updating Calendar.");
		clearBottomArea();
	}
}
