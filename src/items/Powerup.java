package items;

import objectBlueprints.StaticObject;

import org.newdawn.slick.Image;

import util.Settings;

public class Powerup extends StaticObject{

	private String id;
	private int healthAmount;
	
	public Powerup(float x, float y, String id) {
		super(x, y);
		this.setSprite(Settings.heartSprite);
	
		this.id = id;
		if(id.equals("healthItem")){
			this.healthAmount = 50;
		}else if(id.equals("weapon")){
			
		}
		
	}
	
	public void setSprite(Image img){
    	super.sprite = img;
    	this.init();
    }
	
	public int getHealthAmount(){
		
		return this.healthAmount;
	}
	



}
