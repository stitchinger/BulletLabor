package objectBlueprints;

import main.Game;

public class LivingObject extends AdvancedObject {

	protected float acceleration = 0.35f;
	protected int maxWalkSpeed = 6;
	protected int jumpHeight = 8;
	protected int jumpCount = 2;
	protected String direction = "right";
	protected int health;
	protected int maxHealth = 100;
	
	public LivingObject(float x, float y) {
		super(x, y);
	}

	public void update(int delta){
		 this.limitWalkVelocity();
		 super.update(delta);
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
    	if(System.currentTimeMillis() - this.timestampOfLastHit > 500){
    		this.health -= damage;
        	this.timestampOfLastHit = System.currentTimeMillis();
        	if(this.health <= 0){
        		this.die();
        	}
    	}
    	
    }
    
	public int getHealth(){
    	return this.health;
    }
	
	public void setHealth(int health){
    	this.health = health;
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
	
	public void die(){
		Game.removeObject(this);
	}
}

