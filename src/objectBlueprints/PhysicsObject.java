package objectBlueprints;

import main.Game;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;
import util.Vector2;

public class PhysicsObject extends StaticObject {

	protected Vector2 velocity = new Vector2(0,0);
	protected float gravity = 0.38f;
	protected float maxFallSpeed = 20;
	protected float friction = 0.8f;
	protected boolean isRunning = false;
	
	public PhysicsObject(Image img, int x, int y) {
		super(img, x, y);
	}

	public void update() {
		this.fall();
		this.limitFallSpeed();
		this.applyFriction();
		this.actualMovement();
		this.resolveWorldCollision();
	}
	
	public void render(Graphics g){
		
		super.render(g);
	}
	
	public void actualMovement(){
	
		this.position.add(velocity);
	
		hitbox.setLocation(this.position.x(), this.position.y());

	}
	
	public void applyFriction(){
		
		if(!this.isRunning && this.isBottomSideCollided()){
			if(this.getVelocityX() > 0 - this.friction && this.getVelocityX() < 0 + this.friction){
				this.setVelocityX(0);
			}else if(this.getVelocityX() > 0){
				
				this.addForce(-this.friction, 0);
			}else if(this.getVelocityX() < 0){
				
				this.addForce(this.friction, 0);
			}
		}
		
	}

	public void fall() {
		this.addForce(0, this.gravity);
	}
	
	public void limitFallSpeed(){
		this.setVelocityY(Math.min(this.getVelocityY(), this.maxFallSpeed));
	}

	public void resolveWorldCollision(){
		if(this.isBottomSideCollided()){
		
			this.setVelocityY(Math.min(0, this.getVelocityY()));
		
			this.setY(((int)((this.getY()+this.height)/32))*32 - this.height);
		
			
		} 
		if(this.isTopSideCollided()){
		
			this.setVelocityY(Math.max(0, this.getVelocityY()));
			
			this.setY( ((int)(this.getY()/32)+1)*32);
		}
		if(this.isLeftSideCollided()){
		
			this.setVelocityX(Math.max(0, this.getVelocityX()));
			this.setX( ((int)(this.getX()/32)+1)*32);
			
		}
		if(this.isRightSideCollided()){
		
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
		TiledMap tm = Game.gameworld.getTiledMap();
		int collisionLayer = tm.getLayerIndex("CollisionLayer");
		int tileSize = tm.getTileHeight();
    	
    	int topLeftX = (int)((this.getX())/tileSize);
    	int topLeftY = (int)((this.getY()+10)/tileSize);
    	
    	int bottomLeftX = (int)((this.getX())/tileSize);
    	int bottomLeftY = (int)((this.getY()+ this.height-10)/tileSize);
    			
    	int topLeftCornerCollision = tm.getTileId(topLeftX, topLeftY, collisionLayer);
    	int bottomLeftCornerCollision = tm.getTileId(bottomLeftX, bottomLeftY, collisionLayer);
    		
    	if((topLeftCornerCollision > 0 || bottomLeftCornerCollision > 0)){
			
			return true;
		} else{
			
			return false;
		}
		
		
	}	
	
	public boolean isRightSideCollided(){
		
		TiledMap tm = Game.gameworld.getTiledMap();
		
		int collisionLayer = tm.getLayerIndex("CollisionLayer");
		
		int tileSize = tm.getTileHeight();
    	
    	int topRightX = (int)((this.getX() + this.width)/tileSize);
    	int topRightY = (int)((this.getY()+10)/tileSize);
    	
    	int bottomRightX = (int)((this.getX() + this.width)/tileSize);
    	int bottomRightY = (int)((this.getY()+ this.height-10)/tileSize);
    			
    	int topRightCornerCollision = tm.getTileId(topRightX, topRightY, collisionLayer);
    	int bottomRightCornerCollision = tm.getTileId(bottomRightX, bottomRightY, collisionLayer);
    		
    	if((topRightCornerCollision > 0 || bottomRightCornerCollision > 0)){
			
			return true;
		} else{
			
			return false;
		}
		
	}
	
	public boolean isTopSideCollided(){
		TiledMap tm = Game.gameworld.getTiledMap();
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
		TiledMap tm = Game.gameworld.getTiledMap();
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

