package enemy;

import main.Game;
import objectBlueprints.AdvancedObject;
import org.newdawn.slick.Image;

public class Enemy extends AdvancedObject{
	
	public Enemy(Image img, int x, int y) {
        super(img, x, y);
    }
    
    public void update(){
       this.behavior();
       super.update();
    }
    
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
