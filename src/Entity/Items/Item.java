package Entity.Items;

import Entity.StaticObject;

public class Item extends StaticObject {

	private int itemBuffAmount;

	// itemBuffAmount soll sich um den internen Wert des jeweiligen Item kümmern
	// (HealthAmount etc.)

	public Item(float x, float y) {
		super(x, y);
	}

	public int getItemBuffAmount() {
		return this.itemBuffAmount;
	}

	public void setItemBuffAmount(int buffAmount) {
		this.itemBuffAmount = buffAmount;
	}
	
	/* TO-DO Liste für Item System: 
     * Nächster Schritt --> weitere Items hinzufügen
     */
}
