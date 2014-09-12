package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

import player.Player;
import Main.GamePanel;
import utility.Settings;
import utility.Vector2;

public abstract class Rigidbody {
   
	protected Vector2 position;
    protected int width;
    protected int height;
 
    protected Image sprite;
    protected Shape hitbox;
    protected long timestampOfLastHit;
    protected int flashTimeInMillis = 150;
    
    protected float x;
    protected float y;
    
    protected Vector2 velocity = new Vector2(0,0);
	protected float gravity = 0.38f;
	protected float maxFallSpeed = 20;
	protected float friction = 0.90f;
	protected boolean isRunning = false;
    
    public Rigidbody(float x, float y){
        this.position = new Vector2(x,y);
        this.x = x;
        this.y = y;
    }
    
    public void update(){
    	this.fall();
		this.limitFallSpeed();
		this.applyFriction();
		this.actualMovement();
		this.resolveWorldCollision();
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
    
    protected void actualMovement(){
		this.position.add(this.velocity);
		hitbox.setLocation(this.position.x(), this.position.y());
	}
	
	public void applyFriction(){
		if(this.isRunning && this.isBottomSideCollided()){
			//if(this.getVelocityX() > 0 - this.friction && this.getVelocityX() < 0 + this.friction){
			//	this.setVelocityX(0);
		//	}
		if(this.getVelocityX() > 0){
				//this.addForce(-this.friction, 0);
				this.setVelocityX(this.getVelocityX()*this.friction);
			}else if(this.getVelocityX() < 0){
			//	this.addForce(this.friction, 0);
				this.setVelocityX(this.getVelocityX()*this.friction);
			}
		}
		
	}

	protected void fall() {
		this.addForce(0, this.gravity);
	}
	
	protected void limitFallSpeed(){
		this.setVelocityY(Math.min(this.getVelocityY(), this.maxFallSpeed));
	}

	protected void resolveWorldCollision(){
		if(this.isBottomSideCollided()){
			this.setVelocityY(Math.min(0, this.getVelocityY()));
			this.setY(((int)((this.getY()+this.height)/32))*32 - this.height);
		
		} else if(this.isTopSideCollided()){
			this.setVelocityY(Math.max(0, this.getVelocityY()));
			this.setY(((int)(this.getY()/32)+1)*32);
		}
		
		if(this.isLeftSideCollided()){
			this.setVelocityX(Math.max(0, this.getVelocityX()));
			this.setX(this.getX() + 0.4f); //<-- Temp. Hotfix, ist aber definitiv nicht optimal!
		}
		else if(this.isRightSideCollided()){
			this.setVelocityX(Math.min(0, this.getVelocityX()));
			this.setX(((int)((this.getX()+this.width)/32))*32 - this.width);
		}
	}
		
	public void addForce(float velocityX, float velocityY){
	
		this.velocity.add(velocityX, velocityY);
	}
	
	public void addForce(Vector2 force){
		
		this.velocity.add(force);
	}
	
	public void setVelocityX(float x){
		
		this.velocity.setX(x);
	}
	
	public void setVelocityY(float y){
		
		this.velocity.setY(y);
	}

	public Vector2 getVelocity(){
		return this.velocity;
	}	
	
	public float getVelocityX() {
	
		return this.velocity.x();
	}

	public float getVelocityY() {
	
		return this.velocity.y();
	}
	
	public boolean isLeftSideCollided(){
		TiledMap tm = GamePanel.gameworld.getTiledMap();
		int collisionLayer = tm.getLayerIndex("CollisionLayer");
		int tileSize = tm.getTileHeight();
    	
    	int topLeftX = (int)((this.getX())/tileSize);
    	int topLeftY = (int)((this.getY()+15)/tileSize);
    	
    	int bottomLeftX = (int)((this.getX())/tileSize);
    	int bottomLeftY = (int)((this.getY()+ this.height-15)/tileSize);
    			
    	int topLeftCornerCollision = tm.getTileId(topLeftX, topLeftY, collisionLayer);
    	int bottomLeftCornerCollision = tm.getTileId(bottomLeftX, bottomLeftY, collisionLayer);
    		
    	if((topLeftCornerCollision > 0 || bottomLeftCornerCollision > 0)){
			
			return true;
		} else{
			
			return false;
		}
		
		
	}	
	
	public boolean isRightSideCollided(){
		
		TiledMap tm = GamePanel.gameworld.getTiledMap();
		
		int collisionLayer = tm.getLayerIndex("CollisionLayer");
		
		int tileSize = tm.getTileHeight();
    	
    	int topRightX = (int)((this.getX() + this.width)/tileSize);
    	int topRightY = (int)((this.getY()+15)/tileSize);
    	
    	int bottomRightX = (int)((this.getX() + this.width)/tileSize);
    	int bottomRightY = (int)((this.getY()+ this.height-15)/tileSize);
    			
    	int topRightCornerCollision = tm.getTileId(topRightX, topRightY, collisionLayer);
    	int bottomRightCornerCollision = tm.getTileId(bottomRightX, bottomRightY, collisionLayer);
    		
    	if((topRightCornerCollision > 0 || bottomRightCornerCollision > 0)){
			
			return true;
		} else{
			
			return false;
		}
		
	}
	
	public boolean isTopSideCollided(){
		TiledMap tm = GamePanel.gameworld.getTiledMap();
		int collisionLayer = tm.getLayerIndex("CollisionLayer");
		int tileSize = tm.getTileHeight();
    	
    	int topLeftX = (int)((this.getX() + 5)/tileSize);
    	int topLeftY = (int)((this.getY())/tileSize);
    	
    	int topRightX = (int)((this.getX() + this.width - 5)/tileSize);
    	int topRightY = (int)((this.getY())/tileSize);
    			
    	int topLeftCornerCollision = tm.getTileId(topLeftX, topLeftY, collisionLayer);
    	int topRightCornerCollision = tm.getTileId(topRightX, topRightY, collisionLayer);
    		
    	if((topLeftCornerCollision > 0 || topRightCornerCollision > 0)){
    		
			return true;
		} else{
			
			return false;
		}
	}
	
	public boolean isBottomSideCollided() {
		TiledMap tm = GamePanel.gameworld.getTiledMap();
		int collisionLayer = tm.getLayerIndex("CollisionLayer");
		int tileSize = tm.getTileHeight();
    	
    	int bottomLeftX = (int)((this.getX() + 5)/tileSize);
    	int bottomLeftY = (int)((this.getY()+ this.height)/tileSize);
    	
    	int bottomRightX = (int)((this.getX() + this.width - 5)/tileSize);
    	int bottomRightY = (int)((this.getY() + this.height)/tileSize);
    			
    	int bottomLeftCornerCollision = tm.getTileId(bottomLeftX, bottomLeftY, collisionLayer);
    	int bottomRightCornerCollision = tm.getTileId(bottomRightX, bottomRightY, collisionLayer);
    		
    	if((bottomLeftCornerCollision > 0 || bottomRightCornerCollision > 0) && this.getVelocityY() >= 0){
    		
    		
			return true;
		} else{
			
			return false;
		}
		
		
	} 
	
	
		
	
	
	
    

	
    
   
}
