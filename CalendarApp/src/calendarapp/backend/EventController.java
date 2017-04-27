/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.backend;

import calendarapp.Contact;
import calendarapp.Event;
import calendarapp.Location;
import calendarapp.ui.EventUI;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 */
public class EventController {
	private NavigationController parentController;
	private SerializedDataController dataController;
	private EventUI eventUI;
	private Event sourceEvent;
	private User activeUser;
    
    public EventController (NavigationController parentController, 
			User activeUser) {
    	System.out.println("Creating EventController.");
    	this.parentController = parentController;
		this.dataController = SerializedDataController.getSerializedDataController();
		this.activeUser = activeUser;
    	this.eventUI = new EventUI(this, sourceEvent);
    	this.eventUI.setVisible(true);
    	System.out.println("Finished creating EventController.");
    }

    public EventController(NavigationController parentController, 
			User activeUser, Event sourceEvent) {
		System.out.println("Creating the EventController.");
    	this.parentController = parentController;
    	this.sourceEvent = sourceEvent;
        this.dataController = SerializedDataController.getSerializedDataController();
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
		dataController.writeTheSerializedData();
		parentController.updateEventTable();
		dispose();
	}
	
	public void dispose() {
		if(hasUI()) {
			eventUI.dispose();
			eventUI = null;
			parentController.disposeEventController();
		} else {
			System.out.println("The EventController recieved a request to"
					+ " dispose the EventUI but there was not an existing"
					+ " EventUI.");
		}
	}
	
	public void disposeUI() {
		eventUI.dispose();
		eventUI = null;
	}
	
	public boolean hasUI() {
		return eventUI != null;
	}

	public void bringUIToTop() {
		eventUI.setState(JFrame.NORMAL);
		eventUI.toFront();
		eventUI.repaint();
	}

    public ArrayList<Location> getLocationList() {
        return activeUser.getLocationList();
    }
    
    public ArrayList<Contact> getContactList() {
        return activeUser.getContactList();
    }
    
    public void deleteEvent(Event sourceEvent){
        activeUser.getEventList().remove(sourceEvent);
    }
}
