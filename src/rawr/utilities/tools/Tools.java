package rawr.utilities.tools;

import java.util.ArrayList;

public class Tools {
	public static String sanitize(String a) {
		a = a.replaceAll("from", "");
		return a.toLowerCase().replaceAll("\\s", "");
	}
	
	public static String roomExposition(String roomName, String roomDescription, ArrayList<String> roomItems) {
		String message = "[" + roomName + "]\n" + "==>" + roomDescription + ": \n In the room there is: \n";
		for(String item : roomItems) {
			message += item + "\n";
		}
		
		return message;
	}
}
