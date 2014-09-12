package manager;

import gamestate.Camera;
import items.*;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import enemies.*;
import entities.Creature;
import entities.Rigidbody;
import entities.Rigidbody;
import player.Player;
import world.Controller;
import world.World;
import Main.GamePanel;

public class EntityManager {

	private static ArrayList<Rigidbody> world_objects;
    public static ArrayList<Rigidbody> toRemoveObjects;
    private static World gameworld;
    private Controller controller;
	
	
    public EntityManager() throws SlickException{
    	
    	world_objects = new ArrayList<Rigidbody>();
    	toRemoveObjects = new ArrayList<Rigidbody>();
    	
    	this.initObjects();
    }
    
    public void initObjects() throws SlickException{
    	Creature player = new Player(800,100);
    	this.addObject(player);
    	controller = new Controller(player);
    	
    	for (int i = 0; i < 1; i += 1) {
	    	this.addObject(new Slime(600, 200));
	    }
	   
    	for (int i = 0; i < 1; i += 1) {
	    	this.addObject(new Weapon(300, 200));
	    }
	}
    
	public void update() {
		controller.update(GamePanel.in);
		
		for (int i = 0; i< world_objects.size();i++) {
        	Rigidbody obj = world_objects.get(i);
        	obj.update();
        	
        	for (int j = i + 1; j< world_objects.size();j++) {
            	Rigidbody obj2 = world_objects.get(j);
            	if(obj.getHitbox().intersects(obj2.getHitbox())){
            		System.out.println(obj + ", " + obj2);
            	
            	}	
            }
        	
        }
		
	}
	
	public void render(Graphics g){
		
		for (Rigidbody obj : world_objects) {
         	obj.render(g);
   	  	}
		
	}
	
	public void addObject(Rigidbody obj){
    	world_objects.add(obj);
    }

    public void removeObjectsFromList(){
    	for (Rigidbody obj : toRemoveObjects) {
    	   world_objects.remove(obj);
    	   
    	}
    	toRemoveObjects.clear();
    }
    
    public static void removeObject(Rigidbody obj){
    	toRemoveObjects.add(obj);
    }
    
    public static int findObject(String objectName){
    	for(int i = 0; i < world_objects.size(); i++){
    		if(true){
    			return i;
    		}
    	}
    	return -1;
    }
    
    public static Rigidbody getObject(String objectName){
    	if(findObject(objectName) != -1){
    		return world_objects.get(findObject(objectName));
    	}
		return null;
    }    

	public static ArrayList<Rigidbody> getWorld_objects() {
		return world_objects;
	}

	public static ArrayList<Rigidbody> getToRemoveObjects() {
		return toRemoveObjects;
	}
}
