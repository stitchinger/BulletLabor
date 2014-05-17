package enemy;

import org.newdawn.slick.Image;

public class SlimeEnemy extends EnemyMain {
	
	public SlimeEnemy(Image img, int x, int y, int aggrorange, int health) {
		super(img, x, y, aggrorange, health);
    }
	
	public void update(int delta){
		this.behavior(this.aggrorange);
		super.update(delta);
	}
}