
package calendarapp;

import java.io.Serializable;

/**
 *
 * @author Faust
 */
public class Name implements Comparable, Serializable {
    public static final Name TEST_NAME = new Name("", "TEST", "", "USER", "");
    private String title = "";
    //Maybe add nickname field?
    //Maybe Company name 
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private String suffix = "";

    public Name() {
        
    }
    
    public Name(String title, String firstName, String middleName, 
            String lastName, String suffix) {
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffix = suffix;
    }
    
    public String getFullName(boolean extras) {
        String stringForm = "";
        if (extras) {
            if (!this.title.isEmpty()) {
                stringForm = stringForm + this.title + " ";
            }
            if (!this.firstName.isEmpty()) {
                stringForm = stringForm  + this.firstName + " ";
            }
            if(!this.middleName.isEmpty()) {
                stringForm = stringForm + this.getMiddleInitial() + " ";
            }
            if(!this.lastName.isEmpty()) {
                stringForm = stringForm + this.lastName + " ";
            }
            if (!this.suffix.isEmpty()) {
                stringForm = stringForm + this.suffix + " ";
            }
        } else {
            if (!this.firstName.isEmpty()) {
                stringForm = stringForm  + this.firstName + " ";
            }
            if(!this.middleName.isEmpty()) {
                stringForm = stringForm + this.getMiddleInitial() + " ";
            }
            if(!this.lastName.isEmpty()) {
                stringForm = stringForm + this.lastName;
            }
        }
        return stringForm;
    }
    /**
     * @return the title of the Name
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return this.middleName;
    }
    
    public String getMiddleInitial() {
        if(this.middleName != null && !this.middleName.isEmpty())
            return String.valueOf(middleName.charAt(0)).toUpperCase() + ".";
        else
            return "";
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        if (this.lastName != null && !this.lastName.isEmpty())
            return this.lastName;
        else
            return "";
    }

    /**
     * @return the suffix
     */
    public String getSuffix() {
        return this.suffix;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName.trim();
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    /**
     * @param suffix the suffix to set
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix.trim();
    }

    @Override
    public int compareTo(Object otherName) {
        return this.toString().compareTo(otherName.toString());
    }
    
    @Override 
    public String toString() {
        String stringForm = "";
        if (this.lastName != null && !this.lastName.isEmpty()) {
           stringForm = lastName;
        }
        if (this.firstName != null && !this.firstName.isEmpty()) {
            if (stringForm.isEmpty()) {
                stringForm = this.firstName;
            } else {
                stringForm = stringForm + ", " + firstName;
            }
        }
        if (this.middleName != null && !this.middleName.isEmpty()) {
            if (stringForm.isEmpty()) {
                stringForm = getMiddleName();
            } else {
                stringForm = stringForm + " " + getMiddleInitial();
            }  
        }
       return stringForm;
    }
    
}
