package weapons;

import static main.Game.bulletSprite;
import static main.Game.bullet_list;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import objectBlueprints.StaticObject;




public class Weapon extends StaticObject {

	

	private float rotation;
	private int bulletsLeft;
	private int shotsPerMinute;
	private long timeOfLastShot;
	
	
	public Weapon(Image sprite, float x, float y, int width, int height) {
		super(sprite, x, y, width, height);
		
		this.rotation = 0;
		this.bulletsLeft = 100;
		this.shotsPerMinute = 350;
	
	}
	
	public boolean canShoot(){
		return (System.currentTimeMillis() - timeOfLastShot) >= (60 / this.shotsPerMinute) * 1000f;
	}
	
	public void angleShot(float angle){
    	timeOfLastShot = System.currentTimeMillis();
    	this.bulletsLeft--;
    	bullet_list.add(new Bullet(bulletSprite, (this.posX+this.width/2), (this.posY+this.height/2), 25, 30, angle, false));
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
