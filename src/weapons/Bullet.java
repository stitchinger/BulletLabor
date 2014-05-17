package weapons;

import main.Game;
import objectBlueprints.PhysicsObject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import util.Settings;




public class Bullet extends PhysicsObject{
    
	private boolean isAlive = true;
    private int bulletDamage = 15;
    private float bouncyness = 0.0f;
	private int lifeTimeMillis = 4000;
	
    private long timestampOfBirth;
    private float rotation;
    private Image img;
	
  
    
    public Bullet(float x, float y){
    	super(x, y);
    	//super((int)(x-sprite.getWidth()/2), (int)(y-sprite.getHeight()/2)); --> @ich hab dasa leider nicht hinbekommen zu fixen.
    	this.img = Settings.bulletSprite;
    	this.setSprite(img);
    	this.timestampOfBirth = System.currentTimeMillis();
    }
    
    public void update(int delta){
    	if(System.currentTimeMillis() > this.timestampOfBirth + this.lifeTimeMillis){
    		this.die();
    	}
    	if(this.isAlive){
    		this.fall(delta);
        	if(this.bouncyness > 0){
        		this.bounce();
        	}
    		
        }
    	this.adjustRotationToFlightpath();
    	this.actualMovement(delta);
    	
    }

    public void render(Graphics g){
    	this.getImage().setRotation(rotation); 
    	super.render(g);
    }
    
    public void adjustRotationToFlightpath(){
    	this.rotation = (float) new Vector2f(this.getVelocityX(), this.getVelocityY()).getTheta() + 90;
    }
    
    public void bounce(){
    	if(this.isLeftSideCollided()){
    	
    		this.setX(((int)(this.getX()/32)+1)*32);
    	
    		this.setVelocityX(this.getVelocityX() * -this.bouncyness);
    	
    	}else if(this.isRightSideCollided()){
    	
    		this.setX(((int)((this.getX()+this.width)/32))*32 - this.width);
    	
    		this.setVelocityX(this.getVelocityX() * -this.bouncyness);
   		}
    	
    	
    	if(this.isBottomSideCollided()){
    
    		this.setY(((int)((this.getY()+this.height)/32))*32 - this.height);
    		this.setVelocityY( this.getVelocityY() * -this.bouncyness);
    		if(this.getVelocityY() > -1 && this.getVelocityY() < 1){
        		this.setVelocityY(0);
        	}
    		
    		
    			
   		} else if(this.isTopSideCollided()){
   		
   			this.setY(((int)(this.getY()/32)+1)*32);
   		
   			this.setVelocityY(this.getVelocityY() * -this.bouncyness);
   			if(this.getVelocityY() > -1 && this.getVelocityY() < 1){
   	    		this.setVelocityY(0);
   	    	} 
   			
   		
   			
    		
   		}
    	
  
    	
    		
    		
    	}
    	
    public void die(){
    	
    	this.isAlive = false;
    	Game.removeObject(this);
    }
    
    public int getDamage(){
    	return this.bulletDamage;
    }
}
