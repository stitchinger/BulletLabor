package player;

import main.Game;
import objectBlueprints.AdvancedObject;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import util.Settings;
import util.Vector2;
import weapons.Weapon;

public class Player extends AdvancedObject{

	public Player(Image img, int x, int y) {
        super(img, x, y); 
        
        this.weapon = new Weapon(Game.weaponSprite, this.getX(), this.getY());
        this.weapon.setOwner(this);
    }

	public void update(int delta, Input in){
    	 
    	playerControl(in);
    	
    	super.update(delta);
    	if(this.weapon != null){
			this.weapon.update(delta, this.getX()+this.getWidth()/2, this.getY()+this.getHeight()/2, this.getMouseVector().toDegee());
		}
    	
    	
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
