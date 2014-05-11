package player;

import main.Game;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import world.Mover;

import static main.Game.bulletSprite;
import static main.Game.bullet_list;



public class Player extends Mover{
	
	private long timeOfLastShot; 
	private float shotsPerMin;
	public int walkdirection;
	
   
	public Player(Image img, int x, int y, int width, int height) throws SlickException {
        super(img, x, y, width, height); // Hier wird die Konstruktor Methode der Elternklasse aufgerufen und die Werte werden weitergegeben
        
        // dieses sind speziell für den Player gesetzte Variablen
        this.direction = "right";  	// Blickrichtung
        this.maxWalkSpeed = 6;      // Maximale Laufgeschwindigkeit
        this.acceleration = 0.4f;  	// Beschleunigung beim Laufen
        this.jumpHeight = 9;		// Sprungkraft
       	this.shotsPerMin = 600;		// Feuerrate
        this.health = 100;			// Gesundheitspunkte
       
    }

  
    public void update(Input in){
    	 
    	playerControl(in);
    	
    	super.update();
    	
    	
    	
    }
    
    public void playerControl(Input in){
       this.isRunning = false;
        
        if(in.isKeyDown(Input.KEY_A)){
            
        	this.moveLeft();
        	this.walkdirection = 1;
        }
        
        if(in.isKeyDown(Input.KEY_D)){
            
        	this.moveRight();
        	this.walkdirection = 2;
        }
        
        if((in.isKeyPressed(Input.KEY_SPACE)||in.isKeyPressed(Input.KEY_W)) && this.jumpCount < 2){
           
        	this.jump();
        }
        
        /*if(in.isKeyDown(Input.KEY_W)){
        	if((System.currentTimeMillis() - timeOfLastShot) >= ((60 / this.shotsPerMin) * 1000f) / 3){
        		this.directionShot();		
        	}
        }*/
        
        if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
        	if((System.currentTimeMillis() - timeOfLastShot) >= (60 / this.shotsPerMin) * 1000f){
        		this.mouseShot();		
        	}
        }
    }
    
    public void directionShot(){
    	timeOfLastShot = System.currentTimeMillis();
    	bullet_list.add(new Bullet(bulletSprite, (this.posX+this.width/2), (this.posY+this.height/2), 40, 40, direction));
    }
    
    public void mouseShot(){
    	timeOfLastShot = System.currentTimeMillis();
        float mouseX = Mouse.getX() + Game.cam.getX();
        float mouseY = ((Game.getWindowHeight() - Mouse.getY()) + Game.cam.getY());
        float vecX = mouseX - (this.posX+this.width/2);
        float vecY= mouseY - (this.posY+this.height/2);
        float[] normalizedVector = getNormalizedVector2(vecX, vecY);
        bullet_list.add(new Bullet(bulletSprite, (this.posX+this.width/2), (this.posY+this.height/2), 40, 40, vecX, vecY));
        this.addForce(normalizedVector[0]*2 * (-1), 0);
    }

    public void die(){
    	Game.killCount = 0;
    	super.die();
    }
    
    private float[] getNormalizedVector2(float vecX, float vecY){
    	
    	float hypo = (float) Math.sqrt((vecX * vecX) + (vecY * vecY));
        float[] vector2 = new float[2];
    	vector2[0] = (float)(vecX / hypo);
        vector2[1] = (float)(vecY / hypo);
        return vector2;
    }
}
