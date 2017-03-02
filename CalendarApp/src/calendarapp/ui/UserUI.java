/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import calendarapp.Name;
import calendarapp.backend.UserController;
import calendarapp.backend.User;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author mpk5206
 */
public class UserUI extends JFrame {
    private UserController parentController;
    private User user = new User();
    private JLabel usernameLbl;
    private JLabel passwordLbl;
    private JTextField usernameTxtFld;
    private JPasswordField passwordFld;
    private JButton submitBtn;
    private JButton cancelBtn;
    
    public UserUI (UserController parentController, User user) {
        System.out.println("Creating UserUI.");
        this.parentController = parentController;
        this.user = user;
        createWindow();
        addComponents();
        System.out.println("Finished creating UserUI.");
    }

    public UserUI (UserController parentController) {
        System.out.println("Creating UserUI.");
        this.parentController = parentController;
        createWindow();
        addComponents();
        System.out.println("Finished creating UserUI.");
    }
     
    private void createWindow() {
        /*
            This method will create the window itself and not do anything beyond
            that
        */
        
        System.out.println("Creating the UserUI window.");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension windowSize = new Dimension(375, 667);
        this.setPreferredSize(windowSize);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.pack();
        this.setLocation((screenSize.width/2) - this.getWidth()/2, 
                screenSize.height/2 - this.getHeight()/2);
    }
    
        private void addComponents() {
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
        GridBagConstraints c = new GridBagConstraints();
        c.insets = labelInset;
        c.anchor = GridBagConstraints.SOUTH;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        c.weighty = .75;
        this.add(usernameLbl, c);
        
        // mpk5206 is one of the accept usernames
        usernameTxtFld = new JTextField("mpk5206", 25);
        //FIXME: The userNameTxtFld should begin empty
        usernameTxtFld.addActionListener(new UserUI.UsernameTxtFldListener());
        c = new GridBagConstraints();
        c.insets = fieldInset;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 3;
        this.add(usernameTxtFld, c);
        
        passwordLbl = new JLabel("PASSWORD");
        c = new GridBagConstraints();
        c.insets = labelInset;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 4;
        this.add(passwordLbl, c);
        
        //password is the password of the mpk5206 account
        passwordFld = new JPasswordField("password", 25);
        //FIXME: passwordFld should begin empty
        passwordFld.addActionListener(new UserUI.PasswordFldListener());
        c = new GridBagConstraints();
        c.insets = fieldInset;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 5;
        this.add(passwordFld, c);
        
         cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new UserUI.CancelBtnListener());
        c = new GridBagConstraints();
        c.insets = new Insets(150, 10, 0, 5);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridy = 6;
        c.weighty = .75;
        this.add(cancelBtn, c);
        
        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(new UserUI.SubmitBtnListener());
        c = new GridBagConstraints();
        c.insets = new Insets(150, 5, 0, 10);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridy = 6;
        c.weighty = .75;
        this.add(submitBtn, c);
        
    }
    
    public void registerUser() {
        String requestedUsername = usernameTxtFld.getText();
        char[] password = passwordFld.getPassword();
        boolean additionSuccessful = parentController.registerNewUser(new Name(), requestedUsername, password);
        if (additionSuccessful) {
            this.dispose();
        } else {
            System.out.println("Handle Error.");
        }
        
        
    }
    // Below this point are the Private Inner Classes that this UI uses
    
    private class CancelBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            UserUI.this.dispose();
        }
        
    }
    
    private class SubmitBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("The Register Button has been pressed.");
            UserUI.this.registerUser();
            
        }
        
    }
    
        private class UsernameTxtFldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
        }
        
    }
    private class PasswordFldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("The user pressed enter indicating to submit their"
                    + " input.");
        }

        
    }
    
    private class AdditionFailedWindow extends JFrame {
        public AdditionFailedWindow (String requestedUsername) {
            this.createWindow();
            this.addComponents(requestedUsername);
        }
        
        public void createWindow() {
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Dimension windowSize = new Dimension(375, 375);
            this.setPreferredSize(windowSize);
                    this.setPreferredSize(windowSize);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.pack();
            this.setLocation((screenSize.width/2) - this.getWidth()/2, 
                screenSize.height/2 - this.getHeight()/2);
            this.setVisible(true);
        }
        
        public void addComponents(String username) {
            String errorMessage = "The username \"" + username + "\" was already taken.";
            this.setLayout(new FlowLayout());
            this.add(new JLabel(errorMessage));
            JButton okBtn = new JButton("OK");
            okBtn.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                   AdditionFailedWindow.this.okBtnAction();
                }
                
            });
            this.add(okBtn);
        }
        
        public void okBtnAction() {
            this.dispose();
        }

  
    }
}
