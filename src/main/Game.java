package main;

import items.Powerup;

import java.util.LinkedList;
import java.util.List;

import objectBlueprints.PhysicsObject;
import objectBlueprints.StaticObject;

import org.newdawn.slick.*;

import enemy.Enemy;
import player.Player;
import util.Settings;
import weapons.Bullet;
import weapons.Weapon;
import world.World;


public class Game extends BasicGame {

   
    public static Input in;
  
    public static List<StaticObject> toRemoveObjects = new LinkedList<StaticObject>();

 
    public static World gameworld;
    public static Camera cam;
    public static Gui gui;
    public static Settings settings;
    public static Enemy enemy;
    
  
    public static Player player;
    public static List<StaticObject> tile_list = new LinkedList<StaticObject>();
    public static List<Enemy> enemy_list = new LinkedList<Enemy>();
    public static List<Bullet> bullet_list = new LinkedList<Bullet>();
    public static List<Powerup> powerup_list = new LinkedList<Powerup>();
    public static List<Weapon> weapon_list = new LinkedList<Weapon>();
    public static List<PhysicsObject> physics_list = new LinkedList<PhysicsObject>();

    public static int killCount = 0;
    

    public Game(String title) {
        super(title);
    }

    @Override
  	public void init(GameContainer gc) throws SlickException {
        in = gc.getInput();
        
        settings = new Settings();
        gameworld = new World();
        cam = new Camera();
        gui = new Gui();
     
        player = new Player(Settings.playerSprite, 400, 100);
        
        for (int i = 0; i < 0; i += 1) {
        	Enemy enemy = new Enemy(Settings.enemySprite, Enemy.randomX(), 100);
        	enemy_list.add(enemy);
        }
        
        for (int i = 0; i < 1; i += 1) {
            Powerup powerup = new Powerup(Settings.heartSprite,200 , 200, "healthItem");
        	powerup_list.add(powerup);
        }
        
        for (int i = 0; i < 1; i += 1) {
            Weapon weapon = new Weapon(Settings.weaponSprite,250 , 200);
        	weapon_list.add(weapon);
        }
        
       
         
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
    	
    	gameworld.update();
        player.update(delta,in);
       
        for (Enemy enemy : enemy_list) {
            enemy.update(delta);
            if(enemy.getHitbox().intersects(player.getHitbox())){
    			
    			
    			player.receiveDamage(5);

            }
        }
        
        for (Powerup powerup : powerup_list) {
           
            if(powerup.getHitbox().intersects(player.getHitbox())){
    			
    			
    			player.addHealth(powerup.getHealthAmount());
    			Game.removeObject(powerup);

            }
        }
        
        for (Weapon weapon : weapon_list) {
            weapon.update(delta);
            if(weapon.getHitbox().intersects(player.getHitbox())){
            	if(in.isKeyDown(Input.KEY_E)){
                    
            		player.setWeapon(weapon);
            		break;
                	
                }
            	
    			

            }
        }
        
        
        
       
       
        for (Bullet bullet : bullet_list) {
        	bullet.update(delta);
        	
        	for(Enemy enemy : enemy_list){
        		
        		if(bullet.getHitbox().intersects(enemy.getHitbox())){
        			

        			enemy.receiveDamage(bullet.getDamage());
        			enemy.addForce(bullet.getVelocityX()/2, bullet.getVelocityY()/10);
        			
        			bullet.die();
        		}	
        	}
        	  	
        	

        }
        
        for(PhysicsObject physicObj : physics_list){
        	physicObj.update(delta);
        }
        
       
        removeObjects();
       
       
        cam.update(in);
        gui.update();

        
    }
    
    public void render(GameContainer gc, Graphics g) throws SlickException {
    	 
    	cam.render(g);
    	renderBackground(g);
    	renderWorld(g);
    	renderGameObjects(g);
        gui.render(g);
      
       
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game(Settings.TITLE));
        app.setDisplayMode(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT, Settings.FULLSCREEN);
        app.setSmoothDeltas(true);
        app.setTargetFrameRate(Settings.FPS_LIMIT);
        app.setShowFPS(Settings.SHOW_FPS);
        app.start();
    }
    
    public static void removeObject(StaticObject obj){
    	toRemoveObjects.add(obj);
    }

   public void removeObjects(){
	   for (Object o : toRemoveObjects) {
    	   if(o instanceof Bullet){
    		   bullet_list.remove(o);
    	   }else if(o instanceof Enemy){
    		   enemy_list.remove(o);
    	   }
    	   else if(o instanceof Weapon){
    		   weapon_list.remove(o);
    	   }
    	   else if(o instanceof Powerup){
    		   powerup_list.remove(o);
    	   }
	   }
	   
	   toRemoveObjects.clear();
   }
    
    private void renderWorld(Graphics g) {
   	
        gameworld.render(g);
		
	}

	private void renderBackground(Graphics g) {
   	Color bgc = new Color(50,100,200);
   	g.setColor(bgc);
       g.fillRect(0, 0, gameworld.getWidth(), gameworld.getHeight());
		
	}

	private void renderGameObjects(Graphics g) {
   	  
       player.render(g);  // Dafür Animated Mario hinzugefügt
       
       // Gegner rendern
       for (Enemy enemy : enemy_list) {
       	enemy.render(g);
       }
       
       // Bullets rendern
       for (Bullet bullet : bullet_list) {
       	bullet.render(g);
       }
       
    // Powerups rendern
       for (Powerup powerup : powerup_list) {
       	powerup.render(g);
       }
       
       for (Weapon weapon : weapon_list) {
          	weapon.render(g);
          }
       for (PhysicsObject physicsObj : physics_list) {
         	physicsObj.render(g);
       }
		
	}
    
   

}
