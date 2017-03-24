package calendarapp.backend;

import calendarapp.Event;
import calendarapp.Name;
import calendarapp.Person;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import org.jasypt.util.password.StrongPasswordEncryptor;


/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 *
 */

public class User extends Person implements Serializable {
	//Will need a contactList and eventList at some point
	private static StrongPasswordEncryptor encryptor 
			= new StrongPasswordEncryptor();
	private String username;
	private String password;
	private ArrayList<Event> eventList;
	
	/**
	 * This constructor takes a name object and uses it to build the super
	 * class. Additionally, it takes a String to be used for username, and a 
	 * char[] for the password.
	 * @param name
	 * @param username
	 * @param password 
	 */
	public User(Name name, String username, char[] password) {
		super(name);
		this.username = username;
		this.password = encryptor.encryptPassword(Arrays.toString(password));
		this.eventList = new ArrayList<Event>();
	}
	
	public User(String username, char[] password) {
		super();
		this.username = username;
		this.password = encryptor.encryptPassword(Arrays.toString(password));
		this.eventList = new ArrayList<Event>();
	}

	public User(){
		super();
		this.username = "";
		this.eventList = new ArrayList<Event>();
	}


	public void setUsername(String newUsername, String orginalUsername, 
			char[] password) {
		/*
			While updating user information the first step that we should do is
			validate that the original information to ensure that the user is not
			being modified without having the original credentials.
		*/
		if(authenticate(orginalUsername, password)) {
			this.username = newUsername;
		} else {
			System.out.println("Attempted to setUsername(), but the credentials"
				+ " provided were not authentic.");
		}
	}
	/**
	 * This method validates the user information and then makes the changes to
	 * the user.
	 * @param username
	 * @param originalPassword
	 * @param newPassword 
	 */
	public void setPassword(String username, char[] originalPassword,
		char[] newPassword) {
		if(authenticate(username, originalPassword)) 
			this.password = encryptor.encryptPassword(Arrays.toString(newPassword));
		else 
			System.out.println("Invalid credentials");
	
	}
	
	/**
	 * This protected method is called to validate user credentials.
	 *
	 * @param username
	 * @param password
	 * @return 
	 */
	protected boolean authenticate(String username, char[] password) {
		if (this.username.equals(username)) {
			return encryptor.checkPassword(Arrays.toString(password), this.password);
		} 
		return false;
	}
	
	public void addEvent(Event event) {
		this.eventList.add(event);
	}
	
	public String getUsername() {
		return this.username;
	}

	protected String getPassword() { 
		return this.password;
	}
	
	public ArrayList<Event> getEventList() {
		return this.eventList;
	}
}
