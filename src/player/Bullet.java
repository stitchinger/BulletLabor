package player;

import main.Game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import entity.MovedObject;


public class Bullet extends MovedObject{
    
	private boolean isAlive = true;
	private float spreadRange = 0.07f;
    private int speed = 24;
    private int bulletDamage = 15;
    private float bouncyness = 0.5f;
	private int lifeTimeMillis = 3000;
	
    private long timestampOfBirth;
    private float rotation;
	
    
  
    public boolean enemyBullet;
    
    
    
    
    // 1. Konstruktor 
    public Bullet(Image sprite, float x, float y, int width, int height, float rotation, boolean playerBullet){
    	super(sprite, (int)(x-width/2), (int)(y-height/2), width, height);
        
    	this.timestampOfBirth = System.currentTimeMillis();
    	this.rotation = rotation;
    	this.maxFallSpeed = 100;
        this.rotation = (float) ((rotation - (rotation*spreadRange)) + Math.random()*(rotation * spreadRange));
        this.enemyBullet = playerBullet;
        
       
        float vecY = -(float)Math.cos(rotation);
        float vecX = (float)Math.sin(rotation);
         
        this.velocityX = (float) (vecX * this.speed);
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
    	  this.actualMovement();
    	
    }
    
    public void render(Graphics g){
    	this.getImage().setRotation(rotation*60); 
    	super.render(g);
    }
    
    public int getDamage(){
    	return this.bulletDamage;
    }
    
    public void bounce(){
    	
		
		
		
    	if(this.isBottomSideCollided()){
    		this.posY  = ((int)((posY+this.height)/32))*32 - this.height; //pixel to tiles + 1 to pixel
    		this.velocityY = this.velocityY * -this.bouncyness;
    		this.velocityX *= 0.8;
    			
   		} else if(this.isTopSideCollided()){
   			this.posY  = ((int)(posY/32)+1)*32; //pixel to tiles + 1 to pixel
   			this.velocityY = this.velocityY * -this.bouncyness;
   			this.velocityX *= 0.8;
   			
    		
   		}
  
    	if(this.isLeftSideCollided()){
    		this.posX = ((int)(posX/32)+1)*32; //pixel to tiles + 1 to pixel
    		this.velocityX = this.velocityX * -this.bouncyness;	
    	
    	}else if(this.isRightSideCollided()){
    		this.posX  = ((int)((posX+this.width)/32))*32 - this.width; //pixel to tiles + 1 to pixel
    		this.velocityX = this.velocityX * -this.bouncyness;
   		}
    		
    		
    	}
    	
    public void die(){
    	this.setPosition(10000, 10000);
    	this.isAlive = false;
    	Game.toRemove.add(this);
    }
    
}
