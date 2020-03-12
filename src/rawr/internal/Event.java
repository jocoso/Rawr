package rawr.internal;

import rawr.displayer.Console;

/**
 * An Event is a situation that happens once a condition is met; Events can only happen once.
 * @author Joshua Collado Soler
 *
 */
public interface Event {
	/**
	 * An Ocurrence happens after a condition is check.
	 * User can use the con parameter to display a message
	 * directly into the Console
	 * @param con
	 */
	public void occurence(Console con);
	
	/**
	 * A condition to be met. 
	 * @return
	 */
	public boolean check(); 
}
