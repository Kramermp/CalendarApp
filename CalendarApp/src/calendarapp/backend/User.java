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
	private StrongPasswordEncryptor encryptor 
			= new StrongPasswordEncryptor();
	private String username;
	private String password;
	private ArrayList<Event> eventList = new ArrayList<>();
	private ArrayList<Location> locationList = new ArrayList<>();;
	private ArrayList<Contact> contactList = new ArrayList<>();;
	
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
	
	/**
	 * Creates a User with the provided Username and Password
	 * @param username; The User's username
	 * @param password; The User's password before hash
	 */
	public User(String username, char[] password) {
		super();
		this.username = username;
		this.password = encryptor.encryptPassword(Arrays.toString(password));
		this.eventList = new ArrayList<Event>();
		this.contactList = new ArrayList<Contact>();
	}
	
	/**
	 * Creates a User object with no values
	 */
	public User(){
		super();
		this.username = "";
		this.eventList = new ArrayList<Event>();
		this.contactList = new ArrayList<Contact>();
	}
	
	/**
	 * Changes a User's username if authentic credentials are included.
	 * @param newUsername; The desired username
	 * @param orginalUsername; The current username
	 * @param password ; The User's password
	 */
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
	
	/**
	 * Adds an event to the User's event list.
	 * @param event; the Event to be added
	 */
	public void addEvent(Event event) {
		this.eventList.add(event);
		System.out.println("This user now has " + eventList.size() + " events.");
	}
	
	/**
	 * Gets the User's username.
	 * @return 
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Gets the User's password has
	 * @return 
	 */
	protected String getPassword() { 
		return this.password;
	}
	
	/**
	 * Gets the User's Event List
	 * @returns
	 */
	public ArrayList<Event> getEventList() {
		return this.eventList;
	}
	
	/**
	 * Checks if the object is equal to the User.
	 * <p>
	 * The object is equal if the it has the same Username.
	 * @param obj
	 * @return 
	 */
	public boolean equals(Object obj) {
		User testUser = (User) obj;
		return (testUser.getUsername().equals(this.getUsername()));
	}
	
    /**
	 * Adds a location to the User's Locaiton list.
	 * @param location; the Location to be added
	 */
	public void addLocation(Location location ) {
		locationList.add(location);
		System.out.println("This user now has " + locationList.size() + " locations.");
	}
	
	/**
	 * Adds the Contact to the User's contactList 
	 */
	public void addContact (Contact contact) {
		contactList.add(contact);
		System.out.println("This user now has " + contactList.size() + " contacts.");
	}
	
	/**
	 *  Get the user's location list.
	 * @return 
	 */
	public ArrayList<Location> getLocationList() {
		return locationList;
	}
	/**
	 * Gets the User's contact list.
	 * @return 
	 */
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
