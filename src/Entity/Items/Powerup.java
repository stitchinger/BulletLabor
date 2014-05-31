package Entity.Items;

import Util.Settings;
import Entity.StaticObject;

public class Powerup extends StaticObject{

	@SuppressWarnings("unused")
	private String id;
	private int healthAmount;
	
	public Powerup(float x, float y, String id) {
		super(x, y);
		this.setSpriteImage(Settings.heartSprite);
	
		this.id = id;
		if(id.equals("healthItem")){
			this.healthAmount = 50;
		}else if(id.equals("weapon")){
			
		}
		
	}
	
	public int getHealthAmount(){
		
		return this.healthAmount;
	}
	



}
