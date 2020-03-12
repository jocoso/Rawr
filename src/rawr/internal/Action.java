package rawr.internal;

/**
 * 
 * Actions are the way the Engine knows what to do when given a command
 * 
 * 
 * @author Joshua Collado Soler
 *
 */
public interface Action {
	/**
	 * Given a command this action will be executed
	 * The command parameter has information that can be used
	 * for game-play purposes. It returns a String that will be
	 * then displayed in the Console as response to the Action.
	 * @param command
	 * @return
	 */
	public String act(String[] command);
}
