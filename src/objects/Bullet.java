package objects;

import org.newdawn.slick.Image;


public class Bullet extends GameObject{
    
    private double velX;
    private double velY;
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
            velX = -speed;
        }else if(direction == "right"){
            velX = speed;
        }
        
         
       velY = (int)(Math.random()* this.spreadRange - this.spreadRange/2);
       
       
       
    }
    
    // 2. Konstruktor ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Bullet(Image sprite, float x, float y, int width, int height, double vecX, double vecY) {
        
    	super(sprite, x, y, width, height);
        
        this.spreadRange = 3;
        this.speed = 35;
        this.bulletDamage = 10;
        
        float hypo = (float) Math.sqrt((vecX * vecX) + (vecY * vecY));
        
        velX =  ((vecX / hypo) * speed) + this.spreading();
        velY =  ((vecY / hypo) * speed) + this.spreading();
        
        
    }
    
    // Update Methode +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void update(){
        this.posX += velX;
        this.posY += velY;
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
