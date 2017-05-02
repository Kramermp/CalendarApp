/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Location;
import calendarapp.backend.ColorPalette;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author mpk5206
 */
public class LocationListUI extends JPanel {
	private ColorPalette palette;
	private ArrayList<Location> locationList;
	private JList locationJList;
	private JScrollPane scrollPane;
	
	public LocationListUI(ArrayList<Location> locationList) {
		this.locationList = locationList;
		addComponents();
	}
	
	public LocationListUI(ColorPalette palette, ArrayList<Location> locationList) {
		this.locationList = locationList;
		addComponents();
	}

	private void addComponents() {
		removeAll();
		setLayout(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		ArrayList<String> locationListStrings = getStringList();
		
		locationJList = new JList(locationListStrings.toArray());
		scrollPane = new JScrollPane(locationJList);
		add(scrollPane, BorderLayout.CENTER);
	}
	ArrayList<String> getStringList() {
		ArrayList<String> tempList = new ArrayList<String>();
		for(int i = 0; i < locationList.size(); i++) {
			tempList.add(locationList.get(i).getName());
		}
		return tempList;
	}
	
	public void update() {
		removeAll();
		add(new JScrollPane(new JList(getStringList().toArray())), BorderLayout.CENTER);
		revalidate();
	}
}
 