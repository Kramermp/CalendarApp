package calendarapp.backend;

import calendarapp.backend.User;
import calendarapp.Name;
import calendarapp.backend.UserList;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * This class, SerializedData, contains the data for the program. Any requests 
 * for data will be handled through this class. This sentire class will be
 * written to disk and saved, using the SerializedDataController.
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 *
 */

public class SerializedData implements Serializable {
	private UserList userList;
	private ColorPalette colorPalette;
	
	public SerializedData() {
		System.out.println("Creating SerializedData.");
		/* 
		 * I'm not sure why this check is here anymore. I suspect it is 
		 * outdated. Testing will be required to detemine if it is not needed.
		 */	
		if (userList == null) {
			userList = new UserList();
		}
		if(colorPalette == null) {
			colorPalette = new ColorPalette();
		}
		System.out.println("Finished creating SerializedData.");
	}

	/*
	 * Used to create the date in some test instance. If you create the
	 * userList before the data.
	 */
	public SerializedData(UserList userList) {
		System.out.println("Creating SerializedData(UserList).");
		this.userList = userList;
		System.out.println("Finished creating SerializedData()");
	}
	// Similar to contructor that takes UserList.
	public void setUserList(UserList userList) {
		this.userList = userList;
	}

	/**
	 * 
	 * This method, authenicate, takes the entered credentials and returns 
	 * a boolean representing if they are authenic. 
	 * <p>
	 * To do this, it goes to the userList calls authenticate and has it check
	 * if the user exists.
	 * <p>
	 * @param  username: the username being authenticated
	 * @parem  password: the password being authenticated
	 * @return  boolean: the authenticity of the credentials provided 
	 * 
	 */
	public boolean authenticate(String username, char[] password) {
		if(userList == null) {
			System.out.println("userList is null");
		}
		return userList.authenticate(username, password);
	}

	/*
	 * It might makes sense to take this addUser methods and convert them into a 
	 * singular method that takes a User object. This will help reduce our
	 * codebase and make it  more managable.
	 */

	/**
	 * This method will have the userList add the user.
	 * <p> 
	 * If the userList can't add the user then it will throw an exception.
	 * @param requestedUsername
	 * @param password
	 * @throws UserAlreadyExistsException
	 */
	public void addUser(String requestedUsername, char[] password)
			throws UserAlreadyExistsException {
		userList.addUser(requestedUsername, password);
	}
	/**
	 * This method will have the userList add the user.
	 * <p> 
	 * If the userList can't add the user then it will throw an exception.
	 * @param name: the name of the user
	 * @param requestedUsername: the username 
	 * @param password: the password
	 * @throws UserAlreadyExistsException: if a user with that name exists
	 */
	public void addUser(Name name, String requestedUsername, char[] password) 
			throws UserAlreadyExistsException {
		userList.addUser(requestedUsername, password);
	}

	public Color getBackgroundColor() {
		return colorPalette.getBackgroundColor();
	}
	
	public Color getDefaultFontColor() {
		return colorPalette.getDefaultFontColor();
	}
	
	public Color getAlertFontColor() {
		return colorPalette.getAlertFontColor();
	}
}
