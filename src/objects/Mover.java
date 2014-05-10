package objects;

import main.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

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
		
		this.idleSprite = new SpriteSheet("img/mario.png", 78, 80);
        this.idleAnimation = new Animation(idleSprite,100);
		
		
	}

	// Update Methode
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void update() {
		if (this.health <= 0){
	        	this.die();
	    }
		
		if (!this.isOnGround()) {
			this.fall();
		}

		this.velocityX = getLimitedVelocityX();

		this.posX += this.velocityX;   	 	 // Auswirkung der horizontalen Beschleunigung auf die X-Position
		this.posY += this.velocityY; 	   	// Auswirkung der vertikalen Beschleunigung auf die Y-Position

		if (!this.isRunning && this.isOnGround()) {
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
	public boolean isOnGround() {
		
		
		int collisionLayer = Game.level1.getTiledMap().getLayerIndex("CollisionLayer");
		int tileCollideLeft = 1;
    	int tileCollideRight = 1;
    
    	if(this.posX >= 0 && this.posX+this.width <= Game.getWindowWidth() && this.posY >= 0 && this.posY <= Game.getWindowHeight()){
    		tileCollideLeft = (Game.level1.getTiledMap().getTileId((int)((this.posX)/32), (int)((this.posY + this.height)/32), collisionLayer));
    		tileCollideRight = (Game.level1.getTiledMap().getTileId((int)((this.posX + this.width)/32), (int)((this.posY + this.height)/32), collisionLayer));
    		
    	}
		
		
		if((tileCollideLeft > 0 || tileCollideRight > 0)  && this.velocityY >= 0){
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

}
