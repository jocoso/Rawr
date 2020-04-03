package com.rawr.engine;

import java.util.ArrayList;

import com.rawr.engine.gfx.Font;

/**
 * Organizes the text displayed in the console
 * 
 * @author jocoso
 *
 */
public class Console {
	private ArrayList<String> console;
	private ArrayList<Integer> lineColors;
	public final int STANDARD_COLOR = 0xffff0000;
	private final int MARGIN = 5;
	private int health = 10;
	private int w, h;
	
	public Console(GameContainer gc) {
		console = new ArrayList<String>();
		lineColors = new ArrayList<Integer>();
		w = gc.getWindow().getCanvas().getWidth();
		h = gc.getWindow().getCanvas().getHeight();
	}
	
	public void add(String text) {
		add(text, STANDARD_COLOR);
	}
	
	public void add(String text, int color) {
		console.add(text);
		lineColors.add(color);
	}
	
	public void renderBar(String title, int barQty, Renderer r, int x, int y, int color) {
		String bars = "";
		
		for(int i = 0; i < barQty; i++) {
			bars += "$";
		}
		
		r.drawText(title + bars, x, y, color);
	}
	
	public void update(Renderer r) {
		// Bars
		// TODO: generate a way to create bars dynamically
		// TODO: Create a designated area for bars
		renderBar("Health: " , 10, r, 0, 0, STANDARD_COLOR);
		renderBar("Mana: ", 10, r, w-1050, 0, 0xff0276fd);
		
		if(console.isEmpty()) return; // Saves a bit of memory
		
		int yOffset = 20;
		
		for(int i = 0; i < console.size(); i++) {
			
			r.drawText(console.get(i), 0, yOffset, lineColors.get(i));
			yOffset += Font.STANDARD.getFontImage().getHeight() + MARGIN; // XXX: Change into actual used font
			
		}
	}
	
}
