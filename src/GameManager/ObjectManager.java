package GameManager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import World.World;
import Entity.StaticObject;
import Entity.Enemies.*;
import Entity.Events.*;
import Entity.Items.*;
import Entity.Player.Player;
import Entity.Player.Weapons.Weapon;
import Main.GamePanel;

public class ObjectManager {

	private static ArrayList<StaticObject> world_objects;
    private static ArrayList<StaticObject> toRemoveObjects;
    private static World gameworld;
	
    public ObjectManager() throws SlickException{
    	gameworld = new World();
    	world_objects = new ArrayList<StaticObject>();
    	toRemoveObjects = new ArrayList<StaticObject>();
    	
    	this.initObjects();
    }
    
    
    public void initObjects() throws SlickException{
    	
    	this.addObject(new Player(400, 100));
    	
    	for (int i = 0; i < 5; i += 1) {
	    	this.addObject(new Slime(randomX(300), 200));
	    }
	    for (int i = 0; i < 1; i += 1) {
	    	this.addObject(new Heart(100, 100));
	    }
	    for (int i = 0; i < 1; i += 1) {
	    	this.addObject(new Coin(325, 325));
	    }
	    for (int i = 0; i < 1; i += 1) {
	    	this.addObject(new Weapon(randomX(300), 200));
	    }
	    
	    this.addObject(new AirAttack(275, 325, 3));
	    this.addObject(new Ambush(350, 1700, 3));
    }
    
	public void update(int delta) {
	    
		for (int i = 0; i< world_objects.size();i++) {
        	StaticObject obj = world_objects.get(i);
        	
        	if(obj.getObjectName().equalsIgnoreCase(getObject("player").getObjectName())){
        		obj.update(delta, GamePanel.in);
        	}
        	else if(obj.getObjectName().equalsIgnoreCase(getObject("event").getObjectName())){
        		obj.update(GamePanel.in);
        	}
        	else {
        		obj.update(delta);
        	}
        }
		
		for (int i = 0; i< world_objects.size();i++) {
        	
			StaticObject eventObject = world_objects.get(i);
			
			if(world_objects.get(i).getObjectName().equalsIgnoreCase("event")){
        		eventObject = world_objects.get(i);
        		
        		StaticObject player = getObject("player");
                
            	if(eventObject.getHitbox().intersects(player.getHitbox())){
            		eventObject.eventTrigger();
                }
        	}
        	
        	
		}
	    
	    for (int i = 0; i< world_objects.size();i++) {
        	StaticObject one = world_objects.get(i);
        	for (int j = i + 1; j < world_objects.size(); j++) {
            	StaticObject two = world_objects.get(j);
            	if(one.getHitbox().intersects(two.getHitbox())){
            		one.collidedWith(two);
            		two.collidedWith(one);
            	}
            }
        }
	    
        this.removeObjects();
	}
	
	public void render(Graphics g){
		for (StaticObject obj : world_objects) {
         	obj.render(g);
   	  	}
	}
	
	
	public static int randomX(int minDistance) {
        int randomX = (int) (Math.random()* gameworld.getWidth());
        randomX = Math.min(Math.max(randomX, minDistance), gameworld.getWidth()-minDistance);
        return randomX;
	}
	
	public void addObject(StaticObject obj){
    	world_objects.add(obj);
    }

    public void removeObjects(){
    	for (Object obj : toRemoveObjects) {
    	   world_objects.remove(obj);
    	}
    	toRemoveObjects.clear();
    }
    
    public static void removeObject(StaticObject obj){
    	toRemoveObjects.add(obj);
    }
    
    public static int findObject(String objectName){
    	for(int i = 0; i < world_objects.size(); i++){
    		if(world_objects.get(i).getObjectName().equalsIgnoreCase(objectName)){
    			return i;
    		}
    	}
    	return -1;
    }
    
    public static StaticObject getObject(String objectName){
    	if(findObject(objectName) != -1){
    		return world_objects.get(findObject(objectName));
    	}
		return null;
    }    

	public static ArrayList<StaticObject> getWorld_objects() {
		return world_objects;
	}

	public static ArrayList<StaticObject> getToRemoveObjects() {
		return toRemoveObjects;
	}
}
