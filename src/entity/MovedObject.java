package entity;

import static main.Game.bulletSprite;
import static main.Game.bullet_list;
import main.Game;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;
import player.Bullet;


public class MovedObject extends GameObject {

	
	// Instanzvariablen
	protected float velocityX;
	protected float velocityY;
	
	protected float maxWalkSpeed;
	protected float maxFallSpeed;
	protected float acceleration;
	protected float jumpHeight;
	protected float gravity;
	protected int jumpCount;
	protected float friction;
	public long timeOfLastShot; 

	// Zustände
	protected String direction;
	protected boolean isRunning;
	
	// Sprites und Animationen
	protected SpriteSheet idleSprite;
	protected Animation idleAnimation;
	

	
	public MovedObject(Image img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		this.velocityX = 0;
		this.velocityY = 0;
		this.gravity = 0.38f;  
		this.maxFallSpeed = 20;
		this.jumpCount = 0;
		this.friction = 0.8f;
	
	}

	
	public void update() {
		
		super.update();
		
		this.velocityX = getLimitedVelocityX();
		
		this.actualMovement();
		this.detectWorldCollision();
		this.applyFriction();
			
	}
	
	public void render(Graphics g){
		
		super.render(g);
	}
	
	public void actualMovement(){
		this.posX += this.velocityX;   	 	 
		this.posY += this.velocityY; 	
		hitbox.setLocation(this.posX, this.posY); 

	}
	
	public void applyFriction(){
		
		if(!this.isRunning && this.isBottomSideCollided()){
			if(velocityX > 0 - this.friction && velocityX < 0 + this.friction){
				this.velocityX = 0;
			}else if(velocityX > 0){
				this.velocityX -= this.friction;
			}else if(velocityX < 0){
				this.velocityX += this.friction;
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
	
	public float getTargetAngle(int x, int y){
    	
        float vecX = x - (this.posX+this.width/2);
        float vecY= y - (this.posY+this.height/2);
        float[] normalizedVector = getNormalizedVector2(vecX, vecY);
        
      
        return (float)Math.atan2(normalizedVector[0], -normalizedVector[1]);
    }
	
	private float[] getNormalizedVector2(float vecX, float vecY){
	    	
	    	float hypo = (float) Math.sqrt((vecX * vecX) + (vecY * vecY));
	        float[] vector2 = new float[2];
	    	vector2[0] = (float)(vecX / hypo);
	        vector2[1] = (float)(vecY / hypo);
	        return vector2;
	    }
	 
	public void angleShot(float angle, boolean playerBullet) throws SlickException{
	    	timeOfLastShot = System.currentTimeMillis();
	    	bullet_list.add(new Bullet(bulletSprite, (this.posX+this.width/2), (this.posY+this.height/2), 40, 40, angle, playerBullet));
	    
	    	//this.addForce(normalizedVector[0] * (-2), normalizedVector[1]*(-2));
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
		if(this.isBottomSideCollided()){
			this.velocityY = Math.min(0, this.velocityY);
			this.posY  = ((int)((posY+this.height)/32))*32 - this.height; //pixel to tiles + 1 to pixel
			this.jumpCount = 0;
			
		} else{
			this.fall();
		}
		if(this.isTopSideCollided()){
			this.velocityY = Math.max(0, this.velocityY);
			this.posY  = ((int)(posY/32)+1)*32; //pixel to tiles + 1 to pixel
		}
		if(this.isLeftSideCollided()){
			this.velocityX = Math.max(0, this.velocityX);
			this.posX = ((int)(posX/32)+1)*32; //pixel to tiles + 1 to pixel
			
		}
		if(this.isRightSideCollided()){
			this.velocityX = Math.min(0, this.velocityX);
			this.posX  = ((int)((posX+this.width)/32))*32 - this.width; //pixel to tiles + 1 to pixel
		}
		
		
	}

	public boolean isLeftSideCollided(){
		TiledMap tm = Game.gameworld.getTiledMap();
		int collisionLayer = tm.getLayerIndex("CollisionLayer");
		int tileSize = tm.getTileHeight();
    	
    	int topLeftX = (int)((this.posX)/tileSize);
    	int topLeftY = (int)((this.posY+10)/tileSize);
    	
    	int bottomLeftX = (int)((this.posX)/tileSize);
    	int bottomLeftY = (int)((this.posY+ this.height-10)/tileSize);
    			
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
    	int topRightY = (int)((this.posY+10)/tileSize);
    	
    	int bottomRightX = (int)((this.posX + this.width)/tileSize);
    	int bottomRightY = (int)((this.posY+ this.height-10)/tileSize);
    			
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
    	
    	int topLeftX = (int)((this.posX + 5)/tileSize);
    	int topLeftY = (int)((this.posY)/tileSize);
    	
    	int topRightX = (int)((this.posX + this.width - 5)/tileSize);
    	int topRightY = (int)((this.posY)/tileSize);
    			
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
    	
    	int bottomLeftX = (int)((this.posX + 5)/tileSize);
    	int bottomLeftY = (int)((this.posY+ this.height)/tileSize);
    	
    	int bottomRightX = (int)((this.posX + this.width - 5)/tileSize);
    	int bottomRightY = (int)((this.posY + this.height)/tileSize);
    			
    	int bottomLeftCornerCollision = tm.getTileId(bottomLeftX, bottomLeftY, collisionLayer);
    	int bottomRightCornerCollision = tm.getTileId(bottomRightX, bottomRightY, collisionLayer);
    		
    	if((bottomLeftCornerCollision > 0 || bottomRightCornerCollision > 0) && velocityY >= 0){
    		
    		
			return true;
		} else{
			
			return false;
		}
		
		
	}
	
	

}

