package com.rawr.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Generates Renderable images 
 * @author jocoso
 *
 */

public class Image {
	private int w, h;
	private int[] p;
	
	public Image(String path) {
		BufferedImage image = null;
		
		try {
			// Images have to be located in res folder
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		w = image.getWidth();
		h = image.getHeight();
		p = image.getRGB(0, 0, w, h, null, 0, w);
		
		image.flush(); // Just to make sure
	}
	
	public int getWidth() {
		return w;
	}

	public void setWidth(int w) {
		this.w = w;
	}

	public int getHeight() {
		return h;
	}

	public void setHeight(int h) {
		this.h = h;
	}

	public int[] getPixels() {
		return p;
	}

	public void setPixels(int[] p) {
		this.p = p;
	}
}
