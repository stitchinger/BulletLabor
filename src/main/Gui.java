package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Gui {

	private float posX;
	private float posY;
	private int width;
	private int height;
	private Color textColor = new Color(Color.white);
	
	public Gui(){
		Camera cam = Game.cam;
		this.posX = 0;
		this.posY = 0;
		this.width = cam.getViewportWidth();
		this.height = cam.getViewportHeight();
	}
	
	public void update(){
		this.posX = Game.cam.getX();
		this.posY = Game.cam.getY();
	} 

	public void render(Graphics g){
		g.setColor(this.textColor);
	    g.drawString("Velocity X: " + Game.player.getVelocityX(), posX + 10, posY + 50);
	    g.drawString("Velocity Y: " + Game.player.getVelocityY(), posX + 10, posY + 70);
	    
	    g.drawString("Score: " + Game.killCount           , posX + this.width - 120, posY + 50);
	    g.drawString("Health: "+ (Game.player.getHealth()), posX + this.width - 120, posY + 70);	      
	    
	    this.drawTest(g);
	}
	
	public void drawTest(Graphics g){
		float px = this.posX+ this.width - 120;
		float py = this.posY + this.height - 120;
		
		float healthToDegree =  (Game.player.getHealth() * 3.6f);
		
		g.setColor(Color.black);
		g.fillOval(px, py, 100, 100);
		
		g.setColor(new Color(51,179,30));
		g.fillArc(px, py, 100, 100, 0, healthToDegree);
		
		g.setColor(new Color(211,88,50));
		g.fillOval(this.posX+ this.width - 99, this.posY + this.height - 100, 60, 60);
		
		
	}
}


