package world;

import main.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;


public class Mover extends GameObject {

	// Diese Variablen haben alle Objekte gemeinsam, die sich bewegen können

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

	// Konstruktor Methode
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public Mover(Image img, int x, int y, int width, int height) throws SlickException {
		super(img, x, y, width, height);
		this.velocityX = 0;
		this.velocityY = 0;
		this.gravity = 0.38f;  
		this.maxFallSpeed = 20;
		this.jumpCount = 0;
		
		this.idleSprite = new SpriteSheet("Images/Player/mario.png", 78, 80);
        this.idleAnimation = new Animation(idleSprite,100);
		
		
	}

	// Update Methode
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void update() {
		if (this.health <= 0){
	        	this.die();
	    }
		
		if (!this.isBottomSideCollided()) {
			this.fall();
		}

		this.velocityX = getLimitedVelocityX();

		if(this.isLeftSideCollided()){
			this.velocityX = Math.max(0, this.velocityX);
		}
		if(this.isRightSideCollided()){
			this.velocityX = Math.min(0, this.velocityX);
		}
		if(this.isTopSideCollided()){
			this.velocityY = Math.max(0, this.velocityY);
		}
		this.posX += this.velocityX;   	 	 // Auswirkung der horizontalen Beschleunigung auf die X-Position
		this.posY += this.velocityY; 	   	// Auswirkung der vertikalen Beschleunigung auf die Y-Position

		if (!this.isRunning && this.isBottomSideCollided()) {
			this.velocityX = (this.velocityX * 0.2f);
			if (this.velocityX <= 0.1 && this.velocityX >= -0.1) {
				this.velocityX = 0;
			}
		}
		

		hitbox.setLocation(this.posX, this.posY); // Position der Hitbox
													// anpassen
	}
	
	public void render(Graphics g){
		
	//	idleAnimation.draw(this.posX, this.posY);
		super.render(g);
	}

	// Nach rechts gehen
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void moveRight() {
		this.isRunning = true;
		this.velocityX += this.acceleration;
		this.direction = "right";

	}

	// Nach Links gehen
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void moveLeft() {
		this.isRunning = true;
		this.velocityX -= this.acceleration;
		this.direction = "left";
	}

	// Sprung Methode
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void jump() {
		this.velocityY = this.jumpHeight * (-1);
		this.jumpCount++;
	}

	// Fall Methode
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void fall() {
		//this.velocityY += this.gravity;
		this.velocityY = Math.min(this.maxFallSpeed, this.velocityY += this.gravity);
	}

	public void addForce(float velocityX, float velocityY){
		this.velocityX += velocityX;
		this.velocityY += velocityY;
	}
	
	// Horizontale Beschleunigung
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public float getVelocityX() {
		return this.velocityX;
	}

	// Vertikale Beschleunigung
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public float getVelocityY() {
		return this.velocityY;
	}

	// Begrenzt die maximale horizontale Geschwindigkeit
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private float getLimitedVelocityX() {
		if (this.velocityX >= this.maxWalkSpeed) {
			return this.maxWalkSpeed;
		} else if (this.velocityX <= maxWalkSpeed * (-1)) {
			return this.maxWalkSpeed * (-1);
		} else {
			return this.velocityX;
		}
	}

	// Checkt ob das Objekt auf dem Boden steht
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public boolean isBottomSideCollided() {
		TiledMap tm = Game.level1.getTiledMap();
		int collisionLayer = tm.getLayerIndex("CollisionLayer");
		
    	
    	int bottomLeftX = (int)((this.posX + 10)/32);
    	int bottomLeftY = (int)((this.posY+ this.height)/32);
    	
    	int bottomRightX = (int)((this.posX + this.width - 10)/32);
    	int bottomRightY = (int)((this.posY + this.height)/32);
    			
    	int bottomLeftCornerCollision = tm.getTileId(bottomLeftX, bottomLeftY, collisionLayer);
    	int bottomRightCornerCollision = tm.getTileId(bottomRightX, bottomRightY, collisionLayer);
    		
    	if((bottomLeftCornerCollision > 0 || bottomRightCornerCollision > 0) && velocityY >= 0){
    		this.velocityY = 0;
			this.jumpCount = 0;
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
		TiledMap tm = Game.level1.getTiledMap();
		int collisionLayer = tm.getLayerIndex("CollisionLayer");
		
    	
    	int topLeftX = (int)((this.posX)/32);
    	int topLeftY = (int)((this.posY+20)/32);
    	
    	int bottomLeftX = (int)((this.posX)/32);
    	int bottomLeftY = (int)((this.posY+ this.height-20)/32);
    			
    	int topLeftCornerCollision = tm.getTileId(topLeftX, topLeftY, collisionLayer);
    	int bottomLeftCornerCollision = tm.getTileId(bottomLeftX, bottomLeftY, collisionLayer);
    		
    	if((topLeftCornerCollision > 0 || bottomLeftCornerCollision > 0)){
			
			return true;
		} else{
			
			return false;
		}
		
		
	}	
	
	private boolean isRightSideCollided(){
		
		TiledMap tm = Game.level1.getTiledMap();
		int collisionLayer = tm.getLayerIndex("CollisionLayer");
		
    	
    	int topRightX = (int)((this.posX + this.width)/32);
    	int topRightY = (int)((this.posY+20)/32);
    	
    	int bottomRightX = (int)((this.posX + this.width)/32);
    	int bottomRightY = (int)((this.posY+ this.height-20)/32);
    			
    	int topRightCornerCollision = tm.getTileId(topRightX, topRightY, collisionLayer);
    	int bottomRightCornerCollision = tm.getTileId(bottomRightX, bottomRightY, collisionLayer);
    		
    	if((topRightCornerCollision > 0 || bottomRightCornerCollision > 0)){
			
			return true;
		} else{
			
			return false;
		}
		
	}
	
	private boolean isTopSideCollided(){
		TiledMap tm = Game.level1.getTiledMap();
		int collisionLayer = tm.getLayerIndex("CollisionLayer");
		
    	
    	int topLeftX = (int)((this.posX + 10)/32);
    	int topLeftY = (int)((this.posY)/32);
    	
    	int topRightX = (int)((this.posX + this.width - 10)/32);
    	int topRightY = (int)((this.posY)/32);
    			
    	int topLeftCornerCollision = tm.getTileId(topLeftX, topLeftY, collisionLayer);
    	int topRightCornerCollision = tm.getTileId(topRightX, topRightY, collisionLayer);
    		
    	if((topLeftCornerCollision > 0 || topRightCornerCollision > 0)){
    		
			return true;
		} else{
			
			return false;
		}
	}
	
	

}

