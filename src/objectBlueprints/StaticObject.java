package objectBlueprints;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import util.Settings;
import util.Vector2;


public class StaticObject {
   
	protected Vector2 position;
    protected int width;
    protected int height;
 
    protected Image sprite;
    protected Shape hitbox;
    protected long timeOfLastHit;
    protected int flashTimeInMillis;
    
    public StaticObject(Image sprite, float x, float y){
        this.sprite = sprite;
        this.position = new Vector2(x,y);
        this.width = this.sprite.getWidth();
        this.height = this.sprite.getHeight();
        this.hitbox = new Rectangle(x,y,width, height);
        this.flashTimeInMillis = 150;
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
        	//g.drawString("hp: "+ this.getHealth(), this.getX(), this.getY()+ this.getHeight() );
        }
    }
    
    public Image getImage(){
        return this.sprite;
    }
    
    public Shape getHitbox(){
        return this.hitbox;
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