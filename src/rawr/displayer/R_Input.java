package rawr.displayer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class R_Input extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public R_Input() {
		setFont(new Font("Courier New", Font.PLAIN, 15));
		setEditable(true);
		setForeground(Color.WHITE);
		setCaretColor(Color.GREEN);
		setOpaque(false);
	}
	
}