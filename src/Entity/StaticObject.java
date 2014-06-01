package Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import Util.Settings;
import Util.Vector2;

public abstract class StaticObject {
   
	protected Vector2 position;
    protected int width;
    protected int height;
 
    protected Image sprite;
    protected Shape hitbox;
    protected long timestampOfLastHit;
    protected int flashTimeInMillis = 150;
    
    protected float x;
    protected float y;
    
    public StaticObject(float x, float y){
        this.position = new Vector2(x,y);
        this.x = x;
        this.y = y;
    }
    
    public void update(int delta){
    	
    }
    
    public void render(Graphics g){
    	if(this.timestampOfLastHit + flashTimeInMillis > System.currentTimeMillis()){
    		this.getSpriteImage().drawFlash(this.getX(), this.getY());
    	}else{
    		this.getSpriteImage().draw(this.getX(), this.getY());
    	}
    
        if (Settings.DEBUG_MODUS) {
        	g.setColor(Color.red);
        	g.draw(this.getHitbox());
        	g.setColor(Color.white); 
        
        }
    }
    
    public void setSpriteImage(Image sprite){
    	this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        this.hitbox = new Rectangle(x, y, this.width, this.height);
        this.sprite = sprite;
    }
    
    public Image getSpriteImage(){
        return this.sprite;
    }
    
    public Shape getHitbox(){
        return this.hitbox;
    }
    
    public Vector2 getPosition(){
    	return this.position;
    }
    
    public Vector2 getCenteredPosition(){
    	float centeredX = this.getX()+this.getWidth()/2;
    	float centeredY = this.getY()+this.getHeight()/2;
    	return new Vector2(centeredX,centeredY);
    }
    
    public float getX(){
    	return this.position.x();
    }
    
    public float getY(){
    	return this.position.y();
    }
    
    public void setX(float x){
    	this.position.setX(x);
    }
    
    public void setY(float y){
    	this.position.setY(y);
    }
    
    public int getWidth(){
    	return this.width;
    }
    
    public int getHeight(){
    	return this.height;
    }
    
   
}
