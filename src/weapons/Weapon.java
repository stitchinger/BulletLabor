package weapons;

import static main.Game.bullet_list;
import main.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import objectBlueprints.AdvancedObject;
import objectBlueprints.PhysicsObject;
import objectBlueprints.StaticObject;
import util.Settings;
import util.Vector2;

public class Weapon extends PhysicsObject {

	private AdvancedObject owner;
	private float rotation;
	private int bulletsLeft;
	private int shotsPerMinute;
	private float power;
	private float recoil;
	private long timeOfLastShot;
	private boolean equipped;
	private float spreadRange;
	private float inertia;
	
	public Weapon(Image sprite, float x, float y) {
		super(sprite, x, y);
		
		this.power = 30;
		this.recoil = 0.05f;
		this.rotation = 0;
		this.bulletsLeft = 100;
		this.shotsPerMinute = 600;
		this.spreadRange = 5;
		this.inertia = 0.2f;
	
	}
	
	public void update(int delta, float x, float y, float rotation){
		if(this.owner != null){
			
			this.setX(x - this.width/2);
			this.setY(y - this.height/2);
			this.rotation += (rotation - this.rotation)*this.inertia;
			this.hitbox.setLocation(this.getX(), this.getY());
			
		}else{
			super.update(delta);
		}
		
		
	}
	
	public void render(Graphics g){
		
		Image sprite;
		if(rotation < 0){
			sprite = this.getImage().getFlippedCopy(true, false);
		
		} else{
			sprite = this.getImage();
			
		}
		sprite.setRotation(rotation); 
		sprite.draw(this.getX(), this.getY());
    	
    	if (Settings.DEBUG_MODUS) {
          	g.setColor(Color.red);
          	g.draw(this.getHitbox());
          	g.setColor(Color.white); 
          	g.drawString("Ammo: "+ this.bulletsLeft, this.getX(), this.getY()+ this.height );
       
          }
	}
	
	public void trigger(){
		if(this.canShoot()){
			this.angleShot();
		}
	}
	
	public void angleShot(){
    	this.timeOfLastShot = System.currentTimeMillis();
    	this.bulletsLeft--;
    	Vector2 spreadRotation = new Vector2(this.getSpreadRotation()).normalize();
    	Bullet bullet = new Bullet(Settings.bulletSprite, (this.getX()+this.width/2), (this.getY()+this.height/2));
    	
    	
    	bullet.addForce(spreadRotation.mult(this.power));
    	this.owner.addForce(spreadRotation.mult(-this.recoil));
    	
    	Game.bullet_list.add(bullet);
    	
    	
    }
	
	public float getSpreadRotation(){
	 
	   float spreadRotation = (this.rotation-this.spreadRange/2) + (int)(Math.random() * (((this.rotation+this.spreadRange/2) - (this.rotation-this.spreadRange/2)) + 1));
	    if(this.rotation < -180){
	    	this.rotation = 180 - (-this.rotation - 180);
	    } else if(this.rotation > 180){
	        this.rotation = -180 + (this.rotation - 180);
	    }
	    return spreadRotation;
	         
	}

	public boolean canShoot(){
		boolean inFireRate = (System.currentTimeMillis() - this.timeOfLastShot) >= (60f / this.shotsPerMinute) * 1000f;
		boolean notEmpty = this.bulletsLeft > 0;
		return inFireRate && notEmpty;
	}
	
	public void addSpreadingToStartRotation(){
	    	 this.rotation = (float) ((rotation - (spreadRange)) + Math.random()*(spreadRange));
	         if(this.rotation < -180){
	         	this.rotation = 180 - (-this.rotation - 180);
	         } else if(this.rotation > 180){
	         	this.rotation = -180 + (this.rotation - 180);
	         }
	    }
	
	public void drop(){
		
		
		this.owner = null;
		
		
    	
	}

	public int getBulletsLeft(){
		return this.bulletsLeft;
	}
	
	public void setOwner(AdvancedObject owner){
		this.owner = owner;
	}
}
