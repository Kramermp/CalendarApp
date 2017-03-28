package calendarapp.backend;

import calendarapp.Contact;
import calendarapp.Event;
import calendarapp.Location;
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
	private ArrayList<Location> locationList;
	private ArrayList<Contact> contactList;
	
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
		this.locationList = new ArrayList<Location>();
		this.contactList = new ArrayList<Contact>();
	}
	
	public User(String username, char[] password) {
		super();
		this.username = username;
		this.password = encryptor.encryptPassword(Arrays.toString(password));
		this.eventList = new ArrayList<Event>();
		this.contactList = new ArrayList<Contact>();
	}

	public User(){
		super();
		this.username = "";
		this.eventList = new ArrayList<Event>();
		this.contactList = new ArrayList<Contact>();
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
	
	public boolean equals(Object obj) {
		User testUser = (User) obj;
		return (testUser.getUsername().equals(this.getUsername()));
	}
    
	public void addLocation(Location location ) {
		locationList.add(location);
	}
	
	public void addContact (Contact contact) {
		contactList.add(contact);
	}
	
	public ArrayList<Location> getLocationList() {
		return locationList;
	}
	
	public ArrayList<Contact> getContactList() {
		return this.contactList;
	}
	public ArrayList<String> getLocationStringList(){
		ArrayList<String> testLocations = new ArrayList<String>();
		testLocations.add("State College, PA");
		testLocations.add("Lewistown, PA");
		testLocations.add("New York, NY");
		testLocations.add("Harrisburg, PA");
		testLocations.add("Philladelphia, PA");
		testLocations.add("Pittsburgh, PA");
		testLocations.add("Scranton, PA");
		testLocations.add("San Fransisco, CA");
		testLocations.add("Dallas, TX");
		testLocations.add("Orlando, FL");
		testLocations.add("Baltimore, MD");
		testLocations.add("Columbus, OH");
		return testLocations;
	}
}
