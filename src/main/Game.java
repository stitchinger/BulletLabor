package main;

import org.newdawn.slick.*;
import player.Player;
import util.Settings;
import world.World;

public class Game extends BasicGame {

	public static Input in;
  
    public static World gameworld;
    public static Camera cam;
    public static Gui gui;
    public static Settings settings;
    public static GameController controller;
    
    public static Player player;
  
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
        
        controller = new GameController();
        player = new Player(400, 100);
        
    }
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
    	gameworld.update();
        player.update(delta,in);
       
        controller.update(delta);
       
        cam.update(in);
        gui.update();
    }
    
    public void render(GameContainer gc, Graphics g) throws SlickException {
    	cam.render(g);
    	renderBackground(g);
    	renderWorld(g);
    	controller.renderGameObjects(g);
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
    
    private void renderWorld(Graphics g) {
    	gameworld.render(g);
	}

	private void renderBackground(Graphics g) {
   	Color bgc = new Color(50,100,200);
   	g.setColor(bgc);
       g.fillRect(0, 0, gameworld.getWidth(), gameworld.getHeight());
		
	}
}
