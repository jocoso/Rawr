package com.rawr.gameTest;

import com.rawr.engine.AbstractGame;
import com.rawr.engine.Console;
import com.rawr.engine.GameContainer;
import com.rawr.engine.Renderer;
import com.rawr.engine.audio.SoundClip;

public class GameManager implements AbstractGame {
	private SoundClip clip;
	
	public GameManager() {
		clip = new SoundClip("/audio/test.wav");
		clip.setVolumen(-20);
	}
	
	public void set(Console console) {
//		console.addBar("Health", 10, Console.STANDARD_COLOR);
//		console.addBar("Mana", 10, 0xff0276fd);
//		console.addBar("Sanity", 10, 0xff71C671);
//		console.addBar("Hunger", 10, 0xffFFCC11);
	}

	@Override
	public void update(GameContainer gc) {
		gc.getConsole().update(gc);
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		gc.getConsole().render(r);
	}
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
