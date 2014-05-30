package enemy;

import main.Game;
import objectBlueprints.LivingObject;

public class EnemyMain extends LivingObject{
	
	private float aggrorange;
	
	public EnemyMain(float x, float y) {
        super(x, y);
    }
    
    public void update(int delta){
       super.update(delta);
       this.behavior();
    }
    
    public void behavior(){
    	this.enemyMove();
    	this.moveCollision();
    	this.aggroInit();
    	this.enemyJump();
   	}
    
    
    public void enemyJump(){
    	if(Math.random() < 0.01f && super.isBottomSideCollided()){
        	super.jump();
        }
    }
    
   public void moveCollision(){
	   if(super.isRightSideCollided())
		   super.setDirection("left");
	   if(super.isLeftSideCollided())
		   super.setDirection("right");
   }
    
    public void enemyMove(){
    	if(super.direction == "right"){
    		super.moveRight();
        }
        if(super.direction == "left"){
        	super.moveLeft();
        }
    }
    
    public void changeDirection(){
    	if(super.direction == "right"){
    		super.direction = "left";
    	}
    	else if(super.direction == "left"){
    		super.direction = "right";
    	}
    }
    
    public void aggroInit(){
    	if((Math.abs(Game.player.getX() - super.getX()) <= this.aggrorange)){
    		this.aggroEvent();
        }
    }

    public void aggroEvent(){
    	//Methode wird in jedem Gegener definiert
    }

	public float getAggrorange() {
		return aggrorange;
	}

	public void setAggrorange(float aggrorange) {
		this.aggrorange = aggrorange;
	}
    
}
