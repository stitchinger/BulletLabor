package items;

import org.newdawn.slick.Image;

import entity.GameObject;

public class Powerup extends GameObject{

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
