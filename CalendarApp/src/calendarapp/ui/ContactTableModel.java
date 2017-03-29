/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Name;
import calendarapp.backend.User;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Micahel Kramer <mpk5206 @ psu.edu>
 */
public class ContactTableModel extends AbstractTableModel {
	private User user;
	private String[] columns = {"First Name", "Last Name"};
	private Object[][] data;
	
	public ContactTableModel (User activeUser) {
		this.user = activeUser;
		this.data = getContactTableData();
	}
	
	private Object[][] getContactTableData() {
		System.out.println("Getting ContactTable Data.");
		int contactCount = user.getContactList().size();
		Object[][] tempData = new Object[contactCount][2];
		for(int i =0; i < contactCount; i++) {
			Name selectedContactName = user.getContactList().get(i).getName();
			tempData[i][0] = selectedContactName.getFirstName();
			tempData[i][1] = selectedContactName.getLastName();
		}
		System.out.println("Finished getting ContactTable Data");
		return tempData;
	}
	
	@Override
	public int getRowCount() {
		if (user != null) {
			return user.getContactList().size();
		} else {
			return 0;
		}
	}

	@Override
	public int getColumnCount() {
		return this.columns.length;
	}

	@Override
	public Object getValueAt(int i, int i1) {
		return data[i][i1];
	}
	
	public void setData() {
		System.out.println("setting data");
		this.data = getContactTableData();
		this.fireTableDataChanged();
	}

	
	
}
