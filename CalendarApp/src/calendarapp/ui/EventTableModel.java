/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Event;
import calendarapp.backend.User;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Faust
 */
public class EventTableModel extends AbstractTableModel {
	private User user;
	private String[] columnNames = {"Event", "Start Time", "End Time"};;
	private Object[][] data;

	public EventTableModel(User user) {
		this.user = user;
		this.data = getEventTableData();
	}
	
	private Object[][] getEventTableData() {
		ArrayList<Event> eventList = this.user.getEventList();
		int eventCount = eventList.size();
		Object[][] tempData = new Object[eventCount][3];
		for(int i = 0; i < eventCount; i++) {
			if(eventList.get(i) == null)
				System.out.println(i);
			tempData[i][0] = eventList.get(i).getEventName();
			tempData[i][1] = getDate(eventList.get(i).getEventStartDate());
			tempData[i][2] = getDate(eventList.get(i).getEventEndDate());
		}
		return tempData;
	}
	@Override
	public int getRowCount() {
		return data.length;
	}
	
	public String getDate(Calendar calendar) {
		//FIXME: make this less ugly maybe
		String dateString = "";
		dateString = dateString + (calendar.get(Calendar.MONTH) + 1) + "-";
		dateString = dateString + calendar.get(Calendar.DAY_OF_MONTH) + "-";
		dateString = dateString + String.valueOf(calendar.get(Calendar.YEAR));
		return dateString;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int i, int i1) {
		return data[i][i1];
	}

	public void setData() {
		System.out.println("setting data");
		this.data = getEventTableData();
		this.fireTableDataChanged();
	}
}
