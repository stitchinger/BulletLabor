package world;

import main.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimatedObject {
	
    private SpriteSheet walkSprite;
    private Animation walkAnimation;
    private Image walkR;
    
    public AnimatedObject() throws SlickException{
    	
    	 this.walkSprite = new SpriteSheet("Images/Player/mario.png", 78, 80);
         this.walkAnimation = new Animation(walkSprite,300);
         this.walkR = this.walkAnimation.getImage(3);
         
         
        	 
    }
	
    public void update(int delta){
    	
    	walkAnimation.update(delta);
    }
    
    public void render(float posX, float posY){
    	//int posX = Game.getWindowWidth()-100;
    	//int posY = 20;
    	//walkAnimation.draw(posX,posY);
    	if (Game.player.walkdirection == 1){
       	 	this.walkR = this.walkAnimation.getImage(1);
    	}
    	
    	if (Game.player.walkdirection == 2){
       	 	this.walkR = this.walkAnimation.getImage(8);
    	}
    	
    	if (Game.player.walkdirection == 0){
       	 	this.walkR = this.walkAnimation.getImage(3);
    	}
    	
    	
    	walkR.draw(posX,posY);
    }
    
    
}
