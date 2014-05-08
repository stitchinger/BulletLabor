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
	// Neuer Kommentar
    // Konstrukor Methode +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public Player(Image img, int x, int y, int width, int height) {
        super(img, x, y, width, height); // Hier wird die Konstruktor Methode der Elternklasse aufgerufen und die Werte werden weitergereicht
        
        // dieses sind speziell für den Player gesetzte Variablen
        this.direction = "right";
        this.maxSpeed = 8;
        this.acceleration = 0.4f;
        this.jumpHeight = 12;
        this.gravity = 0.35f;
        this.isRunning = false;
        this.isInAir = true;
        this.shootRate = 100;
        this.health = 100;
        
        
        
    }

    // Update Methode --- Wird jedes Frame aufgerufen +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void update(Input in){
    	 if (this.health <= 0){
         	this.die();
         	this.isInAir = true;
         	Game.killCount = 0;
         }
    	playerControl(in);
    	super.update();
    	
    }
    
    // Steuerung des Spielers +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void playerControl(Input in){
        isRunning = false;
        
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
        float xpos = Mouse.getX();
        float ypos = (Game.getWindowHeight() - Mouse.getY());
        xpos = xpos - this.posX;
        ypos = ypos - this.posY;
        bullet_list.add(new Bullet(bulletSprite, this.posX, this.posY, 40, 40, xpos, ypos));
       
        float hypo = (float) Math.sqrt((xpos * xpos) + (ypos * ypos));
        
        xpos =  ((xpos / hypo) * 5);
        ypos =  ((ypos / hypo) * 5);
        
        this.velocityX -= xpos;
        this.velocityY -= ypos;
       
    }

}
