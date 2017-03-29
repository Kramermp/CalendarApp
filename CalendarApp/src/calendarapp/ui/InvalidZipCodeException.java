/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 */
public class InvalidZipCodeException extends Exception {
	
	public InvalidZipCodeException(String message) {
		super(message);
	}
}
