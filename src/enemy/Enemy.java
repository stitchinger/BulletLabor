package enemy;

import main.Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import world.Mover;


public class Enemy extends Mover{

	// Konstruktor Methode +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Enemy(Image img, int x, int y, int width, int height) throws SlickException {
        super(img, x, y, width, height);
        
        this.direction = "right";
        this.maxWalkSpeed = 5;
        this.acceleration = 0.25f;
        this.jumpHeight = 8;
        this.gravity = 0.30f;
        this.isRunning = false;
        this.health = 100;
    }
    
    // Update Methode ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void update(){
        if(direction == "right"){
        	
        	this.moveRight();
        }
        if(direction == "left"){
        	
        	this.moveLeft();
        }
        
        if(Math.random() < 0.005f){
        	
        	this.changeDirection();
        }
        if(this.posX >= Game.getWindowWidth()){
        	this.direction = "left";
        	
        } else if(this.posX <= 0){
        	this.direction = "right";
        }
        if(Math.random() < 0.01f && this.isOnGround()){
        	
        	this.jump();
        }
      
       
        
        super.update();
       
        
    }
    
    // Richtung ändern +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void changeDirection(){
    	
    	if(direction== "right"){
    		direction = "left";
    	}
    	else if(direction == "left"){
    		direction = "right";
    	} else{
    		direction = "right";
    	}
    }
}
