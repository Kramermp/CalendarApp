/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.backend;

import java.util.Arrays;

/**
 *
 * @author Faust
 */
public class User {
    private String username = "";
    private char[] password;
    
    public User(String username, char[] password) {
        this.username = username;
        this.password = password;
    }
    
    public boolean authenticate(String username, char[] password) {
        if(this.username.equals(username) && Arrays.equals(this.password, password))
            return true;
        else {
//            System.out.println("Expected Username: "  + this.username);
//            System.out.println("Got username: " + username);
//            System.out.println("Expected Password: " + this.password.toString());
//            System.out.println("Got Password: " + password.toString());
            return false;
        }
            
    }
    
    //Accessors
    public String getUsername() {
        return this.username;
    }
    
    protected char[] getPassword() {
        return this.password;
    }
        
    //Mutators
    protected void setUsername(String username, char[] password) {
        if(Arrays.equals(this.password, password))
            this.username = username;
    }
    
    protected void setPassword(char[] oldPassword, char[] password) {
        if(Arrays.equals(this.password, oldPassword))
            this.password = password;
    }
    
}
