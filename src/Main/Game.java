package Main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import utility.Settings;

public class Game {
	
	public static void main(String[] args) throws SlickException {
        
		AppGameContainer app = new AppGameContainer(new GamePanel(Settings.TITLE));
		app.setDisplayMode(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT, Settings.FULLSCREEN);
        app.setSmoothDeltas(true);
        app.setTargetFrameRate(Settings.FPS_LIMIT);
        app.setShowFPS(Settings.SHOW_FPS);
        app.start();
    }
}