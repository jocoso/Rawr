package rawr.test.customclass;

import rawr.test.customInterfaces.Readable;
import rawr.utilities.Item;

public class Page extends Item implements Readable {
	private String content;
	
	public Page(String name, String showName, String description, String content) {
		super(name, showName, description);
		// TODO Auto-generated constructor stub
		this.content = content;
	}
	
	
	@Override
	public String read() {
		return content;
	}

}
