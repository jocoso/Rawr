package rawr.components;

import java.util.Scanner;

import rawr.components.tools.TextUtilities;

/**
 * Chapter is a class created as a stand-alone for computer terminals as well as
 * a displayable component for the Console class. It will only display a Window
 * to display text and an Input text to get user orders
 * 
 * @author Joshua Collado Soler
 *
 */
public abstract class TextChapter extends Chapter {
	private Scanner scnr;
	
	public TextChapter(String name, String description) {
		super(name, description);
		
		scnr = new Scanner(System.in);
	}

	// TODO: Replace this with the commented out one once testing is over
	/**
	 * This method is called only when the book will be manually rendered in a
	 * system terminal. It should NEVER be used if intended to be rendered in the
	 * GUI console.
	 */
	public void run() {
		// TODO: Create an ErrorHandler for these case
		if (maps.isEmpty())
			return;
		if (protagonist == null)
			return;

		System.out.println(TextUtilities.inChapterTitleFormat(getName()));
		System.out.println("");
		System.out.println(intro);
		System.out.println("");

		while (!hasFinished()) {
			System.out.print("> ");
			String command = scnr.nextLine();
			String response = cm.resolve(command);
			System.out.println(response);
			checkEvents();
		}
	}

}
