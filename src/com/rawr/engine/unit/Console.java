package com.rawr.engine.unit;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.rawr.engine.GameContainer;
import com.rawr.engine.GeneralInput;
import com.rawr.engine.Renderer;

/**
 * Organizes the text displayed in the console in a way
 * that resembles a text adventure game. 
 * 
 * @author jocoso
 *
 */
public class Console extends Unit {
	// CONSTANTS
	public static final int STANDARD_COLOR = 0xffff0000;
	private final int MARGIN = 5;
	
	// Save the lines to be written in the console and in which color
	// Each line should have a color associated with it.
	private ArrayList<String> notebook;
	private ArrayList<Integer> lineColors;
	
	// Console-controlling variables. !Important
	private int screenWidth, screenHeight; 
	private Integer biggestLetter = null; // The letter in the image Font with the biggest width
	private Point range;                  // Used to keep track of the lines displayed in the screen
	private int maxNumberOfMsg;			  // How many lines can be displayed based on the console size
	private GeneralInput input;
	
	
	public Console() {
		System.out.println("works");
	}
	
	@Override
	public void set(GameContainer gc) {
		notebook = new ArrayList<String>();
		lineColors = new ArrayList<Integer>();
		input = gc.getInput();
		
		// Finding out which letter has biggest width
		// By calculating the average of all the sizes
		biggestLetter = 0;
		
		int[] widths = gc.getRenderer().getFont().getWidths();
			
		for(int i = 0; i < widths.length; i++) {
			biggestLetter += widths[i];
		}
		
		biggestLetter /= widths.length;
		// --------------------------------------------
		
		screenWidth = gc.getWidth();
		screenHeight = gc.getHeight();
		
		// Magic number 21 is the yOffset of every line + 1 for the line
		// TODO: Try different console sizes to see if magic number hold up
		maxNumberOfMsg = (screenHeight / gc.getRenderer().getFont().getFontImage().getHeight()) - 20;
		range = new Point(0, 0);
		
	}
	
	/**
	 * All things related to text manipulation prior to displaying are managed in 
	 * this function.
	 * 
	 * @param input
	 * @param charSize
	 * @param screenWidth
	 * @param color
	 */
	private void sanitize(String input, int charSize, int screenWidth, int color) {
		// Input do not surpass the screenWidth
		if(input.length() * charSize < screenWidth) {
			inputText(input, color);
			return;
		}
		
		// Separate lines that are too long to display in the current
		// screen width
		
		int charactersLeft = input.length();
		int charactersPerLine = screenWidth / charSize;
		int beg = 0;
		int end = charactersPerLine;
		
		while(true) {	
			inputText(input.substring(beg, end), color);
			charactersLeft -=  charactersPerLine;
			
			if(charactersLeft < charactersPerLine) {
				inputText(input.substring(end, input.length()), color);
				break;
			}
			
			beg = end;
			end += charactersPerLine;
			
		} 
		
		// -------------------------------------------------------------
		
	}
	
	/**
	 * Use this function when interacting with text
	 * to avoid input-relating errors
	 * 
	 * @param text
	 * @param color
	 */
	private void inputText(String text, int color) {
		notebook.add(text);
		lineColors.add(color);
		scrollDown();
	}
	
	/**
	 *  Modify the range of messages rendered in the console
	 *  to only display the last x message in the arrayList
	 *  where x can be found in maxNumOfMsg
	 */
	private void scrollDown() {
		if(range.y > maxNumberOfMsg) {
			range.x++;
			range.y++;
		} else {
			range.y++;
		}
	}
	
	/**
	 *  Modify the range of messages rendered in the console
	 *  to only display the first x message in the arrayList
	 *  where x can be found in maxNumOfMsg
	 */
	private void scrollUp() {
		if(range.y > 0) {
			range.x--;
			range.y--;
		} else {
			range.y--;
		}
	}
	
	/**
	 * Main way of interacting with the console
	 * to display written text
	 * 
	 * @param text
	 * @param color
	 */
	public void write(String text, int color) {
		if(!text.isEmpty()) {
			sanitize(text, biggestLetter, screenWidth-10, color); 
		}
	}
	
		
	
	/**
	 * Renders the console
	 * 
	 * @param r
	 */
	public void render(Renderer r) {
		int genHeight = r.getFont().getFontImage().getHeight() + MARGIN;
		r.drawText(input.getRawDump(), 0, screenHeight - genHeight, 0xff00e6e6);
		
		if(notebook.isEmpty()) return; // Saves a bit of memory
		
		
		int yOffset = 0;
		
		for(int i = range.x; i < range.y; i++) {
			r.drawText(notebook.get(i), 0, yOffset, lineColors.get(i));
			yOffset +=  genHeight;
		}
		
		
	}
	
	public void update(GameContainer gc) {
		if(input.isKeyUp(KeyEvent.VK_ENTER)) {
			write(input.getDump(), Console.STANDARD_COLOR);
		}
	}
	
	public void setUnitWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	public void setUnitHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	public int getUnitWidth() {
		return screenWidth;
	}
	
	public int getUnitHeight() {
		return screenHeight;
	}

	
}
