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
public class LocationTest {
    
    public LocationTest() {
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
     * Test of toString method, of class Location.
     */
    @Test
    public void testToString() {
        //The Location objet is abstract and this test is not that important
        System.out.println("toString");
        Location instance = new LocationImpl("Mock Object", "This is an object.");
        String expResult = "This is LocationImpl toString";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getID method, of class Location.
     */
    @Test
    public void testGetID() throws IllegalAccessException, NoSuchFieldException {
        System.out.println("getID");
        Location instance = new LocationImpl("", "");
        Field lastIDField = instance.getClass().getSuperclass().getDeclaredField("lastID");
        lastIDField.setAccessible(true);
        int expResult = lastIDField.getInt(instance);
        int result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Location.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Location instance = new LocationImpl("Mock Object", "This is an object.");
        String expResult = "Mock Object";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Location.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Location instance = new LocationImpl("Mock Object", "This is an object.");
        String expResult = "This is an object.";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setName method, of class Location.
     */
    @Test
    public void testSetName() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("setName");
        String name = "New Name";
        Location instance = new LocationImpl("Mock Object", "This an object.");
        instance.setName(name);
        Field nameField = instance.getClass().getSuperclass().getDeclaredField("name");
        nameField.setAccessible(true);
        assertEquals(name, nameField.get(instance));

    }

    /**
     * Test of setDescription method, of class Location.
     */
    @Test
    public void testSetDescription() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("setDescription");
        String description = "New Description";
        Location instance = new LocationImpl("Mock Object", "This an object.");
        instance.setDescription(description);
        Field descriptionField = instance.getClass().getSuperclass().getDeclaredField("description");
        descriptionField.setAccessible(true);
        assertEquals(description, descriptionField.get(instance));
        
    }

    public class LocationImpl extends Location {
        
        public LocationImpl(String name, String description) {
            super(name, description);
        }

        public String toString() {
            return "This is LocationImpl toString";
        }
    }    
}
