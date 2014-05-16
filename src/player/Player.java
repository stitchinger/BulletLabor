package player;


import main.Game;
import objectBlueprints.AdvancedObject;
import objectBlueprints.PhysicsObject;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import util.Settings;import weapons.Weapon;
import static main.Game.bulletSprite;
import static main.Game.bullet_list;



public class Player extends AdvancedObject{

	
	public int walkdirection;
	
	
   
	public Player(Image img, int x, int y, int width, int height) {
        super(img, x, y, width, height); // Hier wird die Konstruktor Methode der Elternklasse aufgerufen und die Werte werden weitergegeben
        
        this.weapon = new Weapon(Game.weaponSprite, this.posX, this.posY, 17, 60);
        this.direction = "right";  	// Blickrichtung
        this.maxWalkSpeed = 6;      // Maximale Laufgeschwindigkeit
        this.acceleration = 0.4f;  	// Beschleunigung beim Laufen
        this.jumpHeight = 9;		// Sprungkraft
    
     
        this.health = 100;			// Gesundheitspunkte
       
    }

	public void update(Input in) throws SlickException{
    	 
    	playerControl(in);
    	
    	super.update();
    	if(this.weapon != null){
			this.weapon.update(this.getX()+this.getWidth()/2, this.getY()+this.getHeight()/2, this.getMouseAngle());
		}
    	
    	
    }
    
    public void playerControl(Input in) throws SlickException{
       this.isRunning = false;
        
        if(in.isKeyDown(Input.KEY_A)){
            
        	this.moveLeft();
        	this.walkdirection = 1;
        }

        
        if(in.isKeyDown(Input.KEY_D)){
            
        	this.moveRight();
        	this.walkdirection = 2;
        }
        
        if (!in.isKeyDown(Input.KEY_D) && !in.isKeyDown(Input.KEY_A))
        	Game.player.walkdirection = 0;
        
        
        if((in.isKeyPressed(Input.KEY_SPACE)||in.isKeyPressed(Input.KEY_W)) && this.jumpCount < 2){
           
        	this.jump();
        }
        
      
        if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
        	
        	this.weapon.trigger(this.getMouseAngle());
        }
        if(in.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)){
        
        }
    }
    
    public float getMouseAngle(){
    	float mouseX = Mouse.getX() + Game.cam.getX();
        float mouseY = ((Settings.WINDOW_HEIGHT - Mouse.getY()) + Game.cam.getY());
        return this.getTargetAngle(mouseX, mouseY);
       
    }
    
   

    public void die(){
    	Game.killCount = 0;
    	super.die();
    }
   
}
