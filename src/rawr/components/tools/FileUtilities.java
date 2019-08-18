package rawr.components.tools;

import java.io.File;

public class FileUtilities {
	
	public File getFile(String addr) {
		return new File(addr);
	}
	
	public boolean fileExists(File file) {
		return file.exists();
	}
	
}
