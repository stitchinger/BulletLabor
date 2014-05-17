package player;

import main.Game;
import objectBlueprints.LivingObject;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import util.Settings;
import util.Vector2;
import weapons.Weapon;

public class Player extends LivingObject{
	
	public Player(int x, int y) {
        super(x, y);
        this.setHealth(100);
        
        this.weapon = new Weapon(this.getX(), this.getY());
        this.weapon.setOwner(this);
        this.setSprite(Settings.playerSprite);
    }

	public void update(int delta, Input in){
    	 
    	playerControl(in);
    	
    	super.update(delta);
    	if(this.weapon != null){
			this.weapon.update(delta, this.getX()+this.getWidth()/2, this.getY()+this.getHeight()/2, this.getMouseVector().toDegee());
		}
    	
    	
    }
	
	public void setHealth(int health){
    	super.health = health;
    }
    
    public void playerControl(Input in){
       this.isRunning = false;
        
        if(in.isKeyDown(Input.KEY_A)){
            
        	this.moveLeft();
        
        }
        
        if(in.isKeyPressed(Input.KEY_Q)){
            
        	this.dropWeapon();
        }


        
        if(in.isKeyDown(Input.KEY_D)){
            
        	this.moveRight();
        	
        }
        
     
        if((in.isKeyPressed(Input.KEY_SPACE)||in.isKeyPressed(Input.KEY_W)) && this.jumpCount < 2){
           
        	this.jump();
        }
        
      
        if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
        	
        	if(this.weapon != null){
        		this.weapon.trigger();
        		if(this.weapon.getBulletsLeft() <= 0){
        			this.dropWeapon();
        		}
        	}
        	
        	
        }
        if(in.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)){
        
        }
    }
    
    public Vector2 getMouseVector(){
    	float mouseX = Mouse.getX() + Game.cam.getX();
        float mouseY = ((Settings.WINDOW_HEIGHT - Mouse.getY()) + Game.cam.getY());
        Vector2 mousePosition = new Vector2(mouseX, mouseY);
        return this.getTargetVector(mousePosition);
       
    }
    
    public void die(){
    	
    }
}
