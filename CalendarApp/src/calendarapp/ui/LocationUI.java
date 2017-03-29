/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Address;
import calendarapp.Location;
import calendarapp.backend.LocationController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author mpk5206
 */
public class LocationUI extends JFrame {
	private LocationController parentController;
	private Location sourceLocation;
	
	private JTextField locationNameTxtFld;
	private JPanel infoArea;
	private JPanel cards;
	
	private JTextField addressLine1TxtFld;
	private JTextField addressLine2TxtFld;
	private JTextField addressLine3TxtFld;
	
	private static final String ADDRESS_PANEL = "Card for Address";
	private static final String COORDINATE_PANEL = "Card for Coordinates";
	
	public LocationUI(LocationController parentController) {
		System.out.println("Creating the LocationUI.");
		this.parentController = parentController;
		createWindow();
		addComponents();
		this.addWindowListener(new LocationUIWindowListener());
		System.out.println("Finished creating the LocationUI.");
	}
	
	public LocationUI (LocationController parentController, 
			Location sourceLocation) {
		System.out.println("Creating the LocationUI with a sourceLocation.");
		this.parentController = parentController;
		this.sourceLocation = sourceLocation;
		createWindow();
		addComponents();
		this.addWindowListener(new LocationUIWindowListener());
		System.out.println("Finished creating the LocationUI with a "
				+ " sourceLocation.");
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
		locationNameTxtFld = new JTextField (20);
		locationNameTxtFld.setHorizontalAlignment(JTextField.HORIZONTAL);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = .5;
		c.gridy = 0;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		add(locationNameTxtFld, c);
		
		JButton addressBtn = new JButton("Address");
		addressBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("AddressBtn triggered.");
			((CardLayout)cards.getLayout()).show(cards, ADDRESS_PANEL);
		});
		addressBtn.setPreferredSize(new Dimension( 1, 25));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = .5;
		c.gridy = 1;
		c.weighty = .2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.EAST;
		add(addressBtn, c);
		
		JButton coordinateBtn = new JButton("Other");
		coordinateBtn.setPreferredSize(new Dimension( 1, 25));
		coordinateBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("CoordinateBtn triggered.");
			((CardLayout)cards.getLayout()).show(cards, COORDINATE_PANEL);
		});
		c = new GridBagConstraints();
		c.gridx = 1;
		c.weightx = .5;
		c.gridy = 1;
		c.weighty = .2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		add(coordinateBtn, c);
		
		
		infoArea = new JPanel();
		configureInfoArea(infoArea);
		c = new GridBagConstraints ();
		c.gridx = 0;
		c.gridwidth = 2;
		c.weightx = 1;
		c.gridy = 2;
		c.weighty = 1;
		add(infoArea, c);
		
		JPanel buttonArea = new JPanel();
		configureButtonArea(buttonArea);
		c.gridx = 0;
		c.gridwidth = 2;
		c.weightx = 1;
		c.gridy = 3;
		c.weighty = 1;
		add(buttonArea, c);
		
	}
	
	private void configureInfoArea(JPanel infoArea) {
		cards = new JPanel();
		CardLayout cardsLayout = new CardLayout();
		JPanel addressPanel = new AddressPanel();
		addressPanel.setBackground(Color.RED);
		//addressPanel.setPreferredSize(new Dimension(200, 200));
		JPanel coordinatePanel = new CoordinatePanel();
		coordinatePanel.setBackground(Color.yellow);
		//coordinatePanel.setPreferredSize(new Dimension(200, 200));
		cardsLayout.addLayoutComponent(coordinatePanel, COORDINATE_PANEL);
		cardsLayout.addLayoutComponent(addressPanel, ADDRESS_PANEL);
		cards.setLayout(cardsLayout);
		cards.add(coordinatePanel, COORDINATE_PANEL);
		cards.add(addressPanel, ADDRESS_PANEL);
//		infoAreaLayout.show(infoArea, ADDRESS_PANEL);
		
		infoArea.setLayout(new BorderLayout());
		infoArea.add(cards, BorderLayout.CENTER);
		((CardLayout) cards.getLayout()).show(cards, ADDRESS_PANEL);
//		((CardLayout) infoArea.getLayout()).next(infoArea);
		
	}
	
	private void configureButtonArea(JPanel buttonArea) {
		buttonArea.setLayout(new GridBagLayout());
		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener((ActionEvent) -> { 
			System.out.println("SaveBtn triggered.");
			LocationUI.this.parentController.submit();
		});
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		buttonArea.add(saveBtn, c);
		
		JButton cancelBtn= new JButton("Cancel");
		cancelBtn.addActionListener((ActionEvent ae) -> {
			System.out.println("CancelBtn Triggered.");
			LocationUI.this.dispose();
		});
		c.gridx = 1;
		c.gridy = 0;
		buttonArea.add(cancelBtn, c);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("DeleteBtn triggered.");
			System.err.println("DeleteBtn is not implemented yet.");
		});
		
	}
	
	public Location getCALocation() {
		System.out.println("Location.getCALocation() is a stub");
		return new Address();
	}
	private class AddressPanel extends JPanel {
		
		private AddressPanel( ) {
			addComponents();
		}
		
		private void addComponents() {
			add(new JLabel ("This is a Address Panel"));
		}
	}
	
	private class CoordinatePanel extends JPanel {
		
		private CoordinatePanel() {
			addComponents();
		}
		
		private void addComponents() {
			add( new JLabel("This is a Coordinate Panel."));
		}
		
	}
	
	private class LocationUIWindowListener implements WindowListener {

		@Override
		public void windowClosed(WindowEvent we) {
//			System.out.println("LocationUI Window Closed.");
//			LocationUI.this.parentController.dispose();
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
