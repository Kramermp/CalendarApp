/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.backend.LoginController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.Border;


/*
			This class will provide a nice UI for the User that will then
			communicate back to the parentController for the actual tasks. This
			class should be limited to handing tasks back to the parentController
			and to visual commands.
*/
/**
 *
 * @author Michael Kramer <mpk5206 @ psu.edu>
 */
public class LoginUI extends JFrame {
	private LoginController parentController;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private JTextField usernameTxtFld;
	private JPasswordField passwordFld;
	private JPanel errorMessage;
	private JButton submitBtn;
	private JButton cancelBtn;
	private JButton registerBtn;
	
	private Color backgroundColor;
	private Color defaultFontColor = Color.red;
	private Color alertFontColor;
	
	public LoginUI(LoginController parentController) {
		System.out.println("Creating LoginUI.");
		this.parentController = parentController;
		this.backgroundColor = parentController.getBackgroundColor();
		this.defaultFontColor = parentController.getDefaultFontColor();
		this.alertFontColor = parentController.getAlertFontColor();
		createWindow();
		addComponents();
		System.out.println("Finished creating LoginUI.");
	}
	
	private void createWindow() {	
		System.out.println("Creating the LoginUI window.");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		Dimension windowSize = new Dimension(375, 667);
		this.setPreferredSize(windowSize);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBackground(backgroundColor);
		this.pack();
		this.setLocation((screenSize.width/2) - this.getWidth()/2, 
				screenSize.height/2 - this.getHeight()/2);
		System.out.println("Finished creating the LoginUI window.");
	}
	
	private void addComponents() {
		this.getContentPane().setBackground(backgroundColor);
		/*
			This method will add the components to the window and actually
			create the things needed for users to interact with the program
		*/
		
		GridBagLayout loginLayout = new GridBagLayout();
		this.setLayout(loginLayout);
		
		GridBagLayout guiLayout = new GridBagLayout();
		this.setLayout(guiLayout);
		// The labelInset will be the standard inset used by the label objects
		Insets labelInset = new Insets(15, 0, 0, 0);
		// The fieldInset will be the standard inset used by the field objects
		Insets fieldInset = new Insets(5, 0, 0, 0);
		
		usernameLbl = new JLabel("USERNAME");
		usernameLbl.setForeground(defaultFontColor);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = labelInset;
		c.anchor = GridBagConstraints.SOUTH;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 0;
		c.weighty = .75;
		this.add(usernameLbl, c);
		
		// mpk5206 is one of the accept usernames
		usernameTxtFld = new JTextField("Test User 1", 25);
		styleJTextField(usernameTxtFld);
		//FIXME: The userNameTxtFld should begin empty
		usernameTxtFld.addActionListener(new UsernameTxtFldListener());
		c = new GridBagConstraints();
		c.insets = fieldInset;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 3;
		this.add(usernameTxtFld, c);
		
		passwordLbl = new JLabel("PASSWORD");
		passwordLbl.setForeground(defaultFontColor);
		c = new GridBagConstraints();
		c.insets = labelInset;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 4;
		this.add(passwordLbl, c);
		
		//password is the password of the mpk5206 account
		passwordFld = new JPasswordField("password", 25);
		styleJTextField(passwordFld);
		//FIXME: passwordFld should begin empty
		passwordFld.addActionListener(new PasswordFldListener());
		c = new GridBagConstraints();
		c.insets = fieldInset;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 5;
		this.add(passwordFld, c);
		
		errorMessage = new JPanel();
		//errorMessage.setBackground( new Color(0,0,0));
		JLabel errorText = new JLabel("The Username or Password was"
			+ " incorrect.");
		errorText.setForeground(alertFontColor);
		errorMessage.add(errorText);
		errorMessage.setEnabled(false);
		errorMessage.setVisible(false);
		errorMessage.setBackground(backgroundColor);
		c = new GridBagConstraints();
		c.insets = fieldInset;
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 6;	
		this.add(errorMessage, c);	

		cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new CancelBtnListener());
		c = new GridBagConstraints();
		c.insets = new Insets(150, 10, 0, 5);
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridy = 6;
		this.add(cancelBtn, c);
		
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new SubmitBtnListener());
		c = new GridBagConstraints();
		c.insets = new Insets(150, 5, 0, 10);
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridy = 6;
		this.add(submitBtn, c);
		
		registerBtn = new JButton("Register");
		registerBtn.addActionListener(new RegisterBtnListener());
		c = new GridBagConstraints();
		c.insets = new Insets(5, 10, 0, 10);
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridy = 7;
		c.weighty = .75;
		this.add(registerBtn, c);	
	}
	/*
		In the Section below this are the actual methods that this class will
		rely on to communicate back to its parentController
	*/
	private void submitCredentials() {
		/*
			This class takes the values that the user has entered and returns
			them back to the parent controller, which will actually validate the 
			input that the user has entered.
		*/
		
		String username;
		char[] password;

		username = LoginUI.this.usernameTxtFld.getText();
		password = LoginUI.this.passwordFld.getPassword();

		System.out.println("The Fields that were entered are ");
		System.out.println("Username: " + username);
		System.out.println("Password: " + Arrays.toString(password));
		
		//Will now submit this back to the controller class
		parentController.validateCredentials(username, password);
	}
	
	/**
	 *  This method displays a message that the login was unsuccessful.
	 *  <p>
	 * 
	 */
	public void failedLoginAttempt() {
		errorMessage.setEnabled(true);
		errorMessage.setVisible(true);
	}
	
	private void registerBtnAction() {
		System.out.println("Requesting parentController to" 
			+ " createRegisterNewUserWindow()");
		parentController.createUserUI();
	}
	
	// Below this point are the Private Inner Classes that this UI uses
	
	private class CancelBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			System.out.println("The Cancel Button has been pressed.");
			System.out.println("The System will now exit.");
			System.exit(0);
		}	
	}
	
	private class SubmitBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ae) {
			System.out.println("The Submit Button has been pressed.");			
			LoginUI.this.submitCredentials();		
		}	
	}
	
	private class RegisterBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			System.out.println("The Register Button was pressed.");
			LoginUI.this.registerBtnAction();
		}	
	}
	private class UsernameTxtFldListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			LoginUI.this.passwordFld.requestFocus();
		}
	}
	private class PasswordFldListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			System.out.println("The user pressed enter indicating to submit their"
					+ " input.");
			LoginUI.this.submitCredentials();
		}
	}
	/**
	 * This method takes a JTextField and Styles it.
	 * @param field 
	 */
	private void styleJTextField(JTextField field) {		
		field.setHorizontalAlignment(JTextField.CENTER);
		field.setForeground(defaultFontColor);
	}
}
