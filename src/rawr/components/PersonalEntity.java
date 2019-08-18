package rawr.components;

public abstract class PersonalEntity extends Entity {
	private String type;

	public PersonalEntity(String name, String description) {
		super(name, description);
		type = "";
	}
	
	public void setType(String type) {
		this.type = formatType(type);
	}
	
	// returns a list of types
	public String[] getSplitTypes() {
		return type.split(",");
	}
	
	public String getRawTypes() {
		return type;
	}
	
	// Remove extra spaces
	// Returns a clean list of types
	private String formatType(String type) {
		return  type.replaceAll("( )+", ",");
	}
	
	public boolean hasType(String type) {
		String[] types = getSplitTypes();
		for(String t : types) {
			if(type.equals(t))
				return true;
		}
		
		return false;
	}
	
	public boolean hasTypes(String[] types) {
		for(String t : types) {
			if(!hasType(t))
				return false;
		}
		return true;
	}

}
