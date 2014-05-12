package world;

import main.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimatedObject {
	
    private SpriteSheet charakterSpritesheet;
    private Animation goLeft, goRight, idleLeft, idleRight, jumpLeft, jumpRight,fallLeft,fallRight, walkAnimation;
    private Image walkR;
    
    
    public AnimatedObject() throws SlickException{
    	
    	 this.charakterSpritesheet = new SpriteSheet("Images/Player/mario.png", 78, 80);
       
         this.walkR = this.walkAnimation.getImage(3);
         
         this.idleLeft = new Animation(charakterSpritesheet,4,0,4,0,true,300,false);
         this.idleRight = new Animation(charakterSpritesheet,5,0,5,0,true,300, false);
         
         this.goLeft = new Animation(charakterSpritesheet,0,0,1,0,true,150,true);
         this.goRight = new Animation(charakterSpritesheet,8,0,9,0,true,150,true);

         this.jumpLeft = new Animation(charakterSpritesheet,4,1,4,1,true,300,false);
         this.jumpRight = new Animation(charakterSpritesheet,5,1,5,1,true,300,false);
         
         this.fallLeft = new Animation(charakterSpritesheet,3,1,3,1,true,300,false);
         this.fallRight = new Animation(charakterSpritesheet,6,1,6,1,true,300,false);
        	 
    }
	
    public void update(int delta){
    	
    	walkAnimation.update(delta);
    	
    }
    
    public void render(float posX, float posY){
    	//int posX = Game.getWindowWidth()-100;
    	//int posY = 20;
    	//walkAnimation.draw(posX,posY);
    	/*if (Game.player.walkdirection == 1){
       	 	this.walkR = this.walkAnimation.getImage(1);
    	}
    	
    	if (Game.player.walkdirection == 2){
       	 	this.walkR = this.walkAnimation.getImage(8);
    	}
    	
    	if (Game.player.walkdirection == 0){
       	 	this.walkR = this.walkAnimation.getImage(3);
    	}*/
    	
    	/*if(Game.player.velocityX > 0){
    		goRight.draw(posX,posY);
    	}else if(Game.player.velocityX < 0){
    		goLeft.draw(posX,posY);
    	}else{
    		idleLeft.draw(posX,posY);
    	}*/
    	
    	
    	
    	
    
    }
    
    
}
