package enemy;

import main.Game;
import objectBlueprints.LivingObject;

public class EnemyMain extends LivingObject{
	
	protected int aggrorange;
	
	public EnemyMain(int x, int y) {
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
        if((Math.abs(Game.player.getX() - this.getX()) <= aggrorange)){
        	if (Game.player.getX() < this.getX())
        		this.moveLeft();
        	else if (Game.player.getX() > this.getX())
        		this.moveRight();
        }
        if(Math.random() < 0.01f && this.isBottomSideCollided()){
        	this.jump();
        }
   	}
    
    public void setAggrorange(int aggrorange){
    	this.aggrorange = aggrorange;
    }
    
    /* wird nicht verwendet aktuell

    public void changeDirection(){
    	if(direction== "right"){
    		direction = "left";
    	}
    	else if(direction == "left"){
    		direction = "right";
    	} else{
    		direction = "right";
    	}
    }
    */
}
