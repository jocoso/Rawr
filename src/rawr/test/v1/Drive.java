package rawr.test.v1;

import java.util.ArrayDeque;
import java.util.Queue;

import rawr.displayer.Console;
import rawr.test.customclass.Chapter;
import rawr.utilities.Engine;

public class Drive {
	public static Queue<Chapter> chapters;
	public static Engine en;
	public static Thread thread;
	public static boolean isPlaying;
	public static Console window;
	public static Chapter currentChapter;
	
	
	public static void main(String[] args) {
		// Setting up
		chapters = new ArrayDeque<Chapter>();
		en = new Engine();
		isPlaying = true;
		window = new Console("The DollMaker", 700, 500);
		
		chapters.add(new Chapter1(window));
		currentChapter = chapters.poll();
		en = currentChapter.getEngine();
		window.setEngine(en);
		
		window.display();
		
	}
	
}
