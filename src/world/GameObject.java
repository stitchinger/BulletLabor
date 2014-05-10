package world;

import main.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class GameObject {
   
    // Diese Variablen haben alle Objekte gemeinsam, die auf dem Fenster gezeichnet werden und eine Hitbox besitzen
	protected Image sprite;
    protected float posX;
    protected float posY;
    protected int width;
    protected int height;
    protected int health;
    protected Shape hitbox;
    protected long timeOfLastHit;
    protected int flashTimeInMillis;
    
    
    // Konstruktor Methode ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public GameObject(Image sprite, float x, float y, int width, int height){
        this.sprite = sprite;
        this.posY = y;
        this.posX = x;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x,y,width, height);
        this.flashTimeInMillis = 150;
    }
    
    public void render(Graphics g){
    	if(this.timeOfLastHit + flashTimeInMillis > System.currentTimeMillis()){
    		this.getImage().drawFlash(this.getX(), this.getY());
    	}else{
    		this.getImage().draw(this.getX(), this.getY());
    	}
    
        if (Game.showHitbox) {
        	g.setColor(Color.red);
        	g.draw(this.getHitbox());
        }
    }
    
    // getImage ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Image getImage(){
        return this.sprite;
    }
    
    // getHitbox +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Shape getHitbox(){
        return this.hitbox;
    }
    
    // getX +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public float getX(){
         return this.posX;
    }
    
    // getY +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public float getY(){
        return this.posY;
    }
    
    public int getHealth(){
    	return this.health;
    }
    
    // Position ver�ndern +++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void setPosition(float x, float y){
    	this.posX = x;
    	this.posY = y;
    	
    }
    
    // Verletzt werden ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void receiveDamage(int damage){
    	if(System.currentTimeMillis() - this.timeOfLastHit > 1000){
    		this.health -= damage;
        	this.timeOfLastHit = System.currentTimeMillis();
    	}
    	
    }
    
    // Verhalten bei Zerst�rung des Objekts +++++++++++++++++++++++++++++++++++++++
    public void die(){
    	this.posY = -500;
    	this.health = 100;
    	Game.killCount++;
    	
    }
    
    
    
  
     
     
}