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
public abstract class Location {
    private static int lastID = 0;
    private int locationID = -1; //Set to -1 to test for failures to define
    private String name = "";
    private String description= "";
    
    
    public Location (String name, String description) {
        this.locationID = Location.getNextID(); 
        this.name = name;
        this.description = description;
    }
    
    public abstract String toString();
    
    //This program will most likely integrate a SQL table so this method will most
    //likely need to be reworked in the future to rely on that and for now I will 
    //simply create a very basic method that creates logical data.
    private static int getNextID() {
        int nextID = lastID + 1;
        lastID++;
        return nextID;
    }
    //Accessors
    public int getID() {
        return this.locationID;
    } 
    public String getName(){
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    //Mutators
    //Not sure how to control this as of now, but the system should define the ID
    //not users so this should not be public method and needs to be either protected
    //or private to prevent it from being modified. Perhaps this method shouldn't
    //be included and there will be no way to modify the ID and it will instead be
    //defined on object creation and not allowed to be modified by any means. 
    //I'll put this in for now just so that we can have this.
    private void setLocationID(int locationID) {
        this.locationID = locationID;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription (String description) {
        this.description = description;
    }
}
