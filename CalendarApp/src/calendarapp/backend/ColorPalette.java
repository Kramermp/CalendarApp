/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarapp.backend;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Michael Kramer <mpk5206@psu.edu>
 */
class ColorPalette implements Serializable {
	private Color backgroundColor = Color.WHITE;
	private Color defaultFontColor = Color.BLACK;
	private Color alertFontColor = Color.RED;
	
	public ColorPalette() {
		//Do Nothing
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	public Color getDefaultFontColor() {
		return defaultFontColor;
	}
	
	public Color getAlertFontColor() {
		return alertFontColor;
	}
}
