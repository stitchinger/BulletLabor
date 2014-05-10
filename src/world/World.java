package world;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class World {
	private TiledMap tiledMap;
	private int tileSize;
	private float posX;
	private float posY;
	private int width;
	private int height;
	
	public World() throws SlickException{
		tiledMap = new TiledMap("Map/Level2.tmx");
		this.posX = 0;
		this.posY = 0;
		
		this.tileSize = tiledMap.getTileHeight();
		this.width = tiledMap.getWidth() * this.tileSize;
		this.height = tiledMap.getHeight() * this.tileSize;
	}
	
	public void update(){
	
		
		
	}
	
	public void render(Graphics g){
		tiledMap.render((int)this.posX, (int)this.posY);
		
	}
	
	public TiledMap getTiledMap(){
		
		return this.tiledMap;
	}
	
	public int getTileSize(){
		
		return this.tileSize;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
}
