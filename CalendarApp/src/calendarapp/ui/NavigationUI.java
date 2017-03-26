/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.backend.NavigationController;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
	
	public NavigationUI(NavigationController parentController) {
		System.out.println("Creating NavigationUI.");
		this.parentController = parentController;
		createWindow();
		addComponents();
		System.out.println("Finished creating NavigationUI.");
	}
	
	private void createWindow() {	
		System.out.println("Creating the NavigationUI window.");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	}

	private void buildMenuBar() {
		buildFileMenu();
		buildEditMenu();
		buildViewMenu();

	}

	private void buildFileMenu() {
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
		fileMenu.add(logoutBtn);
		fileMenu.add(exitBtn);
		menuBar.add(fileMenu);
	}

	private void buildEditMenu() {
		//Not Sure what to pute here for now now will keep it commented out
		//this is commented out inthe build MenuBar section
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		buildEventMenu(editMenu);
		buildContactMenu(editMenu);
		buildLocationMenu(editMenu);
		//Populate Edit Menu
		menuBar.add(editMenu);
	}
	
	private void buildEventMenu(JMenu parentMenu) {
		//Build Event Menu
		JMenu eventMenu = new JMenu("Events");
		eventMenu.setMnemonic(KeyEvent.VK_E);
		JMenuItem createEventBtn = new JMenuItem("Create a New Event");
				createEventBtn.addActionListener((ActionEvent ae) -> {
					NavigationUI.this.parentController.requestEventUI();
		});
		eventMenu.add(createEventBtn);
		createEventBtn.setMnemonic(KeyEvent.VK_N);	
		JMenuItem editEventBtn = new JMenuItem("Edit an Existing Event");
			// Need to create some way to specify which we want edited
			// probablly sometype of list view
		editEventBtn.setMnemonic(KeyEvent.VK_E);
		eventMenu.add(editEventBtn);
		JMenuItem removeEventBtn = new JMenuItem("Delete an Existing Event");
			// Nee to create some way to specify which we want to delete
			//Probably sometype of lsit view
		removeEventBtn.setMnemonic(KeyEvent.VK_D);
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
		contactMenu.add(createContactBtn);
		JMenuItem editContactBtn = new JMenuItem("Edit an Existing Contact");
		editContactBtn.setMnemonic(KeyEvent.VK_E);
		contactMenu.add(editContactBtn);
		JMenuItem removeContactBtn = new JMenuItem("Delete a Contact");
		removeContactBtn.setMnemonic(KeyEvent.VK_D);
		contactMenu.add(removeContactBtn);

		parentMenu.add(contactMenu);
	}
	
	private void buildLocationMenu(JMenu parentMenu) {
		//TODO: Build Functionality
		JMenu locationMenu= new JMenu("Locations");
		locationMenu.setMnemonic(KeyEvent.VK_L);
		JMenuItem createContactBtn = new JMenuItem("Create a New Location");
		createContactBtn.setMnemonic(KeyEvent.VK_N);
		locationMenu.add(createContactBtn);
		JMenuItem editContactBtn = new JMenuItem("Edit an Existing Location");
		editContactBtn.setMnemonic(KeyEvent.VK_E);
		locationMenu.add(editContactBtn);
		JMenuItem removeContactBtn = new JMenuItem("Delete a Location");
		removeContactBtn.setMnemonic(KeyEvent.VK_D);
		locationMenu.add(removeContactBtn);

		parentMenu.add(locationMenu);
	}

	private void buildViewMenu() {
		JMenu viewMenu = new JMenu("View");
		viewMenu.setMnemonic(KeyEvent.VK_V);
		//Populate View Menu
		menuBar.add(viewMenu);
	}
}
