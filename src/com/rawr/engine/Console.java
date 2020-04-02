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
	private ArrayList<Integer> colors;
	public final int STANDARD_COLOR = 0xffff0000;
	private final int MARGIN = 5;
	
	public Console() {
		console = new ArrayList<String>();
		colors = new ArrayList<Integer>();
	}
	
	public void add(String text) {
		add(text, STANDARD_COLOR);
	}
	
	public void add(String text, int color) {
		console.add(text);
		colors.add(color);
	}
	
	public void update(Renderer r) {
		if(console.isEmpty()) return; // Saves a bit of memory
		
		int yOffset = 0;
		
		for(int i = 0; i < console.size(); i++) {
			
			r.drawText(console.get(i), 0, yOffset, colors.get(i));
			yOffset += Font.STANDARD.getFontImage().getHeight() + MARGIN;
			
		}
	}
	
}
