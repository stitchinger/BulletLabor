package main;

import org.newdawn.slick.Graphics;

import world.Mover;

public class Camera {
	private float posX;
	private float posY;
	private float velocityX;
	private float velocityY;
	private float viewportWidth;
	private float viewportHeight;
	
	private Mover target;
	
	public Camera(){
		this.target = Game.player;
		this.viewportWidth = Game.getWindowWidth();
		this.viewportHeight = Game.getWindowHeight();
		//this.posX = this.target.getX() + this.viewportWidth / 2;
		//this.posY = this.target.getY() - this.viewportHeight / 2;
		this.posX = 0;
		this.posY = 0;
	}
	
	public void update(){
		
		this.posX = Game.player.getX() * (-1) +  this.viewportWidth/2;
		this.posY = Game.player.getY() * (-1) +  this.viewportHeight/2;
	
		
		//target.setPosition(x, y);
	}
	
	public void render(Graphics g){
		
		g.translate(posX, posY);
	}
	
	public void followTarget(){
		this.posX = this.target.getX() - this.viewportWidth / 2;
		this.posY = this.target.getY() - this.viewportHeight / 2;
		
	}
	
	public float getX(){
		
		return this.posX;
	}
	
	public float getY(){
		
		return this.posY;
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
	
	
}
