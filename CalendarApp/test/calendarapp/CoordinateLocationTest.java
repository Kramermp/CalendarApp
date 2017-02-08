/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp;

import java.lang.reflect.Field;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Faust
 */
public class CoordinateLocationTest {
    
    public CoordinateLocationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLongitude method, of class CoordinateLocation.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        CoordinateLocation instance = new CoordinateLocation("Test Object", "test", 10.0, 5.0);
        double expResult = 5.0;
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLatitude method, of class CoordinateLocation.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        CoordinateLocation instance = new CoordinateLocation("Test Object", "test", 10.0, 5.0);
        double expResult = 10.0;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setLongitude method, of class CoordinateLocation.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetLongitude() throws NoSuchFieldException,
            IllegalAccessException{
        System.out.println("setLongitude");
        double longitude = 15.0;
        CoordinateLocation instance = new CoordinateLocation("Test Object", "test", 10.0, 5.0);
        instance.setLongitude(longitude);
        Field longitudeField = instance.getClass().getDeclaredField("longitude");
        longitudeField.setAccessible(true);
        assertEquals(15.0, longitudeField.getDouble(instance), 0.0);
    }

    /**
     * Test of setLatitude method, of class CoordinateLocation.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetLatitude() throws NoSuchFieldException,
            IllegalAccessException{
        System.out.println("setLatitude");
        double latitude = 15.0;
        CoordinateLocation instance = new CoordinateLocation("Test Object", "test", 10.0, 5.0);
        instance.setLatitude(latitude);
        Field latitudeField = instance.getClass().getDeclaredField("latitude");
        latitudeField.setAccessible(true);
        assertEquals(15.0, latitudeField.getDouble(instance), 0.0);
    }

    /**
     * Test of toString method, of class CoordinateLocation.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CoordinateLocation instance = new CoordinateLocation("Test Object", "test", 10.0, 5.0);
        String expResult = "Latitude: 10.0, Longitude: 5.0";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
