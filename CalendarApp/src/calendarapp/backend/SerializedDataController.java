package calendarapp.backend;

import java.io.FileInputStream;
import calendarapp.Name;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class will control the SerializedData. 
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 *
 */
public class SerializedDataController {
	private static SerializedData theSerializedData;
	private String filepath = "Data.ser";

	private SerializedDataController () {
		getTheSerializedData();
	}

	//This is method will attempt to get the serializedData
	private void getTheSerializedData() {
		readTheSerializedData();
		if(theSerializedData == null) {
			System.out.println("After executing readTheSerializedData(),"
				+ " theSerializedData was null.");
			createTheSerializedData();
			//After we create the sample Data we should then write it to disk
			writeTheSerializedData();
			//After it is written to disk we should then read it back
			readTheSerializedData();
		}
	}

	//Will attempt to read the serializedData from disk
	private void readTheSerializedData() {
		System.out.println("Executing readTheSerializedData().");
		String errorType = null;
		String errorMessage = null;
		try {
			FileInputStream fis = null;
			ObjectInputStream in = null;
			fis = new FileInputStream(filepath);
			in = new ObjectInputStream(fis);
			theSerializedData = (SerializedData) in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			errorType = "FileNotFoundException";
			errorMessage = e.getMessage();
		} catch (IOException e) {
			errorType = "IOException";
			errorMessage = e.getMessage();
		} catch (ClassNotFoundException e) {
			errorType = "ClassNameNotFoundException";
			errorMessage = e.getMessage();
		} catch (Exception e) {
			errorType = e.getClass().getName();
			errorMessage = e.getMessage();
		} finally {
			if(errorType != null) {
				System.out.println(errorType + " occurred while executing"
					+ " readTheSerializedData().");
				System.out.println(errorMessage);
			}
		}
	}

	//Will write the active serialized data to disk.
	private void writeTheSerializedData() {
		System.out.println("Executing writeTheSerializedData().");
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filepath);
			out = new ObjectOutputStream(fos);
			out.writeObject(theSerializedData);
			out.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	//Creates a new serializedData
	private void createTheSerializedData() {
		System.out.println("Executing createTheSerializedData().");
		theSerializedData = new SerializedData();
	}

	// returns the serializedData object.
	public SerializedData getSerializedData() {
		return theSerializedData;
	}

	/**
	 *
	 * This method returns the single SerializedDataController.
	 * <p>
	 * To do this it uses the private class LazyHolder. It accesses the private 
	 * static final variable INSTANCE and returns it. The INSTANCE variable is a
	 * SerialzedDataController. This method ensures that there is only one 
	 * SerializedDataController object for the entire program and is 
	 * thread safe.
	 * <p>
	 * This method follows the  Initialization-on-Demand Holder Idiom pattern.
	 *
	 * @return the single SierialzedDataController
	 *
	 */
	public static SerializedDataController getSerializedDataController() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final SerializedDataController INSTANCE = 
				new SerializedDataController();
	}
}
