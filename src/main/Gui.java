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
	        
	        g.drawString("MouseDirectionAngle: "+ ((Game.player.getMouseAngle())), posX + 10, posY + 150);
	        
	       
	}
}


