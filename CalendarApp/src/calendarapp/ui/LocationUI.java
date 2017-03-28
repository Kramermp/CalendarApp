/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.backend.LocationController;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 *
 * @author mpk5206
 */
public class LocationUI extends JFrame {
	private LocationController parentController;
	
	public LocationUI(LocationController parentController) {
		System.out.println("Creating the LocationUI.");
		this.parentController = parentController;
		createWindow();
		addComponents();
		this.addWindowListener(new LocationUIWindowListener());
		System.out.println("Finished creating the LocationUI.");
	}
	
	private void createWindow () {
		Dimension windowSize = new Dimension(375, 667);
		this.setPreferredSize(windowSize);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.pack();
		this.setLocation((screenSize.width/2) - this.getWidth()/2, 
				screenSize.height/2 - this.getHeight()/2);
	}
	
	private void addComponents() {
		//This emethod will add the components to the window.
	}
	
	private class LocationUIWindowListener implements WindowListener {

		@Override
		public void windowClosed(WindowEvent we) {
		}

		@Override
		public void windowIconified(WindowEvent we) { }// Do Nothing
		@Override
		public void windowDeiconified(WindowEvent we) { } // Do Nothing
		@Override
		public void windowActivated(WindowEvent we) { } // Do Nothing
		@Override
		public void windowDeactivated(WindowEvent we) { } // Do Nothing
		@Override
		public void windowOpened(WindowEvent we) { } // Do Nothing
		@Override
		public void windowClosing(WindowEvent we) { 
		System.out.println("LocationUI Window Closed.");
			LocationUI.this.parentController.dispose();
		}
	}
}
