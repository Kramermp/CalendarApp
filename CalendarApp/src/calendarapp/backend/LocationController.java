/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.backend;

import calendarapp.Location;
import calendarapp.ui.LocationUI;

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
			System.out.println("Adding a new Location.");
			locationUI.getLocation();
			parentController.getActiveUser().addLocation(locationUI.getCALocation());
			parentController.writeData();
			dispose();
		} else {
			System.out.println("Source Location was not null implying editing a"
					+ " Location.");
			System.out.println("Editing a Location is not implemented yet.");
		}
	}
	
	public boolean hasUI() {
		return locationUI != null;
	}

	void disposeUI() {
		locationUI.dispose();
		locationUI = null;
	}
}
