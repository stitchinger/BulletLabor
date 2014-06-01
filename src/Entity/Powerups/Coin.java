package Entity.Powerups;

import Util.Settings;

public class Coin extends PowerupMain {
	
	public Coin(float x, float y) {
		super(x, y);
		
		super.setPowerupAmount(1);
		super.setSpriteImage(Settings.coinSprite);
	}
}
