package main;

import java.util.LinkedList;
import java.util.List;


import org.newdawn.slick.*;

import enemy.Enemy;

import player.Bullet;
import player.Player;

import world.AnimatedObject;
import world.GameObject;
import world.World;


public class Game extends BasicGame {

    // Fenster Einstellungen  +++++++++++++
	static int width = 1200;
    static int height = 800;
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static String title = "Shoot'em Up";
    static int fpslimit = 60;
    public static final boolean showHitbox = true;
    
    // Input Instanz ++++++++++++++++++++
    public static Input in;

    // Game Objekte ++++++++++++++++++++++
    public static World level1;
    public static Camera cam;
    public static Player player;
    public static AnimatedObject aniOb;
    public static List<GameObject> tile_list = new LinkedList<GameObject>();
    public static List<Enemy> enemy_list = new LinkedList<Enemy>();
    public static List<Bullet> bullet_list = new LinkedList<Bullet>();

    public static Image bulletSprite;
    public static int killCount = 0;
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Game(String title) {
        super(title);
    }

    @Override
  	public void init(GameContainer gc) throws SlickException {
        in = gc.getInput();
       
        Image playerSprite = new Image("Images/Player/player.png");
        Image enemySprite = new Image("Images/Enemies/enemy.png");
        Image tileSprite = new Image("Map/tile.png");
        
        bulletSprite = new Image("Images/Player/bullet.png");

        level1 = new World();
        cam = new Camera();
        aniOb = new AnimatedObject();
        player = new Player(playerSprite, 400, 0, 32, 60);
       
        for (int i = 0; i < 5; i += 1) {
            Enemy enemy = new Enemy(enemySprite, (int)(Math.random()* width), 400, 40, 34);
        	enemy_list.add(enemy);
        }
        
        
        for (int i = 0; i < 1200; i += 50) {
            GameObject tile = new GameObject(tileSprite, i, 700, 50, 50);
        	tile_list.add(tile);
        }
        
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
    	level1.update();
        player.update(in);
       
        for (Enemy enemy : enemy_list) {
            enemy.update();
            if(enemy.getHitbox().intersects(player.getHitbox())){
    			
    			
    			player.receiveDamage(1);

            }
        }
        
       
       
        for (Bullet bullet : bullet_list) {
        	bullet.update();
        	
        	for(Enemy enemy : enemy_list){
        		
        		if(bullet.getHitbox().intersects(enemy.getHitbox())){
        			
        			bullet.setPosition(100000, 1000000);
        			enemy.receiveDamage(bullet.getDamage());
        			enemy.addForce(bullet.getVelocityX()/2, bullet.getVelocityY()/10);
        			
        		}
        		
        	}
        	
        	

        }
        
        aniOb.update(delta);
        cam.update();
        
        
        
        
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
    	cam.render(g);
    	renderBackground(g);
    	renderWorld(g);
    	renderGameObjects(g);
        renderGui(g);
       
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game(title));
        app.setDisplayMode(width, height, fullscreen);
        app.setSmoothDeltas(true);
        app.setTargetFrameRate(fpslimit);
        app.setShowFPS(showFPS);
        app.start();
    }

    public static int getWindowWidth() {
        return width;
    }

    public static int getWindowHeight() {
        return height;
    }
    
    private void renderWorld(Graphics g) {
   	
        level1.render(g);
		
	}

	private void renderBackground(Graphics g) {
   	
   	g.setColor(Color.blue);
       g.fillRect(0, 0, width, height);
		
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
		
	}
    
    public void renderGui(Graphics g){
    	  
        g.setColor(Color.white);
        g.drawString("Velocity X: " + player.getVelocityX(), 10, 50);
        g.drawString("Velocity Y: " + player.getVelocityY(), 10, 70);
       // g.drawString("Type: " + player.type, 10, 70);
      
        g.drawString("Kills: " + killCount, Game.getWindowWidth() - 100, Game.getWindowHeight() - 40);
        g.drawString("Health: "+ (player.getHealth()), 10, Game.getWindowHeight() - 40);
        
        
        aniOb.render(player.getX()-20, player.getY()-5);
    	
    }

}
