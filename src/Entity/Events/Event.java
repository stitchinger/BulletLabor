package Entity.Events;

import Entity.StaticObject;
import Entity.Enemies.Enemy;
import GameManager.ObjectManager;

public abstract class Event extends StaticObject{
	
	protected int eventSize;
	protected Enemy[] enemys;
	
	public Event(float x, float y, int eventSize){
		super(x, y);
		
		this.eventSize = eventSize;
		enemys = new Enemy [this.eventSize];
	}
	
	public void eventInit(){
		this.enemyInit();
		this.enemyMove();
		this.objectManagerInit();
		this.removeEvent();
	}
	
	public void objectManagerInit(){
		for(int i = 0; i < this.enemys.length; i++){
			ObjectManager.getWorld_objects().add(enemys[i]);
		}
	}
	
	public void enemyMove(){
		for(int i = 0; i < enemys.length; i++){
			enemys[i].moveInit();
		}
	}
	
	public abstract void enemyInit();
	
	public abstract void eventTrigger();
	
	public void removeEvent(){
		ObjectManager.removeObject(this);
	}
	
	public String getObjectName(){
		return "event";
	}
	
	//Nächster Schritt: Event System mit Tilemap verbinden. Heißt man erstellt nur noch ein Tile
	//auf der Karte und dazu wird dann automatisch ein Event Tile generiert + Event Objekt
}