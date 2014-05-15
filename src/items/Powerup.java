package items;

import objectBlueprints.StaticObject;

import org.newdawn.slick.Image;

public class Powerup extends StaticObject{

	private String id;
	private int healthAmount;
	
	public Powerup(Image sprite, float x, float y, int width, int height, String id) {
		super(sprite, x, y, width, height);
	
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
