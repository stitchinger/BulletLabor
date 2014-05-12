package enemy;

import main.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
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
    public void update(int delta){
        if(direction == "right"){
        	
        	this.moveRight();
        }
        if(direction == "left"){
        	
        	this.moveLeft();
        }
        
        if(Math.random() < 0.005f){
        	
        	//this.changeDirection();
        	if (Game.player.getX() < this.getX())
        		this.moveLeft();
        	else
        		this.moveRight();
        }
       
        if(Math.random() < 0.02f && this.isBottomSideCollided()){
        	
        	this.jump();
        }
        
        
       
       
        
        super.update(delta);
       
        
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
