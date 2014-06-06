package Entity.Events;

import Util.Settings;
import Entity.StaticObject;
import Entity.Enemies.Enemy;
import GameManager.ObjectManager;

public abstract class Event extends StaticObject{
	
	private int eventSize;
	private float eventChance;
	
	protected Enemy[] enemys;
	
	public Event(float x, float y, int eventSize){
		super(x, y);
		
		this.eventChance = 1;
		this.eventSize = eventSize;
		enemys = new Enemy [this.eventSize];
	}
	
	public void eventInit(){
		this.enemyInit();
		this.enemyMove();
		this.objectManagerInit();
		this.removeEvent();
	}
	
	public abstract void enemyInit();
	
	public void enemyMove(){
		for(int i = 0; i < enemys.length; i++){
			enemys[i].moveInit();
		}
	}
	
	public void objectManagerInit(){
		for(int i = 0; i < this.enemys.length; i++){
			ObjectManager.getWorld_objects().add(enemys[i]);
		}
	}
	
	public void removeEvent(){
		ObjectManager.removeObject(this);
	}
	
	
	public void loadImage(){
		if(Settings.EVENT_DEBUG_MODUS == true){
			super.setSpriteImage(Settings.eventSpriteDebug);
		}
		else{
			super.setSpriteImage(Settings.eventSprite); //dafür soll ein durchsichtiges Sprite rein
		}
	}
	
	public void eventTrigger(){
		if(randomCalc(1, 2) == this.eventChance && (Math.abs(super.getX() - ObjectManager.getObject("player").getX()) > 30)){
			this.eventInit();
		}
	}
	
	public static int randomCalc(float low, float high) {
		return (int) (Math.random() * (high - low) + low);
	}
	
	public String getObjectName(){
		return "event";
	}
	//Nächster Schritt: Weitere Events erstellen
}