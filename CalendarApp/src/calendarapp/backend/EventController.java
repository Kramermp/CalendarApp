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
	private User activeUser;
    
    public EventController (NavigationController parentController, 
			User activeUser) {
    	System.out.println("Creating EventController.");
    	this.parentController = parentController;
		this.activeUser = activeUser;
    	this.eventUI = new EventUI(this, sourceEvent);
    	this.eventUI.setVisible(true);
    	System.out.println("Finished creating EventController.");
    }

    public EventController(NavigationController parentController, 
			User activeUser, Event sourceEvent) {
		System.out.println("Creating the Eventcontroller.");
    	this.parentController = parentController;
    	this.sourceEvent = sourceEvent;
		this.activeUser = activeUser;
		this.eventUI = new EventUI(this, sourceEvent);
    	this.eventUI.setVisible(true);
		System.out.println("Finished creating the EventController.");
    }
    public void submitEvent() {
		if(sourceEvent == null) {
			//Create new event
			Event newEvent = new Event();
			newEvent.setEventName(eventUI.getEventName());
			newEvent.setEventTag(eventUI.getEventTag());
			newEvent.setEventContactList(eventUI.getEventContacts());
			newEvent.setEventStartDate(eventUI.getEventStartDate());
			newEvent.setEventEndDate(eventUI.getEventEndDate());
			newEvent.setEventLocation(eventUI.getEventLocation());
			activeUser.addEvent(newEvent);
		} else {
			sourceEvent.setEventName(eventUI.getEventName());
			sourceEvent.setEventTag(eventUI.getEventTag());
			sourceEvent.setEventContactList(eventUI.getEventContacts());
			sourceEvent.setEventStartDate(eventUI.getEventStartDate());
			sourceEvent.setEventEndDate(eventUI.getEventEndDate());
			sourceEvent.setEventLocation(eventUI.getEventLocation());
		}
		System.out.println("Herer");
		parentController.writeData();
		disposeEventUI();
	}
    public void disposeEventUI() {
        System.out.println("EventController disposing EventUI.");
		System.out.println(activeUser.getEventList().size());
        eventUI.dispose();
        parentController.disposeEventController();
    }
}
