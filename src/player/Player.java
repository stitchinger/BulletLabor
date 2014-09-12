package player;



import items.Weapon;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import enemies.Enemy;
import enemies.Slime;
import entities.Creature;

import utility.Settings;
import utility.Vector2;
import world.World;
import Main.GamePanel;

public class Player extends Creature{
	
	
	
	public Player(int x, int y) throws SlickException {
        super(x, y);
       
        this.health = 100;
        this.acceleration = 0.35f;
        this.maxWalkSpeed = 6;
        this.jumpHeight = 8;
        this.jumpCount = 2;
        this.maxJumpCount = 2;
        this.direction = "right";
        this.maxHealth = 150;
       
        this.setSpriteImage(Settings.playerSprite);
    }

	public void update(){
    	 super.update();
    }
    
	public String getObjectName(){
    	return "player";
    }
    
    
}
