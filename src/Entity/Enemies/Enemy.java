package Entity.Enemies;

import Entity.LivingObject;
import GameManager.ObjectManager;

public abstract class Enemy extends LivingObject{
	
	private float aggrorange;
	
	public Enemy(float x, float y) {
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
 	   if((Math.abs(ObjectManager.getObject("player").getX() - super.getX()) <= this.aggrorange)){
 		   this.aggroEvent();
        }
    }
    
    public void enemyJump(){
    	if(Math.random() < 0.01f && super.isBottomSideCollided()){
        	super.jump();
        }
    }
    
    public void moveInit(){
    	if(super.getX() > ObjectManager.getObject("player").getX()){
			super.moveLeft();
		}
		else if(super.getX() < ObjectManager.getObject("player").getX()){
			super.moveRight();
		}
    }
    
    /* TO-DO Liste f�r Enemy System: 
     * Init Methode, welche bzgl. der Kamera aggiert (Gegner bewegt sich erst in Reichweite von Kamera) 
     * N�chster Schritt --> x Position von Kamera sauber bekommen; aktuell "buggy" (NullPointer)
     * Weitere Feinde hinzuf�gen.
     * Linke Kollision fixen --> aktuell f�r den Player verbuggt, aber nicht f�r Enemy
     */

    public abstract void aggroEvent();
    	//Methode wird in jedem Gegener definiert, welcher ein Event haben soll

    public float getAggrorange(){
    	return this.aggrorange;
	}

    public void setAggrorange(float aggrorange){
    	this.aggrorange = aggrorange;
    }
}