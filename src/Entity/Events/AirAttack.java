
package Entity.Events;

import org.newdawn.slick.SlickException;

import Entity.Enemies.Slime;

public class AirAttack extends Event {
	
	public AirAttack(float x, float y, int eventSize) throws SlickException {
		super(x, y, eventSize);
		
		super.loadImage();
	}

	public void enemyInit() {
		for(int i = 0; i < enemys.length; i++){
			enemys[i] = new Slime(super.randomCalc(super.getX() - 200, super.getX() + 200), 
					super.randomCalc(super.getY() - 300, super.getY() - 150));
		}
	}
}


/* Event Beschreibung:
 * L�sst es an einer Stelle pl�tzlich Gegner regnen.
 * x, y Werte sind aktuell noch sehr eingeschr�nkt wegen dem Level, l�sst sich aber einfach �ndern.
*/