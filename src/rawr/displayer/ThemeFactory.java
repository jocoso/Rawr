package rawr.displayer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ThemeFactory {
	
	@SuppressWarnings("unchecked")
	public static Theme createNewTheme( 
			String addr,
			String themeName,
			int width,
			int height,
			boolean art) {
		
		
		JSONArray main = getMain(addr);
		JSONObject mainObject = (JSONObject) main.get(0);
		if(mainObject.containsKey(themeName)) return null;
		
		JSONObject newTheme = new JSONObject();
		JSONObject themeDef = new JSONObject();
		
		themeDef.put("width",  width);
		themeDef.put("height",  height);
		themeDef.put("has_art", art);
		newTheme.put("theme_description", themeDef);
		mainObject.put(themeName, newTheme);
		main.set(0, mainObject);
		
		
		try(FileWriter writer = new FileWriter(addr)) {
			writer.write(main.toJSONString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Theme() {

			@Override
			public HashMap<String, Object> reshape() {
				return modifyConsole(themeDef);
				
			}
			
		};
	}
	
	public static Theme getTheme(String themeName, String addr) throws Exception {
		JSONArray main = getMain(addr);
		JSONObject mainObject = (JSONObject) main.get(0);

		if(mainObject.containsKey(themeName)) {
			JSONObject themeTemplate = (JSONObject) mainObject.get(themeName);
			JSONObject themeDescription = (JSONObject) themeTemplate.get("theme_description");
			return new Theme() {

				@Override
				public HashMap<String, Object> reshape() {
					return modifyConsole(themeDescription);
				}
			};
		} else {
			throw new Exception();
		}
	}
	
	private static JSONArray getMain(String addr) {
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(addr));
			return (JSONArray) obj;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static HashMap<String, Object> modifyConsole(JSONObject themeDescription) {
		HashMap<String, Object> shape = new HashMap<String, Object>();
		shape.put("width", themeDescription.get("width"));
		shape.put("height", themeDescription.get("height"));
		shape.put("has_art", themeDescription.get("has_art"));
		return shape;
	}
}
