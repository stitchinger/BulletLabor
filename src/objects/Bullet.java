package objects;

import org.newdawn.slick.Image;


public class Bullet extends GameObject{
    
    private double velocityX;
    private double velocityY;
    private int spreadRange;
    private int speed;
    public int bulletDamage;
    
    
    // 1. Konstruktor +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Bullet(Image sprite, float x, float y, int width, int height, String direction) {
        super(sprite, x, y, width, height);
        
        this.spreadRange = 10;
        this.speed = 50;
        this.bulletDamage = 10;
        
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
        this.bulletDamage = 10;
        
        float hypo = (float) Math.sqrt((vecX * vecX) + (vecY * vecY));
        
        velocityX =  ((vecX / hypo) * speed) + this.spreading();
        velocityY =  ((vecY / hypo) * speed) + this.spreading();
        
        
    }
    
    // Update Methode +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void update(){
        this.posX += velocityX;
        this.posY += velocityY;
        hitbox.setLocation(this.posX, this.posY);
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
