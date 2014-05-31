package World;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Background {
	
	private World gameworld;
	
	public Background() throws SlickException{
		gameworld = new World();
	}
	
	public void renderBackground(Graphics g) {
	   	Color bgc = new Color(50,100,200);
	   	g.setColor(bgc);
	       g.fillRect(0, 0, gameworld.getWidth(), gameworld.getHeight());
			
		}
}
