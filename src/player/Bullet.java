package player;

import org.newdawn.slick.Image;

import world.GameObject;


public class Bullet extends GameObject{
    
    private float velocityX;
    private float velocityY;
    private int spreadRange;
    private int speed;
    public int bulletDamage;
    
    
    // 1. Konstruktor +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Bullet(Image sprite, float x, float y, int width, int height, String direction) {
    	super(sprite, x-width/2, y-height/2, width, height);
        
        this.spreadRange = 30;
        this.speed = 50;
        this.bulletDamage = 2;
        
        if(direction == "left"){
            velocityX = -speed;
        }else if(direction == "right"){
            velocityX = speed;
        }
        
         
       velocityY = (int)(Math.random()* this.spreadRange - this.spreadRange/2);
       
       
       
    }
    
    // 2. Konstruktor ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Bullet(Image sprite, float x, float y, int width, int height, double vecX, double vecY) {
        
    	super(sprite, x-width/2, y-height/2, width, height);
        
        this.spreadRange = 3;
        this.speed = 35;
        this.bulletDamage = 2;
        
        float hypo = (float) Math.sqrt((vecX * vecX) + (vecY * vecY));
        
        velocityX = (float) ((vecX / hypo) * speed) + this.spreading();
        velocityY = (float) ((vecY / hypo) * speed) + this.spreading();
        
        
    }
    
    // Update Methode +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void update(){
        this.posX += velocityX;
        this.posY += velocityY;
        hitbox.setLocation(this.posX, this.posY);
    }
    
    public float getVelocityX() {
		return this.velocityX;
	}

	// Vertikale Beschleunigung
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public float getVelocityY() {
		return this.velocityY;
	}
    
    // Berechnet die Streuung +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public float spreading(){
    	
    	return (float) ((Math.random() * this.spreadRange )- (this.spreadRange/2));
    }
    
    // GetDAmage
    public int getDamage(){
    	return this.bulletDamage;
    }
    
}