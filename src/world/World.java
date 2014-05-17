package world;

import main.Game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class World {
	private TiledMap tiledMap;
	private int collisionLayer;
	private int tileSize;
	private float posX;
	private float posY;
	private int width;
	private int height;
	
	public World() throws SlickException{
		tiledMap = new TiledMap("Map/Level3.tmx");
		this.posX = 0;
		this.posY = 0;
		
		this.tileSize = tiledMap.getTileHeight();
		this.collisionLayer = this.tiledMap.getLayerIndex("CollisionLayer");
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
	
	public boolean isCollidableTile(float coordX, float coordY){
		
		int tileX = (int)((coordX)/tileSize);
    	int tileY = (int)((coordY)/tileSize);
    	int collisionIndex = this.tiledMap.getTileId(tileX, tileY, this.collisionLayer);
    	return collisionIndex > 0;
		
	}
}
