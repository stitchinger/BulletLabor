package main;

import items.Powerup;

import java.util.LinkedList;
import java.util.List;

import objectBlueprints.StaticObject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import weapons.Weapon;
import world.World;
import enemy.SlimeEnemy;

public class GameController {

	private static List<StaticObject> world_objects;
    private static List<StaticObject> toRemoveObjects;
    private static World gameworld;
	
    public GameController() throws SlickException{
    	gameworld = new World();
    	world_objects = new LinkedList<StaticObject>();
    	toRemoveObjects = new LinkedList<StaticObject>();
    	
    	this.initObjects();
    }
    
    public void initObjects(){
    	for (int i = 0; i < 5; i += 1) {
	    	this.addObject(new SlimeEnemy(randomX(300), 200));
	    }
	    for (int i = 0; i < 1; i += 1) {
	    	this.addObject(new Powerup(100, 100, "healthItem"));
	    }
	    for (int i = 0; i < 1; i += 1) {
	    	this.addObject(new Weapon(randomX(300), 200));
	    }
    }
    
	public void update(int delta) {
	    
	    for (StaticObject actor : world_objects) {
        	actor.update(delta);
        }
        removeObjects();
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
	
    public void renderGameObjects(Graphics g) {
   	  	Game.player.render(g);  // Daf�r Animated Mario hinzugef�gt
   	  	for (StaticObject actor : world_objects) {
         	actor.render(g);
   	  	}
	}
    
	public void render (Graphics g){
		renderGameObjects(g);
	}

	public static List<StaticObject> getWorld_objects() {
		return world_objects;
	}

	public static List<StaticObject> getToRemoveObjects() {
		return toRemoveObjects;
	}
}
