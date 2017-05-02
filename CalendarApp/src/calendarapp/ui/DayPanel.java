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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 */
public class DayPanel extends JPanel {
	public static final int NOT_ACTIVE = 0;
	public static final int ACTIVE = 1;
	
	private static final Color GRAY = new Color (211, 211, 211);
	private Color borderColor;
	private int eventCount = 0;
	private int date;
	private int month;
	private int year;
	private JPanel eventPanel;
	
	public DayPanel (int date, int month, int year, int activeState) {
		if(activeState == NOT_ACTIVE) {
			borderColor = GRAY;
		} else {
			borderColor = Color.BLACK;
		}
		addComponents(date);
		this.date = date;
		this.month = month;
		this.year = year;
		//System.out.println("Built date panel for " + month + "-" + date + "-" + 
		//		year);
	}

	private void addComponents(int date) {
		setBorder(new LineBorder(borderColor));
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension (50, 50)); 
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		JPanel datePanel = new JPanel();
		
		MatteBorder datePanelBorder = new MatteBorder(0, 0, 2, 2, borderColor);
		datePanel.setBorder(datePanelBorder);
		datePanel.setLayout(new BorderLayout());
		datePanel.add(new JLabel(String.valueOf(date)), BorderLayout.CENTER);
		add(datePanel, c);

		eventPanel = new JPanel();
		eventPanel.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		add(eventPanel, c);
	}	
	public void addEvent(Event eventToAdd) {
		//eventPanel.setBackground(Color.RED);
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = eventCount;
		eventPanel.add(new JLabel(eventToAdd.getEventName()), c);
		eventCount++;
	}
	public int getDate() {
		return date;
	}
	
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	
	public String getFull() {
		return month+ "-" + date + "-" + year;
	}
}
