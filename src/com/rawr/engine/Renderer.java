package com.rawr.engine;

import java.awt.image.DataBufferInt;

public class Renderer {
	private int pw, ph;
	private int[] p;
	
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
	
}
