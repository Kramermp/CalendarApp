/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.backend;

import calendarapp.Event;
import calendarapp.ui.EventPicker;
import calendarapp.ui.PickerUI;
import java.util.ArrayList;

/**
 *
 * @author Faust
 */
public class PickerController {
	public static final int EVENT = 0;
	public static final int CONTACT = 1;
	public static final int LOCATION = 2;
	
	public static final int EDIT = 0;
	public static final int DELETE = 1;
	
	private PickerUI pickerUI;
	private NavigationController parentController;
	private int pickerCode = -1;
	private int editCode = -1;
	private User activeUser;
	
	public PickerController (NavigationController parentController, 
			User activeUser, int pickerCode, int editCode) {
		this.parentController = parentController;
		this.pickerCode = pickerCode;
		this.activeUser = activeUser;
		this.editCode = editCode;
		switch(pickerCode) {
			case 0:
				pickerUI = new PickerUI(this, editCode,
						new EventPicker(activeUser));
				break;
			case 1:
				//PickerUI(new ContactPicker());
				break;	
			case 2:
				//PickerUI(new LocationPicker());
				break;
			default:
				
				break;
		}
		pickerUI.setVisible(true);
	}
	
	public void disposePickerUI() {
		pickerUI.dispose();
		parentController.disposePickerController();
	}
	
	public void edit(int[] selectedRows) {
		switch(pickerCode) {
			case 0:
				if(selectedRows.length > 1 ) {
					System.out.println("More than one item is selected");
				}else if (selectedRows.length == 1) {
					System.out.println("One Item was selected.");
					parentController.requestEventUI(activeUser.getEventList().get(selectedRows[0]));
				}else {
					System.out.println("No item was selected");
				}
				break;
			default:
				break;
		}
		
	}
}
