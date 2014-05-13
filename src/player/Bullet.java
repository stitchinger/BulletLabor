package player;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import world.MovedObject;


public class Bullet extends MovedObject{
    
   
    private float spreadRange;
    private int speed;
    public int bulletDamage;
    public float angle;
    public boolean enemyBullet;
    
    
    // 1. Konstruktor 
    public Bullet(Image sprite, float x, float y, int width, int height, float angle, boolean playerBullet){
    	super(sprite, (int)(x-width/2), (int)(y-height/2), width, height);
        
    	this.angle = angle;
        this.spreadRange = 0.07f;
        this.speed = 18;
        this.bulletDamage = 15;
        this.gravity = 0.38f;
        this.maxFallSpeed = 100;
        angle = (float) ((angle - (angle*spreadRange)) + Math.random()*(angle * spreadRange));
        this.enemyBullet = playerBullet;
       
       
        
        float vecY = -(float)Math.cos(angle);
        float vecX = (float)Math.sin(angle);
         
        velocityX = (float) (vecX * speed);
        velocityY = (float) (vecY * speed);
       
       
       
    }
    
  
    public void update(){
    	this.fall();
        this.actualMovement();
    	//this.posX += velocityX;
        //this.posY += velocityY;
        //hitbox.setLocation(this.posX, this.posY);
    }
    
    public void render(Graphics g){
    	this.getImage().setRotation(angle*60); 
    	super.render(g);
    }
    
    public float getVelocityX() {
		return this.velocityX;
	}

    public float getVelocityY() {
		return this.velocityY;
	}
    
    public int getDamage(){
    	return this.bulletDamage;
    }
    
}
