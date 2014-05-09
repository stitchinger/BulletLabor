package objects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimatedObject {
	
    private SpriteSheet walkSprite;
    private Animation walkAnimation;
    
    public AnimatedObject() throws SlickException{
    	
    	 this.walkSprite = new SpriteSheet("img/walkspritesheet.png", 32, 64);
         this.walkAnimation = new Animation(walkSprite,100);
    }
	
    public void update(int delta){
    	
    	walkAnimation.update(delta);
    }
    
    public void render(){
    	
    	walkAnimation.draw(100,100);
    }
    
    
}
