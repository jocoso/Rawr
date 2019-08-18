package rawr.components.tools;

import rawr.components.Room;

public class TextUtilities {
	public static String oneWordSanitizer(String text) {
		text =  text.replaceAll("( )+", "");
		text = text.toLowerCase();
		return text;
	}
	
	public static String inChapterTitleFormat(String text) {
		return "================[" + text + "]================";
	}
	
	public static String inRoomTitleFormat(String text) {
		return "< " + text + " >";
	}
	
	public static String roomPresentation(String name, String description) {
		return inRoomTitleFormat(name) + "/n/n/n" + description;
	}
	
	public static String roomPresentation(Room room) {
		return inRoomTitleFormat(room.getName()) + " \n\n " + room.getDescription();
	}
	
	public static String defaultWrongInputMessage() {
		return "I don't think you are using this command properly";
	}
}
