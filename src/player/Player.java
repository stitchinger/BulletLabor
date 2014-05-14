package player;

import main.Game;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import entity.MovingObject;
import static main.Game.bulletSprite;
import static main.Game.bullet_list;



public class Player extends MovingObject{

	private float shotsPerMin;
	public int walkdirection;
	
	
   
	public Player(Image img, int x, int y, int width, int height) {
        super(img, x, y, width, height); // Hier wird die Konstruktor Methode der Elternklasse aufgerufen und die Werte werden weitergegeben
        
        // dieses sind speziell f�r den Player gesetzte Variablen
        this.direction = "right";  	// Blickrichtung
        this.maxWalkSpeed = 6;      // Maximale Laufgeschwindigkeit
        this.acceleration = 0.4f;  	// Beschleunigung beim Laufen
        this.jumpHeight = 9;		// Sprungkraft
       	// die shotsPerMin Variable sollten wir fr�her oder sp�ter von hier entfernen und die Bullet/Waffen verschieben
        this.shotsPerMin = 550;		// Feuerrate 
        this.health = 100;			// Gesundheitspunkte
       
    }

	public void update(Input in) throws SlickException{
    	 
    	playerControl(in);
    	
    	super.update();
    	
    	
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
        	if((System.currentTimeMillis() - timeOfLastShot) >= (60 / this.shotsPerMin) * 1000f){
        		
        		angleShot(this.getMouseAngle(), false);
        	}
        }
        if(in.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)){
        	if((System.currentTimeMillis() - timeOfLastShot) >= (60 / this.shotsPerMin) * 1000f){
         		
          		this.clusterShot( false);
          	}
        }
    }
    
    public float getMouseAngle(){
    	float mouseX = Mouse.getX() + Game.cam.getX();
        float mouseY = ((Game.getWindowHeight() - Mouse.getY()) + Game.cam.getY());
        return this.getTargetAngle(mouseX, mouseY);
       
    }
    
    public void clusterShot( boolean enemyBullet){
    	timeOfLastShot = System.currentTimeMillis();
    	
    	for(int i = 0; i < 20; i++){
    		
    		
    		bullet_list.add(new Bullet(bulletSprite, (this.posX+this.width/2), (this.posY+this.height/2), 20, 24, (float)(-3.1f + 6/20*i), false));
    	}
    }

    public void die(){
    	Game.killCount = 0;
    	super.die();
    }
   
}
