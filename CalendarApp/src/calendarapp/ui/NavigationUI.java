/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.backend.NavigationController;
import calendarapp.backend.PickerController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import calendarapp.backend.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import calendarapp.Contact;
import calendarapp.Event;
import com.google.gson.Gson;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/*
			This class will provide a nice UI for the User that will then
			communicate back to the parentController for the actual tasks. This
			class should be limited to handing tasks back to the parentController
			and to visual commands.
*/
/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 * @version .1
 * @since .1
 */
public class NavigationUI extends JFrame {
	private NavigationController parentController;
	private JMenuBar menuBar;
	private JPanel topArea;
	private JPanel rightArea;
	private JPanel leftArea;
    private User user;
    private Contact contact;
	private JTable eventTable;
	private JScrollPane tablePane;
	private EventTableModel eventModel;
	private JTable contactTable;
	private ContactTableModel contactModel;
	private JPanel cards;
	private CalendarUI calendarUI;
	private ContactListUI contactListUI;
	private LocationListUI locationListUI;
	private EventListUI eventListUI;
	
	private static final String CONTACTLIST_VIEW = "Contact VIEW";
	private static final String LOCATIONLIST_VIEW = "Location VIEW";
	private static final String EVENTLIST_VIEW = "Event View";
	
        //changed to also accept User
	public NavigationUI(NavigationController parentController, User user) {
        this.user = user;
		printEventList(user);
		System.out.println("Creating NavigationUI.");
		this.parentController = parentController;
		createWindow();
		addComponents();
		System.out.println("Finished creating NavigationUI.");
	}
	
	private void printEventList(User user) {
		for(int i = 0; i < user.getEventList().size(); i++) {
			System.out.println(user.getEventList().get(i).getEventStartDate().getTime().toString());
		}
	}
	
	private void createWindow() {	
		System.out.println("Creating the NavigationUI window.");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Calendar App");
		Dimension windowSize = new Dimension(375, 667);
		//this.setPreferredSize(windowSize);
		this.setExtendedState(MAXIMIZED_BOTH);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.pack();
		this.setLocation((screenSize.width/2) - this.getWidth()/2, 
				screenSize.height/2 - this.getHeight()/2);
		System.out.println("Finished creating the NavigationUI window.");
	}
	
	private void addComponents() {
		menuBar = new JMenuBar();
		buildMenuBar();
		this.setJMenuBar(menuBar);
		this.setLayout(new GridBagLayout());
		topArea = new JPanel();
		//topArea.setBackground(Color.MAGENTA);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridwidth = 2;
		c.weightx = 1;
		c.gridy =0;
		c.weighty = .15;
		c.fill = GridBagConstraints.BOTH;
		this.add(topArea, c);
		
		leftArea = new JPanel();
		//leftArea.setBackground(Color.yellow);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = .2;
		c.gridy = 1;
		c.weighty = .85;
		c.fill = GridBagConstraints.BOTH;
		buildLeftArea(leftArea);
		this.add(leftArea, c);
		
		
		rightArea = new JPanel();
		//rightArea.setBackground(Color.red);
		buildRightArea(rightArea);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.weightx = .8;
		c.gridy =1;
		c.weighty = .85;
		c.fill = GridBagConstraints.BOTH;
		this.add(rightArea, c);
	}

	private void buildMenuBar() {
		buildFileMenu();
		buildEditMenu();
		buildViewMenu();
	}

