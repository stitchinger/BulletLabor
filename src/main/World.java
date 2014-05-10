package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class World {
	private TiledMap level1;
	
	public World() throws SlickException{
		level1 = new TiledMap("res/Level1.tmx");
		
	}
	
	public void update(){
		
		
	}
	
	public void render(Graphics g){
		level1.render(0, 0);
		
	}
	
	public TiledMap getTiledMap(){
		
		return this.level1;
	}
}
