package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import world.MovedObject;


public class Camera {
	private float posX;
	private float posY;
	private float velocityX;
	private float velocityY;
	private int viewportWidth;
	private int viewportHeight;
	private boolean followMode;
	private boolean smoothFollowMode;
	private float inertia;
	

	
	
	
	public Camera(){
		
		this.viewportWidth = Game.getWindowWidth();
		this.viewportHeight = Game.getWindowHeight();
		this.posX = 0;
		this.posY = 0;
		this.followMode = true;
		this.smoothFollowMode = true;
		this.inertia = 0.07f;
	}
	
	public void update(Input in){
		 if(in.isKeyPressed(Input.KEY_1)){
             this.toggleFollowMode();
		 }
		 if(in.isKeyPressed(Input.KEY_2)){
             this.toggleSmoothFollowMode();
		 }
		
		 
		 
		 if(this.followMode){
			this.follow(Game.player);
		 }
		
		 if(this.smoothFollowMode){
			 this.smoothMovement();
		 }
		 
		
		 this.actualMovement();
		 this.avoidLeavingWorld();
			
		
	}
	
	public void render(Graphics g){
	
		g.translate(posX, posY);
	
	}
	
	public void follow(MovedObject target){
		float targetX = (target.getX() + target.getWidth()/2) * (-1) +   this.viewportWidth/2;
		float targetY = (target.getY() + target.getHeight()/2) * (-1) +  this.viewportHeight/2;
		this.velocityX = targetX - this.posX;
		this.velocityY = targetY - this.posY;
		
		
	}
	
	public void smoothMovement(){
		this.velocityX *= this.inertia;
		this.velocityY *= this.inertia;
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
	
	public void actualMovement(){
		this.posX += velocityX;
		this.posY += velocityY;
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
	
	public void toggleFollowMode(){
		this.followMode = !this.followMode;
	}
	
	public void toggleSmoothFollowMode(){
		this.smoothFollowMode = !this.smoothFollowMode;
	}
	
	public int getViewportWidth(){
		return this.viewportWidth;
	}
	
	public int getViewportHeight(){
		return this.viewportHeight;
	}
	
	
	
	
	
}
