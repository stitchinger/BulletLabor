package Entity.Enemies;

import Util.Settings;

public class Slime extends EnemyMain {
	
	public Slime(float x, float y) {
		super(x, y);
		
		//Setter von LivingObjects
		super.setHealth(50);
		super.setAcceleration(0.2f);
		super.setMaxWalkSpeed(3);
		super.setDirection("right");
		super.setMaxHealth(50);
		
		//Setter von StaticObjects
		super.setSpriteImage(Settings.slimeEnemySprite);
    }
	
	public void update(int delta){
		super.update(delta);
	}
	
	//Methode mit den Komponenten für das Verhalten dieser Einheit
	public void behavior(){
    	super.enemyMove();
    	super.moveCollision();
   	}
}