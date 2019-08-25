package rawr.displayer;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import rawr.components.Book;
import rawr.components.Chapter;
import rawr.components.Event;
import rawr.components.GameMap;
import rawr.components.utilities.CommandManager;


/**
 * The Console class takes a Book class as a required parameter
 * and uses it to display the story in a GUI window.
 * 
 * @author Joshua Collado Soler
 *
 */
public abstract class Console extends JFrame{

	/**
	 * 
	 * 
	 */
	
	// System
	private static final long serialVersionUID = 1L;
	
	// Name of computer-programmed variables
	boolean art = false;
	
	// Local Classes
	Chapter currentChapter;
	R_Window consoleWindow;
	R_Input consoleInput;
	CommandManager cm;
	Book book;
	
	// Lists
	List<GameMap> maps;
	List<Chapter> chapters;
	List<Event> events;
	
	public Console(String consoleTitle, Theme consoleTheme, Book book) {
		super(consoleTitle);
		
		if(!book.isReady()) {
			System.err.println("Error: No chapters in book");
		}
		
		this.book = book;
		consoleWindow = new R_Window();
		consoleInput = new R_Input();
		
		prepare();
		
		setLookAndFeel();
		getNextChapter();
		addComponents();
		setInputActions();
		setSystemManagement();
	}
	
	public Console(String consoleTitle, Book book) {
		this(consoleTitle, null, book);
	}
	
	/**
	 * Any special non-external custom initialization in the Console class
	 * goes here.
	 */
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
	 * Get next chapter in the book and prepare all the other
	 * variables to resemble the change.
	 * getNextChapter() returns true if the book has new chapters
	 * and false if it is empty.
	 * @return {@code}boolean
	 */
	private boolean getNextChapter() {
		if(!book.hasChapters()) return false;
		
		currentChapter = book.getNextInLine();
		cm = currentChapter.getCommandManager();
		maps = currentChapter.getMaps();
		events = currentChapter.getEvents();
		return true;
	}

	/**
	 * Clear the screen
	 */
	public void clear() {
		consoleWindow.clear();
	}
	
	/**
	 * Play all the events of the chapter one by one
	 * using an Iterator to avoid collisions.
	 * In the case an event is fulfilled the function 
	 * will erase it from the list.
	 */
	private void checkEvents() {
		Iterator<Event> iter = events.iterator();

		while (iter.hasNext()) {
			Event event = iter.next();

			if (event.check())
				iter.remove();
		}
	}

	
	// MAIN LOOP
	private void setInputActions() {
		consoleInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println(currentChapter.getName());
				
				// Get information from the user
				String text = consoleInput.getText();
				if (text.length() > 1) {
					println(text, Color.CYAN);
					// Process the information
					String response = cm.resolve(text);
					
					//return the response
					println(response);
					scrollBottom();
					checkEvents();
					consoleInput.selectAll();
				}
				
				if(currentChapter.hasFinished()) {
					if(!getNextChapter())
						println("The End");
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
