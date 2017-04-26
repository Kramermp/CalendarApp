/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import java.awt.BorderLayout;
import java.awt.Color;
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
	
	public DayPanel (int date) {
		addComponents(date);
	}

	private void addComponents(int date) {
		setBorder(new LineBorder(Color.BLACK));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		JPanel datePanel = new JPanel();
		
		MatteBorder datePanelBorder = new MatteBorder(0, 0, 1, 1, Color.BLACK);
		datePanel.setBorder(datePanelBorder);
		datePanel.setLayout(new BorderLayout());
		datePanel.add(new JLabel(String.valueOf(date)), BorderLayout.CENTER);
		add(datePanel, c);

		JPanel eventPanel = new JPanel();
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		add(eventPanel, c);
	}	
}
