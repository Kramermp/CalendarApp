package calendarapp.backend;

import calendarapp.Name;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 *
 */
public class UserList implements Serializable {
	private String userListFileName = "Userlist.ser";
	private ArrayList<User> userList = new ArrayList<User>();
	
		public UserList(){
			System.out.println("Creating a new UserList object.");
			if(userList.isEmpty() || userList == null){
				this.createTestUserList();
			}
	}
	
	public void createTestUserList(){
		System.out.println("Creating TestUserList.");
		for (int i = 0; i < 20; i++){
			userList.add(new User( Name.TEST_NAME, "Test User " + i, 
					("password".toCharArray())));
		}
		System.out.println("Test UserList created");
		//System.out.println("The UserList is: " + userList);
	}
	public void printUserList(){
		System.out.println("The UserList has these users:");
		for(int i = 0; i < userList.size(); i++){
			User currentUser = (User) userList.get(i);
			System.out.println(currentUser.getUsername());
		}
	}

	protected int getUserCount() {
		return userList.size();
	}

	
	protected boolean authenticate (String username, char[] password) {
		//Validate that the username and pasword match a user from the list
		//FIXME: Have boolean for name and password maybe
		boolean isValid = false;
		int userCount = getUserCount();
		for (int i = 0; i < userCount; i++) {
			User selectedUser = userList.get(i);
			if(selectedUser.authenticate(username, password)) {
				isValid = true;
				return isValid;
			}
		}
		return isValid;
	}
	
	protected void addUser(String username, char[] password) 
			throws UserAlreadyExistsException {
		//The boolean represents if the addition failed or passed
		boolean nameIsAvailable = checkUsername(username);
		if (nameIsAvailable == false) {
			throw new UserAlreadyExistsException(username);
		} else {
			System.out.println(username + " was not as user and was added to"
				+ " the userList.");
			userList.add(new User(Name.TEST_NAME, username, password));
		}
	}

	protected void addUser(Name name, String username, char[] password) 
			throws UserAlreadyExistsException {
		//The boolean represents if the addition failed or passed
		boolean nameIsAvailable = checkUsername(username);
		if (nameIsAvailable == false) {
			throw new UserAlreadyExistsException(username);
		} else {
			System.out.println(username + " was not as user and was added to"
				+ " the userList.");
			userList.add(new User(name, username, password));
		}
	}

	public boolean checkUsername (String requestedUsername) {
		/* 
			This returns either true or false;
				true: the username is available
				false: the username is not available
		*/
		int userCount = getUserCount();
		System.out.println("Checking that " + requestedUsername + " is not" 
			+ " already an existing user.");
		for (int i = 0; i < userCount; i++) {
			User selectedUser = userList.get(i);
			if(selectedUser.getUsername().equals(requestedUsername)) {
				return false;
			}
		}
		return true;
	}
}

