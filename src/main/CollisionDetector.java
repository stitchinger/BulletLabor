package main;

import java.util.LinkedList;
import java.util.List;

import objects.GameObject;

public class CollisionDetector {

	
	public static List<GameObject> gameObjects = new LinkedList<GameObject>();
	
	public CollisionDetector() {
		// TODO Auto-generated constructor stub
	}
	
	public void addObject(GameObject go){
		
		gameObjects.add(go);
		
	}
	
	public void update(){
		
		
	}
	
	

}
