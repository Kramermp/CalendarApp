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
class ContactInfo implements Serializable {
    private Name name;
    private ArrayList<PhoneNumber> phoneNumbers;
    private ArrayList<Location> address;
}
