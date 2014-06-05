package Entity.Events;

import Entity.StaticObject;
import GameManager.ObjectManager;

public abstract class Event extends StaticObject{
	
	protected int enemyWaves;
	
	public Event(float x, float y, int enemyWaves){
		super(x, y);
		this.enemyWaves = enemyWaves;
	}
	
	public void update(int delta){
		super.update(delta);
		this.stopEvent();
	}
	
	public abstract void eventTrigger();
	
	/* TO-DO Liste f�r Event System: 
     * N�chster Schritt --> Events und zugeh�rige Methoden hinzuf�gen
     */
	
	public int getEnemyWaves() {
		return this.enemyWaves;
	}
	
	public void removeEvent(){
		ObjectManager.removeObject(this);
	}
	
	public void stopEvent(){
		if(this.enemyWaves == 0){
			this.removeEvent();
		}
	}
	
	public String getObjectName(){
		return "event";
	}
}