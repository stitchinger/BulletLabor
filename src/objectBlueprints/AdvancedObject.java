package objectBlueprints;

import static main.Game.bulletSprite;
import static main.Game.bullet_list;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import weapons.Bullet;
import weapons.Weapon;

public class AdvancedObject extends PhysicsObject {
	
	protected Weapon weapon;
	private long timeOfLastShot; 
	
	
	
	public AdvancedObject(Image img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		
		
		
	}

	public void update(){
		super.update();
	
		
	}
	
	public void render(Graphics g){
		super.render(g);
		if(this.weapon != null){
			this.weapon.render(g);
		}
	}
	
	

	public float getTargetAngle(float mouseX, float mouseY){
    	
        float vecX = mouseX - (this.posX+this.width/2);
        float vecY= mouseY - (this.posY+this.height/2);
        float[] normalizedVector = getNormalizedVector2(vecX, vecY);
        
        
        
        return (float) Math.toDegrees(Math.atan2(normalizedVector[0], -normalizedVector[1]));
    }
	
	protected float[] getNormalizedVector2(float vecX, float vecY){
	    	
	    	float hypo = (float) Math.sqrt((vecX * vecX) + (vecY * vecY));
	        float[] vector2 = new float[2];
	    	vector2[0] = (float)(vecX / hypo);
	        vector2[1] = (float)(vecY / hypo);
	        return vector2;
	    }
	 
	public void angleShot(float angle){
	    	timeOfLastShot = System.currentTimeMillis();
	    	bullet_list.add(new Bullet(bulletSprite, (this.posX+this.width/2), (this.posY+this.height/2), 25, 30, angle, false));
	    
	    	
	    }

	public void clusterShot(float angle){
	    	timeOfLastShot = System.currentTimeMillis();
	    	int roundCount = 10;
	    	for(int i = -(roundCount); i < roundCount; i += 2){
	    		
	    		this.angleShot(angle + i);
	    		
	    	}
	    }
}
