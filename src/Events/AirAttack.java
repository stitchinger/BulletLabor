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

	public void eventTrigger() {
		if((Math.random() < 0.03f) && (super.getAmountEnemies() > 0)){
			Slime slime = new Slime(300, 100);
			ObjectManager.getWorld_objects().add(slime);
			slime.moveLeft();
			
			super.setAmountEnemies(1);
		}
		
		if(super.getAmountEnemies() == 0){
			super.removeEvent();
		}
		//Gegner fliegen zu den Spieler runter und bewegen sich in seine Richtung
	}
}
