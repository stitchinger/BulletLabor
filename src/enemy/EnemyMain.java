package enemy;

import main.Game;
import objectBlueprints.LivingObject;

public class EnemyMain extends LivingObject{
	
	protected int aggrorange;
	protected boolean follow = false;
	protected int followTimer = 0;
	protected boolean unfollow = false;
	protected int persistent;
	
	public EnemyMain(float x, float y) {
        super(x, y);
    }
    
    public void update(int delta){
       this.behavior(this.aggrorange);
       super.update(delta);
    }
    
    public void behavior(int aggrorange){
    	if(direction == "right"){
    		this.moveRight();
        }
        if(direction == "left"){
        	this.moveLeft();
        }
        
        //Vorläufige Experimentelle Funktion bzgl. einem Aggro System
        if((Math.abs(Game.player.getX() - this.getX()) <= aggrorange)){
        	if(this.followTimer == 0){
        		this.follow = true;
        	}
        }
        
        if(this.follow == true){
        	this.followTimer++;
	        if (Game.player.getX() < this.getX())
	    		this.moveLeft();
	    	else if (Game.player.getX() > this.getX())
	    		this.moveRight();
        }
        
        if(this.followTimer == this.persistent){
        	this.follow = false;
        	this.changeDirection();
        	this.unfollow = true;
        }
        
        if((this.unfollow == true) && (this.followTimer > 0)){
        	this.followTimer -= 2;
        	if(this.followTimer == 0)
        		this.unfollow = false;
    	}
        //Experimentelle Funktion Ende
        	
        if(Math.random() < 0.01f && this.isBottomSideCollided()){
        	this.jump();
        }
        
        /* TO-DO
         * System, womit die Gegner umdrehen, wenn diese gegen eine Wand laufen
         * Weitere LivingObject Instanzvariablen lokal verfügbar machen + ggf. anpassen
            * 	protected float acceleration;
			*	protected int maxWalkSpeed;
			*	protected int jumpHeight;
			*	protected int jumpCount;
			*	protected String direction;
        */
   	}
    
    public void setAggrorange(int aggrorange){
    	this.aggrorange = aggrorange;
    }
    
    public void setPersistent(int persistent){
    	this.persistent = persistent;
    }
    
    public void changeDirection(){
    	if(this.direction == "right"){
    		this.direction = "left";
    	}
    	else if(this.direction == "left"){
    		this.direction = "right";
    	}
    	else{
    		this.direction = "right";
    	}
    }
}
