package rawr.internal;


/**
 * Prop class is the nuclei of everything game-related.
 * Every Prop, Item or Character should inherit from this class
 * 
 * @author Josh
 *
 */
public abstract class Prop {
	String name, showName, description;
	
	/**
	 * A name is a way for the game to recognize objects. They should be written
	 * in lowercase and without a space. A show name is the actual name displayed
	 * in the screen.
	 * 
	 * @param name
	 * @param showName
	 * @param description
	 */
	public Prop(String name, String showName, String description) {
		this.name = name;
		this.description = description;
		this.showName = showName;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
