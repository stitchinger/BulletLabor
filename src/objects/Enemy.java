package objects;

import main.Game;
import org.newdawn.slick.Image;


public class Enemy extends Mover{

	// Konstruktor Methode +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Enemy(Image img, int x, int y, int width, int height) {
        super(img, x, y, width, height);
        
        this.direction = "right";
        this.maxSpeed = 5;
        this.acceleration = 0.25f;
        this.jumpHeight = 8;
        this.gravity = 0.30f;
        this.isRunning = false;
        this.isInAir = true;
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
        if(Math.random() < 0.1f && !this.isInAir){
        	
        	this.jump();
        }
      
        if (this.health <= 0){
        	this.die();
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
