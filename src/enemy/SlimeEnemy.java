package enemy;

import util.Settings;

public class SlimeEnemy extends EnemyMain {
	
	public SlimeEnemy(float x, float y, float xEnd1, float xEnd2) {
		super(x, y, xEnd1, xEnd2);
		
		//Setter von EnemyMain
		this.setPersistent(100);
		this.setAggrorange(50);
		this.setXEnd1(xEnd1);
		this.setXEnd2(xEnd2);
		
		//Setter von LivingObjects
		this.setHealth(50);
		this.setAcceleration(0.35f);
		this.setMaxWalkSpeed(6);
		this.setJumpHeight(8);
		this.setJumpCount(2);
		this.setDirection("right");
		this.setMaxHealth(100);
		
		//Setter von StaticObjects
		this.setSpriteImage(Settings.enemySprite);
    }
	
	public void update(int delta){
		super.update(delta);
	}
}