package rawr.displayer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class R_Window extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextPane console;
	private StyledDocument document;
	
	
	public R_Window() {
		console = new JTextPane();
		console.setFont(new Font("Courier New", Font.PLAIN, 15));
		console.setEditable(false);
		console.setOpaque(false);
		
		setViewportView(console);
		getViewport().setOpaque(false);
		setOpaque(false);

		document = console.getStyledDocument();
	}
	
	
	/**
	 * Change the font size of the console window.
	 */
	public void changeFontSize(int fontsize) {
		console.setFont(new Font("Courier New", Font.PLAIN, fontsize));
	}
	
	/**
	 * Scroll to the top message in the console.
	 */
	public void scrollTop() {
		console.setCaretPosition(0);
	}
	
	/**
	 * Scroll to the bottom message in the console.
	 */
	public void scrollBottom() {
		console.setCaretPosition(
				console.getDocument().getLength());
		
	}
	
	/**
	 * Print a message to the console with a new line.
	 * @param s  A String to display
	 * @param c  The Color of the string
	 */
	public void println(String s, Color c) {
		print(s + "\n", c);
	}
	
	/**
	 * Print a message on the console with 
	 * a new line in default black.
	 * @param s A String to display
	 */
	public void println(String s) {
		print(s + "\n");
	}
	
	
	/**
	 * Print a message on the console without
	 * a new line in default black
	 * @param s A string to display
	 */
	public void print(String s) {
		print(s, new Color(255, 255, 255));
	}
	
	
	/**
	 * Print a message on the console without
	 * a new line in a given color
	 * @param s A String to display
	 * @param c The color of the String
	 */
	public void print(String s, Color c) {
		Style style = this.console.addStyle("Style", null);
		StyleConstants.setForeground(style, c);
		try {
			document.insertString(document.getLength(), s, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Clear the screen
	 */
	
	public void clear() {
		try {
			document.remove(0, document.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	
}
