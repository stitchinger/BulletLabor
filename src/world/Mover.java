package world;

import main.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;


public class Mover extends GameObject {

	
	// Instanzvariablen
	protected float velocityX;
	protected float velocityY;
	protected float maxWalkSpeed;
	protected float maxFallSpeed;
	protected float acceleration;
	protected float jumpHeight;
	protected float gravity;
	protected int jumpCount;

	// Zustände
	protected String direction;
	protected boolean isRunning;
	
	// Sprites und Animationen
	protected SpriteSheet idleSprite;
	protected Animation idleAnimation;

	
	public Mover(Image img, int x, int y, int width, int height) throws SlickException {
		super(img, x, y, width, height);
		this.velocityX = 0;
		this.velocityY = 0;
		this.gravity = 0.38f;  
		this.maxFallSpeed = 20;
		this.jumpCount = 0;
		
		//this.idleSprite = new SpriteSheet("Images/Player/mario.png", 78, 80);
        //this.idleAnimation = new Animation(idleSprite,100);
		
		
	}

	
	public void update() {
		
		if (this.health <= 0){
        	this.die();
		}
		
		this.velocityX = getLimitedVelocityX();
		
		this.detectWorldCollision();
		this.actualMovement();
		this.movementSlide();
			
	}
	
	public void render(Graphics g){
		
		super.render(g);
	}
	
	public void actualMovement(){
		this.posX += this.velocityX;   	 	 
		this.posY += this.velocityY; 	
		hitbox.setLocation(this.posX, this.posY); 

	}
	
	public void movementSlide(){
		if (!this.isRunning && this.isBottomSideCollided()) {
			this.velocityX = (this.velocityX * 0.2f);
			if (this.velocityX <= 0.1 && this.velocityX >= -0.1) {
				this.velocityX = 0;
			}
		}
	}

	public void moveRight() {
		this.isRunning = true;
		this.velocityX += this.acceleration;
		this.direction = "right";

	}

	public void moveLeft() {
		this.isRunning = true;
		this.velocityX -= this.acceleration;
		this.direction = "left";
	}

	public void jump() {
		this.velocityY = this.jumpHeight * (-1);
		this.jumpCount++;
	}

	public void fall() {
		
		this.velocityY = Math.min(this.maxFallSpeed, this.velocityY += this.gravity);
	}

	public void addForce(float velocityX, float velocityY){
		this.velocityX += velocityX;
		this.velocityY += velocityY;
	}
	
	public float getVelocityX() {
		return this.velocityX;
	}

	public float getVelocityY() {
		return this.velocityY;
	}

	private float getLimitedVelocityX() {
		if (this.velocityX >= this.maxWalkSpeed) {
			return this.maxWalkSpeed;
		} else if (this.velocityX <= maxWalkSpeed * (-1)) {
			return this.maxWalkSpeed * (-1);
		} else {
			return this.velocityX;
		}
	}
	
	public void detectWorldCollision(){
		if(this.isLeftSideCollided()){
			this.velocityX = Math.max(0, this.velocityX);
		}
		if(this.isRightSideCollided()){
			this.velocityX = Math.min(0, this.velocityX);
		}
		if(this.isTopSideCollided()){
			this.velocityY = Math.max(0, this.velocityY);
		}
		if(this.isBottomSideCollided()){
			this.velocityY = Math.min(0, this.velocityY);
			this.jumpCount = 0;
		} else{
			this.fall();
		}
	}

	public boolean isBottomSideCollided() {
		TiledMap tm = Game.gameworld.getTiledMap();
		int collisionLayer = tm.getLayerIndex("CollisionLayer");
		int tileSize = tm.getTileHeight();
    	
    	int bottomLeftX = (int)((this.posX + 10)/tileSize);
    	int bottomLeftY = (int)((this.posY+ this.height)/tileSize);
    	
    	int bottomRightX = (int)((this.posX + this.width - 10)/tileSize);
    	int bottomRightY = (int)((this.posY + this.height)/tileSize);
    			
    	int bottomLeftCornerCollision = tm.getTileId(bottomLeftX, bottomLeftY, collisionLayer);
    	int bottomRightCornerCollision = tm.getTileId(bottomRightX, bottomRightY, collisionLayer);
    		
    	if((bottomLeftCornerCollision > 0 || bottomRightCornerCollision > 0) && velocityY >= 0){
    	
			return true;
		} else{
			
			return false;
		}
		
		/*float distanceToGround = 500 - (this.posY + this.height);
		
		if(distanceToGround <= 0 && this.velocityY >= 0){
			this.velocityY = 0;
			this.jumpCount = 0;
			return true;
		} else {
			if(this.velocityY > distanceToGround){
				this.velocityY = distanceToGround;
			}
			return false;
		}   */
	}

	public boolean isLeftSideCollided(){
		TiledMap tm = Game.gameworld.getTiledMap();
		int collisionLayer = tm.getLayerIndex("CollisionLayer");
		int tileSize = tm.getTileHeight();
    	
    	int topLeftX = (int)((this.posX)/tileSize);
    	int topLeftY = (int)((this.posY+20)/tileSize);
    	
    	int bottomLeftX = (int)((this.posX)/tileSize);
    	int bottomLeftY = (int)((this.posY+ this.height-20)/tileSize);
    			
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
    	
    	int topRightX = (int)((this.posX + this.width)/tileSize);
    	int topRightY = (int)((this.posY+20)/tileSize);
    	
    	int bottomRightX = (int)((this.posX + this.width)/tileSize);
    	int bottomRightY = (int)((this.posY+ this.height-20)/tileSize);
    			
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
    	
    	int topLeftX = (int)((this.posX + 10)/tileSize);
    	int topLeftY = (int)((this.posY)/tileSize);
    	
    	int topRightX = (int)((this.posX + this.width - 10)/tileSize);
    	int topRightY = (int)((this.posY)/tileSize);
    			
    	int topLeftCornerCollision = tm.getTileId(topLeftX, topLeftY, collisionLayer);
    	int topRightCornerCollision = tm.getTileId(topRightX, topRightY, collisionLayer);
    		
    	if((topLeftCornerCollision > 0 || topRightCornerCollision > 0)){
    		
			return true;
		} else{
			
			return false;
		}
	}
	
	

}

