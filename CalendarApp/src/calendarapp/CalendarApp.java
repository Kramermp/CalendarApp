package calendarapp;

import calendarapp.backend.ContactController;
import calendarapp.backend.LocationController;
import calendarapp.backend.LoginController;
import calendarapp.backend.NavigationController;
import calendarapp.backend.User;

/**
 *
 * @author Micahel Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 */
public class CalendarApp {

    /**
     * This called to create the login controller and does nothing more.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	System.out.println("Starting the CalendarApp.");
    	new LoginController();
    }

}
