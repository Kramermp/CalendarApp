/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.backend;

import calendarapp.Event;
import calendarapp.ui.EventUI;

/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 */
public class EventController {
	private NavigationController parentController;
	private EventUI eventUI;
	private Event sourceEvent;
    
    public EventController (NavigationController parentController) {
    	System.out.println("Creating EventController.");
    	this.parentController = parentController;
    	this.eventUI = new EventUI(this, sourceEvent);
    	this.eventUI.setVisible(true);
    	System.out.println("Finished creating EventController.");
    }

    public EventController(NavigationController parentController, 
    	Event sourceEvent) {
    	this.parentController = parentController;
    	this.sourceEvent = sourceEvent; 
   	this.eventUI = new EventUI(this, sourceEvent);
    	this.eventUI.setVisible(true);

    }
    
    public void disposeEventUI() {
        System.out.println("EventController disposing EventUI.");
        eventUI.dispose();
        parentController.disposeEventController();
    }
}
