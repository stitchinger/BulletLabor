package World;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Entity.StaticObject;
import Util.Vector2;

public class BackgroundLayer{
	private Image sprite;
	private int width;
	private int height;
	private float scaleX;
	private float scaleY;
	private int distance;
	private Vector2 position;
	
	
	
	public BackgroundLayer(Image sprite,Vector2 position, int distance){
		this.sprite = sprite;
		this.width = this.sprite.getWidth();
		this.height = this.sprite.getHeight();
		this.position = position;
		this.distance = distance;
		
	}
	
	public void render(Graphics g){
		this.sprite.draw(this.position.x(), this.position.y());
	}
	
	public void move(Vector2 vector){
		this.position = vector.mult(0.6f);
		
	}
}
