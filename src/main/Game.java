package main;

import java.util.LinkedList;
import java.util.List;

import objects.Bullet;
import objects.Enemy;
import objects.GameObject;
import objects.Player;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;


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
    public static Player player;
    public static Enemy enemy;
    public static List<GameObject> tile_list = new LinkedList<GameObject>();
    public static List<Enemy> enemy_list = new LinkedList<Enemy>();
    public static List<Bullet> bullet_list = new LinkedList<Bullet>();

    public static Image bulletSprite;
    
    public static int killCount = 0;
   

    // Konstruktor +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Game(String title) {
        super(title);
        
    }

    // init ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public void init(GameContainer gc) throws SlickException {
        in = gc.getInput();
       
        Image playerSprite = new Image("img/player.png");
        Image enemySprite = new Image("img/enemy.png");
        Image tileSprite = new Image("img/tile.png");
        
        bulletSprite = new Image("img/bullet.png");

       
        player = new Player(playerSprite, 50, 600, 32, 60);
       
        for (int i = 0; i < 10; i += 1) {
            Enemy enemy = new Enemy(enemySprite, (int)(Math.random()* width), 400, 40, 40);
        	enemy_list.add(enemy);
        }
        
        
        for (int i = 0; i < 1200; i += 50) {
            GameObject tile = new GameObject(tileSprite, i, 700, 50, 50);
        	tile_list.add(tile);
        }
       
        

    }

    // update +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {

        player.update(in);
       
        for (Enemy enemy : enemy_list) {
            enemy.update();
            if(enemy.getHitbox().intersects(player.getHitbox())){
    			
    			
    			player.setDamage(1);

            }
        }
        
        for (GameObject tile : tile_list) {
            tile.update();

        }
       
        for (Bullet bullet : bullet_list) {
        	bullet.update();
        	int index = 0;
        	for(Enemy enemy : enemy_list){
        		
        		if(bullet.getHitbox().intersects(enemy.getHitbox())){
        			
        			bullet.setPosition(100000, 1000000);
        			enemy.setDamage(bullet.getDamage());
        			//enemy = null;
        			//enemy_list.remove(enemy);
        			//break;
        		}
        		index++;
        	}
        	

        }
        
        
        
        
    }

    // render +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void render(GameContainer gc, Graphics g) throws SlickException {
    	
    	// Hintergrund rendern
    	g.setColor(Color.blue);
        g.fillRect(0, 0, width, height);
        
        // Tile rendern
        for (GameObject tile : tile_list) {

            tile.render(g);
        }
        
        // Player rendern
        player.render(g);
        
        // Gegner rendern
        for (Enemy enemy : enemy_list) {

           enemy.render(g);
        }
        
        // Bullets rendern
        for (Bullet bullet : bullet_list) {

           bullet.render(g);
        }
       

        // Text ausgeben
        g.setColor(Color.white);
        g.drawString("Velocity X: " + player.getVelocityX(), 10, 50);
        g.drawString("Velocity Y: " + player.getVelocityY(), 10, 70);
        g.drawString("Vektor X: " + Mouse.getX(), 10, 90);
        g.drawString("Vektor Y: " + (Game.getWindowHeight() - Mouse.getY()), 10, 110);
        g.drawString("Kills: " + killCount, 10, 130);
        g.drawString("Health: "+ (player.getHealth()), 10, 150);
        
      

    }

    // main +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game(title));
        app.setDisplayMode(width, height, fullscreen);
        app.setSmoothDeltas(true);
        app.setTargetFrameRate(fpslimit);
        app.setShowFPS(showFPS);
        app.start();
    }

    // Breite des Fensters +++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static int getWindowWidth() {
        return width;
    }

    // Höhe des Fensters ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static int getWindowHeight() {
        return height;
    }

}
