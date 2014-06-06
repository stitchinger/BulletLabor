package Entity.Events;

import Entity.StaticObject;
import Entity.Enemies.Enemy;
import GameManager.ObjectManager;

public abstract class Event extends StaticObject{
	
	protected int eventSize;
	protected Enemy[] enemy_list;
	
	protected int smallEvent;
	protected int mediumEvent;
	protected int bigEvent;
	
	public Event(float x, float y, int eventSize){
		super(x, y);
		
		this.eventSize = eventSize;
		enemy_list = new Enemy [this.eventSize];
	}
	
	public void eventInit(){
		this.enemyInit();
		this.enemyMove();
		this.objectManagerInit();
		this.removeEvent();
	}
	
	public void objectManagerInit(){
		for(int i = 0; i < this.enemy_list.length; i++){
			ObjectManager.getWorld_objects().add(enemy_list[i]);
		}
	}
	
	public void enemyMove(){
		for(int i = 0; i < enemy_list.length; i++){
			enemy_list[i].moveInit();
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
}