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
    

    
    public void enemyMove(){
    	if(super.direction == "right"){
    		super.moveRight();
        }
        if(super.direction == "left"){
        	super.moveLeft();
        }
    }
    
    public void moveCollision(){
 	   if(super.isRightSideCollided()){
 		   super.setDirection("left");
 	   }
 	   if(super.isLeftSideCollided()){
 		   super.setDirection("right");
 	   }
    }
    
    public void aggroInit(){
 	   if((Math.abs(Game.player.getX() - super.getX()) <= this.aggrorange)){
 		   this.aggroEvent();
        }
    }
    
    public void enemyJump(){
    	if(Math.random() < 0.01f && super.isBottomSideCollided()){
        	super.jump();
        }
    }
    
    /* TO-DO Liste für Enemy System: 
     * Init Methode, welche bzgl. der Kamera aggiert (Gegner bewegt sich erst in Reichweite von Kamera) 
     * Nächster Schritt --> x Position von Kamera sauber bekommen; aktuell "buggy" (NullPointer)
     * Weitere Feinde hinzufügen.
     * Linke Kollision fixen --> aktuell für den Player verbuggt, aber nicht für Enemy
     */

    public void aggroEvent(){
    	//Methode wird in jedem Gegener definiert, welcher ein Event haben soll
    }


    
    public float getAggrorange(){
    	return this.aggrorange;
	}

    public void setAggrorange(float aggrorange){
    	this.aggrorange = aggrorange;
    }
}