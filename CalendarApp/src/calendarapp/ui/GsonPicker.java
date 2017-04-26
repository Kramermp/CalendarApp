/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;


public class GsonPicker extends Picker implements ActionListener {
   JButton go;
   JList gsonJList;
   JFileChooser chooser;
   String choosertitle;


    public GsonPicker () {
        go = new JButton("Import Gson");
        go.addActionListener(this);
        add(go);     
    }




    public void actionPerformed(ActionEvent e) {            
        chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);  
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
        } else {
            System.out.println("No Selection ");
        }
  }

    public Dimension getPreferredSize(){
        return new Dimension(200, 200);
    }

    public static void main(String s[]) {
        JFrame frame = new JFrame("");
        GsonPicker panel = new GsonPicker();
        frame.addWindowListener(
        new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            System.exit(0);
            }
            }
        );
        frame.getContentPane().add(panel,"Center");
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }

    @Override
    public int[] getSelected() {
    return gsonJList.getSelectedIndices();
    } 
}