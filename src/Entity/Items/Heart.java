package Entity.Items;

import Util.Settings;

public class Heart extends ItemMain {
	
	public Heart(float x, float y) {
		super(x, y);
		
		super.setItemBuffAmount(50);
		super.setSpriteImage(Settings.heartSprite);
	}
}