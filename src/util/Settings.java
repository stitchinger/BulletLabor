//Klasse für alle Konstanten bzw. Berechnunge für Konstanten im Spiel.

package util;

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
    
    public static Image playerSprite;
    public static Image enemySprite;
    public static Image heartSprite;
    public static Image bulletSprite;
    public static Image weaponSprite;
    public static Image shellSprite;
    
    public Settings() throws SlickException {
    	
    	playerSprite = new Image("Images/Player/player.png");
	    enemySprite = new Image("Images/Enemies/enemy.png");
	    heartSprite = new Image("Images/Powerups/heart.png");
	    bulletSprite = new Image("Images/Weapon/bullet.png");
	    weaponSprite = new Image("Images/Weapon/weapon.png");
	    //weaponSprite = new Image("Images/Weapon/shell.png");
    
    }
}