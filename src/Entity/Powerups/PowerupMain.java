package Entity.Powerups;

import Entity.StaticObject;

public class PowerupMain extends StaticObject {

	private int powerupAmount;

	// powerupAmount soll sich um den internen Wert des jeweiligen Item kümmern
	// (HealthAmount etc.)

	public PowerupMain(float x, float y) {
		super(x, y);
	}

	public int getPowerupAmount() {
		return powerupAmount;
	}

	public void setPowerupAmount(int powerupAmount) {
		this.powerupAmount = powerupAmount;
	}
}
