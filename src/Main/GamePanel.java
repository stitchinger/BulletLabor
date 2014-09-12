package Main;


import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import utility.Settings;
import world.World;

public class GamePanel extends BasicGame {

	public static Input in;
    public static World gameworld;
   
    public static Settings settings;
   
   
    public GamePanel(String title) {
        super(title);
    }

    @Override
  	public void init(GameContainer gc) throws SlickException {
        in = gc.getInput();
        settings = new Settings();
        gameworld = new World(new TiledMap("Map/Level2.tmx"));
  
   
    }
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
    	gameworld.update();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
   
    	gameworld.render(g);
   
    }
}
