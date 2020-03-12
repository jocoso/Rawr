package rawr.test.customclass;

import rawr.test.customInterfaces.Survivalist;

public class Protagonist extends DamageableCharacter implements Survivalist {
	
	private boolean standing;
	
	public Protagonist() {
		super("manolo", "Manolo", "A little magician");
	}

	@Override
	public void eat(Food food) {
		food.affect(this);
	}

	@Override
	public void drink(Drink drink) {
		drink.affect(this);
		
	}
	
	public boolean isStanding() {
		return standing;
	}
	
	public void setStanding(boolean standing) {
		this.standing = standing;
	}

}
