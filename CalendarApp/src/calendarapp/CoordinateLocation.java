package calendarapp;

import java.io.Serializable;

/**
 *
 * @author Faust
 */
public class CoordinateLocation extends Location implements Serializable {

    private double longitude = 0.00;
    private double latitude = 0.00;
    
    //There should be some type of verification on the numbers before this point
    public CoordinateLocation(String name, String description, double latitude,
            double longitude) {
        super(name, description);
        this.latitude = latitude;
        this.longitude = longitude;
    }
    

    
    //Accessors
    /**
     * @return the Location object's longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @return the Location object's latitude
     */
    public double getLatitude() {
        return latitude;
    }
    
    
    //Mutators
    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return ("Latitude: " + this.latitude + ", Longitude: " + this.longitude);
    }
    
}
