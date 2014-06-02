package GameManager;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import World.World;
import Entity.StaticObject;
import Entity.Enemies.*;
import Entity.Items.*;
import Entity.Player.Weapons.Weapon;

public class ObjectManager {

	private static List<StaticObject> world_objects;
    private static List<StaticObject> toRemoveObjects;
    private static World gameworld;
	
    public ObjectManager() throws SlickException{
    	gameworld = new World();
    	world_objects = new LinkedList<StaticObject>();
    	toRemoveObjects = new LinkedList<StaticObject>();
    	
    	this.initObjects();
    }
    
    
    public void initObjects(){
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
    }
    
	public void update(int delta) {
	    
	    for (StaticObject obj : world_objects) {
        	obj.update(delta);
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

	public static List<StaticObject> getWorld_objects() {
		return world_objects;
	}

	public static List<StaticObject> getToRemoveObjects() {
		return toRemoveObjects;
	}
}