	private void buildFileMenu() {
		//FIXME: GitHub Issue #3 
		JMenu fileMenu = new JMenu("File");
		
		fileMenu.setMnemonic(KeyEvent.VK_F); //Sets F as shortcut key
		JMenuItem logoutBtn = new JMenuItem("Logout");
		logoutBtn.setMnemonic(KeyEvent.VK_L);
		logoutBtn.addActionListener((ActionEvent ar) -> { 
			System.out.println("logoutBtn Triggered.");
			parentController.logout();
		});
		JMenuItem exitBtn = new JMenuItem("Exit");
		exitBtn.setMnemonic(KeyEvent.VK_E);
		exitBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("exitBtn triggered.");
			parentController.exit();
		});
		buildGsonMenu(fileMenu);
		fileMenu.add(logoutBtn);
		fileMenu.add(exitBtn);
		menuBar.add(fileMenu);
	}

	private void buildEditMenu() {
		//FIXME: GitHub Issue #3
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);	
		buildEventMenu(editMenu);
		buildContactMenu(editMenu);
		buildLocationMenu(editMenu);
                                
		menuBar.add(editMenu);
	}
	
	private void buildEventMenu(JMenu parentMenu) {
		//Build Event Menu
		JMenu eventMenu = new JMenu("Events");
		eventMenu.setMnemonic(KeyEvent.VK_E);
		JMenuItem createEventBtn = new JMenuItem("Create a New Event");
				createEventBtn.addActionListener((ActionEvent ae) -> {
					NavigationUI.this.parentController.requestEventController();
		});
		eventMenu.add(createEventBtn);
		createEventBtn.setMnemonic(KeyEvent.VK_N);	
		JMenuItem editEventBtn = new JMenuItem("Edit an Existing Event");

		editEventBtn.setMnemonic(KeyEvent.VK_E);
		editEventBtn.addActionListener((ActionEvent ae) -> { 
			NavigationUI.this.parentController.requestPickerController(PickerController.EVENT, PickerController.EDIT);
		});
		eventMenu.add(editEventBtn);
		JMenuItem removeEventBtn = new JMenuItem("Delete an Existing Event");
		removeEventBtn.setMnemonic(KeyEvent.VK_D);
		removeEventBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("removeEventBtn triggered.");
			NavigationUI.this.parentController.requestPickerController(PickerController.EVENT, PickerController.DELETE);
		});
		eventMenu.add(removeEventBtn);

		parentMenu.add(eventMenu);
	}

	private void buildContactMenu(JMenu parentMenu) {
		//Build Contact Menu
		//TODO: Build the functionality
		JMenu contactMenu = new JMenu("Contacts");
		contactMenu.setMnemonic(KeyEvent.VK_C);
		JMenuItem createContactBtn = new JMenuItem("Create a New Contact");
		createContactBtn.setMnemonic(KeyEvent.VK_N);
		createContactBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("Create Contact Button was triggered.");
			NavigationUI.this.parentController.requestContactController();
		});
		contactMenu.add(createContactBtn);
		JMenuItem editContactBtn = new JMenuItem("Edit an Existing Contact");
		editContactBtn.setMnemonic(KeyEvent.VK_E);
		editContactBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("EditContactBtn triggered.");
			NavigationUI.this.parentController.requestPickerController(PickerController.CONTACT, PickerController.EDIT);
		});
		contactMenu.add(editContactBtn);
		JMenuItem removeContactBtn = new JMenuItem("Delete a Contact");
		removeContactBtn.setMnemonic(KeyEvent.VK_D);
		removeContactBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("RemoveContactBtn was triggered.");
			NavigationUI.this.parentController.requestPickerController(PickerController.CONTACT, PickerController.DELETE);
		});
		contactMenu.add(removeContactBtn);

		parentMenu.add(contactMenu);
	}
	private void buildGsonMenu(JMenu parentMenu){
            FileOutputStream out = null;
            JMenu gsonMenu = new JMenu("Import/Export");
            gsonMenu.setMnemonic(KeyEvent.VK_G);
            JMenuItem importGson = new JMenuItem("Import Events/Contacts");
            importGson.setMnemonic(KeyEvent.VK_I);
            File file = new File("exportedContacts.Gson");            
            importGson.addActionListener((ActionEvent ae) ->{
                System.out.println("Import Gson selected");
                NavigationUI.this.parentController.requestPickerController(PickerController.GSON, PickerController.IMPORT);
            });
            JMenuItem exportGson = new JMenuItem("Export Events/Contacts");
            exportGson.setMnemonic(KeyEvent.VK_E);
            exportGson.addActionListener((ActionEvent ae) ->{
                try{
                    ArrayList<Contact> contactsToExport = user.getContactList();
                    System.out.println(user.getContactList());
                    PrintWriter writer = new PrintWriter(file);
                    for(int i = 0; i < contactsToExport.size(); i++ ){
                        contact = contactsToExport.get(i);
                        Gson gson = new Gson();
                        writer.println(gson.toJson(contactsToExport));
                    }
                    writer.close();
                }catch (IOException e){
                    System.err.println("Check to make sure you have events and contacts to export");
                }
                try{
                    ArrayList<Event> eventsToExport = user.getEventList();
                    PrintWriter writer = new PrintWriter("exportedEvents.Gson");
                    for(int i = 0; i < eventsToExport.size(); i++ ){
                    writer.println(user.getEventList());
                    }
                    writer.close();
                }catch (IOException e){
                }
            });
            gsonMenu.add(importGson);
            gsonMenu.add(exportGson);
            parentMenu.add(gsonMenu);
        }
	
	private void buildLocationMenu(JMenu parentMenu) {
		//TODO: Build Functionality
		JMenu locationMenu= new JMenu("Locations");
		locationMenu.setMnemonic(KeyEvent.VK_L);
		JMenuItem createLocationBtn = new JMenuItem("Create a New Location");
		createLocationBtn.setMnemonic(KeyEvent.VK_N);
		createLocationBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("Create Location Button triggered.");
			NavigationUI.this.parentController.requestLocationController();
		});
		locationMenu.add(createLocationBtn);
		JMenuItem editLocationBtn = new JMenuItem("Edit an Existing Location");
		editLocationBtn.setMnemonic(KeyEvent.VK_E);
		editLocationBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("Edit LocationBtn triggered.");
			NavigationUI.this.parentController.requestPickerController(PickerController.LOCATION, PickerController.EDIT);
		});
		locationMenu.add(editLocationBtn);
		JMenuItem removeLocationBtn = new JMenuItem("Delete a Location");
		removeLocationBtn.setMnemonic(KeyEvent.VK_D);
		removeLocationBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("Remove LocationBtn triggered.");
			NavigationUI.this.parentController.requestPickerController(PickerController.CONTACT, PickerController.DELETE);
		});
		locationMenu.add(removeLocationBtn);

		parentMenu.add(locationMenu);
	}

	private void buildViewMenu() {
		JMenu viewMenu = new JMenu("View");
		viewMenu.setMnemonic(KeyEvent.VK_V);
		JMenu colorMenu = new JMenu("Change Color Scheme");
		colorMenu.setMnemonic(KeyEvent.VK_C);
		colorMenu.addActionListener((ActionEvent ae) -> { 
			System.out.println("Color menu triggered.");
		});
		viewMenu.add(colorMenu);
		menuBar.add(viewMenu);
	}
	
	private void buildRightArea(JPanel rightArea) {
		System.out.println("Building rightArea");
		buildEventTable();
		rightArea.setLayout(new BorderLayout());
		GregorianCalendar sourceCalendar = new GregorianCalendar();
		sourceCalendar.setTimeInMillis(System.currentTimeMillis());
		calendarUI = new CalendarUI(sourceCalendar, parentController.getEventList());
		rightArea.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,10,10,10);
		c.weightx = 1;
		c.weighty = 1;
		rightArea.add(calendarUI, c);
	}
	
	private void buildLeftArea(JPanel leftArea) {
		System.out.println("Building leftArea.");
		leftArea.setLayout(new GridBagLayout());
		JPanel leftBtnArea = new JPanel();
		leftBtnArea.setLayout(new GridBagLayout());
		//leftBtnArea.setBackground(Color.RED);
		
		JButton contactBtn = new JButton("Contact");
		contactBtn.addActionListener((ActionEvent ActionEvent) -> { 
			System.out.println("ContactBtn Triggered.");
			((CardLayout)cards.getLayout()).show(cards, CONTACTLIST_VIEW);
		});
		contactBtn.setSize(new Dimension (10, 10));
		JButton locationBtn = new JButton("Location");
		locationBtn.addActionListener((ActionEvent ActionEvent) -> { 
			System.out.println("LocationBtn triggered.");
			((CardLayout)cards.getLayout()).show(cards, LOCATIONLIST_VIEW);
		});
		locationBtn.setSize(new Dimension(10, 10));
		
		JButton eventBtn = new JButton("Event");
		eventBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("EventBtn triggered.");
			((CardLayout)cards.getLayout()).show(cards, EVENTLIST_VIEW);
		});
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.insets = new Insets(0,10,0,10);
		leftBtnArea.add(contactBtn, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy =0;
		c.insets = new Insets(0,10,0,10);
		leftBtnArea.add(locationBtn, c);
		//leftBtnArea.setBackground(Color.yellow);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 2;
		c.gridy =0;
		c.insets = new Insets(0,10,0,10);
		leftBtnArea.add(eventBtn, c);
		
		c = new GridBagConstraints();
		c.gridx =0;
		c.weightx =1;
		c.gridy =0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		leftArea.add(leftBtnArea, c);
		
		cards = new JPanel();
		CardLayout cardsLayout = new CardLayout();
		contactListUI = new ContactListUI(parentController.getContactList(), this);
		cardsLayout.addLayoutComponent(contactListUI, CONTACTLIST_VIEW);
		locationListUI = new LocationListUI( parentController.getLocationList());
		cardsLayout.addLayoutComponent(locationListUI, LOCATIONLIST_VIEW);
		eventListUI = new EventListUI(this, parentController.getEventList());
		cardsLayout.addLayoutComponent(eventListUI, EVENTLIST_VIEW);
		//cards.setBackground(Color.BLUE);
		cards.setLayout(cardsLayout);
		cards.add(contactListUI, CONTACTLIST_VIEW);
		cards.add(locationListUI, LOCATIONLIST_VIEW);
		cards.add(eventListUI, EVENTLIST_VIEW);
		c = new GridBagConstraints();
		c.gridx  = 0;
		c.gridy =1;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(10,10,10,10);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		leftArea.add(cards, c);
	}
	
	private void buildEventTable() {
		eventModel = parentController.getEventTableModel();
		eventTable = new JTable(eventModel);
	}
	
	private void buildContactTable() {
		contactModel = parentController.getContactTableModel();
		contactTable = new JTable(contactModel);
	}

	public void updateEventTable() {
		calendarUI.updateCalendar();
		eventListUI.update();
	}
	
	public void updateContactTable() {
		contactListUI.update();
		((CardLayout)cards.getLayout()).show(cards, CONTACTLIST_VIEW);
	}       
	
	public void sortContacts(String sortField) {
		parentController.sortContacts(sortField);
		updateContactTable();
	}

	public void updateLocationTable() {
		System.out.println("Updating Location Table");
		locationListUI.update();
		((CardLayout)cards.getLayout()).show(cards, LOCATIONLIST_VIEW);
	}

	void sortEvents(String fieldName) {
		System.out.println("Sorint events by " + fieldName);
		parentController.sortEvents(fieldName);
		updateEventTable();
		((CardLayout)cards.getLayout()).show(cards, EVENTLIST_VIEW);
	}
}
