package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import world.Mover;
import world.World;

public class Camera {
	private float posX;
	private float posY;
	private float velocityX;
	private float velocityY;
	private float viewportWidth;
	private float viewportHeight;
	private boolean freeMode;
	
	private Mover target;
	
	public Camera(){
		this.target = Game.player;
		this.viewportWidth = Game.getWindowWidth();
		this.viewportHeight = Game.getWindowHeight();
		this.posX = 0;
		this.posY = 0;
		this.freeMode = false;
	}
	
	public void update(Input in){
		 if(in.isKeyDown(Input.KEY_F)){
             
	         	this.toggleLockPosition();
	         }
		if(!freeMode){
			this.followTarget();
			this.avoidLeavingWorld();
		}else{
			
		}
		
		
		
	}
	
	public void render(Graphics g){
		
		g.translate(posX, posY);
	}
	
	public void followTarget(){
		this.posX = Game.player.getX() * (-1) +  this.viewportWidth/2;
		this.posY = Game.player.getY() * (-1) +  this.viewportHeight/2;
		
	}
	
	private void avoidLeavingWorld(){
		  if(this.getX() < 0){
			this.posX = 0;
		}else if(this.getX() + this.viewportWidth > Game.gameworld.getWidth()){
			this.posX = (Game.gameworld.getWidth() - this.viewportWidth) * -1;
		}
		
		if(this.getY() < 0){
			this.posY = 0;
		}else if(this.getY() + this.viewportHeight > Game.gameworld.getHeight()){
			this.posY = (Game.gameworld.getHeight() - this.viewportHeight) * -1; 
		}
		
	}
	
	public float getX(){
		
		return this.posX * -1;
	}
	
	public float getY(){
		
		return this.posY * -1;
	}
	
	public boolean isInViewport(float x, float y){
		float tx = translateX(x);
		float ty = translateY(y);
		
		if(tx < this.viewportWidth && tx > 0 && ty < this.viewportHeight && ty > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public float translateX(float x){
		
		return x - this.posX; 
	}
	
	public float translateY(float y){
		
		return y - this.posY; 
	}
	
	public void toggleLockPosition(){
		if(this.freeMode){
			this.freeMode = false;
		} else{
			this.freeMode = true;
		}
	}
	
	
}