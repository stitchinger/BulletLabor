package world;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class World {
	private TiledMap level1;
	private int tileSize;
	private float posX;
	private float posY;
	
	public World() throws SlickException{
		level1 = new TiledMap("Map/Level1.tmx");
		this.posX = 0;
		this.posY = 0;
		this.tileSize = level1.getTileHeight();
	}
	
	public void update(){
	
		
		
	}
	
	public void render(Graphics g){
		level1.render((int)this.posX, (int)this.posY);
		
	}
	
	public TiledMap getTiledMap(){
		
		return this.level1;
	}
	
	public int getTileSize(){
		
		return this.tileSize;
	}
}
