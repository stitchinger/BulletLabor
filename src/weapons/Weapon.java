package weapons;

import static main.Game.bulletSprite;
import static main.Game.bullet_list;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import objectBlueprints.StaticObject;
import util.Settings;




public class Weapon extends StaticObject {

	

	private float rotation;
	private int bulletsLeft;
	private int shotsPerMinute;
	private float power;
	private long timeOfLastShot;
	private boolean equipped;
	
	
	public Weapon(Image sprite, float x, float y, int width, int height) {
		super(sprite, x, y, width, height);
		
		this.rotation = 0;
		this.bulletsLeft = 100;
		this.shotsPerMinute = 600;
	
	}
	
	public void update(float x, float y, float rotation){
		//this.posX = x - this.width/2;
		//this.posY = y - this.height/2;
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
          	g.drawString("hp: "+ this.health, this.getX(), this.getY()+ this.height );
          }
	}
	
	public void trigger(float rotation){
		if(this.canShoot()){
			this.angleShot(rotation);
		}
	}
	
	public boolean canShoot(){
		boolean inFireRate = (System.currentTimeMillis() - this.timeOfLastShot) >= (60f / this.shotsPerMinute) * 1000f;
		boolean notEmpty = this.bulletsLeft > 0;
		return inFireRate && notEmpty;
	}
	
	public void angleShot(float angle){
    	this.timeOfLastShot = System.currentTimeMillis();
    	this.bulletsLeft--;
    	bullet_list.add(new Bullet(bulletSprite, (this.getX()+this.width/2), (this.getY()+this.height/2), 25, 30, angle, false));
    }

	public void clusterShot(float angle){
    	timeOfLastShot = System.currentTimeMillis();
    	int roundCount = 10;
    	for(int i = -(roundCount); i < roundCount; i += 2){
    		
    		this.angleShot(angle + i);
    		
    	}
    }
	
	public void drop(){
		
	}
	
	public int getBulletsLeft(){
		return this.bulletsLeft;
	}
}
