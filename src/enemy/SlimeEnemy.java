package enemy;

import util.Settings;

public class SlimeEnemy extends EnemyMain {
	
	public SlimeEnemy(float x, float y, float xEnd1, float xEnd2) {
		super(x, y, xEnd1, xEnd2);
		
		//Setter von EnemyMain
		super.setPersistent(100);
		super.setAggrorange(50);
		super.setXEnd1(xEnd1);
		super.setXEnd2(xEnd2);
		
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
}