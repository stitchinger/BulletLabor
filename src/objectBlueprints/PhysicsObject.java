package objectBlueprints;


import static main.Game.bulletSprite;
import static main.Game.bullet_list;
import main.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import util.Vector2;
import weapons.Bullet;


public class PhysicsObject extends StaticObject {

	
	// Instanzvariablen
//	protected float velocityX;
	//protected float velocityY;
	protected Vector2 velocity;
	
	protected float maxWalkSpeed;
	protected float maxFallSpeed;
	protected float acceleration;
	protected float jumpHeight;
	protected float gravity;
	protected int jumpCount;
	protected float friction;
	
	protected int maxHealth;

	// Zustände
	protected String direction;
	protected boolean isRunning;
	
	// Sprites und Animationen
	protected SpriteSheet idleSprite;
	protected Animation idleAnimation;
	

	
	public PhysicsObject(Image img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		//this.velocityX = 0;
		//this.velocityY = 0;
		this.velocity = new Vector2(0,0);
		this.gravity = 0.38f;  
		this.maxFallSpeed = 20;
		this.jumpCount = 0;
		this.friction = 0.8f;
		this.maxHealth = 100;
	
	}

	
	public void update() {
		
	
		if (this.health <= 0){
        	this.die();
		}
		this.setVelocityX(getLimitedVelocityX());
		
		this.actualMovement();
		this.resolveWorldCollision();
		this.applyFriction();
			
	}
	
	public void render(Graphics g){
		
		super.render(g);
	}
	
	public void actualMovement(){
		//this.posX += this.velocityX;   	 	 
		//this.posY += this.velocityY; 	
		this.position.add(velocity);
		//hitbox.setLocation(this.getX(), this.getY()); 
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

	public void moveRight() {
		this.isRunning = true;
		//this.velocityX += this.acceleration;
		this.velocity.add(this.acceleration, 0);
		this.direction = "right";

	}

	public void moveLeft() {
		this.isRunning = true;
		//this.velocityX -= this.acceleration;
		this.velocity.add(-this.acceleration, 0);
		this.direction = "left";
	}

	public void jump() {
		//this.velocityY = this.jumpHeight * (-1);
		//this.velocity.add(0, -jumpHeight );
		this.setVelocityY(-this.jumpHeight);
		this.jumpCount++;
	}

	public void fall() {
		
		//dthis.velocityY = Math.min(this.maxFallSpeed, this.velocityY += this.gravity);
		this.velocity.add(0, gravity);
		 
		//this.setVelocityY(Math.min(this.maxFallSpeed, this.velocityY += this.gravity));
	}

	public void addForce(float velocityX, float velocityY){
		//this.velocityX += velocityX;
		//this.velocityY += velocityY; 
		this.velocity.add(velocityX, velocityY);
	}
	
	public float getVelocityX() {
		//return this.velocityX;
		return this.velocity.x();
	}

	public float getVelocityY() {
		//return this.velocityY;
		return this.velocity.y();
	}
	
	public void setVelocityX(float x){
		//this.velocityX = x;
		this.velocity.setX(x);
	}
	public void setVelocityY(float y){
		//this.velocityY = y;
		this.velocity.setY(y);
	}
	
	 private float getLimitedVelocityX() {
		if (this.getVelocityX() >= this.maxWalkSpeed) {
			return this.maxWalkSpeed;
		} else if (this.getVelocityX() <= maxWalkSpeed * (-1)) {
			return this.maxWalkSpeed * (-1);
		} else {
			return this.getVelocityX();
		}
		/*
		if (this.getVelocityX() >= this.maxWalkSpeed) {
			return this.maxWalkSpeed;
		} else if (this.getVelocityX() <= maxWalkSpeed * (-1)) {
			return this.maxWalkSpeed * (-1);
		} else {
			return this.getVelocityX();
			
		}
		*/
		
	}
	
	public void resolveWorldCollision(){
		if(this.isBottomSideCollided()){
			//this.velocityY = Math.min(0, this.velocityY);
			this.setVelocityY(Math.min(0, this.getVelocityY()));
			//this.posY  = ((int)((posY+this.height)/32))*32 - this.height; //pixel to tiles + 1 to pixel
			this.setY(((int)((this.getY()+this.height)/32))*32 - this.height);
			this.jumpCount = 0;
			
		} else{
			this.fall();
		}
		if(this.isTopSideCollided()){
			//this.velocityY = Math.max(0, this.velocityY);
			this.setVelocityY(Math.max(0, this.getVelocityY()));
			//this.posY  = ((int)(posY/32)+1)*32; //pixel to tiles + 1 to pixel
			this.setY( ((int)(this.getY()/32)+1)*32);
		}
		if(this.isLeftSideCollided()){
			//this.velocityX = Math.max(0, this.velocityX);
			//this.posX = ((int)(posX/32)+1)*32; //pixel to tiles + 1 to pixel
			this.setVelocityX(Math.max(0, this.getVelocityX()));
			this.setX( ((int)(this.getX()/32)+1)*32);
			
		}
		if(this.isRightSideCollided()){
			//this.velocityX = Math.min(0, this.velocityX);
			//this.posX  = ((int)((posX+this.width)/32))*32 - this.width; //pixel to tiles + 1 to pixel
			this.setVelocityX(Math.min(0, this.getVelocityX()));
			this.setX(((int)((this.getX()+this.width)/32))*32 - this.width);
		}
		
		
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
	
	  public void die(){
	    //	this.posY = 100;
	    	
	    	this.setY(100);
	    	this.health = 100;
	    	Game.killCount++;
	    	
	    }
	    
	    public void addHealth(int hp){
	    	this.health += hp; 
	    	if(this.health > this.maxHealth){
	    		this.health = this.maxHealth;
	    	}
	    }
	
	

}

