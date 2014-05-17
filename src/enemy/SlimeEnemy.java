package enemy;

import org.newdawn.slick.Image;

import util.Settings;

public class SlimeEnemy extends EnemyMain {
	
	public SlimeEnemy(int x, int y) {
		super(x, y);
		
		this.aggrorange = 150;
		this.setHealth(50);
		this.setSprite(Settings.enemySprite);
    }
	
	public void update(int delta){
		this.behavior(this.aggrorange);
		super.update(delta);
	}
}