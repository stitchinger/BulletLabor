
package Entity.Events;

import org.newdawn.slick.SlickException;

import Entity.Enemies.Slime;

public class Ambush extends Event {
	
	public Ambush(float x, float y, int eventSize) throws SlickException {
		super(x, y, eventSize);
		
		super.eventChance = (int)randomCalc(1, 10);
		super.loadImage();
	}

	public void enemyInit() {
		for(int i = 0; i < enemys.length; i++){
			enemys[i] = new Slime(super.randomCalc(super.getX() - 200, super.getX() + 200), 
					super.randomCalc(super.getY() - 100, super.getY() - 50));
		}
	}
}


/* Event Beschreibung:
 * Soll an einen (friedlichen) Platz pl�tzlich Gegner spawnen lassen.
 * x, y Werte sind aktuell noch sehr eingeschr�nkt wegen dem Level, l�sst sich aber einfach �ndern.
*/