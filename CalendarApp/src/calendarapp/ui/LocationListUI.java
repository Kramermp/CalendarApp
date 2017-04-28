/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.backend.ColorPalette;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author mpk5206
 */
public class LocationListUI extends JPanel {
	private ColorPalette palette;
	
	public LocationListUI() {
		addComponents();
		setBackground(Color.YELLOW);
	}
	
	public LocationListUI(ColorPalette palette) {
		addComponents();
	}

	private void addComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel topButtonArea = new JPanel();
		c.gridx = 0;
		c.gridy = 0;
		add(topButtonArea, c);
	}
}
