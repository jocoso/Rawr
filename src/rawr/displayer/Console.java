package rawr.displayer;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public abstract class Console extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean art = false;
	R_Window consoleWindow;
	R_Input consoleInput;
	
	public Console(String consoleTitle, Theme consoleTheme) {
		super(consoleTitle);
		setLookAndFeel();
		
		consoleWindow = new R_Window();
		consoleInput = new R_Input();
		
		addComponents();
		setInputActions();
		prepare();
		
		setSystemManagement();
	}
	
	public Console(String consoleTitle) {
		this(consoleTitle, null);
	}
	
	public abstract void prepare(); 
	
	public void display() {
		this.setVisible(true);
	}
	
	/**
	 * Scroll to the top message in the console.
	 */
	public void scrollTop() {
		consoleWindow.scrollTop();
	}
	
	/**
	 * Scroll to the bottom message in the console.
	 */
	public void scrollBottom() {
		consoleWindow.scrollBottom();
	}
	
	/**
	 * Print a message to the console with a new line.
	 * @param s  A String to display
	 * @param c  The Color of the string
	 */
	public void println(String s, Color c) {
		consoleWindow.println(s, c);
	}
	
	/**
	 * Print a message on the console with 
	 * a new line in default black.
	 * @param s A String to display
	 */
	public void println(String s) {
		consoleWindow.println(s);
	}
	
	
	/**
	 * Print a message on the console without
	 * a new line in default black
	 * @param s A string to display
	 */
	public void print(String s) {
		consoleWindow.print(s);
	}
	
	
	/**
	 * Print a message on the console without
	 * a new line in a given color
	 * @param s A String to display
	 * @param c The color of the String
	 */
	public void print(String s, Color c) {
		consoleWindow.print(s, c);
	}
	
	/**
	 * Clear the screen
	 */

	public void clear() {
		consoleWindow.clear();
	}
	
	// MAIN LOOP
	private void setInputActions() {
		consoleInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = consoleInput.getText();
				if (text.length() > 1) {
					println(text, Color.CYAN);
					scrollBottom();
					consoleInput.selectAll();
				}
			}

		});
	}
	
	private void setSystemManagement() {
		setVisible(false);
		getContentPane().setBackground(new Color(50, 50, 50));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void addComponents() {
		add(consoleWindow, BorderLayout.CENTER);
		add(consoleInput, BorderLayout.SOUTH);
	}
	
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
