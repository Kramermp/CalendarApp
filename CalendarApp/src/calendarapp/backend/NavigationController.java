package calendarapp.backend;

import calendarapp.Event;
import javax.swing.JFrame;
import calendarapp.ui.NavigationUI;

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
	private User activeUser;

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
		}
	}

	public void requestEventUI(Event sourceEvent) {
		if(this.eventController == null) {
			this.eventController = new EventController(this, activeUser,
					sourceEvent);
		} else {
			System.out.println("There is already an existing eventController.");
		}
	}
		
		public void disposeEventController() {
			System.out.println("NavigationController disposing eventController.");
			this.eventController = null;
		}

	public void writeData() {
		System.out.println("NavigationController writeData().");
		dataController.writeTheSerializedData();
	}
	
	public void logout () {
		System.out.println("NavigationController Logging out.");
		navigationUI.dispose();
		new LoginController();
	}
	
	public void exit() {
		System.out.println("NavigationController exiting.");
		System.exit(0);
	}
}
