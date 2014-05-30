package enemy;

import util.Settings;

public class SlimeEnemy extends EnemyMain {
	
	public SlimeEnemy(float x, float y) {
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
		super.setMaxHealth(100);
		
		//Setter von StaticObjects
		super.setSpriteImage(Settings.enemySprite);
    }
	
	public void update(int delta){
		super.update(delta);
	}
	
    public void aggroEvent(){
    	//To-DO, Methode bzw. Event ausdenken welches die Slime Unit machen könnte
    }
}