/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.backend;

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

	public LocationController(NavigationController parentController) {
		System.out.println("Creating the LocationController.");
		this.parentController = parentController;
		this.locationUI = new LocationUI(this);
		this.locationUI.setVisible(true);
		System.out.println("Finished creating the LocationController.");
	}
	
	public void dispose () {
		if (locationUI != null) {
			locationUI.dispose();
			locationUI = null;
			parentController.disposeLocationController();
		} else {
			System.out.println("The LocationController recieved a request to"
				+ " dispose of the LocationUI but there was not an existing"
				+ " LocationUI.");
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
