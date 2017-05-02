/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.ListIterator;

/**
 *
 * @author Faust
 */
public class ContactList implements Sortable, Serializable {
    private static final int INSERTIONSORT_THRESHOLD = 7;
    private ArrayList<Contact> listOfContacts;
	private String sortMethod = "Insertion";
    
    public ContactList() {
		this.listOfContacts = new ArrayList<Contact>();
    }
    
    public Contact getContact(int index) {
		return this.listOfContacts.get(index);
    }
    public void addContact(Contact contactToAdd) {
		int count = listOfContacts.size();
		//Fuck optimization here come some nasty Big O
		if(sortMethod.equals("firstName") && count > 0) {
			for(int i = 0; i < count; i ++) {
				if(listOfContacts.get(i).compareBy("firstName", contactToAdd) > 0) {
					listOfContacts.add(i, contactToAdd);
					break;
				}
				if(i == count - 1) {
					listOfContacts.add(contactToAdd);
				}
			}
		}else if (sortMethod.equals("lastName") && count > 0) {
			for(int i = 0; i < count; i ++) {
				if(listOfContacts.get(i).compareBy("lastName", contactToAdd) > 0) {
					listOfContacts.add(i, contactToAdd);
					break;
				}
				if(i == count - 1) {
					listOfContacts.add(contactToAdd);
				}
			}
		} else {
			listOfContacts.add(contactToAdd);
		}
		
    }
    
    public int size() {
		return this.listOfContacts.size();
    }
    @Override 
    public void sort(String direction) {
		int contactCount = listOfContacts.size();
		Collections.sort(listOfContacts);
    }

    @Override
    public void sortBy(String fieldName, String direction) {
		Contact[] array = getArray();
		Contact[] aux = array.clone();
		ListIterator<Contact> i = listOfContacts.listIterator();
		for(int j = 0; j < listOfContacts.size(); j++) {
			System.out.println(listOfContacts.get(j).getName().getFullName(false));
		}
		switch(fieldName)  {
			case "firstName":
				System.out.println("sorting by firstName");
				sortMethod = "firstName";
				mergeSort(fieldName, aux, array, 0, array.length, 0);
				for (int j=0; j<array.length; j++) {
					i.next();
					i.set((Contact) array[j]);
				}
				return;
			case "lastName":
				System.out.println("Sorting by lastName");
				sortMethod = "lastName";
				mergeSort(fieldName, aux, array, 0, array.length, 0);
				for (int j=0; j<array.length; j++) {
					i.next();
					i.set((Contact) array[j]);
				}
				return;
			default: 
				Collections.sort(listOfContacts);
			return;
		}	
    }
    private Contact[] getArray() {
		Contact[] array = new Contact[this.listOfContacts.size()];
		for(int i = 0; i < array.length; i++) {
			array[i] = this.listOfContacts.get(i);
		}
		return array;
    }
    
    private static void mergeSort(String fieldName, Contact[] src, 
	    Contact[] dest, int low, int high, int off) {
		int length = high - low;
		//System.out.println("Here)";

		// Insertion sort on smallest arrays
		if (length < INSERTIONSORT_THRESHOLD) {
			for (int i=low; i<high; i++)
			for (int j=i; j>low &&
				(dest[j-1]).compareBy(fieldName, dest[j])>0; j--) {
				swap(dest, j, j-1);
	//		    System.out.print(dest[j].getName().getFirstName());
	//		    System.out.print(" swapped with ");
	//		    System.out.println(dest[j-1].getName().getFirstName());
			}	    
			return;
		}

		// Recursively sort halves of dest into src
		int destLow  = low;
		int destHigh = high;
		low  += off;
		high += off;
		int mid = (low + high) >>> 1;
		mergeSort(fieldName, dest, src, low, mid, -off);
		mergeSort(fieldName, dest, src, mid, high, -off);

		// If list is already sorted, just copy from src to dest.  This is an
		// optimization that results in faster sorts for nearly ordered lists.
		if ((src[mid-1]).compareBy(fieldName, src[mid]) <= 0) {
			System.arraycopy(src, low, dest, destLow, length);
			return;
		}

		// Merge sorted halves (now in src) into dest
		for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
		   if (q >= high || p < mid && (src[p]).compareBy(fieldName, src[q])<=0)
			   dest[i] = src[p++];
		   else
			   dest[i] = src[q++];
		}
    }
    
    private static void swap(Contact[] x, int a, int b) {
		Contact t = x[a];
		x[a] = x[b];
		x[b] = t;
    }

	public void removeContact(Contact sourceContact) {
		this.listOfContacts.remove(sourceContact);
	}

	public ArrayList<Contact> getListOfContacts() {
		return this.listOfContacts;
	}
}
    
