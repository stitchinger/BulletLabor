package objects;

import main.Game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import static main.Game.bulletSprite;
import static main.Game.bullet_list;



public class Player extends Mover{
	
	private long timeOfLastShot; 
	private float shotsPerMin;
	
    // Konstrukor Methode +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public Player(Image img, int x, int y, int width, int height) {
        super(img, x, y, width, height); // Hier wird die Konstruktor Methode der Elternklasse aufgerufen und die Werte werden weitergegeben
        
        // dieses sind speziell für den Player gesetzte Variablen
        this.direction = "right";  	// Blickrichtung
        this.maxWalkSpeed = 6;      // Maximale Laufgeschwindigkeit
        this.acceleration = 0.4f;  	// Beschleunigung beim Laufen
        this.jumpHeight = 9;		// Sprungkraft
       	this.shotsPerMin = 600;		// Feuerrate
        this.health = 100;			// Gesundheitspunkte
        //(shotsPerMinute / 60) * 1000
    }

    // Update Methode --- Wird jedes Frame aufgerufen +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void update(Input in){
    	 
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
        
        if(in.isKeyDown(Input.KEY_W)){
        	if((System.currentTimeMillis() - timeOfLastShot) >= ((60 / this.shotsPerMin) * 1000f) / 3){
        		this.directionShot();		
        	}
        }
        
        if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
        	if((System.currentTimeMillis() - timeOfLastShot) >= (60 / this.shotsPerMin) * 1000f){
        		this.mouseShot();		
        	}
        }
    }
    
    // Methode zum schießen in Blickrichtung +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void directionShot(){
    	timeOfLastShot = System.currentTimeMillis();
    	bullet_list.add(new Bullet(bulletSprite, (this.posX+this.width/2), (this.posY+this.height/2), 40, 40, direction));
    }
    
    // Mehode um in Mausrichtung zu schießen ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void mouseShot(){
    	timeOfLastShot = System.currentTimeMillis();
        float mouseX = Mouse.getX();
        float mouseY = (Game.getWindowHeight() - Mouse.getY());
        float vecX = mouseX - (this.posX+this.width/2);
        float vecY= mouseY - (this.posY+this.height/2);
        bullet_list.add(new Bullet(bulletSprite, (this.posX+this.width/2), (this.posY+this.height/2), 40, 40, vecX, vecY));
       
       
       
    }

    public void die(){
    	Game.killCount =0;
    	super.die();
    }
}
