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
public class NameTest {
    
    public NameTest() {
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
     * Test of getTitle method, of class Name.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "");
        String expResult = "Mr.";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFirstName method, of class Name.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "");
        String expResult = "Michael";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMiddleName method, of class Name.
     */
    @Test
    public void testGetMiddleName() {
        System.out.println("getMiddleName");
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "");
        String expResult = "Packer";
        String result = instance.getMiddleName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMiddleInitial method, of class Name.
     */
    @Test
    public void testGetMiddleInitial() {
        System.out.println("getMiddleInitial");
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "");
        String expResult = "P.";
        String result = instance.getMiddleInitial();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLastName method, of class Name.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "");
        String expResult = "Kramer";
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSuffix method, of class Name.
     */
    @Test
    public void testGetSuffixVoid() {
        System.out.println("getSuffix: Void");
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "");;
        String expResult = "";
        String result = instance.getSuffix();
        
        
        assertEquals(expResult, result);   
    }
    /**
     * Test of getSuffix()
     */
    @Test
    public void testGetSuffix() {
        System.out.println("getSuffix");
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "Jr.");
        String expResult = "Jr.";
        String result = instance.getSuffix();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class Name.
     */
    @Test
    public void testSetTitle() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("setTitle");
        String title = "The Honorable";
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "Jr.");
        instance.setTitle(title);
        
        Field field = instance.getClass().getDeclaredField("title");
        field.setAccessible(true);
        assertEquals(title, field.get(instance));
    }

    /**
     * Test of setFirstName method, of class Name.
     */
    @Test
    public void testSetFirstName() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("setFirstName");
        String firstName = "Gerard";
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "Jr.");
        instance.setFirstName(firstName);
        
        Field field = instance.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        assertEquals(firstName, field.get(instance));
    }

    /**
     * Test of setMiddleName method, of class Name.
     */
    @Test
    public void testSetMiddleName() throws NoSuchFieldException, IllegalAccessException{
        System.out.println("setMiddleName");
        String middleName = "New Middle Name";
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "Jr.");
        instance.setMiddleName(middleName);
        
        Field field = instance.getClass().getDeclaredField("middleName");
        field.setAccessible(true);
        assertEquals(middleName, field.get(instance));
    }

    /**
     * Test of setLastName method, of class Name.
     */
    @Test
    public void testSetLastName() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("setLastName");
        String lastName = "ASDF";
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "Jr.");
        instance.setLastName(lastName);
         
        Field field = instance.getClass().getDeclaredField("lastName");
        field.setAccessible(true);
        assertEquals(lastName, field.get(instance));
    }

    /**
     * Test of setSuffix method, of class Name.
     */
    @Test
    public void testSetSuffix() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("setSuffix");
        String suffix = "Sr.";
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "Jr.");
        instance.setSuffix(suffix);
        
        Field field = instance.getClass().getDeclaredField("suffix");
        field.setAccessible(true);
        assertEquals(suffix, field.get(instance));
    }

    /**
     * Test of compareTo method, of class Name.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Object otherName = new Name("Mr.", "Michael", "Packer", "Kramer", "Jr.");
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "Jr.");
        int expResult = 0;
        int result = instance.compareTo(otherName);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Name.
     */
    @Test
    public void testToStringFull() {
        System.out.println("toString");
        Name instance = new Name("Mr.", "Michael", "Packer", "Kramer", "Jr.");
        String expResult = "Kramer, Michael P.";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
        /**
     * Test of toString method, of class Name.
     */
    @Test
    public void testToStringNoLast() {
        System.out.println("toString");
        Name instance = new Name("Mr.", "Michael", "Packer", "", "Jr.");
        String expResult = "Michael P.";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
        /**
     * Test of toString method, of class Name.
     */
    @Test
    public void testToStringNoFirst() {
        System.out.println("toString");
        Name instance = new Name("Mr.", "", "Packer", "Kramer", "Jr.");
        String expResult = "Kramer P.";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
        /**
     * Test of toString method, of class Name.
     */
    @Test
    public void testToStringNoMiddle() {
        System.out.println("toString");
        Name instance = new Name("Mr.", "Michael", "", "Kramer", "Jr.");
        String expResult = "Kramer, Michael";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
        /**
     * Test of toString method, of class Name.
     */
    @Test
    public void testToStringOnlyLast() {
        System.out.println("toString");
        Name instance = new Name("Mr.", "", "", "Kramer", "Jr.");
        String expResult = "Kramer";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
        /**
     * Test of toString method, of class Name.
     */
    @Test
    public void testToStringOnlyFirst() {
        System.out.println("toString");
        Name instance = new Name("Mr.", "Michael", "", "", "Jr.");
        String expResult = "Michael";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
        /**
     * Test of toString method, of class Name.
     */
    @Test
    public void testToStringOnlyMiddle() {
        System.out.println("toString");
        Name instance = new Name("Mr.", "", "Packer", "", "Jr.");
        String expResult = "Packer";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
