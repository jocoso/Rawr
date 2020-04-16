package com.rawr.engine.unit;

import com.rawr.engine.GameContainer;
import com.rawr.engine.Renderer;

public abstract class Unit {
	public abstract void set(GameContainer gc);
	public abstract void update(GameContainer gc);
	public abstract void render(Renderer r);
}
