package Entity.Items;

import Entity.StaticObject;

public class ItemMain extends StaticObject {

	private int itemBuffAmount;

	// itemBuffAmount soll sich um den internen Wert des jeweiligen Item kümmern
	// (HealthAmount etc.)

	public ItemMain(float x, float y) {
		super(x, y);
	}

	public int getItemBuffAmount() {
		return this.itemBuffAmount;
	}

	public void setItemBuffAmount(int buffAmount) {
		this.itemBuffAmount = buffAmount;
	}
}
