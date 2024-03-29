//Klasse f�r alle Konstanten bzw. Berechnunge f�r Konstanten im Spiel.

package utility;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Settings {
	
	public static String TITLE = "Shoot'em Up";
	public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    public static final boolean FULLSCREEN = false;
    public static final boolean SHOW_FPS = true;
    public static final int FPS_LIMIT = 60;
    public static final boolean DEBUG_MODUS = false;
    
    public static boolean EVENT_DEBUG_MODUS = false;
    
    public static Image playerSprite;
    
    public static Image heartSprite;
    public static Image coinSprite;
    
    public static Image slimeEnemySprite;
    
    public static Image bulletSprite;
    public static Image weaponSprite;
    public static Image rocketSprite;
    public static Image rocketlauncherSprite;
    
    public static Image bg1Sprite;
    public static Image bg2Sprite;
    
    public static Image eventSpriteDebug;
    public static Image eventSprite;
    
    
    public Settings() throws SlickException {
    	
    	playerSprite = new Image("Images/Player/player.png");
    	
    	heartSprite = new Image("Images/Powerups/heart.png");
    	coinSprite = new Image("Images/Powerups/coin.png");
    	
    	slimeEnemySprite = new Image("Images/Enemies/slime.png");
    	
	    bulletSprite = new Image("Images/Weapon/bullet.png");
	    rocketSprite = new Image("Images/Weapon/rocket.png");
	    weaponSprite = new Image("Images/Weapon/weapon.png");
	    rocketlauncherSprite = new Image("Images/Weapon/rocketlauncher.png");
	    
	    bg1Sprite = new Image("Images/Backgrounds/bg4.png");
	    //bg2Sprite = new Image("Images/Backgrounds/bg2.png");
	    
	    eventSpriteDebug = new Image("Map/eventTileDebug.png");
	    eventSprite = new Image("Map/eventTile.png");
    
    }
}