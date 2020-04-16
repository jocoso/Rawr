package com.rawr.engine;

/**
 * 
 * @author jocoso
 *
 */
public interface AbstractGame {
	public void set(GameContainer gc);
	public void update(GameContainer gc);
	public void render(GameContainer gc, Renderer r);
}
