/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author mpk5206
 */
public class Event implements Serializable {
    private String eventName = "";
	private String eventTag = "";
	private Calendar eventStartDate = new GregorianCalendar();
	private Calendar eventEndDate = new GregorianCalendar();
	private ArrayList<Contact> eventContactList = new ArrayList<Contact>();
	private Location eventLocation;
	
	public Event () {
		
	}
	public Event (Object[] eventData) {
		this.eventName = String.valueOf(eventData[0]);
//		this.eventStartDate = (Date) eventData[1];
//		this.eventEndDate = (Date) eventData[2];
	}
	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the eventTag
	 */
	public String getEventTag() {
		return eventTag;
	}

	/**
	 * @param eventTag the eventTag to set
	 */
	public void setEventTag(String eventTag) {
		this.eventTag = eventTag;
	}

	/**
	 * @return the eventStartDate
	 */
	public Calendar getEventStartDate() {
		return eventStartDate;
	}

	/**
	 * @param eventStartDate the eventStartDate to set
	 */
	public void setEventStartDate(Calendar eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	/**
	 * @return the eventEndDate
	 */
	public Calendar getEventEndDate() {
		return eventEndDate;
	}

	/**
	 * @param eventEndDate the eventEndDate to set
	 */
	public void setEventEndDate(Calendar eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	/**
	 * @return the eventContactList
	 */
	public ArrayList<Contact> getEventContactList() {
		return eventContactList;
	}

	/**
	 * @param eventContactList the eventContactList to set
	 */
	public void setEventContactList(ArrayList<Contact> eventContactList) {
		this.eventContactList = eventContactList;
	}

	/**
	 * @return the eventLocation
	 */
	public Location getEventLocation() {
		return eventLocation;
	}

	/**
	 * @param eventLocation the eventLocation to set
	 */
	public void setEventLocation(Location eventLocation) {
		this.eventLocation = eventLocation;
	}
	
	public boolean equals(Object obj) {
		Event otherEvent = (Event) obj;
		return this.getEventName().equals(otherEvent.getEventName()) &&
				this.eventStartDate.equals(otherEvent.getEventStartDate()) &&
				this.eventEndDate.equals(otherEvent.getEventEndDate());
	}
}
