package Entity.Items;

import Util.Settings;

public class Heart extends Item {
	
	public Heart(float x, float y) {
		super(x, y);
		
		super.setItemBuffAmount(50);
		super.setSpriteImage(Settings.heartSprite);
	}
	
	public String getObjectName() {
		return "heart";
	}
}