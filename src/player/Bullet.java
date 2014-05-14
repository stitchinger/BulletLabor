package player;

import main.Game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import entity.MovingObject;


public class Bullet extends MovingObject{
    
	private boolean isAlive = true;
	private float spreadRange = 10f;
    private int speed = 15;
    private int bulletDamage = 15;
    private float bouncyness = 0.3f;
	private int lifeTimeMillis = 4000;
	
    private long timestampOfBirth;
    private float rotation;
	
    public boolean enemyBullet;
    
    public Bullet(Image sprite, float x, float y, int width, int height, float rotation, boolean playerBullet){
    	super(sprite, (int)(x-width/2), (int)(y-height/2), width, height);
        
    	this.timestampOfBirth = System.currentTimeMillis();
    	this.rotation = rotation;
    	this.maxFallSpeed = 100;
        //this.rotation = (float) ((rotation - (rotation*spreadRange)) + Math.random()*(rotation * spreadRange));
       
    	
        this.enemyBullet = playerBullet;
        
        this.addSpreadingToStartRotation();
     

        float radians = (float) (this.rotation * (Math.PI / 180));

       
        
        float vecX = (float)Math.sin(radians);
        float vecY = -(float)Math.cos(radians);
       
         
        this.velocityX = (float) (vecX  * this.speed);
        this.velocityY = (float) (vecY * this.speed);
       
       
       
    }
    
    public void update(){
    	if(System.currentTimeMillis() > this.timestampOfBirth + this.lifeTimeMillis){
    		this.die();
    	}
    	if(this.isAlive){
    		this.fall();
        	if(this.bouncyness > 0){
        		this.bounce();
        	}
    		
          
    	}
    	this.adjustRotationToFlightpath();
    	
    	this.actualMovement();
    	
    }

    public void render(Graphics g){
    	this.getImage().setRotation(rotation); 
    	super.render(g);
    }
    
    public void addSpreadingToStartRotation(){
    	 this.rotation = (float) ((rotation - (spreadRange)) + Math.random()*(spreadRange));
         if(this.rotation < -180){
         	this.rotation = 180 - (-this.rotation - 180);
         } else if(this.rotation > 180){
         	this.rotation = -180 + (this.rotation - 180);
         }
    }
    
    public void adjustRotationToFlightpath(){
    	this.rotation = (float) new Vector2f(this.velocityX, this.velocityY).getTheta() + 90;
    }
    
    public void bounce(){
    	if(this.isLeftSideCollided()){
    		this.posX = ((int)(posX/32)+1)*32; //pixel to tiles + 1 to pixel
    		this.velocityX = this.velocityX * -this.bouncyness;	
    	
    	}else if(this.isRightSideCollided()){
    		this.posX  = ((int)((posX+this.width)/32))*32 - this.width; //pixel to tiles + 1 to pixel
    		this.velocityX = this.velocityX * -this.bouncyness;
   		}
    	
    	
    	if(this.isBottomSideCollided()){
    		this.posY  = ((int)((posY+this.height)/32))*32 - this.height; //pixel to tiles + 1 to pixel
    		this.velocityY = this.velocityY * -this.bouncyness;
    		if(velocityY > -1 && velocityY < 1){
        		velocityY = 0;
        	}
    		this.velocityX *= 0.8;
    			
   		} else if(this.isTopSideCollided()){
   			this.posY  = ((int)(posY/32)+1)*32; //pixel to tiles + 1 to pixel
   			this.velocityY = this.velocityY * -this.bouncyness;
   			if(velocityY > -1 && velocityY < 1){
   	    		velocityY = 0;
   	    	} 
   			
   			this.velocityX *= 0.8;
   			
    		
   		}
    	
  
    	
    		
    		
    	}
    	
    public void die(){
    	this.setPosition(10000, 10000);
    	this.isAlive = false;
    	Game.toRemove.add(this);
    }
    
    public int getDamage(){
    	return this.bulletDamage;
    }
}
