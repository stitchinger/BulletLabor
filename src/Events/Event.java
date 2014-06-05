package Events;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import Entity.LivingObject;

public abstract class Event extends LivingObject{
	
	public Event(float x, float y) throws SlickException{
		super(x, y);
	}
	
	public void update(int delta){
		super.update(delta);
		this.collisionEvent(null);
	}
	
	public abstract int findEvent(TiledMap tiledMap);
		//soll Index des nächsten Events liefern
	
	public boolean collisionEvent(TiledMap tiledMap){
		//soll abfragen ob der Spieler mit einem Event Tile collidiert
		return false;
	}
	
	public abstract void eventTrigger();
		//ist der eigentliche Inhalt des Events; beschreibt zb wie die Gegner dann spawnen sollen
		//kann auch durch weitere Methoden beschrieben werden
	
}
