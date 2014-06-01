package World;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import GameState.Camera;
import Main.GamePanel;
import Util.Settings;
import Util.Vector2;

public class Background {
	
	private World gameworld;
	private int width;
	private int height;
	private Camera cam;
	private BackgroundLayer[] bgLayer = new BackgroundLayer[2];
	
	public Background() throws SlickException{
		
		this.width = GamePanel.gameworld.getWidth();
		this.height = GamePanel.gameworld.getHeight();
		this.cam = GamePanel.cam;
		
		this.bgLayer[0] = new BackgroundLayer(Settings.bg1Sprite, new Vector2(0,0), 5);
		this.bgLayer[1] = new BackgroundLayer(Settings.bg2Sprite, new Vector2(0,0), 15);
	
		
	}
	
	public void update(){
		for(int i = 0; i < 2; i++){
			bgLayer[i].move(new Vector2(GamePanel.cam.getX(),GamePanel.cam.getY()));
		}
	}
	
	public void render(Graphics g) {
	   	Color bgc = new Color(50,100,200);
	   	g.setColor(bgc);
	   	g.fillRect(0, 0, this.width, this.height);
	   	for(int i = 0; i < 2; i++){
	   		bgLayer[i].render(g);
		}
	   		
	   	
			
	}
}
