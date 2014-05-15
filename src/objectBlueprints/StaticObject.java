package objectBlueprints;


import main.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import util.Settings;
import util.Vector2;


public class StaticObject {
   
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
    public StaticObject(Image sprite, float x, float y, int width, int height){
        this.sprite = sprite;
        this.posY = y;
        this.posX = x;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x,y,width, height);
        this.flashTimeInMillis = 150;
       
    }
    
    public void update(){
    	
    }
    
    public void render(Graphics g){
    	
    	if(this.timeOfLastHit + flashTimeInMillis > System.currentTimeMillis()){
    		this.getImage().drawFlash(this.getX(), this.getY());
    	}else{
    		this.getImage().draw(this.getX(), this.getY());
    	}
    
        if (Settings.DEBUG_MODUS) {
        	g.setColor(Color.red);
        	g.draw(this.getHitbox());
        	g.setColor(Color.white); 
        	g.drawString("hp: "+ this.health, this.getX(), this.getY()+ this.height );
        }
    }
    
    public Image getImage(){
        return this.sprite;
    }
    
    public Shape getHitbox(){
        return this.hitbox;
    }
    
    public float getX(){
         return this.posX;
    }
    
    public float getY(){
        return this.posY;
    }
    
    public int getWidth(){
    	return this.width;
    }
    
    public int getHeight(){
    	return this.height;
    }
    
    public int getHealth(){
    	return this.health;
    }
    
    public void setPosition(float x, float y){
    	this.posX = x;
    	this.posY = y;
    	
    }
    
    public void receiveDamage(int damage){
    	if(System.currentTimeMillis() - this.timeOfLastHit > 500){
    		this.health -= damage;
        	this.timeOfLastHit = System.currentTimeMillis();
    	}
    	
    }
    
  
    
}
