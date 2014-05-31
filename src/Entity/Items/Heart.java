package Entity.Items;

import Util.Settings;

public class Heart extends ItemMain {

	private int healthAmount;
	
	public Heart(float x, float y, String id) {
		super(x, y, id);
		
		this.setSpriteImage(Settings.heartSprite);
		
		if(this.HeartId(id) == true){
			this.healthAmount = 50;
		}
	}
	
	private boolean HeartId(String id){
		if(id.equals("healthItem")){
			return true;
		}
		else {
			return false;
		}
	}

	public int getHealthAmount() {
		return healthAmount;
	}
}