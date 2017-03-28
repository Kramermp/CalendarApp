package calendarapp.backend;

import calendarapp.Event;
import calendarapp.ui.EventTableModel;
import javax.swing.JFrame;
import calendarapp.ui.NavigationUI;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 *
 */

public class NavigationController {
	private SerializedDataController dataController;
	private SerializedData serializedData;
	private NavigationUI navigationUI;
	private EventController eventController;
	private LocationController locationController;
	private ContactController contactController;
	private User activeUser;
	private JTable eventTable;
	private PickerController pickerController;

	public NavigationController(User activeUser) {
		System.out.println("Creating NavigationController.");
		dataController = SerializedDataController.getSerializedDataController();
		this.activeUser = activeUser;
		System.out.println("This user has " + this.activeUser.getEventList().size()
			+ " events");
		System.out.println("This user is " + this.activeUser.getUsername());
		serializedData = dataController.getSerializedData();
		navigationUI = new NavigationUI(this);
		navigationUI.setVisible(true);
		System.out.println("Finished creating the NavigationController.");
	}

	/**
	 * This method will open a new EventUI. It will take no parameters and 
	 * return nothing. It will will open an EventUI without a source Event.
	 */
	public void requestEventUI( ) {
		if(this.eventController == null) {
			this.eventController = new EventController(this, activeUser);
		} else {
			System.out.println("There is already an existing eventController.");
			//FIXME: Event Controller should get Focus if this happens 
			//Issue: #16
		}
	}

	public void requestEventUI(Event sourceEvent) {
		if(this.eventController == null) {
			//FIXME: GitHub Issue #16
			this.eventController = new EventController(this, activeUser,
					sourceEvent);
		} else {
			System.out.println("There is already an existing eventController.");
		}
	}	
	/**
	 * This methods dumps the exist eventController.
	 * <p>
	 * If there is an existing eventController then the NavigationController 
	 * will tell it to dispose of its UI and then set the eventController to 
	 * null.
	 */

	public void disposeEventController() { 
		if(this.eventController != null) {
			System.out.println("NavigationController disposing" 
				+ " eventController.");
			this.eventController = null;
		} else {
			System.out.println("The navigation Controller received a request"
				+ " to disposeEventController but there was not and existing"
				+ "eventController.");
		}
	}

	/**
	 * This method tells the dataController to write the data to disk.
	 */
	public void writeData() {
		System.out.println("The NavigationController is requesting a" 
			+ " writeData()");
		dataController.writeTheSerializedData();
	}
	

	public Object[][] getTableData() {
		ArrayList<Event> eventList = activeUser.getEventList();
		int eventCount = activeUser.getEventList().size();
		Object[][] data = new Object[eventCount][3];
		for(int i =0; i < eventCount; i++) {
			data[i][0] = eventList.get(i).getEventName();
			data[i][1] = eventList.get(i).getEventStartDate();
			data[i][2] = eventList.get(i).getEventEndDate();
		}
		return data;
	}
	
	/**
	 * This method is called when the user requests a logout.
	 */
	public void logout () {
		System.out.println("NavigationController Logging out.");
		navigationUI.dispose();
		new LoginController();
	}
	
	public void exit() {
		System.out.println("NavigationController exiting.");
		System.exit(0);
	}

	public void updateTable() {
		navigationUI.updateTable();
	}

	public void requestPickerUI(int pickerCode, int editCode) {
		
		if(pickerController == null) {
			pickerController = new PickerController(this, activeUser, 
					pickerCode, editCode);
		} else {
			//FIXME: GitHub Issue #16
			System.out.println("There is already a pickerController.");
		}
	}
	
	public void requestLocationController () {
		if(locationController == null) {
			System.out.println("The NavigationController is creating a"
				+ " LocationController.");
			locationController = new LocationController(this);
		} else {
			//FIXME: GitHub Issue #16
			System.out.println("The NavigationController recieved a request"
				+ " for a LocationController but there was already an existing"
				+ " LocationController.");
		}
	}
	
	public void requestContactController () {
		if(contactController == null) {
			contactController = new ContactController(this);
		} else {
			//FIXME: GitHub Issue #16
			System.out.println("The NavigationController recieved a request"
				+ " for a ContactController but there was already an existing"
				+ " ContactController.");
		}
	}
	/**
	 * This method dumps the existing pickerCoontroller.
	 * <p>
	 * If there is currently a pickerController then the navigationController 
	 * will tell it to dispose of its UI and then set the pickerController to 
	 * null.
	 */
	public void disposePickerController() {
		if(pickerController != null) {
			System.out.println("The Navigation Controller is disposing of the"
					+ " pickerController.");
			if (pickerController.hasUI()) {
				pickerController.disposeUI();
			}
			pickerController = null;
			System.out.println("The NaviagtionController disposed of the"
				+ " PickerController.");
		} else {
			System.out.println("The navigationController received a request" 
				+ " to dispose of the pickerController but there was not an"
				+ " existing pickerController.");
		}
	}
	
	/**
	 * This method will dump existing ContactController
	 * <p>
	 * If there is currently a ContactController then the navigationController 
	 * will tell it to dispose of its UI and then set the pickerController to 
	 * null.
	 */
	public void disposeContactController () {
		if(contactController != null) {
			System.out.println("The NavigationController is disposing of the" 
				+ " ContactController.");
			if(contactController.hasUI()) {
				contactController.disposeUI();
			}
			contactController = null;
			System.out.println("The NavigationController disposed of the"
					+ " ContactController.");
		} else {
			System.out.println("The NavigationController recieved a request to"
				+ " to dispose of the ContactController but there was not an"
				+ " existing ContractController.");
		}
	}
	/**
	 * This method will dump existing LocationController
	 * <p>
	 * If there is currently a LocationController then the navigationController 
	 * will tell it to dispose of its UI and then set the LocationController to 
	 * null.
	 */
	public void disposeLocationController() {
		if(locationController != null) {
			System.out.println("The NavigationController is disposing of the" 
				+ " LocationController.");
			if(locationController.hasUI()) {
				locationController.disposeUI();
			}
			locationController = null;
			System.out.println("The NavigationController disposed of the"
					+ " LocationController.");
		} else {
			System.out.println("The NavigationController recieved a request to"
				+ " to dispose of the LocationController but there was not an"
				+ " existing LocationController.");
		}
	}

	public EventTableModel getTableModel() {
		return new EventTableModel(activeUser);
	}
}
