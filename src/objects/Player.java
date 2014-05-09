package objects;

import main.Game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import static main.Game.bulletSprite;
import static main.Game.bullet_list;



public class Player extends Mover{
	
	private long currentTime; 
	private int shootRate;
	
    // Konstrukor Methode +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public Player(Image img, int x, int y, int width, int height) {
        super(img, x, y, width, height); // Hier wird die Konstruktor Methode der Elternklasse aufgerufen und die Werte werden weitergegeben
        
        // dieses sind speziell für den Player gesetzte Variablen
        this.direction = "right";  	// Blickrichtung
        this.maxWalkSpeed = 6;      // Maximale Laufgeschwindigkeit
        this.acceleration = 0.4f;  	// Beschleunigung beim Laufen
        this.jumpHeight = 9;		// Sprungkraft
        this.gravity = 0.38f;		// Gravitation
        this.shootRate = 100;		// Feuerrate
        this.health = 100;			// Gesundheitspunkte
        
    }

    // Update Methode --- Wird jedes Frame aufgerufen +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void update(Input in){
    	 
    	if (this.health <= 0){
         	this.die();
         	
         	Game.killCount = 0;
         }
    	
    	playerControl(in);
    	super.update();
    	
    }
    
    // Steuerung des Spielers +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void playerControl(Input in){
       this.isRunning = false;
        
        if(in.isKeyDown(Input.KEY_A)){
            
        	this.moveLeft();
        }
        
        if(in.isKeyDown(Input.KEY_D)){
            
        	this.moveRight();
        }
        
        if(in.isKeyPressed(Input.KEY_SPACE) && this.jumpCount < 2){
           
        	this.jump();
        }
        
        if(in.isKeyDown(Input.KEY_E)){
           
        	this.shoot();
        }
        
        if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && ((System.currentTimeMillis() - currentTime) >= shootRate)){
        	
        	this.mouseShot();
        }
    }
    
    // Methode zum schießen +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void shoot(){
    	currentTime = System.currentTimeMillis();
    	bullet_list.add(new Bullet(bulletSprite, this.posX, this.posY, 40, 40, direction));
    }
    
    // Mehode um in Mausrichtung zu schießen ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void mouseShot(){
    	currentTime = System.currentTimeMillis();
        float mouseX = Mouse.getX();
        float mouseY = (Game.getWindowHeight() - Mouse.getY());
        float vecX = mouseX - (this.posX+this.width/2);
        float vecY= mouseY - (this.posY+this.height/2);
        bullet_list.add(new Bullet(bulletSprite, (this.posX+this.width/2), (this.posY+this.height/2), 40, 40, vecX, vecY));
       
       
       
    }

}
