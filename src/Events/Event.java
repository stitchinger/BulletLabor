package Events;

import Entity.StaticObject;
import GameManager.ObjectManager;

public abstract class Event extends StaticObject{
	
	protected int amountEnemies;
	
	public Event(float x, float y, int amountEnemies){
		super(x, y);
		this.amountEnemies = amountEnemies;
	}
	
	public abstract void eventTrigger();
		//ist der eigentliche Inhalt des Events; beschreibt zb wie die Gegner dann spawnen sollen
		//kann auch durch weitere Methoden beschrieben werden
	
	/* TO-DO Liste für Event System: 
     * Nächster Schritt --> Events und zugehörige Methoden hinzufügen
     */
	
	public int getAmountEnemies() {
		return this.amountEnemies;
	}
	
	public void removeEvent(){
		ObjectManager.removeObject(this);
	}
	
	public String getObjectName(){
		return "event";
	}
}