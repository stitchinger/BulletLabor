package Entity.Player.Weapons;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Entity.AdvancedObject;
import Entity.PhysicsObject;
import GameManager.ObjectManager;
import GameManager.SoundManager;
import Util.Settings;
import Util.Vector2;

public class Weapon extends PhysicsObject {

	protected AdvancedObject owner;

	protected String id;
	protected float rotation;
	protected Bullet typeOfBullet;
	protected int ammo;
	protected int shotsPerMinute;
	protected float power;
	protected float recoil;
	protected long timeOfLastShot;
	protected float spreadRange;
	protected float rotationInertia;
	protected float positionInertia;
	
	
	public Weapon(float x, float y) {
		super(x, y);
		this.typeOfBullet = new Bullet(0,0);
		this.power = 30;
		this.recoil = 0.05f;
		this.rotation = 0;
		this.ammo = 100;
		this.shotsPerMinute = 600;
		this.spreadRange = 6;
		this.rotationInertia = 0.2f;
		this.positionInertia = 0.49f;
		
		this.setSpriteImage(Settings.weaponSprite);
	
	}
	
	public void render(Graphics g){
		
		Image sprite;
		if(rotation < 0){
			sprite = this.getSpriteImage().getFlippedCopy(true, false);
		
		} 
		else{
			sprite = this.getSpriteImage();
		}
		sprite.setRotation(rotation); 
		sprite.draw(this.getX(), this.getY());
    	
    	if (Settings.DEBUG_MODUS) {
          	g.setColor(Color.red);
          	g.draw(this.getHitbox());
          	g.setColor(Color.white); 
          	g.drawString("Ammo: "+ this.ammo, this.getX(), this.getY()+ this.height );
          }
	}
	
	public void move(Vector2 position){
		Vector2 distance = position.sub(this.getCenteredPosition());
		Vector2 inertia = new Vector2(1, this.positionInertia);
		
		this.velocity.set(distance.mult(inertia));
		this.position.add(this.velocity);
		this.hitbox.setLocation(this.position.x(), this.position.y());
	}
	
	public void setRotation(float rotation){
		
		float rotationDistance = rotation - this.rotation;
		
	
		this.rotation = this.rotation + rotationDistance * this.rotationInertia;
	}
	
	public void trigger(){
		if(this.canShoot()){
			this.angleShot();
		}
	}
	
	public void angleShot(){
    	this.timeOfLastShot = System.currentTimeMillis();
    	this.ammo--;
    	//GameManager.SoundManager.weaponSound.play();
    	SoundManager.play("weapon");
    	
    	
    	Vector2 spreadRotation = new Vector2(this.getSpreadRotation()).normalize();
    
    	Bullet bullet = this.getBullet((this.getX()+this.width/2), (this.getY()+this.height/2));
    
    	bullet.addForce(spreadRotation.mult(this.power));
    	this.owner.addForce(spreadRotation.mult(-this.recoil));
    	ObjectManager.getWorld_objects().add(bullet);
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
		boolean notEmpty = this.ammo > 0;
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

	public int getAmmo(){
		return this.ammo;
	}
	
	public void setOwner(AdvancedObject owner){
		this.owner = owner;
	}
	
	public String getObjectName() {
		return "weapon";
	}
	
	public Bullet getBullet(float x, float y){
		if(this.id.equals("Rocketlauncher")){
			return new Rocket(x,y);
		}else if(this.id.equals("M4")){
			return new Bullet(x,y);
		}
		else
			return null;
		
	}
}