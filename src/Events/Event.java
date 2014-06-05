package Events;

import Entity.StaticObject;
import GameManager.ObjectManager;

public abstract class Event extends StaticObject{
	
	private int amountEnemies;
	
	public Event(float x, float y, int amountEnemies){
		super(x, y);
		this.amountEnemies = amountEnemies;
	}
	
	public abstract void eventTrigger();
		//ist der eigentliche Inhalt des Events; beschreibt zb wie die Gegner dann spawnen sollen
		//kann auch durch weitere Methoden beschrieben werden
	
	public void removeEvent(){
		ObjectManager.removeObject(this);
	}
	
	/* TO-DO Liste für Event System: 
     * Nächster Schritt --> Events und zugehörige Methoden hinzufügen
     */
	
	public String getObjectName(){
		return "event";
	}

	public int getAmountEnemies() {
		return this.amountEnemies;
	}

	public void setAmountEnemies(int amount) {
		this.amountEnemies -= amount;
	}
}
