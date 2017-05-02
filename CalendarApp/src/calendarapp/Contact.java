/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * 
 */
public class Contact extends Person implements Comparable, Serializable {
    private ArrayList<String> emails;
    private ArrayList<String> phoneNumbers;
    
	/**
	 * Creates a test Contact
	 */
	public Contact () {
		super();
		emails = new ArrayList<String>();
		phoneNumbers = new ArrayList<String>();
	}

	/**
	 * Creates a Contact and builds the Name object
	 * <p>
	 * @param title
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param suffix
	 */
	public Contact(String title, String firstName, String middleName, String lastName, String suffix) {
		super(title, firstName, middleName, lastName, suffix);
	}
	
	/**
	 * Creates a Contact, builds the Name, and sets the Emails and PhoneNumbers
	 * <p>
	 * @param title
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param suffix
	 * @param emailList
	 * @param phoneNumberList
	 */
	public Contact(String title, String firstName, String middleName, 
			String lastName, String suffix, ArrayList<String> emailList,
			ArrayList<String> phoneNumberList) {
		super(title, firstName, middleName, lastName, suffix);
		this.emails = emailList;
		this.phoneNumbers = phoneNumberList;
	}


        
        public ArrayList<String> getEmailList(){
            return emails;
        }
        public String returnEmail(int i){
           return emails.get(i);
        }
        
        public ArrayList<String> getPhoneNumbers(){
            return phoneNumbers;
        }
        public String phoneNumber(int i){
            return phoneNumbers.get(i);
        }
	
	/**
	 * Returns the Name object of the Contact
	 * <p>
	 * @return
	 */
	public Name getName() {
		return super.getName();
    }
    
	/**
	 * Compares two Contact
	 * <p>
	 * Compares two Contacts by converting them to strings and then comparing
	 * the resulting strings.
	 * <p>
	 * @param object
	 * @return
	 */
	@Override
    public int compareTo(Object object) {
		return this.toString().toLowerCase().compareTo(object.toString().toLowerCase());
    }

	/**
	 * Compares two Contacts using the specified field.
	 * <p>
	 * Takes the Contact object gets the request field and then returns the
	 * result of comparison.
	 * <p>
	 * @param fieldName
	 * @param contact
	 * @return
	 */
	public int compareBy(String fieldName, Contact contact) {
		switch (fieldName) {
			case "firstName":
				return this.getName().getFirstName().compareToIgnoreCase(contact.getName().getFirstName());
			case "lastName":
				return this.getName().toString().compareToIgnoreCase(contact.getName().toString());
			default:
				return this.getName().getFirstName().compareToIgnoreCase(contact.getName().getFirstName());
		}
    }
}
