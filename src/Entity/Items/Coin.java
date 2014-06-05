package Entity.Items;

import Util.Settings;

public class Coin extends ItemMain {
	
	public Coin(float x, float y) {
		super(x, y);
		
		super.setItemBuffAmount(1);
		super.setSpriteImage(Settings.coinSprite);
	}
	
	public String getObjectName() {
		return "coin";
	}
}
