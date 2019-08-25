package rawr.components.utilities;

import java.util.HashMap;
import java.util.Map;

import rawr.components.Command;
import rawr.components.tools.TextUtilities;

public class CommandManager {
	public Map<String, Command> commands;
	
	public CommandManager() {
		this.commands = new HashMap<String, Command>();
	}
	
	public boolean isEmpty() {
		return commands.isEmpty();
	}
	
	public void addCommand(String command_name, Command command) {
		// TODO: Create an error handle for this case
		if(command_name == null || command == null) return;
		commands.put(command_name, command);
	}
	
	public void addCommands(String[] command_names, Command[] commands) {
		// TODO: Add an error message in the case this happens
		if(command_names.length != commands.length) return;
		
		for(int i = 0; i < commands.length; i++) {
			addCommand(command_names[i], commands[i]);
		}
	}
	
	public String resolve(String command) {
		
		String[] separated_command = command.split(" ");
		String verb = separated_command[0];
		verb = TextUtilities.oneWordSanitizer(verb);
		
		if(commands.containsKey(verb)) {
			String response = commands.get(verb).act(separated_command);
			return response;
		} else {
			return "I don't know what you meant with that";
		}
	}
}
