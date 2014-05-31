package Entity.Enemies;

import Util.Settings;

public class Slime extends EnemyMain {
	
	public Slime(float x, float y) {
		super(x, y);
		
		//Setter von EnemyMain
		super.setAggrorange(100);
		
		//Setter von LivingObjects
		super.setHealth(50);
		super.setAcceleration(0.35f);
		super.setMaxWalkSpeed(6);
		super.setJumpHeight(8);
		super.setJumpCount(2);
		super.setDirection("right");
		super.setMaxHealth(50);
		
		//Setter von StaticObjects
		super.setSpriteImage(Settings.slimeEnemySprite);
    }
	
	public void update(int delta){
		super.update(delta);
	}
	
	//Methode mit den Komponenten f�r das Verhalten dieser Einheit
	public void behavior(){
    	this.enemyMove();
    	this.moveCollision();
   	}
}