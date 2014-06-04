package GameManager;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import Entity.LivingObject;
import Entity.StaticObject;

public class AnimationManager {

	private LivingObject owner;
	private SpriteSheet spriteSet;
	private Animation idleLeft, idleRight;
	private Animation walkLeft, walkRight;
	private Animation jumpLeft, jumpRight;
	private Animation fallLeft, fallRight;
	
	public AnimationManager(LivingObject obj) throws SlickException{
		this.owner = obj;
		this.spriteSet = new SpriteSheet("Images/Player/mario.png", 78, 80);

		this.idleLeft = new Animation(this.spriteSet,4,0,4,0,true,300,false);
        this.idleRight = new Animation(this.spriteSet,5,0,5,0,true,300, false);
        
        this.walkLeft = new Animation(this.spriteSet,0,0,1,0,true,150,true);
        this.walkRight = new Animation(this.spriteSet,8,0,9,0,true,150,true);

        this.jumpLeft = new Animation(this.spriteSet,4,1,4,1,true,300,false);
        this.jumpRight = new Animation(this.spriteSet,5,1,5,1,true,300,false);
        
        this.fallLeft = new Animation(this.spriteSet,3,1,3,1,true,300,false);
        this.fallRight = new Animation(this.spriteSet,6,1,6,1,true,300,false);
		
	}
	
	public void update(LivingObject obj){
		//this.owner = obj;
	}
	
	public void render(Graphics g){
		float posX = this.owner.getCenteredPosition().x() - this.spriteSet.getWidth() / this.spriteSet.getHorizontalCount()/2;
		System.out.println("text:   " + this.spriteSet.getTextureWidth());
		float posY = this.owner.getCenteredPosition().y() - this.spriteSet.getHeight() / this.spriteSet.getVerticalCount()/2;
	
		if(this.owner.getDirection() == "right"){
			if(this.owner.getVelocityY()<0){
				jumpRight.draw(posX,posY);
			}else if(this.owner.getVelocityY()>0){
				fallRight.draw(posX,posY);
			} else{
				if(this.owner.getVelocityX()>0){
					walkRight.draw(posX,posY);
				}else{
					idleRight.draw(posX,posY);
				}
			}
		}else if(this.owner.getDirection() == "left"){
			if(this.owner.getVelocityY()<0){
				jumpLeft.draw(posX,posY);
			}else if(this.owner.getVelocityY()>0){
				fallLeft.draw(posX,posY);
			} else{
				if(this.owner.getVelocityX()<0){
					walkLeft.draw(posX,posY);
				}else{
					idleLeft.draw(posX,posY);
				}
				
			}
		}
		
		
		
	}
	
}
