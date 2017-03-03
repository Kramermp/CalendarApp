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
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
 */
public class NavigationUI extends JFrame {
	private NavigationController parentController;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private JTextField usernameTxtFld;
	private JPasswordField passwordFld;
	private JButton submitBtn;
	private JButton cancelBtn;
	private JButton registerBtn;
	
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
		
	}
}
