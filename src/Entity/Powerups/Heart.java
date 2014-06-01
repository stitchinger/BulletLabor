package Entity.Powerups;

import Util.Settings;

public class Heart extends PowerupMain {
	
	public Heart(float x, float y) {
		super(x, y);
		
		super.setPowerupAmount(50);
		super.setSpriteImage(Settings.heartSprite);
	}
}