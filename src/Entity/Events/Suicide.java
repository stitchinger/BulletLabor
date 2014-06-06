package Entity.Events;

import org.newdawn.slick.SlickException;

import Entity.Enemies.Slime;
import GameManager.ObjectManager;

public class Suicide extends Event {
	
	public Suicide(float x, float y, int eventSize) throws SlickException {
		super(x, y, eventSize);
	}

	public void enemyInit() {
		for(int i = 0; i < enemys.length; i++){
			enemys[i] = new Slime(super.randomCalc(super.getX() - 200, super.getX() + 200), 
					super.randomCalc(super.getY() - 100, super.getY() - 50));
		}
	}
	
	public void enemySuicide(){
		for(int i = 0; i < enemys.length; i++){
			if(Math.abs(super.getX() - ObjectManager.getObject("player").getX()) <= 100){
				//ruft suicide() auf
			}
		}
	}
	
	public void suicide(){ //muss vermutlich in Gegner verschoben werden bzw. 
						   //in eine spezifische Gegner Klasse (bombs.java oder so)
		
		//Animation erstellen, welche zeigt dass der Gegner gleich hochgeht
		//Gegner kann sich nicht mehr bewegen gleichzeitig
		
		int i = 100;
		while(1 < i){ i--; }
		
		//Animation erstellen 
		//Bereich abfragen und ggf. Spieler Schaden geben, sollte er zu nah gewesen sein
		//Gegner löschen
	}
}


/* Event Beschreibung:
 * Soll Einheiten spawnen, die sich selbst zerstören, wenn sie den Spieler zu nahe kommen.
*/
