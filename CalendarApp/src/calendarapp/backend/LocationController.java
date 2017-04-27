/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.backend;

import calendarapp.Location;
import calendarapp.ui.InvalidZipCodeException;
import calendarapp.ui.LocationUI;
import javax.swing.JFrame;

/**
 *
 * @author mpk5206
 * @version .1
 * @since .1
 */
public class LocationController {
	private NavigationController parentController;
	private LocationUI locationUI;
	private Location sourceLocation;

	public LocationController(NavigationController parentController) {
		System.out.println("Creating the LocationController.");
		this.parentController = parentController;
		this.locationUI = new LocationUI(this);
		this.locationUI.setVisible(true);
		System.out.println("Finished creating the LocationController.");
	}
	
	public LocationController(NavigationController parentController, 
			Location sourceLocation) {
		System.out.println("Creating the LocationController with a"
				+ " sourceLocation.");
		this.parentController = parentController;
		this.sourceLocation = sourceLocation;
		this.locationUI = new LocationUI(this, sourceLocation);
		this.locationUI.setVisible(true);
		System.out.println("Finished the creating LoationController with a"
				+ " sourceLocation.");
	}
	
	public void dispose () {
		if (locationUI != null){
			System.out.println("Disposing of the LocationController.");
			locationUI.dispose();
			locationUI = null;
			parentController.disposeLocationController();
			System.out.println("Finished disposing of the LocationController.");
		} else {
			System.out.println("The LocationController recieved a request to"
				+ " dispose of the LocationUI but there was not an existing"
				+ " LocationUI.");
		}
	}
	
	public void submit() {
		if(sourceLocation == null) {
			try {
				System.out.println("Adding a new Location.");
				Location location = locationUI.getCALocation();
				parentController.getActiveUser().addLocation(location);
				parentController.writeData();
				dispose();
			} catch (InvalidZipCodeException e) {
				System.err.println("An error Occured while parsing the zip.");
				locationUI.setErrorMessage(e.getMessage());
			} catch (NumberFormatException e) {
				System.err.println("An Error Occured while parseing lat or long");
				locationUI.setErrorMessage("Lat or Long is incorrect");
			}
		} else {
			System.out.println("Source Location was not null implying editing a"
					+ " Location.");
			try {
				System.out.println("Saving Location");
				Location tempLocation = locationUI.getCALocation();
				parentController.getActiveUser().removeLocation(sourceLocation);
				parentController.getActiveUser().addLocation(tempLocation);
				parentController.writeData();
				dispose();
			} catch (InvalidZipCodeException e) {
				System.err.println("An error Occured while parsing the zip.");
				locationUI.setErrorMessage(e.getMessage());
			} catch (NumberFormatException e) {
				System.err.println("An Error Occured while parseing lat or long");
				locationUI.setErrorMessage("Lat or Long is incorrect");
			}
		}
	}
	
	public boolean hasUI() {
		return locationUI != null;
	}

	void disposeUI() {
		locationUI.dispose();
		locationUI = null;
	}

	void bringUIToTop() {
		locationUI.setState(JFrame.NORMAL);
		locationUI.toFront();
		locationUI.repaint();
	}

    public void delete(Location sourceLocation) {
        this.parentController.deleteLocation(sourceLocation);
    }
}
