package Main;

import org.newdawn.slick.*;

import Util.Settings;
import World.Background;
import World.World;
import Entity.Player.Player;
import GameState.Camera;
import GameState.Gui;

public class GamePanel extends BasicGame {

	public static Input in;
    public static World gameworld;
    public static Camera cam;
    public static Gui gui;
    public static Settings settings;
    public static GameController controller;
    public static Background background;
    public static Player player;
    

    public GamePanel(String title) {
        super(title);
    }

    @Override
  	public void init(GameContainer gc) throws SlickException {
        in = gc.getInput();
        settings = new Settings();
        gameworld = new World();
        cam = new Camera();
        gui = new Gui();
        background = new Background();
        controller = new GameController();
        player = new Player(400, 100);
    }
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
    	gameworld.update();
        player.update(delta, in);
        controller.update(delta);
        cam.update(in);
        background.update();
        gui.update();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
    	cam.render(g);
    	background.renderBackground(g);
    	gameworld.render(g);
    	controller.renderGameObjects(g);
        gui.render(g);
    }
}
