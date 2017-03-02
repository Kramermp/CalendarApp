package calendarapp.backend;

/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 *
 */
public class UserAlreadyExistsException extends Exception {

	/**
	 * Creates a new instance of <code>UserAlreadyExists</code> without detail
	 * message.
	 */
	public UserAlreadyExistsException() {
	}

	/**
	 * Constructs an instance of <code>UserAlreadyExists</code> with the
	 * specified detail message.
	 *
	 * @param username, the user that already exists.
	 */

	public UserAlreadyExistsException(String username) {
	super("The User: " + username + " already exists.");
	}
}
