package Entity.Player;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Entity.LivingObject;
import Entity.StaticObject;
import Entity.Enemies.Slime;
import Entity.Player.Weapons.Weapon;
import GameManager.AnimationManager;
import Main.GamePanel;
import Util.Settings;
import Util.Vector2;

public class Player extends LivingObject{
	
	
	
	public Player(int x, int y) throws SlickException {
        super(x, y);
        
        this.weapon = new Weapon(this.getX(), this.getY());
        this.weapon.setOwner(this);
        this.aniMan = new AnimationManager(this);
        
        //Setter von LivingObjects
      	this.setHealth(100);
      	this.setAcceleration(0.35f);
      	this.setMaxWalkSpeed(6);
      	this.setJumpHeight(8);
      	this.setJumpCount(2);
      	this.setDirection("right");
      	this.setMaxHealth(100);
      		
      	//Setter von StaticObjects
      	this.setSpriteImage(Settings.playerSprite);
    }

	
	
	public void update(int delta, Input in){
    	 
		playerControl(in);
    	super.update(delta);
    	if(this.weapon != null){
    		this.weapon.setRotation(this.getMousePosition().toDegee());
			this.weapon.move(this.getCenteredPosition());
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
        		if(this.weapon.getAmmo() <= 0){
        			this.dropWeapon();
        		}
        	}     	
        }
       
    }
    
    public void collidedWith(StaticObject obj){
    	if(obj.getClass() == Slime.class){
    		
    	}
    	System.out.println(obj.getClass());
    }
    
    public Vector2 getMousePosition(){
    	float mouseX = Mouse.getX() + GamePanel.cam.getX();
        float mouseY = ((Settings.WINDOW_HEIGHT - Mouse.getY()) + GamePanel.cam.getY());
        Vector2 mousePosition = new Vector2(mouseX, mouseY);
        return this.getTargetVector(mousePosition);
       
    }
    
    public String getObjectName(){
    	return "player";
    }
    
    
}
