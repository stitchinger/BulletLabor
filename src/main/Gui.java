package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import util.Vector2;

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
	
	    this.drawTest(g);
	    this.drawPointer(g);
	}
	
	public void drawTest(Graphics g){
		float px = this.posX+ this.width - 120;
		float py = this.posY + this.height - 120;
		
		float healthToDegree =  (Game.player.getHealth() * 3.6f);
	
		g.setColor(Color.black);
		g.fillOval(px, py, 100, 100);
		
		g.setColor(new Color(51,179,30));
		g.fillArc(px, py, 100, 100, -90, healthToDegree-90);
		
		g.setColor(new Color(211,88,50));
		g.fillOval(this.posX+ this.width - 99, this.posY + this.height - 100, 60, 60);
		
		g.setColor(Color.white);
		g.drawString("" + Game.player.getAmmo(), px+40 , py+40);
	}
	
	public void drawPointer(Graphics g){
		Vector2 mouseDirection = Game.player.getMousePosition();
		Vector2 pointerPosition1 = new Vector2(Game.player.getCenteredPosition()).add(mouseDirection.mult(100));
		g.setColor(Color.red);
		g.fillOval(pointerPosition1.x()-5, pointerPosition1.y()-5, 10, 10);
	
	}
}


