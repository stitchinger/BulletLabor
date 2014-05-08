package objects;

import org.newdawn.slick.Image;


public class Mover extends GameObject{

    
    // Diese Variablen haben alle Objekte gemeinsam, die sich bewegen können
	protected String direction;
    protected float velocityX;
    protected float velocityY;
    protected float maxSpeed;
    protected float acceleration;
    protected float jumpHeight;
    protected float gravity;
    protected int jumpCount;
    
    protected boolean isRunning;
    protected boolean isInAir;
   
    
    
    // Konstruktor Methode +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Mover(Image img, int x, int y, int width, int height) {
        super(img, x, y, width, height);
        this.velocityX = 0;
        this.velocityY = 0;
        this.jumpCount = 0;
    }

    // Update Methode +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void update(){
    	// is on ground ?
        if(this.isLanded()){
            this.isInAir = false;
            this.velocityY = 0;
            this.jumpCount = 0;
        }
        if(isInAir){
            this.fall();
        }
       
        // Maximale Geschwindigkeit begrenzen
        if(this.velocityX >= this.maxSpeed){
            this.velocityX = this.maxSpeed;
        } else if(this.velocityX <= maxSpeed * (-1)){
            this.velocityX = this.maxSpeed * (-1);
        }
        
        this.posX += this.velocityX;        // Auswirkung der horizontalen Beschleunigung auf die X-Position
        this.posY += this.velocityY;		// Auswirkung der horizontalen Beschleunigung auf die X-Position
        
        if(!isRunning){  
        	// 
        	this.velocityX = (this.velocityX * 0.2f);
            if(this.velocityX <= 0.1 && this.velocityX >= -0.1){
                this.velocityX = 0;
            }
        }
        
       
        hitbox.setLocation(this.posX, this.posY); // Position der Hitbox anpassen
    	
    }
    
    // Horizontale Beschleunigung +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public double getVelocityX(){
    	
    	return this.velocityX;
    }
    
    // Vertikale Beschleunigung +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public double getVelocityY(){
    	
    	return this.velocityY;
    }
    
    // Nach rechts gehen +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void moveRight(){
            this.isRunning = true;
            this.velocityX += this.acceleration;
            this.direction = "right";
        
    }
    
    // Nach Links gehen +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void moveLeft(){
            this.isRunning = true;
            this.velocityX -= this.acceleration;
            this.direction = "left";
    }
    
    // Fall Methode ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void fall(){
        this.velocityY += this.gravity;
    }
   
    // Sprung Methode ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void jump(){
        this.velocityY = this.jumpHeight * (-1);
        this.isInAir = true;
        this.jumpCount++;
    }
    
    // ist gelandet? Methode ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public boolean isLanded(){
    	
    	return this.posY + this.height >= 690 && this.velocityY > 0;
    }
   
    
   
    
   
    
    
    
   
    
    
}
