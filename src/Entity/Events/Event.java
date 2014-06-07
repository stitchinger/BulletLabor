package Entity.Events;

import org.newdawn.slick.Input;

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
		
		this.loadImage();
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
	
	
	//Debug Modus Ausdruck Start 
	//Kontrolle �ber die Taste "3" - vereinfachte ingame Tests m�glich
	
	public void update(Input in){
		this.debugControl(in);
		this.loadImage();
	}
	
	private void debugControl(Input in){
		 if(in.isKeyPressed(Input.KEY_3)){
			 if(Settings.EVENT_DEBUG_MODUS == true){
				 Settings.EVENT_DEBUG_MODUS = false;
			 }
			 else {
				 Settings.EVENT_DEBUG_MODUS = true;
			 }
		 }
	}
	
	public void loadImage(){
		if(Settings.EVENT_DEBUG_MODUS == true){
			super.setSpriteImage(Settings.eventSpriteDebug);
		}
		else{
			super.setSpriteImage(Settings.eventSprite);
		}
	}
	//Debug Modus Ausdruck Ende
	
	
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
}

/* N�chster Schritt:
 * Weitere Events erstellen;
 * Animationen beim Spawnen zur Verf�gung stellen?
 * Boss Event hinzuf�gen --> bekommt ebenfalls eine komplett eigene Klasse
 */