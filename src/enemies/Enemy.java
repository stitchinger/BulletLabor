package enemies;

import player.Player;
import entities.Creature;
import entities.Rigidbody;
import manager.EntityManager;

public abstract class Enemy extends Creature{
	
	private float aggrorange;
	private int damage = 50;
	
	public Enemy(float x, float y) {
        super(x, y);
    }
    
    public void update(){
       super.update();
       this.behavior();
    }
    
    public void behavior(){
    	this.enemyMove();
    	this.turnOnCollision();
    	
    	this.enemyJump();
   	}
    
    public void enemyMove(){
    	if(this.direction == "right"){
    		this.moveRight();
        }
        if(this.direction == "left"){
        	this.moveLeft();
        }
    }
    
    public void turnOnCollision(){
 	   if(super.isRightSideCollided()){
 		   super.setDirection("left");
 	   }
 	   if(super.isLeftSideCollided()){
 		   super.setDirection("right");
 	   }
    }
    
    public void enemyJump(){
    	if(Math.random() < 0.01f && super.isBottomSideCollided()){
        	this.jump();
        }
    }
    
    
    
    
}
   
    
   