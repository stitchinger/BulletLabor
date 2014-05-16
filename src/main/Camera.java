package main;

import objectBlueprints.PhysicsObject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import util.Settings;
import util.Vector2;


public class Camera {
	
	private Vector2 position;
	private Vector2 velocity;
	private int viewportWidth;
	private int viewportHeight;
	private boolean followMode;
	private boolean smoothFollowMode;
	private float inertia;
	
	public Camera(){
		
		this.viewportWidth = Settings.WINDOW_WIDTH;
		this.viewportHeight = Settings.WINDOW_HEIGHT;
		this.position = new Vector2(0,0);
		this.velocity = new Vector2(0,0);
		this.followMode = true;
		this.smoothFollowMode = true;
		this.inertia = 0.2f;
	}
	
	public void update(Input in){
		
		 this.cameraControl(in);
		 
		 if(this.followMode){
			this.follow(Game.player);
		 }
		
		 if(this.smoothFollowMode){
			 this.smoothMovement();
		 }
		 
		 this.actualMovement();
		 this.avoidLeavingWorld();
			
		 System.out.println("X: " + this.getX() + ", Y: " +  this.getY());
	}
	
	public void render(Graphics g){
		g.translate(-this.getX(), -this.getY());
	}
	
	public void follow(PhysicsObject target){
		float targetX = target.getX() + target.getWidth()/2 - this.viewportWidth/2;
		float targetY = target.getY() + target.getHeight()/2 - this.viewportHeight/2;
		
		targetX += target.getVelocityX()*10;
		
		this.velocity.setX(targetX - this.getX());
		this.velocity.setY(targetY - this.getY()); 
	}
	
	private void cameraControl(Input in){
		 if(in.isKeyPressed(Input.KEY_1)){
             this.toggleFollowMode();
		 }
		 if(in.isKeyPressed(Input.KEY_2)){
             this.toggleSmoothFollowMode();
		 }
		 if(in.isKeyDown(Input.KEY_UP)){
             this.position.add(0, -10);
		 }
		 if(in.isKeyDown(Input.KEY_DOWN)){
			 this.position.add(0, 10);
		 }
		 if(in.isKeyDown(Input.KEY_LEFT)){
			 this.position.add(-10, 0);
		 }
		 if(in.isKeyDown(Input.KEY_RIGHT)){
			 this.position.add(10, 0);
		 }
		
	}
	
	public void smoothMovement(){
		
		this.velocity.mult(this.inertia);
	}
	
	private void avoidLeavingWorld(){
		float maxX = Game.gameworld.getWidth() - this.viewportWidth - 32;
		float minX = 32;
		float maxY = Game.gameworld.getHeight() - this.viewportHeight - 32;
		float minY = 32;
		
		float posX = Math.min(Math.max(minX, this.getX()), maxX);
		float posY = Math.min(Math.max(minY, this.getY()), maxY);
		
		this.position.set(posX, posY);
		
	}
	
	public void actualMovement(){
		this.position.add(this.velocity);
	}
	
	public float getX(){
		return this.position.x();
	}
	
	public float getY(){
		return this.position.y();
		
	}
	
	public boolean isInViewport(Vector2 position){
		float tx = translateX(position.x());
		float ty = translateY(position.y());
		
		if(tx < this.viewportWidth && tx > 0 && ty < this.viewportHeight && ty > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public float translateX(float x){
		
		return x - this.getX(); 
	}
	
	public float translateY(float y){
		
		return y - this.getY(); 
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
