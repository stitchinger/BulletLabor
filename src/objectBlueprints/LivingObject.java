package objectBlueprints;

import org.newdawn.slick.Image;

public class LivingObject extends PhysicsObject {

	protected float acceleration;
	protected int maxWalkSpeed;
	protected int jumpHeight;
	protected int jumpCount;
	protected String direction;
	protected int health;
	protected int maxHealth;
	
	
	
	public LivingObject(Image img, int x, int y) {
		super(img, x, y);
		
	}

	public void update(){
		 this.limitWalkVelocity();
		 super.update();
		 if(this.isBottomSideCollided()){
			 this.jumpCount= 0;
		 }
	 }
	 
	public void moveRight() {
		this.isRunning = true;
			
		this.velocity.add(this.acceleration, 0);
		this.direction = "right";

	}

	public void moveLeft() {
		this.isRunning = true;
	
		this.velocity.add(-this.acceleration, 0);
		this.direction = "left";
	}

	public void jump() {
		
		this.setVelocityY(-this.jumpHeight);
		this.jumpCount++;
	}
	
	public void addHealth(int hp){
		this.health += hp; 
		if(this.health > this.maxHealth)
		{
			this.health = this.maxHealth;
		}
	}
	
	public void receiveDamage(int damage){
    	if(System.currentTimeMillis() - this.timeOfLastHit > 500){
    		this.health -= damage;
        	this.timeOfLastHit = System.currentTimeMillis();
    	}
    	
    }
    
	public int getHealth(){
    	return this.health;
    }
	
	private void limitWalkVelocity() {
		float value;
		if (this.getVelocityX() >= this.maxWalkSpeed) {
		
			value = this.maxWalkSpeed;
		} else if (this.getVelocityX() <= maxWalkSpeed * (-1)) {
			
			value = -this.maxWalkSpeed;
		} else {
		
			value = this.getVelocityX();
		}
		this.setVelocityX(value);
	
		
	}
}

