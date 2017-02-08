/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.backend;

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
public class UserTest {
    
    public UserTest() {
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
     * Test of authenticate method, of class User.
     */
    @Test
    public void testAuthenticateTrue() {
        System.out.println("authenticate; expected true");
        String username = "Test";
        char[] password = "pass".toCharArray();
        User instance = new User("Test", "pass".toCharArray());
        boolean expResult = true;
        boolean result = instance.authenticate(username, password);
        assertEquals(expResult, result);
    }
    
    /**
    * Test of authenticate method, of class User.
    */
    @Test
    public void testAuthenticateFalse() {
        System.out.println("authenticate; expected false");
        String username = "Test";
        char[] password = "pass".toCharArray();
        User instance = new User("Test", password);
        boolean expResult = false;
        boolean result = instance.authenticate(username, "Not password".toCharArray());
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        User instance = new User("Test", "pass".toCharArray());;
        String expResult = "Test";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = new User("Test", "pass".toCharArray());;
        char[] expResult = "pass".toCharArray();
        char[] result = instance.getPassword();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class User.
     */
    @Test
    public void testSetUsernameTrue() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("setUsername: True");
        String username = "New Username";
        char[] password = "pass".toCharArray();
        User instance = new User("Test", "pass".toCharArray());
        instance.setUsername(username, password);
        Field field = instance.getClass().getDeclaredField("username");
        field.setAccessible(true);
        assertEquals(username, field.get(instance));
    }
    
    /**
    * Test of setUsername method, of class User.
    */
    @Test
    public void testSetUsernameFail() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("setUsername: Fail");
        String username = "New Username";
        char[] password = "pass".toCharArray();
        User instance = new User("Test", password);
        instance.setUsername(username, "Wrong Password".toCharArray());
        Field field = instance.getClass().getDeclaredField("username");
        field.setAccessible(true);
        assertEquals("Test", field.get(instance));
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPasswordTrue() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("setPassword: True");
        String username = "Test";
        char[] oldPassword = "pass".toCharArray();
        char[] password = "New Pass".toCharArray();
        User instance = new User(username, oldPassword);
        instance.setPassword(oldPassword, password);
        Field field = instance.getClass().getDeclaredField("password");
        field.setAccessible(true);
        assertEquals(password, field.get(instance));
    }
    
        /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPasswordFail() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("setPassword: False");
        String username = "Test";
        char[] oldPassword = "pass".toCharArray();
        char[] password = "New Pass".toCharArray();
        User instance = new User(username, oldPassword);
        instance.setPassword("Wrong Password".toCharArray(), password);
        Field field = instance.getClass().getDeclaredField("password");
        field.setAccessible(true);
        assertEquals(oldPassword, field.get(instance));
    }
    
}
