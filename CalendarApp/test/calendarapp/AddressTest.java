
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
public class AddressTest {
    
    public AddressTest() {
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
     * Test of toString method, of class Address.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String eol = System.getProperty("line.separator");
        Address instance = new Address("Test Address", "Test Description", 
                "Test Line 1", "Test Line 2" , "Test Line 3", "Test City",
                "Test State", 0, "Test Country");  
        String expResult = "Test Line 1" + eol + "Test Line 2" + eol + "Test Line 3"
                + eol + "Test City, Test State 0";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddressLine1 method, of class Address.
     */
    @Test
    public void testGetAddressLine1() {
        System.out.println("getAddressLine1");
        Address instance = new Address("Test Address", "Test Description", 
                "Test Line 1", "Test Line 2" , "Test Line 3", "Test City",
                "Test State", 0, "Test Country");
        String expResult = "Test Line 1";
        String result = instance.getAddressLine1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddressLine2 method, of class Address.
     */
    @Test
    public void testGetAddressLine2() {
        System.out.println("getAddressLine2");
        Address instance = new Address("Test Address", "Test Description", 
                "Test Line 1", "Test Line 2" , "Test Line 3", "Test City",
                "Test State", 0, "Test Country");
        String expResult = "Test Line 2";
        String result = instance.getAddressLine2();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddressLine3 method, of class Address.
     */
    @Test
    public void testGetAddressLine3() {
        System.out.println("getAddressLine3");
        Address instance = new Address("Test Address", "Test Description", 
                "Test Line 1", "Test Line 2" , "Test Line 3", "Test City",
                "Test State", 0, "Test Country");
        String expResult = "Test Line 3";
        String result = instance.getAddressLine3();
        assertEquals(expResult, result);
    }

    /**
     * Test of getZipCode method, of class Address.
     */
    @Test
    public void testGetZipCode() {
        System.out.println("getZipCode");
        Address instance = new Address("Test Address", "Test Description", 
                "Test Line 1", "Test Line 2" , "Test Line 3", "Test City",
                "Test State", 0, "Test Country");
        int expResult = 0;
        int result = instance.getZipCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Address.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Address instance = new Address("Test Address", "Test Description", 
                "Test Line 1", "Test Line 2" , "Test Line 3", "Test City",
                "Test State", 0, "Test Country");
        String expResult = "Test State";
        String result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCountry method, of class Address.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        Address instance = new Address("Test Address", "Test Description", 
                "Test Line 1", "Test Line 2" , "Test Line 3", "Test City",
                "Test State", 0, "Test Country");
        String expResult = "Test Country";
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddressLine1 method, of class Address.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.NoSuchFieldException
     */
    @Test
    public void testSetAddressLine1() throws IllegalAccessException, NoSuchFieldException {
        System.out.println("setAddressLine1");
        String addressLine1 = "New Line 1";
        Address instance = new Address("Test Address", "Test Description", 
                "Test Line 1", "Test Line 2" , "Test Line 3", "Test City",
                "Test State", 0, "Test Country");
        
        instance.setAddressLine1(addressLine1);
        Field line1Field = instance.getClass().getDeclaredField("addressLine1");
        line1Field.setAccessible(true);
        assertEquals(addressLine1, line1Field.get(instance));

    }

    /**
     * Test of setAddressLine2 method, of class Address.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetAddressLine2() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        System.out.println("setAddressLine2");
        String addressLine2 = "New Line 2";
        Address instance = new Address("Test Address", "Test Description", 
                "Test Line 1", "Test Line 2" , "Test Line 3", "Test City",
                "Test State", 0, "Test Country");
        instance.setAddressLine2(addressLine2);
        
        Field line2Field = instance.getClass().getDeclaredField("addressLine2");
        line2Field.setAccessible(true);
        assertEquals(addressLine2, line2Field.get(instance));
    }

    /**
     * Test of setAddressLine3 method, of class Address.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetAddressLine3() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("setAddressLine3");
        String addressLine3 = "New Line 3";
        Address instance = new Address("Test Address", "Test Description", 
                "Test Line 1", "Test Line 2" , "Test Line 3", "Test City",
                "Test State", 0, "Test Country");
        instance.setAddressLine3(addressLine3);
        
        Field line3Field = instance.getClass().getDeclaredField("addressLine3");
        line3Field.setAccessible(true);
        assertEquals(addressLine3, line3Field.get(instance));
    }

    /**
     * Test of setZipCode method, of class Address.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.NoSuchFieldException
     */
    @Test
    public void testSetZipCode() throws IllegalAccessException, NoSuchFieldException {
        System.out.println("setZipCode");
        int zipCode = 5;
        Address instance = new Address("Test Address", "Test Description", 
                "Test Line 1", "Test Line 2" , "Test Line 3", "Test City",
                "Test State", 0, "Test Country");
        instance.setZipCode(zipCode);
        
        Field zipCodeField = instance.getClass().getDeclaredField("zipCode");
        zipCodeField.setAccessible(true);
        assertEquals(zipCode, zipCodeField.getInt(instance));
        
    }

    /**
     * Test of setState method, of class Address.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSetState() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("setState");
        String state = "New State";
        Address instance = new Address("Test Address", "Test Description", 
                "Test Line 1", "Test Line 2" , "Test Line 3", "Test City",
                "Test State", 0, "Test Country");
        instance.setState(state);
        
        Field stateField = instance.getClass().getDeclaredField("state");
        stateField.setAccessible(true);
        assertEquals(state, stateField.get(instance));
    }

    /**
     * Test of setCountry method, of class Address.
     */
    @Test
    public void testSetCountry() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("setCountry");
        String country = "New Country";
        Address instance = new Address("Test Address", "Test Description", 
                "Test Line 1", "Test Line 2" , "Test Line 3", "Test City",
                "Test State", 0, "Test Country");
        instance.setCountry(country);
        
        Field countryField = instance.getClass().getDeclaredField("country");
        countryField.setAccessible(true);
        assertEquals(country, countryField.get(instance));
    }
    
}
