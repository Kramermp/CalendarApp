/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.ui;

import javax.swing.JPanel;

/**
 *
 * @author Faust
 */
public abstract class Picker extends JPanel {
	public abstract int[] getSelected();
}
