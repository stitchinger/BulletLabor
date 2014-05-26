package enemy;

import main.Game;
import objectBlueprints.LivingObject;

public class EnemyMain extends LivingObject{
	
	private int aggrorange;
	private boolean follow = false;
	private int followTimer = 0;
	private boolean unfollow = false;
	private int persistent;
	
	private float xEnd1;
	private float xEnd2;
	
	public EnemyMain(float x, float y, float xEnd1, float xEnd2) {
        super(x, y);
    }
    
    public void update(int delta){
       super.update(delta);
       this.behavior();
    }
    
    public void behavior(){
    	this.enemyMove();
    	this.enemyArea();
    	
        this.enemyAggro();
        this.follow();
        this.followTimer();
        this.unfollow();
        
        this.enemyJump();
   	}
    
    
    public void enemyJump(){
    	if(Math.random() < 0.01f && super.isBottomSideCollided()){
        	super.jump();
        }
    }
    
    public void enemyMove(){
    	if(super.direction == "right"){
    		super.moveRight();
        }
        if(super.direction == "left"){
        	super.moveLeft();
        }
    }
    
    public void enemyAggro(){
    	if((Math.abs(Game.player.getX() - super.getX()) <= this.aggrorange)){
        	if(this.followTimer == 0){
		        this.follow = true;
		    }
        }
    }
    
    public void follow(){
    	if(this.follow == true){
        	this.followTimer++;
	        if (Game.player.getX() < super.getX())
	    		super.moveLeft();
	    	else if (Game.player.getX() > super.getX())
	    		super.moveRight();
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
    	if(super.getX() < this.xEnd1)
        	super.moveRight();	
        if(super.getX() > this.xEnd2)
        	super.moveLeft();
    }
    
    public void changeDirection(){
    	if(super.direction == "right"){
    		super.direction = "left";
    	}
    	else if(super.direction == "left"){
    		super.direction = "right";
    	}
    	else {
    		super.direction = "right";
    	}
    }
    
    //Setter-Methoden
    protected void setAggrorange(int aggrorange){
    	this.aggrorange = aggrorange;
    }
    
    protected void setPersistent(int persistent){
    	this.persistent = persistent;
    }
    
    protected void setXEnd1(float xEnd1){
    	this.xEnd1 = xEnd1;
    }
    
    protected void setXEnd2(float xEnd2){
    	this.xEnd2 = xEnd2;
    }
}
