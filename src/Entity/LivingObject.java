package Entity;

import org.newdawn.slick.Graphics;

import GameManager.AnimationManager;
import GameManager.ObjectManager;
import GameManager.SoundManager;

public abstract class LivingObject extends AdvancedObject {

	protected float acceleration;
	protected int maxWalkSpeed;
	protected int jumpHeight;
	protected int jumpCount;
	protected String direction;
	protected int health;
	protected int maxHealth;
	
	protected AnimationManager aniMan;
	
	public LivingObject(float x, float y) {
		super(x, y);
		this.aniMan = null;
	}

	public void update(int delta){
		 this.limitWalkVelocity();
		 super.update(delta);
		 if(this.isBottomSideCollided()){
			 this.jumpCount= 0;
		 }
		 if(this.aniMan != null)
			 this.aniMan.update(this);
	 }
	
	public void render(Graphics g){
		
		super.render(g);
		if(this.aniMan != null)
			this.aniMan.render(g);
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
		
		if (this.jumpCount <= 1)
			SoundManager.play("jump2");
		else
			SoundManager.play("jump");
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
	
	public void setMaxWalkSpeed(int maxWalkSpeed){
    	this.maxWalkSpeed = maxWalkSpeed;
    }
	
	public void setAcceleration(float acceleration){
    	this.acceleration = acceleration;
    }
	
	public void setJumpHeight(int jumpHeight){
    	this.jumpHeight = jumpHeight;
    }
	
	public void setJumpCount(int jumpCount){
    	this.jumpCount = jumpCount;
    }
	
	public void setDirection(String direction){
    	this.direction = direction;
    }
	
	public String getDirection(){
		return this.direction;
	}
	
	public void setMaxHealth(int maxHealth){
    	this.maxHealth = maxHealth;
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
		ObjectManager.removeObject(this);
	}
}

