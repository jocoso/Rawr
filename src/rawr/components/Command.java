package rawr.components;

/**
 * A Command is an interface that represents a verb.
 * Take, Eat, Jump etc...
 * 
 * The command is executed when the player inputs its name in text field.
 * @author Joshua Collado Soler
 *
 */
public interface Command {
	public String act(String[] command);
}
