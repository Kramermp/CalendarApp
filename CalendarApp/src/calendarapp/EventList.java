/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 */
public class EventList implements Serializable, Sortable {
	private ArrayList<Event> listOfEvents;
	private String sortMethod = "Insertion";
	private static final int INSERTIONSORT_THRESHOLD = 7;
	
	public EventList() {
		listOfEvents = new ArrayList<Event>();
	}
	
	@Override
	public void sort(String direction) {
		//This is unused as of now.
	}
	private Event[] getArray() {
		Event[] array = new Event[this.listOfEvents.size()];
		for(int i = 0; i < array.length; i++) {
			array[i] = this.listOfEvents.get(i);
		}
		return array;
	}
	@Override
	public void sortBy(String fieldName, String direction) {
		Event[] array = getArray();
		Event[] aux = array.clone();
		ListIterator<Event> i = listOfEvents.listIterator();
		switch(fieldName)  {
			default:
			case "eventName":
				System.out.println("sorting by eventName");
				sortMethod = "eventName";
				mergeSort(fieldName, aux, array, 0, array.length, 0);
				for (int j=0; j<array.length; j++) {
					i.next();
					i.set((Event) array[j]);
				}
				return;
			case "eventTag":
				System.out.println("Sorting by eventTag");
				sortMethod = "eventTag";
				mergeSort(fieldName, aux, array, 0, array.length, 0);
				for (int j=0; j<array.length; j++) {
					i.next();
					i.set((Event) array[j]);
				}
				return;
			case "eventPriority":
				System.out.println("Sorting by eventPriortity");
				sortMethod = "eventPriority";
				mergeSort(fieldName, aux, array, 0, array.length, 0);
				for (int j=0; j<array.length; j++) {
					i.next();
					i.set((Event) array[j]);
				}
				return;
		}	
	}
	
	 private static void mergeSort(String fieldName, Event[] src, 
	    Event[] dest, int low, int high, int off) {
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
    
    private static void swap(Event[] x, int a, int b) {
		Event t = x[a];
		x[a] = x[b];
		x[b] = t;
    }
	
	public ArrayList<Event> getListOfEvents() {
		return listOfEvents;
	}

	public void addEvent(Event eventToAdd) {
		int count = listOfEvents.size();
		//Fuck optimization here come some nasty Big O
		if(sortMethod.equals("eventName") && count > 0) {
			for(int i = 0; i < count; i ++) {
				if(listOfEvents.get(i).compareBy("eventName", eventToAdd) > 0) {
					listOfEvents.add(i, eventToAdd);
					break;
				}
				if(i == count - 1) {
					listOfEvents.add(eventToAdd);
				}
			}
		}else if (sortMethod.equals("eventTag") && count > 0) {
			for(int i = 0; i < count; i ++) {
				if(listOfEvents.get(i).compareBy("eventTag", eventToAdd) > 0) {
					listOfEvents.add(i, eventToAdd);
					break;
				}
				if(i == count - 1) {
					listOfEvents.add(eventToAdd);
				}
			}
		} else if (sortMethod.equals("eventPriority") && count > 0 ){ 
			for(int i = 0; i < count; i ++) {
				if(listOfEvents.get(i).compareBy("eventPriority", eventToAdd) > 0) {
					listOfEvents.add(i, eventToAdd);
					break;
				}
				if(i == count - 1) {
					listOfEvents.add(eventToAdd);
				}
			}
		} else if (sortMethod.equals("eventStartDate") && count > 0) { 
			for(int i = 0; i < count; i ++) {
				if(listOfEvents.get(i).compareBy("eventStartDate", eventToAdd) > 0) {
					listOfEvents.add(i, eventToAdd);
					break;
				}
				if(i == count - 1) {
					listOfEvents.add(eventToAdd);
				}
			}
		}else {
			listOfEvents.add(eventToAdd);
		}
	}

	public void removeEvent(Event event) {
		listOfEvents.remove(event);
	}

	public int size() {
		return listOfEvents.size();
	}

}
