package rawr.displayer;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import rawr.utilities.Engine;


/**
 * The Console class takes a Book class as a required parameter
 * and uses it to display the story in a GUI window.
 * 
 * @author Joshua Collado Soler
 *
 */
public class Console extends JFrame{

	/**
	 * 
	 * 
	 */
	
	// System
	private static final long serialVersionUID = 1L;
	
	// Required classes for console to work
	R_Window consoleWindow;
	R_Input consoleInput;
	Point size; // Keeping this information in case it becomes useful (Doubt it)
	Engine en;

	/***
	 * A Window the player can interact with
	 * 
	 * @param consoleTitle
	 * @param width
	 * @param height
	 */
	public Console(String consoleTitle, int width, int height) {
		super(consoleTitle);
		
		// Waste of memory
		size = new Point(width, height);
		
		// Core part of Console
		consoleWindow = new R_Window();
		consoleInput = new R_Input();
		
		// Other important things
		setLookAndFeel();
		addComponents();
		setInputActions();
		setSystemManagement();
		// Set size of Console
		this.setSize(new Dimension(width, height));
	}
	
	public void setEngine(Engine en) {
		this.en = en;
	}
	

	/**
	 * Not needed, but it makes me feel okay
	 */
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
				// Get information from the user
				String text = consoleInput.getText();
				
				if (text.length() > 0) {
					println(text, Color.CYAN);
					// Process the information
					en.inputManager(text, "No se");
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