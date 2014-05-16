package main;

import items.Powerup;

import java.util.LinkedList;
import java.util.List;

import objectBlueprints.StaticObject;

import org.newdawn.slick.*;

import enemy.Enemy;
import player.Player;
import util.Settings;
import weapons.Bullet;
import world.World;


public class Game extends BasicGame {

   
    public static Input in;
    public static List<Bullet> toRemove = new LinkedList<Bullet>();

    // Game Objekte ++++++++++++++++++++++
    public static World gameworld;
    public static Camera cam;
    public static Gui gui;
    
    // Entitis
    public static Player player;
    public static List<StaticObject> tile_list = new LinkedList<StaticObject>();
    public static List<Enemy> enemy_list = new LinkedList<Enemy>();
    public static List<Bullet> bullet_list = new LinkedList<Bullet>();
    public static List<Powerup> powerup_list = new LinkedList<Powerup>();

    public static Image bulletSprite;
    public static Image weaponSprite;
    public static int killCount = 0;
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Game(String title) {
        super(title);
    }

    @Override
  	public void init(GameContainer gc) throws SlickException {
        in = gc.getInput();
       
        // Bilder initialisieren
        Image playerSprite = new Image("Images/Player/player.png");
        Image enemySprite = new Image("Images/Enemies/enemy.png");
        Image heartSprite = new Image("Images/Powerups/heart.png");
        bulletSprite = new Image("Images/Weapon/bullet.png");
        weaponSprite = new Image("Images/Weapon/weapon.png");

        gameworld = new World();
        cam = new Camera();
        gui = new Gui();
     
        player = new Player(playerSprite, 400, 100);
       
        for (int i = 0; i < 2; i += 1) {
            int minDistance = 300;
            int randomX = (int) (Math.random()* gameworld.getWidth());
            randomX = Math.min(Math.max(randomX, minDistance), gameworld.getWidth()-minDistance);
        	Enemy enemy = new Enemy(enemySprite,randomX, 100);
        	enemy_list.add(enemy);
        }
        
        
        
        for (int i = 0; i < 1; i += 1) {
            Powerup powerup = new Powerup(heartSprite,200 , 200, "healthItem");
        	powerup_list.add(powerup);
        }
        
        
        
        
        
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
    	
    	gameworld.update();
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

   public void removeObjects(){
	   for (Object o : toRemove) {
    	   bullet_list.remove(o);
	   }
	   toRemove.clear();
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
		
	}
    
   

}
