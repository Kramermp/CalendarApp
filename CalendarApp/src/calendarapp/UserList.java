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
public class UserList implements Serializable {
    private ArrayList<User> listOfUsers = new ArrayList<User>();
    private String userListFileName = "Userlist.ser";
    private ArrayList<User> userList = new ArrayList<User>();
    private int userCount = 0;
    
        public UserList(){
            System.out.println("Creating a new UserList object.");
            if(userList.isEmpty() || userList == null){
                this.createTestUserList();
            }
            userCount = userList.size();
    }
    
    public void createTestUserList(){
        System.out.println("Creating TestUserList.");
        for (int i = 0; i < 20; i++){
            userList.add(new User( Name.TEST_NAME, "Test User " + i, ("password".toCharArray())));
        }
        userCount = userList.size();
        System.out.println("Test UserList created");
        //System.out.println("The UserList is: " + userList);
    }
    public void printUserList(){
        System.out.println("The UserList has these users:");
        for(int i = 0; i < userList.size(); i++){
            User currentUser = (User) userList.get(i);
            System.out.println(currentUser.getUsername());
        }
    }

    
    protected boolean authenticate (String username, char[] password) {
        //Validate that the username and pasword match a user from the list
        //FIXME: Have boolean for name and password maybe
        boolean isValid = false;
        User testUser = new User(Name.TEST_NAME, username, password);
        for (int i = 0; i < userCount; i++) {
            User selectedUser = userList.get(i);
            if(selectedUser.equals(testUser)) {
                isValid = true;
                System.out.println("Found a matching user!");
                return isValid;
            } else {
                //System.out.println("Entery did not match");
            }
        }
        
        return isValid;
    }
    protected boolean attemptAdd(String username, char[] password) {
        //The boolean represents if the addition failed or passed
        boolean nameIsAvailable = checkUsername(username);
        if (nameIsAvailable == false) {
            return false;
        } else {
            System.out.println(username + " was not as user and was added to the"
                    + " userList.");
            userList.add(new User(Name.TEST_NAME, username, password));
            return true;
        }
        
    }
    public boolean checkUsername (String requestedUsername) {
        /* 
            This returns either true or false;
                true: the username is available
                false: the username is not available
        */
        
        System.out.println("Checking that " + requestedUsername + " is not already"
                + " an existing user.");
        for (int i = 0; i < userCount; i++) {
            User selectedUser = userList.get(i);
            if(selectedUser.getUsername().equals(requestedUsername)) {
                return false;
            }
        }
        return true;
    }
}

