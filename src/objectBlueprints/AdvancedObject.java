package objectBlueprints;

import main.Game;

import org.newdawn.slick.Graphics;

import util.Vector2;
import weapons.Weapon;

public class AdvancedObject extends PhysicsObject {
	
	protected Weapon weapon;
	
	public AdvancedObject(int x, int y) {
		super(x, y);
	}

	public void update(int delta){
		super.update(delta);
	
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
       
    }
	
	 public void dropWeapon(){
	    	if(this.weapon != null){
	    		this.weapon.drop();
	    		Game.weapon_list.add(this.weapon);
	    		this.velocity.set(this.getVelocity());
	    		this.weapon.addForce(this.getSomehowUp().mult(8));
	    		this.weapon = null;
		    }
	  }
	 
	 public Vector2 getSomehowUp(){
		 int range = 30;
		 float randomUp = (0-range/2) + (int)(Math.random() * (((0+range/2) - (0-range/2)) + 1));
		 Vector2 upv = new Vector2(randomUp);
		 return upv;
	 }
	    
	 public void setWeapon(Weapon weapon){
	    	if(this.weapon == null){
	    		this.weapon = weapon;
		    	Game.removeObject(weapon);
		    	this.weapon.setOwner(this);
	    	}
		 	
	    	
	 }
	 
	 public boolean hasWeapon(){
		 return this.weapon != null;
	 }
	    
	 public Weapon getWeapon(){
	    	return this.weapon;
	 } 
	 
	 public int getAmmo(){
		 int leftAmmo;
		 if(this.hasWeapon()){
			 leftAmmo = this.weapon.getBulletsLeft();
		 }else{
			 leftAmmo = 0;
		 }
		 return leftAmmo;
	 }
	    

	
	
	
}
