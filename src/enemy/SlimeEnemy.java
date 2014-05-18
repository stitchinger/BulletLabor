package enemy;

import util.Settings;

public class SlimeEnemy extends EnemyMain {
	
	public SlimeEnemy(float x, float y) {
		super(x, y);
		
		this.setAggrorange(150);
		this.setHealth(50);
		this.setSprite(Settings.enemySprite);
    }
	
	public void update(int delta){
		super.update(delta);
	}
}