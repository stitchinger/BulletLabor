package objects;

import org.newdawn.slick.Image;

public class Mover extends GameObject {

	// Diese Variablen haben alle Objekte gemeinsam, die sich bewegen können

	protected float velocityX;
	protected float velocityY;
	protected float maxSpeed;
	protected float acceleration;
	protected float jumpHeight;
	protected float gravity;
	protected int jumpCount;

	// Zustände
	protected String direction;
	protected boolean isRunning;

	// Konstruktor Methode
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public Mover(Image img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		this.velocityX = 0;
		this.velocityY = 0;
		this.jumpCount = 0;
	}

	// Update Methode
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void update() {
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
		this.velocityY += this.gravity;
	}

	// Horizontale Beschleunigung
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public double getVelocityX() {
		return this.velocityX;
	}

	// Vertikale Beschleunigung
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public double getVelocityY() {
		return this.velocityY;
	}

	// Begrenzt die maximale horizontale Geschwindigkeit
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private float getLimitedVelocityX() {
		if (this.velocityX >= this.maxSpeed) {
			return this.maxSpeed;
		} else if (this.velocityX <= maxSpeed * (-1)) {
			return this.maxSpeed * (-1);
		} else {
			return this.velocityX;
		}
	}

	// Checkt ob das Objekt auf dem Boden steht
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public boolean isOnGround() {
		float distanceToGround = 700 - (this.posY + this.height);
		
		if(distanceToGround <= 0 && this.velocityY >= 0){
			this.velocityY = 0;
			this.jumpCount = 0;
			return true;
		} else {
			if(this.velocityY > distanceToGround){
				this.velocityY = distanceToGround;
			}
			return false;
		}
	}

}
