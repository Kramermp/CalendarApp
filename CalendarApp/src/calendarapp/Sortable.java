/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp;

import java.util.ArrayList;

/**
 * This interface will be used to denote that a class is sortable. It will
 * will provide abstract methods that are used and need for sorting. 
 * @author Faust
 */
public interface Sortable {
    /**
     * This method will sort a sortable collection by its default field, in the
     * specified direction(Ascending or Descending)
     * @param direction: the direction that you want it sorted in
     */
    public abstract void sort(String direction);
    
    /**
     * This method will sort the object by the requested field and then sort in
     * the direction that is requested.
     * @param fieldName
     * @param direction 
     */
    public abstract void sortBy(String fieldName, String direction);
    
}
