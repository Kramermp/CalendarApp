package calendarapp.backend;

import calendarapp.Name;
import calendarapp.ui.UserUI;

/**
 * This class will be used to control the UserUI. 
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 *
 */

public class UserController {
	private SerializedDataController dataController;
	private UserUI userUI;
	private SerializedData serializedData;

	public UserController() {
		System.out.println("Creating UserController.");
		dataController = SerializedDataController.getSerializedDataController();
		serializedData = dataController.getSerializedData();
		userUI = new UserUI(this);
		userUI.setVisible(true);
		System.out.println("Finished Creating UserController.");
	}
	/**
	 * Creates and controls a UserUI using the user provided.
	 *
	 * @param user, the User who's data should be loaded into the form.
	 */
	public UserController(User user) {
		System.out.println("Creating UserController.");
		dataController = SerializedDataController.getSerializedDataController();
		serializedData = dataController.getSerializedData();
		userUI = new UserUI(this, user);
		userUI.setVisible(true);
		System.out.println("Finished Creating UserController(User).");
	}

	public boolean registerNewUser(Name name, String username, 
			char[] password) {
	    boolean additionSuccessful = false;
		try {
			serializedData.addUser(name, username, password);
			additionSuccessful = true;
		} catch (UserAlreadyExistsException err) {
			System.out.println(err.getMessage());
		    additionSuccessful = false;    
		}
		return additionSuccessful;
	}
}
