/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Faust
 */
public class Contact extends Person implements Comparable, Serializable {
    private ArrayList<String> emails;
    private ArrayList<String> phoneNumbers;
    
	
	public Contact () {
		super();
		emails = new ArrayList<String>();
		phoneNumbers = new ArrayList<String>();
	}
	public Contact(String title, String firstName, String middleName, String lastName, String suffix) {
		super(title, firstName, middleName, lastName, suffix);
	}
    public Contact(Name name) {
		super(name);
		emails = new ArrayList<String>();
		phoneNumbers = new ArrayList<String>();
    }
	
    public Name getName() {
		return super.getName();
    }
    
    @Override
    public int compareTo(Object object) {
		return this.toString().toLowerCase().compareTo(object.toString().toLowerCase());
    }

    public int compareBy(String fieldName, Contact contact) {
		switch (fieldName) {
			case "firstName":
				return this.getName().getFullName(false).compareTo(contact.getName().getFullName(false));
			default:
				return this.getName().getFirstName().compareTo(contact.getName().getFirstName());
		}
    }
}
