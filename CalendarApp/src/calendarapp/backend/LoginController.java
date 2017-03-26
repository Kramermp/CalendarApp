package calendarapp.backend;

import javax.swing.JFrame;
import calendarapp.ui.LoginUI;
import java.awt.Color;

/**
 * This class LoginController is the controlls the Login process. It creates
 * and manages the LoginUI. If it recieves a request to register a user it will
 * create a UserController, to manage creating an account.
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 *
 */

public class LoginController {
	private SerializedDataController dataController;
	private SerializedData serializedData;
	private LoginUI loginUI;

	public LoginController() {
		System.out.println("Creating LoginController.");
		dataController = SerializedDataController.getSerializedDataController();
		serializedData = dataController.getSerializedData();
		loginUI = new LoginUI(this);
		loginUI.setVisible(true);
		System.out.println("Finished creating the LoginController.");
	}
	
	/**
	 *
	 * This method takes the entered credentials and passes them to the
	 * serializedData. The serializedData will handle actual authentication. If 
	 * serializedData determines the credentials to be valid, then the loginUI 
	 * is closed and the controller will create the new window.
	 *
	 * @param username: a String that is the username to be validated
	 * @param password: a char[] that is the password to be validated
	 *
	 */
	public void validateCredentials(String username, char[] password) {
		System.out.println("Executing validateCredentials(String, char[]).");
		if (dataController == null) {
			System.out.println("The dataController is null. Do Nothing.");
		} else {
			boolean isValid = serializedData.authenticate(username, password);
			if (isValid) {
				System.out.println("The User credentials were valid.");
				loginUI.dispose();
				User activeUser = serializedData.getUser(username);
				//This Controller will be the program.
				new NavigationController(activeUser);
			} else { // (!isValid)
				System.out.println("The credentials were not validated.");
				loginUI.failedLoginAttempt();
			}
		}
		System.out.println("Finished executing validateCredentials().");
	}

	/**
	 *
	 * This method creates a new UserController. The UserController will handle
	 * registering the new User.
	 *
	 */
	public void createUserUI() {
		System.out.println("LoginController received request for"
				+ " createNewUserWindow");
	    new UserController();
	}
	
	public Color getBackgroundColor() {
		return serializedData.getBackgroundColor();
	}

	public Color getDefaultFontColor() {
		return serializedData.getDefaultFontColor();
	}
	
	public Color getAlertFontColor() {
		return serializedData.getAlertFontColor();
	}
}
