package Main;

import org.newdawn.slick.*;

import Util.Settings;
import World.Background;
import World.World;
import GameManager.ObjectManager;
import GameManager.SoundManager;
import GameState.Camera;
import GameState.Gui;

public class GamePanel extends BasicGame {

	public static Input in;
    public static World gameworld;
    public static Camera cam;
    public static Gui gui;
    public static Settings settings;
    public static SoundManager soundmanager;
    public static ObjectManager entitymanager;
    public static Background background;
    

    public GamePanel(String title) {
        super(title);
    }

    @Override
  	public void init(GameContainer gc) throws SlickException {
        in = gc.getInput();
        settings = new Settings();
        soundmanager = new SoundManager();
        gameworld = new World();
       
       
        background = new Background();
        entitymanager = new ObjectManager();
        cam = new Camera(entitymanager.getObject("player"));
        gui = new Gui();
    }
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
    	gameworld.update();
        entitymanager.update(delta);
        cam.update(in);
        background.update();
        gui.update();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
    	cam.render(g);
    	background.render(g);
    	gameworld.render(g);
    	entitymanager.render(g);
        gui.render(g);
    }
}
