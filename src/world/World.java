package world;

import items.Powerup;

import java.util.LinkedList;
import java.util.List;

import main.Game;
import objectBlueprints.StaticObject;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import player.Player;
import weapons.Bullet;
import weapons.Weapon;
import enemy.Enemy;

public class World {
	private TiledMap tiledMap;
	private int tileSize;
	private float posX;
	private float posY;
	private int width;
	private int height;
	
	private int collisionLayer;
	private int eventLayaer;
	
	
	public World() throws SlickException{
		this.tiledMap = new TiledMap("Map/Level3.tmx");
		this.posX = 0;
		this.posY = 0;
		
		this.tileSize = tiledMap.getTileHeight();
		this.width = tiledMap.getWidth() * this.tileSize;
		this.height = tiledMap.getHeight() * this.tileSize;
		
		this.collisionLayer = tiledMap.getLayerIndex("CollisionLayer");
	}
	
	public void init(){
		for(int y = 0; y < this.height; y++){
			for(int x = 0; x < this.width; x++){
				
			}
		}
	
		
	}
	
	public void update(Input in) throws SlickException{
		
	}
	
	public void render(Graphics g){
		
		
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
