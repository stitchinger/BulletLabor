package weapons;

import static main.Game.bulletSprite;
//import static main.Game.gameworld.bullet_list;
import main.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import objectBlueprints.StaticObject;
import util.Settings;
import util.Vector2;

public class Weapon extends StaticObject {

	private float rotation;
	private int bulletsLeft;
	private int shotsPerMinute;
	private float power;
	private long timeOfLastShot;
	private boolean equipped;
	private float spreadRange;
	
	public Weapon(Image sprite, float x, float y) {
		super(sprite, x, y);
		
		this.power = 30;
		this.rotation = 0;
		this.bulletsLeft = 100;
		this.shotsPerMinute = 600;
		this.spreadRange = 5;
	
	}
	
	public void update(float x, float y, float rotation){
		this.setX(x - this.width/2);
		this.setY(y - this.height/2);
		this.rotation = rotation;
		this.hitbox.setLocation(this.getX(), this.getY());
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
          //	g.drawString("hp: "+ this.health, this.getX(), this.getY()+ this.height );
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
    	
    	Bullet bullet = new Bullet(bulletSprite, (this.getX()+this.width/2), (this.getY()+this.height/2));
    	bullet.addForce(new Vector2(this.getSpreadRotation()).mult(this.power));
    	Game.gameworld.bullet_list.add(bullet);
    	
    }
	
	public boolean canShoot(){
		boolean inFireRate = (System.currentTimeMillis() - this.timeOfLastShot) >= (60f / this.shotsPerMinute) * 1000f;
		boolean notEmpty = this.bulletsLeft > 0;
		return inFireRate && notEmpty;
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
	
	public void drop(){
		Weapon weapon = new Weapon(Game.weaponSprite,this.getX()-50, this.getY());
    	Game.gameworld.weapon_list.add(weapon);
	}
	
	public int getBulletsLeft(){
		return this.bulletsLeft;
	}
}

