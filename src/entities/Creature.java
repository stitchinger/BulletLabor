package entities;


import java.util.ArrayList;

import manager.EntityManager;

import org.newdawn.slick.Graphics;

public abstract class Creature extends Rigidbody {

	protected float acceleration;
	protected int maxWalkSpeed;
	protected int jumpHeight;
	protected int jumpCount;
	protected int maxJumpCount;
	protected String direction;
	protected int health;
	protected int maxHealth;
	private static ArrayList<Rigidbody> collided_objects;
	
	
	
	public Creature(float x, float y) {
		super(x, y);
		
		
		
		
	}

	// VOIDS

	public void update(){
		 this.limitWalkVelocity();
		 super.update();
		 if(this.isBottomSideCollided()){
			 this.jumpCount= this.maxJumpCount;
		 }
		 if(this.health<=0){
			 EntityManager.removeObject(this);
		 }
		
	 }
	
	public void render(Graphics g){
		
		super.render(g);
		
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
		if(this.jumpCount > 0){
			this.setVelocityY(-this.jumpHeight);
			this.jumpCount--;
		}
		
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
		//EntityManager.removeObject(this);
	}
	// Getter
	

	public int getHealth(){
    	return this.health;
    }
	
	public String getDirection(){
		return this.direction;
	}
	
	// Setter 
	
	public void setDirection(String direction){
    	this.direction = direction;
    }
	
	public void causeDamage(float damage){
		this.health -= damage;
	}

	public void collideWith(Rigidbody obj) {
		// TODO Auto-generated method stub
		
	}
	
}

