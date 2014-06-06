package Entity.Events;

import org.newdawn.slick.SlickException;

import Entity.Enemies.Slime;
import Util.Settings;

public class AirAttack extends Event {
	
	public AirAttack(float x, float y, int eventSize) throws SlickException {
		super(x, y, eventSize);
		
		super.setSpriteImage(Settings.eventSprite);
	}

	public void eventTrigger(){
		if(Math.random() < 0.50f){
			super.eventInit();
		}
	}

	public void enemyInit() {
		for(int i = 0; i < enemys.length; i++){
			enemys[i] = new Slime(randomXY(super.getX() - 200, super.getX() + 200), randomXY(50, 100));
		}
	}
	
	public static int randomXY(float low, float high) {
		return (int) (Math.random() * (high - low) + low);
	}
}
