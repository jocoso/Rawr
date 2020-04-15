package com.rawr.engine;

/**
 * 
 * @author jocoso
 *
 */
public interface AbstractGame {
	public void set(Console console);
	public void update(GameContainer gc);
	public void render(GameContainer gc, Renderer r);
}
