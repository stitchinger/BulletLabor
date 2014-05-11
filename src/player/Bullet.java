package player;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import world.GameObject;


public class Bullet extends GameObject{
    
    private float velocityX;
    private float velocityY;
    private float spreadRange;
    private int speed;
    public int bulletDamage;
    public float angle;
    
    
    // 1. Konstruktor 
    public Bullet(Image sprite, float x, float y, int width, int height, float angle) {
    	super(sprite, x-width/2, y-height/2, width, height);
        
    	this.angle = angle;
        this.spreadRange = 0.07f;
        this.speed = 35;
        this.bulletDamage = 15;
        angle = (float) ((angle - (angle*spreadRange)) + Math.random()*(angle * spreadRange));
       
       
        
        float vecY = -(float)Math.cos(angle);
        float vecX = (float)Math.sin(angle);
         
        velocityX = (float) (vecX * speed);
        velocityY = (float) (vecY * speed);
       
       
       
    }
    
  
    public void update(){
    	
        this.posX += velocityX;
        this.posY += velocityY;
        hitbox.setLocation(this.posX, this.posY);
    }
    
    public void render(Graphics g){
    	this.getImage().setRotation(angle*120); 
    	super.render(g);
    }
    
    public float getVelocityX() {
		return this.velocityX;
	}

    public float getVelocityY() {
		return this.velocityY;
	}
    
    private float getSpreading(){
    	
    	return (float) ((Math.random() * this.spreadRange )- (this.spreadRange/2));
    }
    
    public int getDamage(){
    	return this.bulletDamage;
    }
    
}
