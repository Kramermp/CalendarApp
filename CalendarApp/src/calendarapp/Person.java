/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp;

/**
 *
 * @author Faust
 */
public abstract class Person {
    private Name name;
    
    /**
     *  This constructor populates creates the person object and populates their
     *  name.
     * @param title
     * @param firstName
     * @param middleName
     * @param lastName
     * @param suffix 
     */
    public Person(String title, String firstName, String middleName, 
	    String lastName, String suffix) {
	name = new Name(title, firstName, middleName, lastName, suffix);
    }
    /**
     * This constructor takes a pre-built name object and uses this object to
     * create the person object.
     * @param name 
     */
    public Person(Name name) {
	this.name = name;
    }
}
