package enemy;


import main.Game;
import objectBlueprints.AdvancedObject;
import objectBlueprints.PhysicsObject;

import org.newdawn.slick.Image;


public class Enemy extends AdvancedObject{

	// Konstruktor Methode +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Enemy(Image img, int x, int y, int width, int height) {
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
       
        
       this.behavior();
       
        
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
    
    public void behavior(){
    	 if(direction == "right"){
         	
         	this.moveRight();
         }
         if(direction == "left"){
         	
         	this.moveLeft();
         }
         
         if(Math.random() < 0.005f){
         	//this.angleShot(this.getTargetAngle((int)Game.player.getX()+ 16, (int)Game.player.getY()+32), true);
         	
         	//this.changeDirection();
         	if (Game.player.getX() < this.getX())
         		this.moveLeft();
         	else
         		this.moveRight();
         }
        
         if(Math.random() < 0.02f && this.isBottomSideCollided()){
         	
         	this.jump();
         }
         
    }
}
