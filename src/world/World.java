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
	
	public static Player player;
	public static List<StaticObject> object_list = new LinkedList<StaticObject>();
	public static List<Enemy> enemy_list = new LinkedList<Enemy>();
	public static List<Bullet> bullet_list = new LinkedList<Bullet>();
	public static List<Powerup> powerup_list = new LinkedList<Powerup>();
	public static List<Weapon> weapon_list = new LinkedList<Weapon>();

	
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
		this.initObjects();
		
	}
	
	public void update(Input in) throws SlickException{
		this.updateObjects(in);
	}
	
	public void render(Graphics g){
		this.renderObjects(g);
		
	}
	
	public void initObjects(){
		player = new Player(Game.playerSprite, 400, 100);
	       
        for (int i = 0; i < 2; i += 1) {
            int minDistance = 300;
            int randomX = (int) (Math.random()* this.getWidth());
            randomX = Math.min(Math.max(randomX, minDistance), this.getWidth()-minDistance);
        	Enemy enemy = new Enemy(Game.enemySprite,randomX, 100);
        	enemy_list.add(enemy);
        }
        
        
        
        for (int i = 0; i < 1; i += 1) {
            Powerup powerup = new Powerup(Game.heartSprite,200 , 200, "healthItem");
        	powerup_list.add(powerup);
        }
        
        for (int i = 0; i < 1; i += 1) {
        	Weapon weapon = new Weapon(Game.weaponSprite,200 , 200);
        	weapon_list.add(weapon);
        }
        
	}
	
	public void updateObjects(Input in) throws SlickException{

        player.update(in);
       
        for (Enemy enemy : enemy_list) {
            enemy.update();
            if(enemy.getHitbox().intersects(player.getHitbox())){
    			
    			
    			player.receiveDamage(5);

            }
        }
        
        for (Powerup powerup : powerup_list) {
           
            if(powerup.getHitbox().intersects(player.getHitbox())){
    			
    			powerup.setX(10000);
    			powerup.setY(10000);
    			player.addHealth(powerup.getHealthAmount());

            }
        }
        
        for (Weapon weapon : weapon_list) {
            
            if(weapon.getHitbox().intersects(player.getHitbox())){
    			
    			
    			player.setWeapon(weapon);

            }
        }
        
       
       
        for (Bullet bullet : bullet_list) {
        	bullet.update();
        	
        	for(Enemy enemy : enemy_list){
        		
        		if(bullet.getHitbox().intersects(enemy.getHitbox())){
        			

        			enemy.receiveDamage(bullet.getDamage());
        			enemy.addForce(bullet.getVelocityX()/2, bullet.getVelocityY()/10);
        			
        			bullet.die();
        		}	
        	}
        	  	
        	

        }
        
       
        
	}	

	public void renderObjects(Graphics g){
			player.render(g);  
	       
			for (Enemy enemy : enemy_list) {
	    	   enemy.render(g);
	       }
	       
	     
	       for (Bullet bullet : bullet_list) {
	       		bullet.render(g);
	       }
	       
	       for (Powerup powerup : powerup_list) {
	    	   powerup.render(g);
	       }
	       
	       for (Weapon weapon : weapon_list) {
	          	weapon.render(g);
	       }
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
	
	public void addObject(StaticObject obj){
		object_list.add(obj);
	}
	
}
