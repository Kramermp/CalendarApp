/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.backend.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Faust
 */
public class EventPicker extends Picker {
	private User activeUser;
	private JScrollPane area; 
	private JTable table; 
	public EventPicker (User activeUser) {
		this.activeUser = activeUser;
		setLayout(new BorderLayout());
		addComponents();
	}
	
	private void addComponents(){
		table = new JTable(new EventTableModel(activeUser));
		area = new JScrollPane(table);
		this.add(area);
	}

	public int[] getSelected() {
		return table.getSelectedRows();
	}
	
	
}
