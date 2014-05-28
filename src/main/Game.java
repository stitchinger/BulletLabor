package main;

import items.Powerup;

import java.util.LinkedList;
import java.util.List;


import objectBlueprints.PhysicsObject;
import objectBlueprints.StaticObject;

import org.newdawn.slick.*;

import enemy.SlimeEnemy;
import player.Player;
import util.Settings;
import util.Vector2;
import weapons.Bullet;
import weapons.Weapon;
import world.World;


public class Game extends BasicGame {

   
    public static Input in;
  
    public static World gameworld;
    public static Camera cam;
    public static Gui gui;
    public static Settings settings;
    
    public static Player player;
    public static List<StaticObject> world_objects = new LinkedList<StaticObject>();
    public static List<StaticObject> toRemoveObjects = new LinkedList<StaticObject>();
  
  
    public static float deltaTime;
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
     
        player = new Player(400, 100);
        
        for (int i = 0; i < 5; i += 1) {
        	
        	SlimeEnemy enemy = new SlimeEnemy(Game.randomX(), 200, (Game.randomX() - 100), (Game.randomX() + 100));
        	
        	world_objects.add(enemy);
        }
        
        for (int i = 0; i < 1; i += 1) {
        	
        	Powerup powerup = new Powerup(100, 100, "healthItem");
        
        	world_objects.add(powerup);
        }
        
        for (int i = 0; i < 1; i += 1) {
        	
        	Weapon weapon = new Weapon(randomX(), 200);
        	
        	world_objects.add(weapon);
        }
        
       
         
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
    	gameworld.update();
        player.update(delta,in);
       
        for (StaticObject actor : world_objects) {
        	actor.update(delta);
            
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
    	   world_objects.remove(o);
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
       
       for (StaticObject actor : world_objects) {
         	actor.render(g);
       }
       
     
		
	}
    
	
	
	public static int randomX() {

        int minDistance = 300;
        int randomX = (int) (Math.random()* gameworld.getWidth());
        randomX = Math.min(Math.max(randomX, minDistance), gameworld.getWidth()-minDistance);
        return randomX;
	}

}
