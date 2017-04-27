/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Address;
import calendarapp.CoordinateLocation;
import calendarapp.Location;
import calendarapp.backend.LocationController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author mpk5206
 */
public class LocationUI extends JFrame {
	private LocationController parentController;
	private Location sourceLocation;
	private int windowHeight;
	private int windowWidth;
	
	private JTextField locationNameTxtFld;
	private JPanel infoArea;
	private JPanel cards;
	private JTextArea descriptionArea;
	
	private boolean isAddress = true;
	
	private static final String ADDRESS_PANEL = "Card for Address";
	private static final String COORDINATE_PANEL = "Card for Coordinates";
	private AddressPanel addressPanel;
	private CoordinatePanel coordinatePanel;
	private JPanel errorPanel;
	
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
		populateFields();
		this.addWindowListener(new LocationUIWindowListener());
		System.out.println("Finished creating the LocationUI with a "
				+ " sourceLocation.");
	}
	
	private void createWindow () {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		windowHeight = (int) screenSize.getHeight() * 2 / 3; // 66% of screen height
		windowWidth = (int) screenSize.getWidth() / 5; //20% of screen width
		Dimension windowSize = new Dimension(windowWidth, windowHeight);
		setPreferredSize(windowSize);
		this.pack();
		this.setLocation((screenSize.width/2) - this.getWidth()/2, 
				screenSize.height/2 - this.getHeight()/2);
	}
	
	private void addComponents() {
		Insets txtFldInsets = new Insets(0, getWidth() / 20 , getHeight() / 100,
				getWidth() / 20);
		locationNameTxtFld = new JTextField ("Location Name", 20);
		locationNameTxtFld.setHorizontalAlignment(JTextField.HORIZONTAL);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(getHeight() / 20, getWidth() / 20 ,
				getHeight() / 100, getWidth() / 20);
		c.gridx = 0;
		c.weightx = .5;
		c.gridy = 0;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		add(locationNameTxtFld, c);
		
		JButton addressBtn = new JButton("Address");
		addressBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("AddressBtn triggered.");
			((CardLayout)cards.getLayout()).show(cards, ADDRESS_PANEL);
			isAddress = true;
		});
		addressBtn.setPreferredSize(new Dimension( 1, 25));
		c = new GridBagConstraints();
		c.insets = txtFldInsets;
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
			isAddress = false;
		});
		c = new GridBagConstraints();
		c.insets = txtFldInsets;
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
		c.fill = GridBagConstraints.BOTH;
		add(infoArea, c);
		
		
		descriptionArea = new JTextArea();
		descriptionArea.setPreferredSize(new Dimension(50, 50)); //FixMe:
		c = new GridBagConstraints();
		c.insets = txtFldInsets;
		c.gridx = 0;
		c.gridwidth = 2;
		c.weightx = 1;
		c.gridy = 3;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		JScrollPane descriptionPane = new JScrollPane(descriptionArea);
		add(descriptionPane, c);
		
		errorPanel = new JPanel();
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridwidth = 2;
		c.weightx = 1;
		c.gridy = 4;
		add(errorPanel, c);
		
		JPanel buttonArea = new JPanel();
		configureButtonArea(buttonArea);
		c.gridx = 0;
		c.gridwidth = 2;
		c.weightx = 1;
		c.gridy = 5;
		c.weighty = 1;
		add(buttonArea, c);
		
	}
	
	private void configureInfoArea(JPanel infoArea) {
		cards = new JPanel();
		CardLayout cardsLayout = new CardLayout();
		addressPanel = new AddressPanel();
		//addressPanel.setBackground(Color.GRAY);
		//addressPanel.setPreferredSize(new Dimension(200, 200));
		coordinatePanel = new CoordinatePanel();
		//coordinatePanel.setBackground(Color.yellow);
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
			LocationUI.this.parentController.dispose();
		});
		c.gridx = 1;
		c.gridy = 0;
		buttonArea.add(cancelBtn, c);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener((ActionEvent ae) -> { 
			System.out.println("DeleteBtn triggered.");
			LocationUI.this.parentController.delete(sourceLocation);
		});
                c.gridx = 2;
                c.gridy = 0;
                buttonArea.add(deleteBtn);
		
	}
	
	public Location getCALocation() throws InvalidZipCodeException, 
			NumberFormatException {
		System.out.println("Location.getCALocation() is a stub");
		if(isAddress) {
			System.out.println("The Entered Location was detected to be an"
					+ " Address.");
			String name = locationNameTxtFld.getText();
			String addressLine1 = addressPanel.getAddressLine1();
			String addressLine2 = addressPanel.getAddressLine2();
			String addressLine3 = addressPanel.getAddressLine3();
			String city = addressPanel.getCity();
			String state = addressPanel.getState();
			int zipCode = addressPanel.getZip();
			String description = descriptionArea.getText();

			return new Address(name, description, addressLine1, addressLine2,
					addressLine3, city, state, zipCode, "United States");
		} else {
			System.out.println("The entered location was detected to be a"
					+ " CoordinateLocation.");
			String name = locationNameTxtFld.getText();
			double longitude = coordinatePanel.getLongitude();
			double latitude = coordinatePanel.getLatitude();
			String description = descriptionArea.getText();
			return 	new CoordinateLocation(name, description, longitude,
					latitude);		
		}
	}
	/**
	 * This method will display the message as an error message
	 * <p>
	 * @param message String; the message to display
	 */
	public void setErrorMessage(String message) {
		errorPanel.add(new JLabel(message));
		errorPanel.revalidate();
		errorPanel.repaint();
		errorPanel.paint(errorPanel.getGraphics());
	}
	
	public boolean getAddressState () {
		return isAddress;
	}
	
	public void populateFields() {
		locationNameTxtFld.setText(sourceLocation.getName());
                if(sourceLocation instanceof Address){
                    addressPanel.addressLine1TxtFld.setText(((Address)sourceLocation).getAddressLine1());
                    addressPanel.addressLine2TxtFld.setText(((Address)sourceLocation).getAddressLine2());
                    addressPanel.addressLine3TxtFld.setText(((Address)sourceLocation).getAddressLine3());
                    
                    addressPanel.cityTxtFld.setText(((Address)sourceLocation).getCity());
                    addressPanel.stateTxtFld.setText(((Address)sourceLocation).getState());
                    addressPanel.zipTxtFld.setText(String.valueOf(((Address)sourceLocation).getZipCode()));
                }
                else if(sourceLocation instanceof CoordinateLocation){
                    coordinatePanel.latitudeTxtFld.setText(String.valueOf(((CoordinateLocation)sourceLocation).getLatitude()));
                    coordinatePanel.longitudeTxtFld.setText(String.valueOf(((CoordinateLocation)sourceLocation).getLongitude()));
                    ((CardLayout) cards.getLayout()).show(cards, COORDINATE_PANEL);
                }
	}
	
	private class AddressPanel extends JPanel {
		private JTextField addressLine1TxtFld;
		private JTextField addressLine2TxtFld;
		private JTextField addressLine3TxtFld;
		private JTextField cityTxtFld;
		private JTextField stateTxtFld;
		private JTextField zipTxtFld;
		
		
		private AddressPanel( ) {
			setLayout(new GridBagLayout());
			addComponents();
		}
		
		private void addComponents() {
			Insets txtFldInsets = new Insets(0,
					LocationUI.this.getWidth() / 20 ,
					LocationUI.this.getHeight() / 100,
					LocationUI.this.getWidth() / 20);
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.weightx = 1;
			c.gridwidth = 3;
			c.gridy = 0;	
			add(new JLabel ("This is a Address Panel"), c);
			
			
			addressLine1TxtFld = new JTextField("Address Line 1", 30);
			c.insets = txtFldInsets;
			c.gridx = 0;
			c.weightx = 1;
			c.gridwidth = 3;
			c.gridy = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(addressLine1TxtFld, c);
			
			addressLine2TxtFld = new JTextField("Address Line 2", 30);
			c = new GridBagConstraints();
			c.insets = txtFldInsets;
			c.gridx = 0;
			c.gridwidth = 3;
			c.gridy = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(addressLine2TxtFld, c);
			
			addressLine3TxtFld = new JTextField("Address Line 3", 30);
			c.insets = txtFldInsets;
			c.gridx = 0;
			c.gridwidth = 3;
			c.gridy = 3;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(addressLine3TxtFld, c);
			
			cityTxtFld = new JTextField("City");
			c = new GridBagConstraints();
			c.insets = new Insets(0, (LocationUI.this.getWidth() / 20) , 
					LocationUI.this.getHeight() / 100,  0);
			c.gridx = 0;
			c.weightx = .75;
			c.gridy = 4;
			c.fill = GridBagConstraints.BOTH;
			c.anchor = GridBagConstraints.WEST;
			add(cityTxtFld, c);
			
			stateTxtFld = new JTextField("State");
			c = new GridBagConstraints();
			c.insets = new Insets(0, LocationUI.this.getWidth() / 80,
					LocationUI.this.getHeight() / 100,  0);
			c.weightx = .25;
			c.gridx = 1;
			c.gridy = 4;
			c.fill = GridBagConstraints.BOTH;
			add(stateTxtFld, c);
			
			zipTxtFld = new JTextField("Zip");
			c = new GridBagConstraints();
			c.insets = new Insets(0, LocationUI.this.getWidth() / 80, 
					LocationUI.this.getHeight() / 100,
					LocationUI.this.getWidth() / 20 );
			c.gridx = 2;
			c.weightx = .1;
			c.gridy = 4;
			c.anchor = GridBagConstraints.EAST;
			c.fill = GridBagConstraints.BOTH;
			add(zipTxtFld, c);
		}
		
		public String getAddressLine1() {
			return addressLine1TxtFld.getText();
		}
		
		public String getAddressLine2() {
			return addressLine2TxtFld.getText();
		}
		
		public String getAddressLine3() {
			return addressLine3TxtFld.getText();
		}
		
		public String getCity() {
			return cityTxtFld.getText();
		}
		
		public int getZip() throws InvalidZipCodeException {
			try {
				int zipCode = Integer.parseInt(zipTxtFld.getText());
				if(zipCode < 0 || zipCode > 99999) {
					throw new InvalidZipCodeException("The Zip Code Enter was"
							+ " not valid.");
				} else {
					return zipCode;
				}
			} catch(NumberFormatException e){
				throw new InvalidZipCodeException("The Zip Code Entered was not"
						+ " a valid number.");	
			}
		}
		
		public String getState() {
			return stateTxtFld.getText();
		}
	}
	
	private class CoordinatePanel extends JPanel {
		private JTextField latitudeTxtFld;
		private JTextField longitudeTxtFld;
		
		private CoordinatePanel() {
			addComponents();
		}
		
		private void addComponents() {
			Insets txtFldInsets = new Insets(0,
					LocationUI.this.getWidth() / 20 ,
					LocationUI.this.getHeight() / 100,
					LocationUI.this.getWidth() / 20);
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = 0;
			c.gridy = 0;
			add(new JLabel("This is a Coordinate Panel."), c);
			
			latitudeTxtFld = new JTextField("Latitude", 30);
			c = new GridBagConstraints();
			c.insets = txtFldInsets;
			c.gridx = 0;
			c.weightx = 1;
			c.gridy = 1;
			c.fill = GridBagConstraints.BOTH;
			add(latitudeTxtFld, c);
			
			longitudeTxtFld = new JTextField("Longitude", 30);
			c = new GridBagConstraints();
			c.insets = txtFldInsets;
			c.gridx = 0;
			c.weightx = 1;
			c.gridy = 2;
			c.fill = GridBagConstraints.BOTH;
			add(longitudeTxtFld, c);
		}
		
		private double getLatitude() throws NumberFormatException {
			return Double.parseDouble(latitudeTxtFld.getText());
		}
		
		private double getLongitude() throws NumberFormatException {
			return Double.parseDouble(longitudeTxtFld.getText());
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
