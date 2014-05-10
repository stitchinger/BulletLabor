package objects;

import main.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimatedObject {
	
    private SpriteSheet walkSprite;
    private Animation walkAnimation;
    
    public AnimatedObject() throws SlickException{
    	
    	 this.walkSprite = new SpriteSheet("Player/mario.png", 78, 80);
         this.walkAnimation = new Animation(walkSprite,300);
    }
	
    public void update(int delta){
    	
    	walkAnimation.update(delta);
    }
    
    public void render(){
    	int posX = Game.getWindowWidth()-100;
    	int posY = 20;
    	walkAnimation.draw(posX,posY);
    }
    
    
}
