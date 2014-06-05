package Events;

import org.newdawn.slick.SlickException;

import Entity.Enemies.Slime;
import GameManager.ObjectManager;
import Util.Settings;

public class AirAttack extends Event {
	
	public AirAttack(float x, float y, int amountEnemies) throws SlickException {
		super(x, y, amountEnemies);
		
		super.setSpriteImage(Settings.eventSprite);
	}
	
	public void update(int delta){
		super.update(delta);
	}

	public void eventTrigger() {
		if((Math.random() < 0.50f && (Math.abs(super.getX() - ObjectManager.getObject("player").getX()) > 30))){
			Slime slime = new Slime(250, 100);
			ObjectManager.getWorld_objects().add(slime);
			
			if(super.getX() > ObjectManager.getObject("player").getX()){
				slime.moveLeft();
			}
			else {
				slime.moveRight();
			}
			
			super.enemyWaves--;
		}
	}
}
