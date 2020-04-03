package com.rawr.engine;

import java.awt.image.DataBufferInt;

import com.rawr.engine.gfx.Font;

public class Renderer {
	private int pw, ph;
	private int[] p;
	private Font font = Font.STANDARD;
	
	public Renderer(GameContainer gc) {
		pw = gc.getWidth();
		ph = gc.getHeight();
		
		p = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}
	
	public void clear() {
		for(int i = 0; i < p.length; i++) {
			p[i] = 0;
		}
	}
	
	/**
	 * Clips image out of window bound
	 * @param x
	 * @param y
	 * @param value
	 */
	public void setPixel(int x, int y, int value) {
		// TODO: 0xffff00ff be a default color user can change
		if(x < 0 || x >= pw || y < 0 || y > ph || value == 0xffff00ff) {
			return;
		}
		
		p[x + y * pw] = value;
		
	}
	
	// TODO: Add a class that allows RGB conversion to hexa
	// TODO: Add a complete Image Font
	// TODO: Implement a static version of this method that will take a font as a parameter
	public void drawText(String text, int offX, int offY, int color) {
		text = text.toUpperCase(); // Example given contains only uppercase letter
		int offset = 0;
		
		for(int i = 0; i < text.length(); i++) {
			int unicode = text.codePointAt(i) - 32; // Avoid some characters not found on Font Image
			
			for(int y = 0; y < font.getFontImage().getHeight(); y++) {
				for(int x = 0; x < font.getWidths()[unicode]; x++) {
					if(font.getFontImage().getPixels()[(x + font.getOffsets()[unicode]) + y * font.getFontImage().getWidth()] == 0xffffffff) {
						setPixel(x + offX + offset, y + offY, color);
					}
				}
			}
			
			offset += font.getWidths()[unicode];
		}
	}

	public Font getFont() {
		return font;
	}
	
	
}
