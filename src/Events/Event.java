package Events;

import Entity.StaticObject;
import GameManager.ObjectManager;

public abstract class Event extends StaticObject{
	
	public Event(float x, float y){
		super(x, y);
	}
	
	public abstract void eventTrigger();
		//ist der eigentliche Inhalt des Events; beschreibt zb wie die Gegner dann spawnen sollen
		//kann auch durch weitere Methoden beschrieben werden
	
	public void deleteEvent(){
		ObjectManager.removeObject(this);
	}
}
