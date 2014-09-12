package world;

import org.newdawn.slick.Input;


import entities.Creature;

public class Controller{

	private Creature player;
	
	public Controller(Creature creature){
		this.player = creature;
	}
	
	public void update(Input in){
			if(in.isKeyDown(Input.KEY_A)){
	            this.player.moveLeft();
	        }
	        if(in.isKeyPressed(Input.KEY_E)){
	            
	        }
	        if(in.isKeyDown(Input.KEY_D)){
	            this.player.moveRight();
	        }
	        if((in.isKeyPressed(Input.KEY_SPACE)||in.isKeyPressed(Input.KEY_W)) ){ //&& this.player.jumpCount < 2
	           this.player.jump();
	   		
	        }
	}
}
