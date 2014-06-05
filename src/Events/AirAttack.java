package Events;

import Util.Settings;

public class AirAttack extends Event {

	public AirAttack(float x, float y) {
		super(x, y);
		
		super.setSpriteImage(Settings.eventSprite);
	}

	public void eventTrigger() {
		//Gegner fliegen zu den Spieler runter und bewegen sich in seine Richtung
	}

}
