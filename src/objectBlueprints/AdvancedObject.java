package objectBlueprints;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import util.Vector2;
import weapons.Weapon;

public class AdvancedObject extends LivingObject {
	
	protected Weapon weapon;
	
	public AdvancedObject(Image img, int x, int y) {
		super(img, x, y);
	}

	public void update(){
		super.update();
	
		
	}
	
	public void render(Graphics g){
		super.render(g);
		if(this.weapon != null){
			this.weapon.render(g);
		}
	}
	
	public Vector2 getTargetVector(Vector2 targetPosition){
    	
        float vecX = targetPosition.x() - (this.getX()+this.width/2);
        float vecY= targetPosition.y() - (this.getY()+this.height/2);
        Vector2 vecNorm = new Vector2(vecX, vecY).normalize();
        return vecNorm;
        //return (float) Math.toDegrees(Math.atan2(vecNorm.x(), -vecNorm.y()));
    }
	
	
	
}
