/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Contact;
import calendarapp.backend.PickerController;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GsonPicker extends Picker implements ActionListener {
   JButton importEvents;
   JButton importContacts;
   JList gsonJList;
   JFileChooser chooser;
   String choosertitle;
   Object sourceButton;
   private PickerController parentController;


    public GsonPicker (PickerController parentController) {
        this.parentController = parentController;
        importEvents = new JButton("Import Events");
        importContacts = new JButton("Import Contacts");
        importEvents.addActionListener(this);
        importContacts.addActionListener(this);
        add(importContacts);
        add(importEvents);
        
    }




    public void actionPerformed(ActionEvent e) {
        sourceButton = e.getSource();
        chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);  
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            if(sourceButton == importContacts){
                System.out.println("ImportContacts");
                File fileToImport = chooser.getSelectedFile();
                try {
                    FileInputStream fis = new FileInputStream(fileToImport);
                    InputStreamReader in = new InputStreamReader(fis);
                    Gson gson = new Gson();
                    System.out.println("Input stream created");
                    if(parentController == null)
                        System.out.println("Parent null");
                    this.parentController.importContacts(readContactArray(gson.newJsonReader(in)));
                    
                    //(user.getContactList() + ", " + contact.returnEmail(i) + ", " + contact.phoneNumber(i))
                } catch (FileNotFoundException ex) {
                    System.out.println("Failed to import file data");
                } catch (IOException ex) {
                    Logger.getLogger(GsonPicker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (sourceButton == importEvents){
                System.out.println("ImportEvents");
                File fileToImport = chooser.getSelectedFile();
                try {
                    FileInputStream fis = new FileInputStream(fileToImport);
                    InputStreamReader in = new InputStreamReader(fis);
                    Gson gson = new Gson();
                    gson.newJsonReader(in);
//                    Event event = gson.fromJson(new FileReader(fileToImport), Event.class);
                } catch (FileNotFoundException ex) {
                    System.out.println("Failed to import file data");
                }
            }
            
        } else {
            System.out.println("No Selection ");
        }
  }
   public List<Contact> readContactArray(JsonReader reader) throws IOException {
     List<Contact> contacts = new ArrayList<>();

     reader.beginArray();
     while (reader.hasNext()) {
       System.out.println("test");
       contacts.add(readContact(reader));
     }
     reader.endArray();
     return contacts;
   }

    public Dimension getPreferredSize(){
        return new Dimension(200, 200);
    }

    

    @Override
    public int[] getSelected() {
    return gsonJList.getSelectedIndices();
    } 

  public Contact readContact(JsonReader reader) throws IOException {
     ArrayList<String> emails = new ArrayList<String>();
     ArrayList<String> phoneNumber = new ArrayList<String>();
     String firstName = "";
     String middleName = "";
     String lastName = "";
     ArrayList<Object> names = null;
     String title = "";
     String suffix = "";

     reader.beginObject();
     while (reader.hasNext()) {
       String name = reader.nextName();
       if (name.equals("emails")) {
         emails = readStringArray(reader);
       } else if (name.equals("phoneNumber")) {
         phoneNumber = readStringArray(reader);
       }else if (name.equals("name")){
         names = readObjectArray(reader);
       }else if(name.equals("title")){
          title = reader.nextString();
       }else if (name.equals("firstName")) {
         firstName = reader.nextString();
       } else if (name.equals("middleName")) {
         middleName = reader.nextString();
       } else if (name.equals("LastName")){
           lastName = reader.nextString();
       } else {
         reader.skipValue();
       }
     }
     reader.endObject();
     return new Contact(title, firstName, middleName, lastName, suffix, emails, phoneNumber);
   }
  
     public List<Double> readDoublesArray(JsonReader reader) throws IOException {
     List<Double> doubles = new ArrayList<Double>();

     reader.beginArray();
     while (reader.hasNext()) {
       doubles.add(reader.nextDouble());
     }
     reader.endArray();
     return doubles;
   }
     
    public ArrayList<String> readStringArray(JsonReader reader) throws IOException {
     ArrayList<String> strings = new ArrayList<String>();
     reader.beginArray();
     while (reader.hasNext()) {
       strings.add(reader.nextString());
     }
     reader.endArray();
     return strings;
   }
   public ArrayList<Object> readObjectArray(JsonReader reader) throws IOException {
     ArrayList<Object> strings = new ArrayList<Object>();
     reader.beginObject();
     while (reader.hasNext()) {
         int i = 0;
         if(i == 0){
       strings.add(reader.nextName());
       strings.add(reader.nextString());
       i++;
     }
         else if(i != 0){
             strings.add(reader.nextString());
         } 
     }
     reader.endObject();
     return strings;
   }
   
}