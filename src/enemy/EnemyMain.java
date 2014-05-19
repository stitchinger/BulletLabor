package enemy;

import main.Game;
import objectBlueprints.LivingObject;

public class EnemyMain extends LivingObject{
	
	protected int aggrorange;
	protected boolean follow = false;
	protected int followTimer = 0;
	protected boolean unfollow = false;
	protected int persistent;
	
	protected float xEnd1;
	protected float xEnd2;
	
	public EnemyMain(float x, float y, float xEnd1, float xEnd2) {
        super(x, y);
    }
    
    public void update(int delta){
       super.update(delta);
       
       
       this.behavior(this.aggrorange);
    }
    
    public void behavior(int aggrorange){
    	this.enemyMove();
    	this.enemyArea();
    	
        this.enemyAggro();
        this.follow();
        this.followTimer();
        this.unfollow();
        
        this.enemyJump();
   	}
    
    
    public void enemyJump(){
    	if(Math.random() < 0.01f && this.isBottomSideCollided()){
        	this.jump();
        }
    }
    
    public void enemyMove(){
    	if(direction == "right"){
    		this.moveRight();
        }
        if(direction == "left"){
        	this.moveLeft();
        }
    }
    
    public void enemyAggro(){
    	if((Math.abs(Game.player.getX() - this.getX()) <= aggrorange)){
        	if(this.followTimer == 0){
		        this.follow = true;
		    }
        }
    }
    
    public void follow(){
    	if(this.follow == true){
        	this.followTimer++;
	        if (Game.player.getX() < this.getX())
	    		this.moveLeft();
	    	else if (Game.player.getX() > this.getX())
	    		this.moveRight();
        }
    }
    
    public void unfollow(){
    	if((this.unfollow == true) && (this.followTimer > 0)){
        	this.followTimer -= 2;
        	if(this.followTimer == 0)
        		this.unfollow = false;
    	}
    }
    
    public void followTimer(){
    	if(this.followTimer == this.persistent){
        	this.follow = false;
        	this.changeDirection();
        	this.unfollow = true;
        }
    }
    
    public void enemyArea(){
    	if(this.getX() < this.xEnd1)
        	this.moveRight();	
        if(this.getX() > this.xEnd2)
        	this.moveLeft();
    }
    
    public void changeDirection(){
    	if(this.direction == "right"){
    		this.direction = "left";
    	}
    	else if(this.direction == "left"){
    		this.direction = "right";
    	}
    	else {
    		this.direction = "right";
    	}
    }
    
    //Setter-Methoden
    public void setAggrorange(int aggrorange){
    	this.aggrorange = aggrorange;
    }
    
    public void setPersistent(int persistent){
    	this.persistent = persistent;
    }
    
    public void setXEnd1(float xEnd1){
    	this.xEnd1 = xEnd1;
    }
    
    public void setXEnd2(float xEnd2){
    	this.xEnd2 = xEnd2;
    }
}
