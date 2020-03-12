package rawr.test.customclass;

import rawr.utilities.GameCharacter;

public class DamageableCharacter extends GameCharacter {
	private int health;
	private int mana; 
	
	public DamageableCharacter(String name, String showName, String description) {
		super(name, showName, description);
	}

	public int getHealth() {
		return health;
	}

	public int getMana() {
		return mana;
	}
	
	public void modifyHealth(int health) {
		this.health += health;
	}
	
	public void modifyMana(int mana) {
		this.mana += mana;
	}
	
	public void receiveDamage(int damage) {
		modifyHealth(-damage);
	}
}
